package com.kvc.joy.web.plugins.message.comet;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Kevice
 * @time 14-3-5 下午11:30
 * @since 1.0.0
 */
public class AsyncContextQueueWriter extends Writer {

    /**
     * AsyncContext 队列
     */
    private Queue<AsyncContext> queue;

    /**
     * 消息队列
     */
    private static final BlockingQueue<String> MESSAGE_QUEUE  = new LinkedBlockingQueue<String>();

    /**
     * 发送消息到异步线程，最终输出到 http response 流
     * @param cbuf
     * @param off
     * @param len
     * @throws IOException
     */
    private void sendMessage(char[] cbuf, int off, int len) throws IOException {
        try {
            MESSAGE_QUEUE.put(new String(cbuf, off, len));
        } catch (Exception ex) {
            IOException t = new IOException();
            t.initCause(ex);
            throw t;
        }
    }

    /**
     * 异步线程，当消息队列中被放入数据，将释放 take 方法的阻塞，将数据发送到 http response 流上
     */
    private Runnable notifierRunnable = new Runnable() {
        public void run() {
            boolean done = false;
            while (!done) {
                String message = null;
                try {
                    message = MESSAGE_QUEUE.take();
                    for (AsyncContext ac : queue) {
                        try {
                            PrintWriter acWriter = ac.getResponse().getWriter();
                            acWriter.println(htmlEscape(message));
                            acWriter.flush();
                        } catch (IOException ex) {
                            System.out.println(ex);
                            queue.remove(ac);
                        }
                    }
                } catch (InterruptedException iex) {
                    done = true;
                    System.out.println(iex);
                }
            }
        }
    };

    /**
     * @param message
     * @return
     */
    private String htmlEscape(String message) {
        return "<script type='text/javascript'>\nwindow.parent.update(\""
                + message.replaceAll("\n", "").replaceAll("\r", "") + "\");</script>\n";
    }

    /**
     * 保持一个默认的 writer，输出至控制台
     * 这个 writer 是同步输出，其它输出到 response 流的 writer 是异步输出
     */
    private static final Writer DEFAULT_WRITER = new OutputStreamWriter(System.out);

    /**
     * 构造 AsyncContextQueueWriter
     * @param queue
     */
    AsyncContextQueueWriter(Queue<AsyncContext> queue) {
        this.queue = queue;
        Thread notifierThread = new Thread(notifierRunnable);
        notifierThread.start();
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        DEFAULT_WRITER.write(cbuf, off, len);
        sendMessage(cbuf, off, len);
    }

    @Override
    public void flush() throws IOException {
        DEFAULT_WRITER.flush();
    }

    @Override
    public void close() throws IOException {
        DEFAULT_WRITER.close();
        for (AsyncContext ac : queue) {
            ac.getResponse().getWriter().close();
        }
    }
}


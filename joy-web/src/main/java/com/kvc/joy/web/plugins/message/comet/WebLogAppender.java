package com.kvc.joy.web.plugins.message.comet;

import org.apache.log4j.WriterAppender;

import javax.servlet.AsyncContext;
import java.io.Writer;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Kevice
 * @time 14-3-5 下午11:28
 * @since 1.0.0
 */
public class WebLogAppender extends WriterAppender {
    /**
     * 异步 Servlet 上下文队列
     */
    public static final Queue<AsyncContext> ASYNC_CONTEXT_QUEUE = new ConcurrentLinkedQueue<AsyncContext>();

    /**
     * AsyncContextQueue Writer
     */
    private Writer writer = new AsyncContextQueueWriter(ASYNC_CONTEXT_QUEUE);

    public WebLogAppender() {
        setWriter(writer);
    }

}


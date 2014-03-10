package com.kvc.joy.plugin.message.comet.core;

import com.kvc.joy.commons.data.json.JsonTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.plugin.message.comet.listener.SocketEvent;
import com.kvc.joy.plugin.message.comet.support.JsonObject;
import com.kvc.joy.plugin.message.comet.listener.SocketListener;
import com.kvc.joy.plugin.message.comet.support.JsonProtocolUtils;
import com.kvc.joy.plugin.message.comet.web.listener.AsyncAdapter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * PushSocket
 * 
 * states:
 * 
 * 是否在等待: isWaiting
 * 
 * 是否有消息: hasMessage
 * 
 * 是否已经关闭: isClosed
 * 
 * @author XiaohangHu
 * */
public class AbstractPushSocket implements Socket, PushSocket {

	private Serializable id;

	private static final JsonObject CLOSE_MESSAGE;
	private static final JsonObject Suspend_Message;

	/** 消息队列 */
	private List<Object> messages;

	/** 是否已经预关闭 */
	private boolean close = false;

	/** 异步超时时间，默认一小时 */
	private long asyncTimeout = 3600000;

	/**
	 * 记录上一次推送的时间。客户端长时间没有轮询，应该发生一个异常。
	 * */
	private Long lastPushTime = getNowTimeInMillis();
	
	/**
	 * 最后一次通信时间（push和writeMessage多算通信）
	 */
	private Long lastCommunicationTime = getNowTimeInMillis();

	private AsyncContext asyncContext;

	private SocketManager socketManager;

	private List<SocketListener> listeners = new LinkedList<SocketListener>();

    protected Log log = LogFactory.getLog(AbstractPushSocket.class);

	static {
		CLOSE_MESSAGE = JsonProtocolUtils.getCloseCommend();
		Suspend_Message = JsonProtocolUtils.getSuspendCommend();
	}
	{
		messages = new LinkedList<Object>();
	}

	public AbstractPushSocket() {
		super();
	}

	public AbstractPushSocket(Serializable id) {
		this.id = id;
	}

	public Serializable getId() {
		return id;
	}

	public void setId(Serializable id) {
		this.id = id;
	}

	public long getTimeout() {
		return asyncTimeout;
	}

	public void setTimeout(long timeout) {
		this.asyncTimeout = timeout;
	}

	private long getNowTimeInMillis() {
		return System.currentTimeMillis();
	}

	/** 添加监听器 */
	public void addListener(SocketListener listener) {
		listeners.add(listener);
	}

	public SocketManager getSocketManager() {
		return socketManager;
	}

	public void setSocketManager(SocketManager socketManager) {
		this.socketManager = socketManager;
	}

	/** 重置最后推送时间 */
	private void resetLastPushTime() {
		this.lastPushTime = getNowTimeInMillis();
		resetLastCommunicationTime("推送消息");
	}
	
	/** 重置最后通信时间 */
	private void resetLastCommunicationTime(String type) {
		System.out.println("resetLastCommunicationTime: ["+getId()+"] "+type);
		this.lastCommunicationTime = getNowTimeInMillis();
	}

	/** 获取最后一次推送的时间 */
	protected Long getLastPushTime() {
		return lastPushTime;
	}
	
	private Long getLastCommunicationTime(){
		return lastCommunicationTime;
	}

	/** 异步等待消息 */
	private void waitMessage(HttpServletRequest request) {
		try {
			AsyncContext ac = request.startAsync();
			ac.setTimeout(this.asyncTimeout);
			ac.addListener(new AsyncAdapter() {
				@Override
				public void onError(AsyncEvent asyncevent) throws IOException {
					//关闭都应该由ConnectionManager来处理，单次的http请求不该影响到此次pushsocket通信
					suspend();
					PushException e = new PushException("Async context error!");
					fireError(e);
				}

				@Override
				public void onTimeout(AsyncEvent asyncevent) throws IOException {
					//close();//异步超时不应该关闭pushsocket，而应该由ConnectionManager(AbstractConnectionManager)去关闭
					//即：一次pushsocket连接，可以对应多次http请求
					suspend();//把close()改suspend()
					PushException e = new AsyncTimeoutException(
							"Async context timeout! wait message more then ["
									+ asyncTimeout + "]ms");
					fireError(e);
				}
			});
			//删除之前与此socket对应的request的asyncContext
			if(this.asyncContext!=null){
				System.out.println("pushsocket ["+this.getId()+"] 完成上次的异步连接asyncContext.complete()");
				this.asyncContext.complete();
			}
			this.asyncContext = ac;
		} catch (Exception e) {
			PushException pe = new PushException(
                    "StartAsync exception! May be the servlet or filter is not async.",
                    e);
			this.fireError(pe);
		} catch (Throwable te) {
			throw new PushException(
					"StartAsync exception! May be the servlet or filter is not async.",
					te);
		}
	}

	private boolean isCloseMessage(Object msg) {
		// 用==提高比较效率
		return CLOSE_MESSAGE == msg;
	}

	private void complete() {
		this.asyncContext.complete();
		this.asyncContext = null;
	}

	/**
	 * 真正关闭连接
	 * */
	private void reallyClose() {
		socketManager.removeSocket(id);
		fireReallyClose();
	}

	/**
	 * 触发真正关闭连接事件
	 * */
	protected void fireReallyClose() {
		SocketEvent event = new SocketEvent(this);
		for (SocketListener listener : listeners) {
			listener.onReallyClose(event);
		}
	}

    /**
     * 触发超时事件
     * @author 唐玮琳
     * @since  1.0.0
     * @time 2014-03-09 18:22
     */
    protected void fireTimeout() {
        SocketEvent event = new SocketEvent(this);
        for (SocketListener listener : listeners) {
            listener.onTimeout(event);
        }
    }

	/**
	 * 触发异常处理
	 * */
	protected void fireError(PushException e) {
        for (SocketListener listener : listeners) {
            listener.onError(e);
        }
	}

	/** ~~~~~~~~~~~~~~~~~~~~~~~推送消息~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/**
	 * 将消息用指定的writer发送
	 * */
	private void pushMessage(List<Object> messages, PrintWriter writer) {
		List<Object> array = new ArrayList<Object>();
		boolean isClose = false;
		for (Object message : messages) {
			if (isCloseMessage(message)) {
				isClose = true;
			}
			array.add(message);
		}
		writer.write(JsonTool.toJson(array));
		writer.flush();
        // 如果发送的消息中有关闭消息，则真正关闭连接
		if (isClose) {
			reallyClose();
		}
		// 重置最后推送时间
		resetLastPushTime();
	}

	private void pushMessage(List<Object> messages, ServletResponse response) {
		try {
			pushMessage(messages, response.getWriter());
		} catch (IOException e) {
			PushException pe = new PushException("IOException push message", e);
			this.fireError(pe);
		}
	}

	private void pushMessage(Object message, ServletResponse response)
			throws IOException {
		List<Object> msgs = new LinkedList<Object>();
		msgs.add(message);
		pushMessage(msgs, response);
	}

	public List<String> getCachedData() {
		List<String> userMessages = new LinkedList<String>();
		for (Object message : messages) {
			if (message instanceof String && !this.isCloseMessage(message)) {
				userMessages.add((String) message);
			}
		}
		// 清空缓存
		this.messages.clear();
		return userMessages;
	}

	/** ~~~~~~~~~~~~~~~~~~~~~~~状态获取~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
	/**
	 * 是在等待
	 * */
	private boolean isWaiting() {
		return null != this.asyncContext;
	}

	/**
	 * 是否有消息要发送
	 * */
	private boolean hasMessage() {
		return !this.messages.isEmpty();
	}

	/**
	 * 是否已经关闭
	 * */
	public boolean isClosed() {
		return close;
	}

	/**
	 * 接待取消息请求
	 * 
	 * @throws java.io.IOException
	 * */
	public void receiveRequest(HttpServletRequest request,
			HttpServletResponse response) {
		// if (isClosed()) {
		// PushException e = new PushException("Use a closed pushSocked!");
		// this.fireError(e);
		// }
		this.resetLastCommunicationTime("等待接收消息");
		if (this.hasMessage()) {
			// 如果有消息则直接将消息推送
			pushMessage(this.messages, response);
			// 发送后清空缓冲区
			this.messages.clear();
		} else {
			// 如果没有消息则等待消息
			this.waitMessage(request);
		}
	}

	public void sendObjectMessage(Object message) {
		if (isClosed()) {
			PushException e = new PushException("Use a closed pushSocked!");
			this.fireError(e);
			return;
		}
		// 如果不是等待状态，将消息缓存
		if (!isWaiting()) {
			this.messages.add(message);
			return;
		}
		// 如果是等待状态，发送消息
		ServletResponse response = this.asyncContext.getResponse();
		try {
			pushMessage(message, response);
		} catch (IOException e) {
			PushException pe = new PushException("IOException push message", e);
			this.fireError(pe);
			return;
		}
		complete();
	}

	@Override
	public void send(String message) {
		sendObjectMessage(message);
	}

	/**
	 * 关闭连接
	 * */
	public void close() {
		this.sendObjectMessage(CLOSE_MESSAGE);
		this.close = true;
	}
	
	/**
	 * 中断异步连接
	 */
	public void suspend(){
		this.sendObjectMessage(Suspend_Message);
	}

	/**
	 * 处理推送超时，超时推送代表客户端长时间没有发送连接请求
	 * 
	 * 超时会发生一个连接异常。
	 * 
	 * @param pushTimeout
	 *            超时时间
	 * @return 是否超时
	 */
	public boolean checkPushTimeOut(long pushTimeout) {
		if (this.isWaiting()) {//如果pushsocket对应的asyncContext已经为空后才会回收，此时pushsocket的超时时间为1分钟。（即：等到异步连接中断后，才考虑关闭comet连接）
			return false;
		}
		Long lastTime = this.getLastCommunicationTime();//getLastPushTime()改为getLastCommunicationTime()
		long now = this.getNowTimeInMillis();
		long sent = now - lastTime;
		System.out.println("checkPushTimeOut ["+this.getId()+"]: "+sent+"ms");
		if (sent > pushTimeout) {
            fireTimeout();
            log.warn("Push timeout! The client has no connection more than[" + pushTimeout + "]ms");
			this.socketManager.removeSocket(id);
//			fireError(e);
			return true;
		}
		return false;
	}

}

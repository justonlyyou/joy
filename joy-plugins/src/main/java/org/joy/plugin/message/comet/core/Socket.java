package org.joy.plugin.message.comet.core;

import java.util.List;

import org.joy.plugin.message.comet.listener.SocketListener;

/**
 * PushSocket：单向推送消息
 *
 * 该接口提供给handle
 *
 * @author XiaohangHu
 * */
public interface Socket {

	/**
	 * 发送消息
	 *
	 */
	void send(String message);

	/**
	 * 关闭连接
	 *
	 */
	void close();

//	/**
//	 * 设置错误处理器
//	 *
//	 */
//	void setErrorHandler(ErrorHandler errorHandler);

	/**
	 * 添加监听器
	 *
	 */
	void addListener(SocketListener listener);

	/**
	 * 获得缓存的数据
	 *
	 * 当发生异常时，可以尝试获取没有发送成功的数据
	 *
	 */
	List<String> getCachedData();

    /**
     * 是否已经关闭
     * @author Kevice
     * @since  1.0.0
     * @time 2014-03-09 16:30
     */
    boolean isClosed();

}

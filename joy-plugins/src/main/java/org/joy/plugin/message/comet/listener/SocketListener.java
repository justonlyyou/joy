package org.joy.plugin.message.comet.listener;

import org.joy.plugin.message.comet.core.PushException;

import java.util.EventListener;

/**
 * @author XiaohangHu
 * */
public interface SocketListener extends EventListener {

	/**
	 * 当真正关闭连接时触发
	 * */
	void onReallyClose(SocketEvent event);

    /**
     * 当连接超时时触发
     *
     * @author Kevice
     * @since  1.0.0
     * @time 2014-03-09 16:35
     */
    void onTimeout(SocketEvent event);

    /**
     * 当发生错误时触发
     *
     * @author Kevice
     * @since  1.0.0
     * @time 2014-03-09 18:35
     */
    void onError(PushException event);

}

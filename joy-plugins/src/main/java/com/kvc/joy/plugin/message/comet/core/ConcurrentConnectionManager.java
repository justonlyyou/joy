package com.kvc.joy.plugin.message.comet.core;


/**
 * 该类用于处理各种通信请求，并管理socket
 * 
 * @author XiaohangHu
 * */
public class ConcurrentConnectionManager extends AbstractConnectionManager {

	public ConcurrentConnectionManager(long pushTimeout) {
		super(pushTimeout);
	}

}

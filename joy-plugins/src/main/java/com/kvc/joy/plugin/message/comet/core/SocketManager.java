package com.kvc.joy.plugin.message.comet.core;

import java.io.Serializable;

public interface SocketManager {

	/**
	 * 根据Socket id删除Socket
	 * */
	PushSocket removeSocket(Serializable id);

}

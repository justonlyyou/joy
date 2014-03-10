package com.kvc.joy.plugin.message.comet.listener;

import com.kvc.joy.plugin.message.comet.core.PushSocket;

/**
 *
 * @author XiaohangHu
 * */
public class SocketEvent {

	private PushSocket socket;

	public SocketEvent(PushSocket socket) {
		this.socket = socket;
	}

	public PushSocket getPushSocket() {
		return this.socket;
	}
}

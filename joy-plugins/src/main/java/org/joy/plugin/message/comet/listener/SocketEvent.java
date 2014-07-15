package org.joy.plugin.message.comet.listener;

import org.joy.plugin.message.comet.core.PushSocket;

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

package com.kvc.joy.plugin.message.comet.support;

import java.io.Serializable;

import com.kvc.joy.plugin.message.comet.core.Protocol;

/**
 * 
 * @author XiaohangHu
 */
public class JsonProtocolUtils {

	public static JsonObject getCloseCommend() {
		JsonObject commend = new JsonObject();
		commend.put(Protocol.SYNCHRONIZE_KEY, Protocol.DISCONNECT_VALUE);
		return commend;
	}
	
	public static JsonObject getSuspendCommend() {
		JsonObject commend = new JsonObject();
		commend.put(Protocol.SYNCHRONIZE_KEY, Protocol.Suspend_Value);
		return commend;
	}

	public static String getConnectionCommend(Serializable socketId) {
		JsonObject commend = new JsonObject();
		commend.put(Protocol.CONNECTIONID_KEY, socketId);
		return commend.toString();
	}

	// suppress default constructor for noninstantiability
	private JsonProtocolUtils() {
		throw new AssertionError();
	}

}

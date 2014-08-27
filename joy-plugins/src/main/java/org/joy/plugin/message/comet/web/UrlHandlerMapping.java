package org.joy.plugin.message.comet.web;

import org.joy.plugin.message.comet.core.SocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiaohangHu
 * */
public class UrlHandlerMapping {

	private final Map<String, SocketHandler> urlMap = new HashMap<String, SocketHandler>();

	public SocketHandler getHandler(String uri) {
		return urlMap.get(uri);
	}

	public void putHandler(String uri, SocketHandler controller) {
		this.urlMap.put(uri, controller);
	}

}

package org.joy.plugin.message.comet.config;

/**
 * @author XiaohangHu
 * */
public class CometMetadata {

	private String request;
	private String handler;

    public CometMetadata() {
    }

    public CometMetadata(String request, String handler) {
        this.request = request;
        this.handler = handler;
    }

    public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

}

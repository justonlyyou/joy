package org.joy.plugin.message.comet.web.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * @author XiaohangHu
 * */
public abstract class AsyncAdapter implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent asyncevent) throws IOException {
	}

	@Override
	public void onTimeout(AsyncEvent asyncevent) throws IOException {
	}

	@Override
	public void onError(AsyncEvent asyncevent) throws IOException {
	}

	@Override
	public void onStartAsync(AsyncEvent asyncevent) throws IOException {
	}

}

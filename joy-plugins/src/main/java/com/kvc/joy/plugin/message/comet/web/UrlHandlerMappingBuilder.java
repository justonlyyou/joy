package com.kvc.joy.plugin.message.comet.web;

import java.util.Collection;

import com.kvc.joy.plugin.message.comet.core.ObjectFactory;
import com.kvc.joy.plugin.message.comet.core.SocketHandler;
import com.kvc.joy.plugin.message.comet.config.CometConfigMetadata;
import com.kvc.joy.plugin.message.comet.config.CometMetadata;

/**
 * @author XiaohangHu
 * */
public class UrlHandlerMappingBuilder {

	private ObjectFactory objectFactory;

	public UrlHandlerMappingBuilder(ObjectFactory objectFactory) {
		if (null == objectFactory) {
			throw new IllegalArgumentException(
					"objectFactory must not be null!");
		}
		this.objectFactory = objectFactory;

	}

	public UrlHandlerMapping buildHandlerMapping(CometConfigMetadata cometConfig) {
		UrlHandlerMapping handlerMapping = new UrlHandlerMapping();
		Collection<CometMetadata> cometMetadatas = cometConfig.getCometMetadatas();
		addMapping(cometMetadatas, handlerMapping);
		return handlerMapping;
	}

	public void addMapping(Collection<CometMetadata> cometMetadatas,
			UrlHandlerMapping handlerMapping) {
		for (CometMetadata cometMetadata : cometMetadatas) {
			addMapping(cometMetadata, handlerMapping);
		}
	}

	public void addMapping(CometMetadata cometMetadata,
			UrlHandlerMapping handlerMapping) {
		String uri = cometMetadata.getRequest();
		String name = cometMetadata.getHandler();
		Object obj = objectFactory.getObject(name);
		if (obj instanceof SocketHandler) {
			handlerMapping.putHandler(uri, (SocketHandler) obj);
		} else {
			throw new IllegalStateException("Handler must implements ["
					+ SocketHandler.class.getName() + "]");
		}
	}
}

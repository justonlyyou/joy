package org.joy.plugin.message.comet.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author XiaohangHu
 * */
public class CometConfigMetadata {

	public static final String OBJECT_FACTORY_PROPERTY_NAME = "objectFactory";

	private Integer timeout;

	private Properties properties = new Properties();

    private Properties handlers = new Properties();

	public void addProperty(String name, String value) {
		this.properties.put(name, value);
	}

	public String getProperty(String name) {
		return this.properties.getProperty(name);
	}

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

    public Properties getHandlers() {
        return handlers;
    }

    public void setHandlers(Properties handlers) {
        this.handlers = handlers;
    }

    public Set<CometMetadata> getCometMetadatas() {
        Set<CometMetadata> results = new HashSet<CometMetadata>();
        Set<String> keys = handlers.stringPropertyNames();
        for (String key : keys) {
            results.add(new CometMetadata(key, handlers.getProperty(key)));
        }
        return results;
    }

}

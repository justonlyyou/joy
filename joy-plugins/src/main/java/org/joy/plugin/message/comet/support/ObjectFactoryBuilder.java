package org.joy.plugin.message.comet.support;

import org.joy.commons.lang.ClassTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.plugin.message.comet.config.CometConfigMetadata;
import org.joy.plugin.message.comet.core.ObjectFactory;

import javax.servlet.ServletContext;

/**
 * @author XiaohangHu
 * */
public class ObjectFactoryBuilder {

    private ObjectFactoryBuilder() {
    }

	public static ObjectFactory creatObjectFactory(
			CometConfigMetadata cometConfig, ServletContext servletContext) {
		String className = cometConfig
				.getProperty(CometConfigMetadata.OBJECT_FACTORY_PROPERTY_NAME);

		if (StringTool.isBlank(className)) {
			return creatDefaultObjectFactory(servletContext);
		}
		return creatObjectFactory(className, servletContext);
	}

	private static ObjectFactory creatObjectFactory(String className,
			ServletContext servletContext) {

		Object object = newInstance(className);

		if (object instanceof ObjectFactory) {
			ObjectFactory factory = (ObjectFactory) object;
			factory.init(servletContext);
			return factory;
		} else {
			throw new IllegalStateException("ObjectFactory must implements ["
					+ ObjectFactory.class.getName() + "]");
		}
	}

	private static Object newInstance(String className) {
		return newInstance(getClass(className));
	}

	private static Class<?> getClass(String className) {
		return ClassTool.getClass(className);
	}

	private static Object newInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalStateException("Is it an abstract class["
					+ clazz.getName() + "]?", e);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException(
					"the constructor can't access by class[" + clazz.getName()
							+ "]", e);
		}
	}

	protected static ObjectFactory creatDefaultObjectFactory(
			ServletContext servletContext) {
		ObjectFactory objectFactory = new ClassNameObjectFactory();
		objectFactory.init(servletContext);
		return objectFactory;
	}
}

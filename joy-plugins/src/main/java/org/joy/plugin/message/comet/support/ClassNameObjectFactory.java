package org.joy.plugin.message.comet.support;

import org.joy.commons.lang.ClassTool;
import org.joy.plugin.message.comet.core.ObjectFactory;
import org.joy.plugin.message.comet.web.ServletContextSetter;

import javax.servlet.ServletContext;

/**
 *
 * @author XiaohangHu
 * */
public class ClassNameObjectFactory implements ObjectFactory {

	protected ServletContext servletContext;

	@Override
	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public Object getObject(String objectName) {
		Object obj = newInstance(objectName);
		if (obj instanceof ServletContextSetter) {
			ServletContextSetter contextAware = (ServletContextSetter) obj;
			contextAware.setServletContext(servletContext);
		}
		return obj;
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

}

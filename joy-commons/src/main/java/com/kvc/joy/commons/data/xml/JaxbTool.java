package com.kvc.joy.commons.data.xml;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.ClassTool;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 * jaxb操作的工具类
 * </p>
 * 
 * <p>
 * JAXB = Java Architecture for XML Binding
 * </p>
 * 
 * <p>
 * 使用Jaxb2.0实现XML和Java的相互转化, OXM(Object XML Mapping), JAXB2在底层是用StAX(JSR
 * 173)来处理XML文档的
 * </p>
 * 
 * @author 唐玮琳
 * @since 1.0.0
 * @version 2013-01-15
 */
public class JaxbTool {

	private static final ConcurrentMap<Class<?>, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class<?>, JAXBContext>();

	private JaxbTool() {
	}

	/**
	 * 序列化，将bean转为xml
	 * 
	 * @param root 待序列化的根对象
	 * @return 序列化后的xml字符串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:22:02
	 */
	public static String toXml(Object root) {
		Class<?> clazz = ClassTool.getUserClass(root);
		return toXml(root, clazz, null);
	}

	/**
	 * 序列化，按指定编码将bean转为xml
	 * 
	 * @param obj 待序列化的根对象
	 * @param encoding 编码名称
	 * @return 序列化后的xml字符串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:22:02
	 */
	public static String toXml(Object root, String encoding) {
		Class<?> clazz = ClassTool.getUserClass(root);
		return toXml(root, clazz, encoding);
	}

	/**
	 * 序列化，按指定编码将bean转为xml
	 * 
	 * @param obj 待序列化的根对象
	 * @param clazz 类
	 * @param encoding 编码名称
	 * @return 序列化后的xml字符串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:22:02
	 */
	public static String toXml(Object root, Class<?> clazz, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 序列化，特别支持Root Element是Collection的情形.
	 * 
	 * @param obj 待序列化的根容器对象
	 * @param rootName 根的名称
	 * @param clazz 类
	 * @return 序列化后的xml字符串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:22:02
	 */
	public static String toXml(Collection<?> root, String rootName, Class<?> clazz) {
		return toXml(root, rootName, clazz, null);
	}

	/**
	 * 序列化，按指定编码将bean转为xml, 特别支持Root Element是Collection的情形.
	 * 
	 * @param obj 待序列化的根容器对象
	 * @param rootName 根的名称
	 * @param clazz 类
	 * @param encoding 编码名称
	 * @return 序列化后的xml字符串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:22:02
	 */
	public static String toXml(Collection<?> root, String rootName, Class<?> clazz, String encoding) {
		try {
			CollectionWrapper wrapper = new CollectionWrapper();
			wrapper.collection = root;

			JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(new QName(rootName),
					CollectionWrapper.class, wrapper);

			StringWriter writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(wrapperElement, writer);

			return writer.toString();
		} catch (JAXBException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 反序列化，将xml转为指定类的实例
	 * </p>
	 * 
	 * @param xml xml字符串
	 * @param clazz 实例的类型
	 * @return 指定类的实例
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:29:27
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 创建Marshaller并设定encoding. 线程不安全，需要每次创建或pooling。
	 * </p>
	 * 
	 * @param clazz 类
	 * @param encoding 可为null
	 * @return Marshaller
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:31:49
	 */
	public static Marshaller createMarshaller(Class<?> clazz, String encoding) {
		try {
			JAXBContext jaxbContext = getJaxbContext(clazz);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}

			return marshaller;
		} catch (JAXBException e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 创建UnMarshaller. 线程不安全，需要每次创建或pooling。
	 * </p>
	 * 
	 * @param clazz 类
	 * @return UnMarshaller
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-15 下午9:33:57
	 */
	public static Unmarshaller createUnmarshaller(Class<?> clazz) {
		try {
			JAXBContext jaxbContext = getJaxbContext(clazz);
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new SystemException(e);
		}
	}

	protected static JAXBContext getJaxbContext(Class<?> clazz) {
		// Assert.notNull(clazz, "'clazz' must not be null");
		JAXBContext jaxbContext = jaxbContexts.get(clazz);
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(clazz, CollectionWrapper.class);
				jaxbContexts.putIfAbsent(clazz, jaxbContext);
			} catch (JAXBException ex) {
				throw new SystemException(ex, "不能为类 [{0}]创建JAXBContext: {1}", clazz, ex.getMessage());
			}
		}
		return jaxbContext;
	}

	/**
	 * 封装Root Element 是 Collection的情况.
	 */
	protected static class CollectionWrapper {

		@XmlAnyElement
		protected Collection<?> collection;
	}
}

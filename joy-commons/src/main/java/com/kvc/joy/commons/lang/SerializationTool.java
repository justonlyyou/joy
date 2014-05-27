package com.kvc.joy.commons.lang;

import org.apache.commons.lang3.SerializationException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * 序列化工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午8:54:14
 */
public class SerializationTool {

	private SerializationTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.SerializationUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	// Clone
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 对指定的对象深度克隆
	 * </p>
	 * 
	 * <p>
	 * 该方法比直接在对象图中的所有对象重写克隆方法慢很多倍. 但是, 对于复杂的对象图, 或那些不支持深底克隆的对象, 这提供了另一种实现. 当然, 所有对象都必须实现 {@code Serializable}接口.
	 * </p>
	 * 
	 * @param <T> 待克隆的对象的类型
	 * @param object 要克隆的{@code Serializable}对象
	 * @return 克隆后的对象
	 * @throws SerializationException (运行时) 如果序列化失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:15:30
	 */
	public static <T extends Serializable> T clone(T object) {
		return org.apache.commons.lang3.SerializationUtils.clone(object);
	}

	// Serialize
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 序列化一个对象到指定的输出流
	 * </p>
	 * 
	 * <p>
	 * 输出流将在对象写完时关闭.这就避免了在应用程序代码中需要的finally子句，或异常处理。
	 * </p>
	 * 
	 * <p>
	 * 传入的流没有在方法内部缓存. 如果需要的话这是你的应用程序的责任.
	 * </p>
	 * 
	 * @param obj 要序列化为字节的对象, 可以为 null
	 * @param outputStream 要写入的流, 不能为 null
	 * @throws IllegalArgumentException 如果 {@code outputStream} 为 {@code null}
	 * @throws SerializationException (运行时) 如果序列化失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:24:52
	 */
	public static void serialize(Serializable obj, OutputStream outputStream) {
		org.apache.commons.lang3.SerializationUtils.serialize(obj, outputStream);
	}

	/**
	 * <p>
	 * 将一个对象序列化为字节数组
	 * </p>
	 * 
	 * @param obj 要序列化为字节的对象, 可以为 null
	 * @return 字节数组
	 * @throws SerializationException (运行时) 如果序列化失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:26:04
	 */
	public static byte[] serialize(Serializable obj) {
		return org.apache.commons.lang3.SerializationUtils.serialize(obj);
	}

	// Deserialize
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从输入流中反序列化一个对象
	 * </p>
	 * 
	 * <p>
	 * 输入流将在对象读完时关闭.这就避免了在应用程序代码中需要的finally子句，或异常处理。
	 * </p>
	 * 
	 * <p>
	 * 传入的流没有在方法内部缓存. 如果需要的话这是你的应用程序的责任.
	 * </p>
	 * 
	 * @param inputStream 输入流, 不能为 null
	 * @return 反序列化后的对象
	 * @throws IllegalArgumentException 如果 {@code inputStream} 为 {@code null}
	 * @throws SerializationException (运行时) 如果反序列化失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:28:42
	 */
	public static Object deserialize(InputStream inputStream) {
		return org.apache.commons.lang3.SerializationUtils.deserialize(inputStream);
	}

	/**
	 * <p>
	 * 从字节数组中反序列化一个对象
	 * </p>
	 * 
	 * @param objectData 字节数组, 不能为 null
	 * @return 反序列化后的对象
	 * @throws IllegalArgumentException 如果 {@code objectData} 为 {@code null}
	 * @throws SerializationException (运行时) 如果反序列化失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午9:30:19
	 */
	public static Object deserialize(byte[] objectData) {
		return org.apache.commons.lang3.SerializationUtils.deserialize(objectData);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.SerializationUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

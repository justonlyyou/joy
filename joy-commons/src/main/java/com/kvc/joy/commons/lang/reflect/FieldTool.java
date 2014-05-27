package com.kvc.joy.commons.lang.reflect;

import com.kvc.joy.commons.exception.SystemException;

import java.lang.reflect.Field;

/**
 * <p>
 * 字段工具类
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-2 下午10:56:14
 */
public class FieldTool {
	
	private FieldTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.reflect.FieldUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 根据字段名, 取得指定类(包括其父类)对应的可访问字段.
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:06:01
	 */
	public static Field getField(Class<?> cls, String fieldName) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.getField(cls, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 根据字段名, 取得指定类(包括其父类)对应的可访问字段, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 *  					IllegalArgumentException 如果类或字段名为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:18:15
	 */
	public static Field getField(final Class<?> cls, String fieldName, boolean forceAccess) {
		try {	
			return org.apache.commons.lang3.reflect.FieldUtils.getField(cls, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 根据字段名, 取得指定类本身(不包括其父类)对应的可访问字段(不突破范围限制).
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:21:18
	 */
	public static Field getDeclaredField(Class<?> cls, String fieldName) {
		try {	
			return org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField(cls, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}	
	}

	/**
	 * <p>
	 * 根据字段名, 取得指定类本身(不包括其父类)对应的可访问字段, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 			IllegalArgumentException 如果类或字段名为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:22:41
	 */
	public static Field getDeclaredField(Class<?> cls, String fieldName, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.getDeclaredField(cls, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得一个可访问的静态字段的值
	 * </p>
	 * 
	 * @param field 要读取的字段
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						 IllegalArgumentException 如果字段为null或不是静态的或该字段不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:25:22
	 */
	public static Object readStaticField(Field field) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readStaticField(field);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得一个可访问的静态字段的值, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param field 要读取的字段
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null或不是静态的或该字段不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:27:39
	 */
	public static Object readStaticField(Field field, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readStaticField(field, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得指定类(包括父类)指定字段名的public的静态字段的值  
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 字段找不到或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:31:21
	 */
	public static Object readStaticField(Class<?> cls, String fieldName) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readStaticField(cls, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得指定类(包括父类)指定字段名的静态字段的值, 可以指定是否突破范围限制 
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 字段找不到或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:33:38
	 */
	public static Object readStaticField(Class<?> cls, String fieldName, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readStaticField(cls, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得指定类(不包括父类)指定字段名的public的静态字段的值
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 字段找不到或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:35:42
	 */
	public static Object readDeclaredStaticField(Class<?> cls, String fieldName) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readDeclaredStaticField(cls, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 取得指定类(不包括父类)指定字段名的静态字段的值, 可以指定是否突破范围限制 
	 * </p>
	 * 
	 * @param cls 要反射的类, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 字段找不到或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 上午12:36:40
	 */
	public static Object readDeclaredStaticField(Class<?> cls, String fieldName, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readDeclaredStaticField(cls, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定字段的值
	 * </p>
	 * 
	 * @param field 字段对象
	 * @param target 目标对象, 如果为null表示读取的是静态字段
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:05:11
	 */
	public static Object readField(Field field, Object target) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readField(field, target);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定字段的值, 可以指定是否突破范围限制
	 * </p>
	 *  
	 * @param field 字段对象
	 * @param target 目标对象, 如果为null表示读取的是静态字段
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null或不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:15:27
	 */
	public static Object readField(Field field, Object target, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readField(field, target, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定public字段的值, 对指定对象的类的父类字段有效
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 或指定的字段不是public的
	 */
	public static Object readField(Object target, String fieldName) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readField(target, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定public字段的值, 对指定对象的类的父类字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						llegalArgumentException 如果类或字段名为null, 或指定字段不可访问
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:19:07
	 */
	public static Object readField(Object target, String fieldName, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readField(target, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定public字段的值, 仅对指定对象的类本身字段有效
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @return 字段值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果类或字段名为null, 或指定的字段不是public的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:18:59
	 */
	public static Object readDeclaredField(Object target, String fieldName) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readDeclaredField(target, fieldName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取指定对象的指定public字段的值, 仅对指定对象的类本身字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @return 字段对象
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果 <code>target</code> 或 <code>fieldName</code> 为 null, 或指定的字段不是public的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:21:13
	 */
	public static Object readDeclaredField(Object target, String fieldName, boolean forceAccess) {
		try {
			return org.apache.commons.lang3.reflect.FieldUtils.readDeclaredField(target, fieldName, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段
	 * </p>
	 * 
	 * @param field 要写入的字段
	 * @param 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果指定的字段为null或不是静态的, 或不是public的, 或为final
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:22:32
	 */
	public static void writeStaticField(Field field, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeStaticField(field, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param field 要写入的字段
	 * @param 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制<code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null或不是静态的或该字段不可访问或为final
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:24:17
	 */
	public static void writeStaticField(Field field, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeStaticField(field, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段, 父类字段有效
	 * </p>
	 * 
	 * @param cls 要查找的字段的类
	 * @param fieldName 要写入的字段的名称
	 * @param 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段找不到, 或不是静态的, 或不是public的, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:30:21
	 */
	public static void writeStaticField(Class<?> cls, String fieldName, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeStaticField(cls, fieldName, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段, 父类字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param cls 要查找的字段的类
	 * @param fieldName 要写入的字段的名称
	 * @param value 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段找不到, 或不是静态的, 或不可访问, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:31:31
	 */
	public static void writeStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeStaticField(cls, fieldName, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段, 仅指定类本身字段有效
	 * </p>
	 * 
	 * @param cls 要查找的字段的类
	 * @param fieldName 要写入的字段的名称
	 * @param value 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段找不到, 或不是静态的, 或不是public的, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:33:03
	 */
	public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredStaticField(cls, fieldName, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 写入一个public的静态字段, 仅指定类本身字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param cls 要查找的字段的类
	 * @param fieldName 要写入的字段的名称
	 * @param value 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 *						IllegalArgumentException 如果字段找不到, 或不是静态的, 或不可访问, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:34:07
	 */
	public static void writeDeclaredStaticField(Class<?> cls, String fieldName, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredStaticField(cls, fieldName, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定字段
	 * </p>
	 * 
	 * @param field 要写入的字段
	 * @param target 目标对象, 如果为null表示读取的是静态字段
	 * @param value 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null, 或不可访问, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:34:59
	 */
	public static void writeField(Field field, Object target, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeField(field, target, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定字段, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param field 要写入的字段
	 * @param target 目标对象, 如果为null表示读取的是静态字段
	 * @param value 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 如果字段为null或不可访问, 或为final的
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:07:12
	 */
	public static void writeField(Field field, Object target, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeField(field, target, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定字段, 父类字段有效
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param value 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 			IllegalArgumentException 如果 <code>target</code> 或 <code>fieldName</code> 为 null, 或字段不可访问 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:10:44
	 */
	public static void writeField(Object target, String fieldName, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeField(target, fieldName, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定字段, 父类字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param value 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 *		IllegalArgumentException 如果 <code>target</code> 或 <code>fieldName</code> 为 null, 或字段不可访问 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:13:14
	 */
	public static void writeField(Object target, String fieldName, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeField(target, fieldName, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定public字段, 仅指定对象的类本身的字段有效
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param value 要设置的值
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 		IllegalArgumentException 如果 <code>target</code> 或 <code>fieldName</code> 为 null, 或字段不可访问 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:14:51
	 */
	public static void writeDeclaredField(Object target, String fieldName, Object value) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(target, fieldName, value);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将指定值写入目标对象的指定public字段, 仅指定对象的类本身的字段有效, 可以指定是否突破范围限制
	 * </p>
	 * 
	 * @param target 要反射的目标对象, 不能为null
	 * @param fieldName 要获取的字段的名称
	 * @param value 要设置的值
	 * @param forceAccess 是否使用<code>setAccessible</code>方法突破范围限制, <code>False</code>将仅匹配public的字段
	 * @throws SystemException 该异常是对下面异常的包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 		IllegalArgumentException 如果 <code>target</code> 或 <code>fieldName</code> 为 null, 或字段不可访问 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午2:14:59
	 */
	public static void writeDeclaredField(Object target, String fieldName, Object value, boolean forceAccess) {
		try {
			org.apache.commons.lang3.reflect.FieldUtils.writeDeclaredField(target, fieldName, value, forceAccess);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.reflect.FieldUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

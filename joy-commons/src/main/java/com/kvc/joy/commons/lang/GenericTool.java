package com.kvc.joy.commons.lang;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型工具类
 * 
 * @author 唐玮琳
 * @time 2012-6-26 下午9:13:34
 */
public class GenericTool {

	private static Logger logger = LoggerFactory.getLogger(GenericTool.class);

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 整理SpringSide的Generics类
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 获取指定类的父类的泛型参数的实际类型. 如: NameAndAge extends Pair<String, Integer>
	 * </p>
	 * 
	 * @param clazz 需要获取泛型参数实际类型的类, 该类必须继承泛型父类
	 * @param index 泛型参数所在索引, 从0开始.
	 * @return 泛型参数的实际类型. 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回{@code Object.class}, 如果索引越界返回null
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午9:47:11
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();// 得到泛型父类
		// 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
		if (genType instanceof ParameterizedType == false) {
			return Object.class;
		}
		// 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class,
		// 如NameAndAge extends Pair<String, Integer>就返回String和Integer类型
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index < 0 || index >= params.length) {
			logger.error("输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
			return null;
		}
		if (params[index] instanceof Class == false) {
			return Object.class;
		}
		return (Class<?>) params[index];
	}

	/**
	 * <p>
	 * 获取指定类的父类的第0个泛型参数的实际类型. 如: NameAndAge extends Pair<String, Integer>将返回Strin.class
	 * </p>
	 * 
	 * @param clazz 需要获取泛型参数实际类型的类, 该类必须继承泛型父类
	 * @return 泛型参数的实际类型. 如果没有实现ParameterizedType接口，即不支持泛型，所以直接返回{@code Object.class}
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午9:58:12
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * <p>
	 * 获取方法返回值泛型参数的实际类型. 如: public Map<String, Integer> getNameAndAge()
	 * </p>
	 * 
	 * @param method 方法
	 * @param index 泛型参数所在索引, 从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回{@code Object.class}, 如果索引越界返回null
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:07:43
	 */
	public static Class<?> getMethodGenericReturnType(Method method, int index) {
		Type returnType = method.getGenericReturnType();
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			if (index < 0 || index >= typeArguments.length) {
				logger.error("输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
				return null;
			}
			return (Class<?>) typeArguments[index];
		}
		return Object.class;
	}

	/**
	 * <p>
	 * 获取方法返回值的第0个泛型参数的实际类型. 如: public Map<String, Integer> getNameAndAge()将返回String.class
	 * </p>
	 * 
	 * @param method 方法
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回{@code Object.class}
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:07:43
	 */
	public static Class<?> getMethodGenericReturnType(Method method) {
		return getMethodGenericReturnType(method, 0);
	}

	/**
	 * <p>
	 * 获取方法输入参数第index个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, Integer> maps, List<String> names){}
	 * </p>
	 * 
	 * @param method 方法
	 * @param index 第几个输入参数
	 * @return 输入参数的泛型参数的实际类型列表, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回空列表, 如果索引越界返回null
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:17:52
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method, int index) {
		List<Class<?>> results = new ArrayList<Class<?>>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();
		if (index < 0 || index >= genericParameterTypes.length) {
			logger.error("输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
			return null;
		}
		Type genericParameterType = genericParameterTypes[index];
		if (genericParameterType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				Class<?> parameterArgClass = (Class<?>) parameterArgType;
				results.add(parameterArgClass);
			}
			return results;
		}
		return results;
	}

	/**
	 * <p>
	 * 获取方法输入参数第0个输入参数的所有泛型参数的实际类型. 如: public void add(Map<String, Integer> maps, List<String> names){}
	 * </p>
	 * 
	 * @param method 方法
	 * @return 输入参数的泛型参数的实际类型列表, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回空列表
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:17:52
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method) {
		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * <p>
	 * 获取字段泛型参数的实际类型. 如: public Map<String, Integer> nameAndAge;
	 * </p>
	 * 
	 * @param field 字段
	 * @param index 泛型参数所在索引, 从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回{@code Object.class}, 如果索引越界返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:20:51
	 */
	public static Class<?> getFieldGenericType(Field field, int index) {
		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index < 0 || index >= fieldArgTypes.length) {
				logger.error("输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
				return null;
			}
			return (Class<?>) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * <p>
	 * 获取字段第0个泛型参数的实际类型. 如: public Map<String, Integer> nameAndAge;将返回String.class
	 * </p>
	 * 
	 * @param field 字段
	 * @param index 泛型参数所在索引, 从0开始.
	 * @return 泛型参数的实际类型, 如果没有实现ParameterizedType接口，即不支持泛型，将直接返回{@code Object.class}
	 * @since 1.0.0
	 * @author calvin
	 * @author 唐玮琳
	 * @time 2013-5-1 上午10:20:51
	 */
	public static Class<?> getFieldGenericType(Field field) {
		return getFieldGenericType(field, 0);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 整理SpringSide的Generics类
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

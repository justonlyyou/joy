/**
 * 
 */
package com.kvc.joy.commons.lang;

import com.kvc.joy.commons.bean.Pair;
import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-1 上午9:29:01
 */
public class GenericToolTest {

	@Test
	public void testGetSuperClassGenricType() {
		// 有参数化的类
		Class<?> clazz = NameAndAge.class;

		// 没有指定索引默认第0个
		Class<?> firstClass = GenericTool.getSuperClassGenricType(clazz);
		Assert.assertEquals(String.class, firstClass);

		// 指定索引为第1个
		Class<?> secondClass = GenericTool.getSuperClassGenricType(clazz, 1);
		Assert.assertEquals(Integer.class, secondClass);

		// 索引越界
		Class<?> nullClass = GenericTool.getSuperClassGenricType(clazz, 2);
		Assert.assertEquals(null, nullClass);

		// 没有参数化的类
		clazz = NameAndSex.class;

		// 没有指定索引默认第0个
		firstClass = GenericTool.getSuperClassGenricType(clazz);
		Assert.assertEquals(Object.class, firstClass);

		// 指定索引为第1个
		secondClass = GenericTool.getSuperClassGenricType(clazz, 1);
		Assert.assertEquals(Object.class, secondClass);

		// 索引越界
		nullClass = GenericTool.getSuperClassGenricType(clazz, 2);
		Assert.assertEquals(Object.class, nullClass);
	}

	@Test
	public void testGetMethodGenericReturnType() throws NoSuchMethodException, SecurityException {
		// 有指定泛型
		Method method = NameAndAge.class.getMethod("get");

		// 没有指定索引默认第0个
		Class<?> returnType = GenericTool.getMethodGenericReturnType(method);
		Assert.assertEquals(String.class, returnType);

		// 指定索引为第1个
		returnType = GenericTool.getMethodGenericReturnType(method, 1);
		Assert.assertEquals(Integer.class, returnType);

		// 索引越界
		returnType = GenericTool.getMethodGenericReturnType(method, 2);
		Assert.assertEquals(null, returnType);

		// 没有指定泛型
		method = NameAndAge.class.getMethod("getAll");

		// 没有指定索引默认第0个
		returnType = GenericTool.getMethodGenericReturnType(method);
		Assert.assertEquals(Object.class, returnType);

		// 指定索引为第1个
		returnType = GenericTool.getMethodGenericReturnType(method, 1);
		Assert.assertEquals(Object.class, returnType);

		// 索引越界
		returnType = GenericTool.getMethodGenericReturnType(method, 2);
		Assert.assertEquals(Object.class, returnType);
	}

	@Test
	public void testGetMethodGenericParameterTypes() throws NoSuchMethodException, SecurityException {
		// 有指定泛型
		Method method = NameAndAge.class.getMethod("get", Map.class);

		// 没有参数索引默认第0个
		List<Class<?>> returnTypes = GenericTool.getMethodGenericParameterTypes(method);
		Assert.assertEquals(String.class, returnTypes.get(0));
		Assert.assertEquals(Integer.class, returnTypes.get(1));

		// 参数索引越界
		returnTypes = GenericTool.getMethodGenericParameterTypes(method, 1);
		Assert.assertEquals(null, returnTypes);

		// 没有指定泛型
		method = NameAndAge.class.getMethod("getAll", Map.class);

		// 没有参数索引默认第0个
		returnTypes = GenericTool.getMethodGenericParameterTypes(method);
		Assert.assertEquals(0, returnTypes.size());

		// 参数索引越界
		returnTypes = GenericTool.getMethodGenericParameterTypes(method, 1);
		Assert.assertEquals(null, returnTypes);
	}

	@Test
	public void testGetFieldGenericType() throws NoSuchFieldException, SecurityException {
		// 有指定泛型
		Field field = NameAndAge.class.getDeclaredField("map");

		// 没有索引默认第0个
		Class<?> type = GenericTool.getFieldGenericType(field);
		Assert.assertEquals(String.class, type);

		// 指定索引为第1个
		type = GenericTool.getFieldGenericType(field, 1);
		Assert.assertEquals(Integer.class, type);

		// 索引越界
		type = GenericTool.getFieldGenericType(field, 2);
		Assert.assertEquals(null, type);

		// 沒有指定泛型
		field = NameAndAge.class.getDeclaredField("map2");

		// 没有索引默认第0个
		type = GenericTool.getFieldGenericType(field);
		Assert.assertEquals(Object.class, type);

		// 指定索引为第1个
		type = GenericTool.getFieldGenericType(field, 1);
		Assert.assertEquals(Object.class, type);

		// 索引越界
		type = GenericTool.getFieldGenericType(field, 2);
		Assert.assertEquals(Object.class, type);
	}

	private class NameAndAge extends Pair<String, Integer> {
		private static final long serialVersionUID = 1L;

		@SuppressWarnings("unused")
		private Map<String, Integer> map;
		@SuppressWarnings({ "unused", "rawtypes" })
		private Map map2;

		@SuppressWarnings("unused")
		public Map<String, Integer> get() {
			return null;
		}

		@SuppressWarnings({ "unused", "rawtypes" })
		public Map getAll() {
			return null;
		}

		@SuppressWarnings("unused")
		public void get(Map<String, Integer> map) {
		}

		@SuppressWarnings({ "unused", "rawtypes" })
		public void getAll(Map map) {
		}

		@SuppressWarnings("unused")
		public Map<String, Integer> add(Map<String, Integer> map) {
			return null;
		}

	}

	@SuppressWarnings("rawtypes")
	private class NameAndSex extends Pair {
		private static final long serialVersionUID = 1L;
	}

}

/**
 * 
 */
package org.joy.commons.lang.reflect;

import org.joy.commons.bean.Pair;
import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-3 下午11:57:38
 */
public class MethodToolTest {
	
	@Test
	public void testGetReadMethods() {
		List<Method> readMethods = MethodTool.getReadMethods(Pair.class);
		Set<String> methodNameSet = new HashSet<String>();
		for (Method method : readMethods) {
			methodNameSet.add(method.getName());
		}
		Assert.assertTrue(methodNameSet.contains("getLeft"));
		Assert.assertTrue(methodNameSet.contains("getRight"));
	}
	
	@Test
	public void invokeGetterAndSetter() {
		TestBean bean = new TestBean();
		Assert.assertEquals(bean.inspectPublicField() + 1, MethodTool.invokeGetter(bean, "publicField"));

		bean = new TestBean();
		// 通过setter的函数将+1
		MethodTool.invokeSetter(bean, "publicField", 10);
		Assert.assertEquals(10 + 1, bean.inspectPublicField());
	}
	
	public static class ParentBean<T, ID> {
	}

	public static class TestBean extends ParentBean<String, Long> {
		/** 没有getter/setter的field */
		private final int privateField = 1;
		/** 有getter/setter的field */
		private int publicField = 1;

		public int getPublicField() {
			return publicField + 1;
		}

		public void setPublicField(int publicField) {
			this.publicField = publicField + 1;
		}

		public int inspectPrivateField() {
			return privateField;
		}

		public int inspectPublicField() {
			return publicField;
		}

		@SuppressWarnings("unused")
		private String privateMethod(String text) {
			return "hello " + text;
		}
	}

	@SuppressWarnings("rawtypes")
	public static class TestBean2 extends ParentBean {
	}

	public static class TestBean3 {

		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

}

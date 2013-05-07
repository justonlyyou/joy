/**
 * 
 */
package com.kvc.joy.core.persistence.orm.jpa.annotations.listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.persistence.PrePersist;

import com.kvc.joy.core.persistence.orm.jpa.annotations.MaxValue;
import com.kvc.joy.core.persistence.orm.jpa.annotations.MinValue;
import com.kvc.joy.core.persistence.orm.jpa.annotations.Unsigned;

/**
 * 方法级注解监听器
 * 
 * @author 唐玮琳
 * @time 2012-5-2 下午8:26:58
 */
public class MethodAnnotationListener {

	@PrePersist
	public void prePersist(Object obj) throws Exception {
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAccessible()) {
				String methodName = method.getName();
				if (methodName.startsWith("get") || methodName.startsWith("is")) { // TODO
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation : annotations) {
						Class<? extends Annotation> annoClass = annotation.getClass();
						if (annoClass == MaxValue.class) {
							checkMaxValue(obj, method);
						} else if (annoClass == MinValue.class) {
							checkMinValue(obj, method);
						} else if(annoClass == Unsigned.class) {
							checkUnsigned(obj, method);
						}
					}
				}
			}
		}
	}

	private void checkMaxValue(Object obj, Method method) throws Exception {
		Object value = method.invoke(obj);
		if (value != null) {
			MaxValue max = method.getAnnotation(MaxValue.class);
			if ((value + "").compareTo(max.value()) > 0) {
				throw new RuntimeException("方法" + method + "返回值：" + value + "超过其注解限定的最大值：" + max.value());
			}
		}
	}

	private void checkMinValue(Object obj, Method method) throws Exception {
		Object value = method.invoke(obj);
		if (value != null) {
			MinValue min = method.getAnnotation(MinValue.class);
			if ((value + "").compareTo(min.value()) < 0) {
				throw new RuntimeException("方法" + method + "返回值：" + value + "超过其注解限定的最小值：" + min.value());
			}
		}
	}

	private void checkUnsigned(Object obj, Method method) throws Exception {
		Object value = method.invoke(obj);
		if (value != null) {
			if (value instanceof Number) {
				if ((value + "").startsWith("-")) {
					throw new RuntimeException("方法" + method + "返回值：" + value + "不是注解限定的无符号数！");
				}
			}
		}
	}

}

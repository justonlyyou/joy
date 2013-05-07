/**
 * 
 */
package com.kvc.joy.core.persistence.orm.jpa.annotations.listener;

import java.lang.annotation.Annotation;

import javax.persistence.PrePersist;

import com.kvc.joy.core.persistence.orm.jpa.annotations.AbstractWithTable;

/**
 * 类级注解监听器
 * 
 * @author 唐玮琳
 * @time 2012-5-2 下午8:26:14
 */
public class ClassAnnotationListener {

	@PrePersist
	public void prePersist(Object obj) throws Exception {
		Annotation[] annotations = obj.getClass().getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.getClass() == AbstractWithTable.class) {
				processAbstractWithTable();
			}
		}
	}

	private void processAbstractWithTable() {
		throw new RuntimeException("abstract class cannot be persisted");
	}

}

package com.kvc.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释注解
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午8:35:44
 */
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Comment {

	/**
	 * 简要描述
	 * 
	 * @return 简要描述
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-10 下午8:40:42
	 */
	String value();

	/**
	 * 详细描述
	 * 
	 * @return 详细描述
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-10 下午8:40:55
	 */
	String detailDesc() default "";

	/**
	 * 代码表id
	 * 
	 * @return 代码表id
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-10 下午8:41:43
	 */
	String codeId() default "";

}

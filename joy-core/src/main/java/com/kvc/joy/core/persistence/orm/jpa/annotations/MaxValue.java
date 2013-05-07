package com.kvc.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段最大值注解
 * 
 * @author 唐玮琳
 * @time 2012-5-2 下午7:48:36
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MaxValue {
	
    String value();
    
}

package org.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.*;

/**
 * 字段最大值注解
 * 
 * @author Kevice
 * @time 2012-5-2 下午7:48:36
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MaxValue {
	
    String value();
    
}

/**
 * 
 */
package com.kvc.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.*;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-11-9 下午10:28:51
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DefaultValue {

	/**
	 * 默认值
	 * 
	 * @return 默认值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-11-09 下午10:30:42
	 */
	String value();

}
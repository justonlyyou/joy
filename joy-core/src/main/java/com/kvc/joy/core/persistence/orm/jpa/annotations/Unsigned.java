package com.kvc.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.*;

/**
 * 表示某个属性的值是无符号数
 * @author 唐玮琳
 * @time 2012-5-2 下午8:22:25
 */
@Target({ElementType.FIELD}) 
@Retention(RetentionPolicy.RUNTIME) 
@Inherited 
public @interface Unsigned {

}

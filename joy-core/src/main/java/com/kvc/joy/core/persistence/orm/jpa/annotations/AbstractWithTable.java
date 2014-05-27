package com.kvc.joy.core.persistence.orm.jpa.annotations;

import java.lang.annotation.*;

/**
 * 在 OpenJPA 中，针对实体之间的继承关系如何在数据库中展现，共有三种方式：<br>
 * SINGLE_TABLE，TABLE_PER_CLASS 和 JOINED。<br>
 * 当应用程序使用 TABLE_PER_CLASS 这种方式时，Java 的抽象类（Abstract Class）在 OpenJPA 中不会被映射到数据库中的表项，<br>
 * 同时抽象性阻止了用户不能通过 OpenJPA 直接对该类进行持久化的操作。有些应用需要保持一个类在持久化操作方面的抽象性，<br>
 * 但是又能保证该类在数据库中有对应的表项。那么该类必须被定义为 Java 的具体类，同时引入 @AbstractWithTable <br>
 * 用法：<br>
 * 	@Entity <br>
 * @AbstractWithTable <br> 
 * @EntityListeners(value = { ClassAnnotationListener.class }) <br> 
 * public class YourEntity {  <br>
 *  @Basic  <br>
 *   public String field; <br> 
 * } <br>
 * 
 * @author Kevice
 * @time 2012-5-2 下午7:27:31
 */
@Target({ElementType.TYPE}) 
@Retention(RetentionPolicy.RUNTIME) 
@Inherited
public @interface AbstractWithTable { 

}

package org.joy.core.persistence.jdbc.service;

/**
 * 关系型数据库表修改反向同步服务接口 <br>
 * 在JPA实体反向同步完表后，将利用该类根据JPA实体上自定义的扩展注解，设置数据库表和列的注释、列的默认值。<br>
 * 因为JPA实体所对应的表是通用hibernate反向生成ddl的，但这一功能不能生成注释(包括表和列)和列的默认值。
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-11-10 下午8:32:17
 */
public interface IMdRdbAlterReverseSyncService {

    /**
     * 根据指定的包模式串，查找JPA实体类，利用JPA实体类上自定义的扩展注解，生成数据库表和列的注释、列的默认值
     * @param pkgs 包模式串(支持星号通配符匹配包名)可变数组
     */
	void generate(String... pkgs);
	
}

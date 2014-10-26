package org.joy.core.init.dao;

import java.sql.Connection;
import java.util.Properties;

/**
 * 数据库中属性访问对象接口
 *
 * @author Kevice
 * @time 14-3-17 上午7:15
 * @since 1.0.0
 */
public interface IDbPropertiesDao {

    String TABLE_NAME = "t_joy_properties";
    String SCRIPT_PREFIX = "JOY";
    String SCRIPT_SUFFIX = ".sql";
    String SCRIPT_CLASSPATH = "sql";
    String SCRIPT_VERSION_PROPERTY = "SCRIPT_VERSION";

    /**
     * 查找joy基础脚本的版本
     *
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    String findScriptVersion(Connection connection);

    /**
     * 加载表t_joy_properties中的所有属性
     *
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    Properties findProperties(Connection connection);

    /**
     * 初始化并加载表t_joy_properties中的所有属性
     *
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    Properties initTable();

}

package com.kvc.joy.core.init.dao;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author Kevice
 * @time 14-3-17 上午7:15
 * @since 1.0.0
 */
public interface IJoyPropertiesDao {

    String TABLE_NAME = "t_joy_properties";
    String SCRIPT_PREFIX = "JOY";
    String SCRIPT_SUFFIX = ".sql";
    String SCRIPT_CLASSPATH = "sql";
    String SCRIPT_VERSION_PROPERTY = "SCRIPT_VERSION";

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    String findScriptVersion(Connection connection);

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    Properties findProperties(Connection connection);

    /**
     * @author Kevice
     * @time 14-3-17 上午7:17
     * @since 1.0.0
     */
    Properties initTable();

}

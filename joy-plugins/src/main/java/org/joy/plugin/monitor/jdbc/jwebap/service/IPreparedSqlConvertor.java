package org.joy.plugin.monitor.jdbc.jwebap.service;

import java.util.List;

/**
 * 预编译sql语句转化器接口
 *
 * @author Kevice
 * @time 2013-2-5 上午12:39:07
 * @since 1.0.0
 */
public interface IPreparedSqlConvertor {
    /**
     * 设置预编译参数
     *
     * @param i     参数位置
     * @param param 参数值
     * @author Kevice
     * @time 2013-2-5 上午12:39:07
     * @since 1.0.0
     */
    void setParam(int i, Object param);

    /**
     * 转化预编译sql
     *
     * @return 转化后的sql
     * @author Kevice
     * @time 2013-2-5 上午12:39:07
     * @since 1.0.0
     */
    String convert();

    /**
     * 获取参数
     *
     * @return 参数列表
     * @author Kevice
     * @time 2013-2-5 上午12:39:07
     * @since 1.0.0
     */
    List<String> getParams();

    /**
     * 获取预编译sql
     *
     * @return 预编译sql
     * @author Kevice
     * @time 2013-2-5 上午12:39:07
     * @since 1.0.0
     */
    String getSql();
}

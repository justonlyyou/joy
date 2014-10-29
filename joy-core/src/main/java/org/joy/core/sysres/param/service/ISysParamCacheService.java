package org.joy.core.sysres.param.service;

import org.joy.core.sysres.param.model.po.TSysParam;

import java.util.Map;

/**
 * 系统参数缓存服务接口
 *
 * @author Kevice
 * @time 14-3-16 下午4:01
 * @since 1.0.0
 */
public interface ISysParamCacheService {

    /**
     * 返回所有参数信息
     *
     * @return Map<参数名称，系统参数实体>
     * @author Kevice
     * @time 2013-2-7 下午10:43:48
     * @since 1.0.0
     */
    Map<String, TSysParam> get();

    /**
     * 返回所有property参数
     *
     * @return Map<参数名称，系统参数实体>
     * @author Kevice
     * @time 2014-3-16 15:43:48
     * @since 1.0.0
     */
    Map<String, TSysParam> getProperties();

    /**
     * 根据参数名获取参数信息
     *
     * @param paramName 参数名
     * @return 系统参数实体
     * @author Kevice
     * @time 2013-2-7 下午10:44:24
     * @since 1.0.0
     */
    TSysParam get(String paramName);


}

package com.kvc.joy.core.sysres.param.service;

import com.kvc.joy.core.sysres.param.model.po.TSysParam;

import java.util.Map;

/**
 * @author 唐玮琳
 * @time 14-3-16 下午4:01
 * @since 1.0.0
 */
public interface ISysParamCacheService {

    /**
     * 获取所有参数信息
     *
     * @return
     * @author 唐玮琳
     * @time 2013-2-7 下午10:43:48
     */
    Map<String, TSysParam> get();

    /**
     * 获取所有property参数
     *
     * @return
     * @author 唐玮琳
     * @time 2014-3-16 15:43:48
     */
    Map<String, TSysParam> getProperties();

    /**
     * 根据参数名获取参数信息
     *
     * @param paramName
     * @return
     * @author 唐玮琳
     * @time 2013-2-7 下午10:44:24
     */
    TSysParam get(String paramName);


}

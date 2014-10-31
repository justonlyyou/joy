package org.joy.plugin.persistence.mybatis.service;

import org.apache.ibatis.mapping.MappedStatement;
import org.joy.plugin.persistence.mybatis.BaseMapper;

/**
 * 返回结果修改器接口
 *
 * @author Kevice
 * @time 2014年10月22日 上午11:36:42
 * @since 1.0.0
 */
public interface IReturnMapModifier {

    /**
     * 执行修改
     *
     * @param mapperClass Mapper类
     * @author Kevice
     * @time 2014年10月22日 上午11:36:42
     * @since 1.0.0
     */
    void modify(Class<? extends BaseMapper> mapperClass);

}

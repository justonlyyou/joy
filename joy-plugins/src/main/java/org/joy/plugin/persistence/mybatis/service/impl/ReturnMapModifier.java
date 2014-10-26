package org.joy.plugin.persistence.mybatis.service.impl;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.joy.commons.bean.IEntity;
import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.GenericTool;
import org.joy.plugin.persistence.mybatis.BaseMapper;
import org.joy.plugin.persistence.mybatis.service.IReturnMapModifier;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果修改器
 *
 * @author Kevice
 * @time 2014年10月22日 上午11:36:42
 * @since 1.0.0
 */
public class ReturnMapModifier implements IReturnMapModifier {

    private SqlSessionTemplate sqlSessionTemplate;
    private Map<String, Class<?>> resultMetaMap = new HashMap<>(); // Map<ResultMap的id去掉Mapper类名部分，ResultType>

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();

    public ReturnMapModifier() {
        registerResultMetas();
    }

    protected void registerResultMetas() {
        // PO 临时用IEntity.class代替，使用时会根据mapperClass的实际PO替换(见modify方法)
        Class<?> clazz = IEntity.class;
        resultMetaMap.put("_get-Class-Serializable", clazz);
        resultMetaMap.put("_inSearch-Class-String-Map", clazz);
        resultMetaMap.put("_search-Class-Order[]", clazz);
        resultMetaMap.put("_andSearch-Class-Map-Order[]", clazz);
        resultMetaMap.put("_orSearch-Class-Map-Order[]", clazz);
        resultMetaMap.put("_pagingSearch-Class-Paging-Map-Order[]", clazz);

        // Map
        clazz = Map.class;
        resultMetaMap.put("_inSearch-Class-String-Collection-Map", clazz);
        resultMetaMap.put("_searchReturnProperties-Class-Collection-Order[]", clazz);
        resultMetaMap.put("_andSearchReturnProperties-Class-Map-Collection-Order[]", clazz);
        resultMetaMap.put("_orSearchReturnProperties-Class-Map-Collection-Order[]", clazz);

        // Object
        clazz = Object.class;
        resultMetaMap.put("_inSearch-Class-String-String-Map", clazz);
        resultMetaMap.put("_searchReturnProperty-Class-String-Order[]", clazz);
        resultMetaMap.put("_andSearchReturnProperty-Class-Map-String-Order[]", clazz);
        resultMetaMap.put("_orSearchReturnProperty-Class-Map-String-Order[]", clazz);
    }

    @Override
    public void modify(Class<? extends BaseMapper> mapperClass) {
        Configuration cfg = sqlSessionTemplate.getConfiguration();

        Class<? extends IEntity> poClass = getPoClass(mapperClass);
        Collection<ResultMap> resultMaps = cfg.getResultMaps();
        for(ResultMap resultMap : resultMaps) {
            String id = resultMap.getId();
            String className = mapperClass.getName();
            String suffix = id.substring(className.length() + 1); // className后面还有个点
            Class<?> resultType = resultMetaMap.get(suffix);
            if (resultType != null) {
                if(resultType == IEntity.class) {
                    resultType =  poClass;
                }
                MetaObject metaObject = MetaObject.forObject(resultMap, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
                metaObject.setValue("type", resultType);
            }
        }
    }

    protected Class<? extends IEntity> getPoClass(Class<? extends BaseMapper> mapperClass) {
        Class<?> clazz = GenericTool.getSuperClassGenricType(mapperClass);
        if (clazz == Object.class) {
            throw new SystemException("实现BaseMapper接口的类必须指定泛型参数！");
        }
        return (Class<? extends IEntity>) clazz;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
}

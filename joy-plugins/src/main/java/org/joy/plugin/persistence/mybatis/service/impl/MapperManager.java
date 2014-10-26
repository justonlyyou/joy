package org.joy.plugin.persistence.mybatis.service.impl;

import org.apache.ibatis.session.Configuration;
import org.joy.plugin.persistence.mybatis.BaseMapper;
import org.joy.plugin.persistence.mybatis.service.IMapperManager;
import org.joy.plugin.persistence.mybatis.service.IReturnMapModifier;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Collection;

/**
 * 实体映射管理者
 *
 * @author Kevice
 * @time 2014年10月22日 下午17:20:00
 * @since 1.0.0
 */
public class MapperManager implements IMapperManager {

    private IReturnMapModifier returnMapModifier;
    private SqlSessionTemplate sqlSessionTemplate;

    public void manage() {
        Configuration cfg = sqlSessionTemplate.getConfiguration();

        Collection<Class<?>> mappers = cfg.getMapperRegistry().getMappers();
        for (Class<?> mapperClazz : mappers) {
            if (BaseMapper.class.isAssignableFrom(mapperClazz)) {
                returnMapModifier.modify((Class<? extends BaseMapper>) mapperClazz);
            }
        }
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void setReturnMapModifier(IReturnMapModifier returnMapModifier) {
        this.returnMapModifier = returnMapModifier;
    }
}

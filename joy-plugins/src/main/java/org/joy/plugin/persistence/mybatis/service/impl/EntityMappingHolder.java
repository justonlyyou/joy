package org.joy.plugin.persistence.mybatis.service.impl;

import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.joy.commons.bean.IEntity;
import org.joy.commons.exception.SystemException;
import org.joy.commons.lang.GenericTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.plugin.persistence.mybatis.BaseMapper;
import org.joy.plugin.persistence.mybatis.model.vo.EntityMappingInfo;
import org.joy.plugin.persistence.mybatis.service.IEntityMappingHolder;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MyBatis实体类和数据库表映射信息持有者
 *
 * @author Kevice
 * @time 2014年10月22日 下午17:00:37
 * @since 1.0.0
 */
public class EntityMappingHolder implements IEntityMappingHolder {

    private SqlSessionTemplate sqlSessionTemplate;
    private Map<Class<? extends IEntity>, EntityMappingInfo> map = new HashMap<>();
    private Map<Class<? extends IEntity>, Class<? extends BaseMapper>> mapperClassMap = new HashMap<>();
    private static final String TABLE_NAME_PREFIX = "TABLE:";

    public void retriveMappingInfo() {
        Configuration cfg = sqlSessionTemplate.getConfiguration();
        Collection<ResultMap> resultMaps = cfg.getResultMaps();
        for (ResultMap resultMap : resultMaps) {
            Class<?> type = resultMap.getType();
            if (type != IEntity.class && IEntity.class.isAssignableFrom(type) &&
                    (resultMap.getId().contains(TABLE_NAME_PREFIX) || !map.containsKey(type))) {
                Class<? extends IEntity> clazz = (Class<? extends IEntity>) type;
                EntityMappingInfo entityMappingInfo = createEntityMappingInfo(clazz, resultMap);
                map.put(clazz, entityMappingInfo);
            }
        }

        Collection<Class<?>> mapperClasses = cfg.getMapperRegistry().getMappers();
        for (Class<?> mapperClass : mapperClasses) {
            if (BaseMapper.class.isAssignableFrom(mapperClass)) {
                Class<?> poClass = GenericTool.getSuperClassGenricType(mapperClass);
                if (poClass.equals(Object.class)) {
                    throw new SystemException("实现BaseMapper接口的类必须指定泛型参数！");
                }
                mapperClassMap.put((Class<? extends IEntity>) poClass, (Class<? extends BaseMapper>) mapperClass);
            }
        }
    }

    private EntityMappingInfo createEntityMappingInfo(Class<? extends IEntity> clazz, ResultMap resultMap) {
        EntityMappingInfo entityMappingInfo = new EntityMappingInfo();
        entityMappingInfo.setEntityClass(clazz);

        // 获取表名，mapper的xml文件中的resultMap元素的id被约定为TABLE_NAME_PREFIX+实体对应的表名
        String id = resultMap.getId();
        String tableName = null;
        if(id.contains(TABLE_NAME_PREFIX)) {
            tableName = id.replaceAll("\\w+\\.", "").replaceFirst(TABLE_NAME_PREFIX, "");
        } else {
            tableName = StringTool.humpToUnderscore(clazz.getSimpleName()).toLowerCase();
        }
        entityMappingInfo.setTableName(tableName);

        // 获取属性与字段的映射关系
        List<ResultMapping> resultMappings = resultMap.getResultMappings();
        Map<String, String> propertyMap = new HashMap<>(resultMappings.size());
        Map<String, String> columnMap = new HashMap<>(resultMappings.size());
        for (ResultMapping resultMapping : resultMappings) {
            String property = resultMapping.getProperty();
            String column = resultMapping.getColumn();
            propertyMap.put(property, column);
            columnMap.put(column, property);
        }
        entityMappingInfo.setPropertyMap(propertyMap);
        entityMappingInfo.setColumnMap(columnMap);
        return entityMappingInfo;
    }

    @Override
    public String getTableName(Class<? extends IEntity> clazz) {
        EntityMappingInfo entityMappingInfo = map.get(clazz);
        if (entityMappingInfo == null) {
            return StringTool.humpToUnderscore(clazz.getSimpleName()).toLowerCase();
        } else {
            return entityMappingInfo.getTableName();
        }
    }

    @Override
    public String getKeyColumnName(Class<? extends IEntity> clazz) {
        return getColumnName(clazz, "id");
    }

    @Override
    public String getColumnName(Class<? extends IEntity> clazz, String propertyName) {
        EntityMappingInfo entityMappingInfo = map.get(clazz);
        if (entityMappingInfo != null) {
            String columnName = entityMappingInfo.getPropertyMap().get(propertyName);
            if (StringTool.isNotBlank(columnName)) {
                return columnName;
            }
        }
        return StringTool.humpToUnderscore(propertyName).toLowerCase();
    }

    @Override
    public String getPropertyName(Class<? extends IEntity> clazz, String columnName) {
        EntityMappingInfo entityMappingInfo = map.get(clazz);
        if (entityMappingInfo != null) {
            String propertyName = entityMappingInfo.getColumnMap().get(columnName);
            if (StringTool.isNotBlank(propertyName)) {
                return propertyName;
            }
        }
        return StringTool.underscoreToHump(columnName);
    }

    @Override
    public BaseMapper getMapper(Class<? extends IEntity> clazz) {
        Class<? extends BaseMapper> mapperClass = mapperClassMap.get(clazz);
        return sqlSessionTemplate.getMapper(mapperClass);
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

}

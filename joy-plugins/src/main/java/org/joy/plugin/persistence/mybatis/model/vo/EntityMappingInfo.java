package org.joy.plugin.persistence.mybatis.model.vo;

import org.joy.commons.bean.IEntity;

import java.util.Map;

/**
 * 实体映射信息
 *
 * @author Kevice
 * @time 2014年10月22日 下午17:10:00
 * @since 1.0.0
 */
public class EntityMappingInfo {

    private Class<? extends IEntity> entityClass; // 实体类
    private String tableName; // 表名
    private Map<String, String> propertyMap; // Map<属性名，列名>
    private Map<String, String> columnMap;  // Map<列名, 属性名>

    public Class<? extends IEntity> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<? extends IEntity> entityClass) {
        this.entityClass = entityClass;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public Map<String, String> getColumnMap() {
        return columnMap;
    }

    public void setColumnMap(Map<String, String> columnMap) {
        this.columnMap = columnMap;
    }
}

package org.joy.plugin.persistence.mybatis.service;

import org.joy.commons.bean.IEntity;
import org.joy.plugin.persistence.mybatis.BaseMapper;

/**
 * MyBatis实体类和数据库表映射信息持有者接口
 *
 * @author Kevice
 * @time 2014年10月22日 下午17:00:00
 * @since 1.0.0
 */
public interface IEntityMappingHolder {

    /**
     * 根据实体类获取对应的表名
     *
     * @param clazz 实体类
     * @return 表名
     */
    String getTableName(Class<? extends IEntity> clazz);

    /**
     * 根据实体类获取对应的表的主键列名
     *
     * @param clazz 实体类
     * @return 主键列名
     */
    String getKeyColumnName(Class<? extends IEntity> clazz);

    /**
     * 根据实体类和类的属性名，获取对应的表的列名
     *
     * @param clazz    实体类
     * @param propertyName 属性名
     * @return 列名
     */
    String getColumnName(Class<? extends IEntity> clazz, String propertyName);

    /**
     * 根据实体类和表的列名，获取对应的类的属性名
     *
     * @param clazz  实体类
     * @param columnName 表的列名
     * @return 属性名
     */
    String getPropertyName(Class<? extends IEntity> clazz, String columnName);

    /**
     * 根据实体类，获取对应的Mapper
     *
     * @param clazz 实体类
     * @return Mapper
     */
    BaseMapper getMapper(Class<? extends IEntity> clazz);

}

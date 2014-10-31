package org.joy.plugin.security.erbac.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.model.po.TErbacGroup;

/**
 * 组数据访问对象
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-27 下午8:00:56
 */
public interface IErbacGroupDao extends IJpaEntityRepository<TErbacGroup, String> {

}

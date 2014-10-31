package org.joy.plugin.security.erbac.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.model.po.TErbacAuthority;

/**
 * 权限数据访问对象接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-27 下午8:03:22
 */
public interface IErbacAuthorityDao extends IJpaEntityRepository<TErbacAuthority, String> {

}

package org.joy.plugin.security.erbac.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.model.po.TErbacRole;

/**
 * 角色数据访问对象接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-27 下午8:01:46
 */
public interface IErbacRoleDao extends IJpaEntityRepository<TErbacRole, String> {

}

package org.joy.plugin.security.erbac.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.model.po.TErbacRole;

/**
 * 
 * @author Kevice
 * @time 2012-6-27 下午8:01:46
 */
public interface IErbacRoleDao extends IJpaEntityRepository<TErbacRole, String> {

}

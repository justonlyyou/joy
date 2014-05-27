package com.kvc.joy.plugin.security.erbac.dao;

import com.kvc.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacAuthority;

/**
 * 
 * @author Kevice
 * @time 2012-6-27 下午8:03:22
 */
public interface IErbacAuthorityDao extends IJpaEntityRepository<TErbacAuthority, String> {

}

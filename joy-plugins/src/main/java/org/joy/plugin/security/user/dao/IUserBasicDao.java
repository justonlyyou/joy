package org.joy.plugin.security.user.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.dao.IErbacUserRepository;
import org.joy.plugin.security.user.model.po.TUserBasic;

/**
 * 
 * @author Kevice
 * @time 2012-6-25 下午10:14:58
 */
public interface IUserBasicDao extends IJpaEntityRepository<TUserBasic, String> , IErbacUserRepository {

	TUserBasic findByAccountAndPassword(String account, String password);

}
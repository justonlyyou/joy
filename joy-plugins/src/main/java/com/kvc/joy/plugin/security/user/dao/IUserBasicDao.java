package com.kvc.joy.plugin.security.user.dao;

import com.kvc.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import com.kvc.joy.plugin.security.erbac.dao.IErbacUserRepository;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;

/**
 * 
 * @author Kevice
 * @time 2012-6-25 下午10:14:58
 */
public interface IUserBasicDao extends IJpaEntityRepository<TUserBasic, String> , IErbacUserRepository {

	TUserBasic findByAccountAndPassword(String account, String password);

}
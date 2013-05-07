package com.kvc.joy.plugin.security.erbac.dao;

import com.kvc.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-25 下午10:14:58
 */
public interface IErbacUserDao extends IJpaEntityRepository<TErbacUser, String> ,  IErbacUserRepository {

	TErbacUser findByAccountAndPassword(String account, String password);

}
package com.kvc.joy.plugin.security.erbac.dao;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;

import java.util.Collection;
import java.util.Map;


/**
 * 
 * @author 唐玮琳
 * @time 2012-6-28 下午7:54:15
 */
public interface IErbacUserRepository {

	Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds);

}

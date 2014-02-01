package com.kvc.joy.plugin.security.erbac.biz.impl;

import com.kvc.joy.plugin.security.erbac.biz.IErbacUserBiz;
import com.kvc.joy.plugin.security.erbac.dao.IErbacUserDao;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 * 
 * @author 唐玮琳
 * @time 2012-5-8 下午11:16:04
 */
public class ErbacUserBiz implements IErbacUserBiz {

	private IErbacUserDao iErbacUserDao;

	public List<TErbacUser> getAllActiveUsers() {
		// TODO
		return null;
	}

	public TErbacUser getUser(String account, String password) {
		return iErbacUserDao.findByAccountAndPassword(account, password);
	}

	public void setiErbacUserDao(IErbacUserDao iErbacUserDao) {
		this.iErbacUserDao = iErbacUserDao;
	}

	public Map<TErbacGroup, Collection<TErbacUser>> getUsersByGroupIds(Collection<String> groudIds) {
		return iErbacUserDao.getUsersByGroupIds(groudIds);
	}

}

package org.joy.plugin.security.erbac.biz.impl;

import org.joy.plugin.security.erbac.biz.IErbacUserBiz;
import org.joy.plugin.security.erbac.model.po.TErbacGroup;
import org.joy.plugin.security.user.dao.IUserBasicDao;
import org.joy.plugin.security.user.model.po.TUserBasic;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-5-8 下午11:16:04
 */
public class ErbacUserBiz implements IErbacUserBiz {

	private IUserBasicDao iUserBasicDao;

	public List<TUserBasic> getAllActiveUsers() {
		// TODO
		return null;
	}

	public TUserBasic getUser(String account, String password) {
		return iUserBasicDao.findByAccountAndPassword(account, password);
	}

	public void setiUserBasicDao(IUserBasicDao iUserBasicDao) {
		this.iUserBasicDao = iUserBasicDao;
	}

	public Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds) {
		return iUserBasicDao.getUsersByGroupIds(groudIds);
	}

}

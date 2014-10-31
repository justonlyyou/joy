package org.joy.plugin.security.user.dao.impl;

import org.joy.core.persistence.orm.jpa.BaseJpaDao;
import org.joy.plugin.security.user.model.po.TUserBasic;

/**
 * 基本jpa数据访问对象
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-23 下午3:03:33
 */
public class TUserBasicDao extends BaseJpaDao<TUserBasic> {

	/**
     * 根据用户名获取用户基本信息
	 * 
	 * @param username 用户名
	 * @return 用户基本信息对象
     * @since 1.0.0
	 * @author Kevice
	 * @time 2013-2-23 下午3:04:49
	 */
	public TUserBasic getUserByUsername(String username) {
		return null;
	}

}

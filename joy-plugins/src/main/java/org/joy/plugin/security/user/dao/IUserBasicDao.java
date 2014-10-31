package org.joy.plugin.security.user.dao;

import org.joy.core.persistence.orm.jpa.IJpaEntityRepository;
import org.joy.plugin.security.erbac.dao.IErbacUserRepository;
import org.joy.plugin.security.user.model.po.TUserBasic;

/**
 * 用户基本信息数据访问对象接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-25 下午10:14:58
 */
public interface IUserBasicDao extends IJpaEntityRepository<TUserBasic, String> , IErbacUserRepository {

    /**
     * 查找用户基本信息
     *
     * @param account 用户账号
     * @param password 用户密码
     * @return 用户基本信息对象
     * @since 1.0.0
     * @author Kevice
     * @time 2012-6-25 下午10:14:58
     */
	TUserBasic findByAccountAndPassword(String account, String password);

}
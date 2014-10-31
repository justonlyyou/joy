package org.joy.plugin.security.erbac.dao;

import org.joy.plugin.security.erbac.model.po.TErbacGroup;
import org.joy.plugin.security.user.model.po.TUserBasic;

import java.util.Collection;
import java.util.Map;


/**
 * 用户基本信息数据访问对象接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2012-6-28 下午7:54:15
 */
public interface IErbacUserRepository {

    /**
     * 返回组id下的所有用户
     *
     * @param groudIds 组id集合
     * @return Map<用户组，Collection<用户基本信息>>
     * @since 1.0.0
     * @author Kevice
     * @time 2012-6-28 下午7:54:15
     */
	Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds);

}

package org.joy.plugin.security.erbac.biz;

import org.joy.plugin.security.erbac.model.po.TErbacGroup;
import org.joy.plugin.security.user.model.po.TUserBasic;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户服务接口
 *
 * @author Kevice
 * @time 2012-5-8 下午11:10:11
 * @since 1.0.0
 */
public interface IErbacUserBiz {

    /**
     * 取得所有有效的用户
     *
     * @return List<TUserBasic>
     * @author Kevice
     * @time 2012-5-8 下午11:21:06
     * @since 1.0.0
     */
    List<TUserBasic> getAllActiveUsers();

    /**
     * 返回指定用户
     *
     * @param account  帐号
     * @param password 密码
     * @return 用户基本信息
     * @author Kevice
     * @time 2012-5-8 下午11:21:06
     * @since 1.0.0
     */
    TUserBasic getUser(String account, String password);

    /**
     * 返回组id下的所有用户
     *
     * @param groudIds 组id集合
     * @return Map<用户组，Collection<用户基本信息>>
     * @author Kevice
     * @time 2012-5-8 下午11:21:06
     * @since 1.0.0
     */
    Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds);

}

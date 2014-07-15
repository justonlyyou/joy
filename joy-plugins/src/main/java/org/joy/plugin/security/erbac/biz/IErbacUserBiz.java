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
 */
public interface IErbacUserBiz {

	/**
	 * 取得所有有效的用户
	 * 
	 * @return List<TUserBasic>
	 * @author Kevice
	 * @time 2012-5-8 下午11:21:06
	 */
	List<TUserBasic> getAllActiveUsers();
	
	TUserBasic getUser(String account, String password);
	
	Map<TErbacGroup, Collection<TUserBasic>> getUsersByGroupIds(Collection<String> groudIds);
	
}

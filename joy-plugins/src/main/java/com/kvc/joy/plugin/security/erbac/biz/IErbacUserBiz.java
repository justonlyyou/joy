package com.kvc.joy.plugin.security.erbac.biz;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

/**
 * 用户服务接口
 * 
 * @author 唐玮琳
 * @time 2012-5-8 下午11:10:11
 */
public interface IErbacUserBiz {

	/**
	 * 取得所有有效的用户
	 * 
	 * @return List<TErbacUser>
	 * @author 唐玮琳
	 * @time 2012-5-8 下午11:21:06
	 */
	List<TErbacUser> getAllActiveUsers();
	
	TErbacUser getUser(String account, String password);
	
	Map<TErbacGroup, Collection<TErbacUser>> getUsersByGroupIds(Collection<String> groudIds);
	
}

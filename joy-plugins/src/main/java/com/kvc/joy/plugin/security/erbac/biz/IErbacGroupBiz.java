package com.kvc.joy.plugin.security.erbac.biz;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 组服务
 * 
 * @author Kevice
 * @time 2012-5-3 下午10:21:13
 */
public interface IErbacGroupBiz {
	
	/**
	 * 取得所有组，组装成树
	 * 
	 * @return List<组的树结点> 
	 * @author Kevice
	 * @time 2012-5-3 下午10:21:30
	 */
	List<TreeNode<TErbacGroup>> getGroupTree();
	
	/**
	 * 根据组id，取得该组下的直接用户(不包含孩子组的用户)
	 * 
	 * @param groupId 组id
	 * @return Collection<用户>
	 * @author Kevice
	 * @time 2012-5-3 下午10:24:10
	 */
	Collection<TUserBasic> getGroupUsers(String groupId) ;
	
	/**
	 * 根据组id，取得该组下所有的用户(包含本身和其所有孩子组的用户)
	 * 
	 * @param groupId 组id
	 * @return Map<组，用户>
	 * @author Kevice
	 * @time 2012-5-3 下午10:33:39
	 */
	Map<TErbacGroup, Collection<TUserBasic>> getAllGroupUsers(String groupId);
	
	/**
	 * 从组中移除用户
	 * 
	 * @param groupAndUserIdsMap Map<组id，Collection<用户id>>
	 * @author Kevice
	 * @time 2012-4-20 下午11:01:10
	 */
	boolean removeUsersFromGroup(Map<String, Collection<String>> groupAndUserIdsMap);
	
	/**
	 * 添加组用户
	 * 
	 * @param groupId 组id
	 * @param userIds 用户id集合
	 * @return
	 * @author Kevice
	 * @time 2012-5-28 下午11:49:56
	 */
	boolean addUsers(String groupId, Collection<String> userIds);

}

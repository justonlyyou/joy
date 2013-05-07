package com.kvc.joy.plugin.security.erbac.biz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.commons.collections.CollectionTool;
import com.kvc.joy.commons.support.ListToTreeConvertor;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.plugin.security.erbac.biz.IErbacGroupBiz;
import com.kvc.joy.plugin.security.erbac.biz.IErbacUserBiz;
import com.kvc.joy.plugin.security.erbac.dao.IErbacGroupDao;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup_;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacUser;

/**
 * 组服务实现类
 * 
 * @author 唐玮琳
 * @time 2012-5-5 下午11:07:10
 */
@Transactional
public class ErbacGroupBiz implements IErbacGroupBiz {

	private IErbacGroupDao iErbacGroupDao;
	private IErbacUserBiz erbacUserBiz;
	private Logger logger = LoggerFactory.getLogger(getClass());

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TreeNode<TErbacGroup>> getGroupTree() {
		List<TErbacGroup> groupList = iErbacGroupDao.findAll();
		List treeNode = ListToTreeConvertor.convert(groupList);
		return treeNode;
	}

	public Collection<TErbacUser> getGroupUsers(String groupId) {
		if (groupId != null) {
			TErbacGroup group = iErbacGroupDao.findOne(groupId);
			if (group != null) {
				return group.getUsers();
			} else {
				logger.warn("组#" + groupId + "不存在！");
			}
		}
		return new ArrayList<TErbacUser>(0);
	}

	public boolean removeUsersFromGroup(Map<String, Collection<String>> groupAndUserIdsMap) {
		Set<Entry<String, Collection<String>>> entrySet = groupAndUserIdsMap.entrySet();
		for (Entry<String, Collection<String>> entry : entrySet) {
			String groupId = entry.getKey();
			TErbacGroup group = iErbacGroupDao.findOne(groupId);
			Collection<TErbacUser> users = group.getUsers();
			Map<String, TErbacUser> userMap = new HashMap<String, TErbacUser>(users.size());
			for (TErbacUser user : users) {
				userMap.put(user.getId(), user);
			}

			Collection<String> userIds = entry.getValue();
			for (String userId : userIds) {
				users.remove(userMap.get(userId));
			}
			iErbacGroupDao.save(group);
		}
		return true;
	}

	public Map<TErbacGroup, Collection<TErbacUser>> getAllGroupUsers(String groupId) {
		List<TErbacGroup> groupList = iErbacGroupDao.search(TErbacGroup_.parentId, groupId);
		Map<String, TErbacGroup> groupMap = CollectionTool.toEntityMap(groupList);
		Set<String> groudIds = new HashSet<String>(groupMap.keySet());
		groudIds.add(groupId);
		return erbacUserBiz.getUsersByGroupIds(groudIds);
	}

	public boolean addUsers(String groupId, Collection<String> userIds) {
		TErbacGroup group = iErbacGroupDao.findOne(groupId);
		if (group != null) {
			List<TErbacUser> users = JpaUtils.inSearch(TErbacUser.class, userIds);
			group.getUsers().addAll(users);
			JpaUtils.persist(group);
		}
		return true;
	}

	public void setiErbacGroupDao(IErbacGroupDao iErbacGroupDao) {
		this.iErbacGroupDao = iErbacGroupDao;
	}
	
	public void setErbacUserService(IErbacUserBiz erbacUserBiz) {
		this.erbacUserBiz = erbacUserBiz;
	}

}

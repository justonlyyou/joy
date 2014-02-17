package com.kvc.joy.plugin.security.erbac.biz.impl;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.commons.collections.CollectionTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.support.ListToTreeConvertor;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.plugin.security.erbac.biz.IErbacGroupBiz;
import com.kvc.joy.plugin.security.erbac.biz.IErbacUserBiz;
import com.kvc.joy.plugin.security.erbac.dao.IErbacGroupDao;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup;
import com.kvc.joy.plugin.security.erbac.model.po.TErbacGroup_;
import com.kvc.joy.plugin.security.user.model.po.TUserBasic;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.Map.Entry;

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
	protected static final Log logger = LogFactory.getLog(ErbacGroupBiz.class);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TreeNode<TErbacGroup>> getGroupTree() {
		List<TErbacGroup> groupList = iErbacGroupDao.findAll();
		List treeNode = ListToTreeConvertor.convert(groupList);
		return treeNode;
	}

	public Collection<TUserBasic> getGroupUsers(String groupId) {
		if (groupId != null) {
			TErbacGroup group = iErbacGroupDao.findOne(groupId);
			if (group != null) {
				return group.getUsers();
			} else {
				logger.warn("组#" + groupId + "不存在！");
			}
		}
		return new ArrayList<TUserBasic>(0);
	}

	public boolean removeUsersFromGroup(Map<String, Collection<String>> groupAndUserIdsMap) {
		Set<Entry<String, Collection<String>>> entrySet = groupAndUserIdsMap.entrySet();
		for (Entry<String, Collection<String>> entry : entrySet) {
			String groupId = entry.getKey();
			TErbacGroup group = iErbacGroupDao.findOne(groupId);
			Collection<TUserBasic> users = group.getUsers();
			Map<String, TUserBasic> userMap = new HashMap<String, TUserBasic>(users.size());
			for (TUserBasic user : users) {
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

	public Map<TErbacGroup, Collection<TUserBasic>> getAllGroupUsers(String groupId) {
		List<TErbacGroup> groupList = iErbacGroupDao.search(TErbacGroup_.parentId, groupId);
		Map<String, TErbacGroup> groupMap = CollectionTool.toEntityMap(groupList);
		Set<String> groudIds = new HashSet<String>(groupMap.keySet());
		groudIds.add(groupId);
		return erbacUserBiz.getUsersByGroupIds(groudIds);
	}

	public boolean addUsers(String groupId, Collection<String> userIds) {
		TErbacGroup group = iErbacGroupDao.findOne(groupId);
		if (group != null) {
			List<TUserBasic> users = JpaTool.inSearch(TUserBasic.class, userIds);
			group.getUsers().addAll(users);
			JpaTool.persist(group);
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

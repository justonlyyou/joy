package com.kvc.joy.core.sysres.menu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.commons.collections.ListTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.support.ListToTreeConvertor;
import com.kvc.joy.core.persistence.orm.jpa.JpaOrder;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.sysres.menu.model.po.TSysMenu;
import com.kvc.joy.core.sysres.menu.model.po.TSysMenu_;
import com.kvc.joy.core.sysres.menu.service.ISysMenuService;

public class SysMenuService implements ISysMenuService {

	protected static final Log logger = LogFactory.getLog(SysMenuService.class);

	@Override
	public List<TreeNode<TSysMenu>> getAllMenus(String userId) {
		List<TSysMenu> allMenuList = JpaTool.search(TSysMenu.class, TSysMenu_.deleted, false,
				JpaOrder.asc(TSysMenu_.orderNum));
		return processMenus(allMenuList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<TreeNode<TSysMenu>> processMenus(List<TSysMenu> menuList) {
		// TODO 根据权限过滤菜单

		List list = ListToTreeConvertor.convert(menuList);
		return list;
	}

	@Override
	public List<TreeNode<TSysMenu>> getRootMenus(String userId) {
		List<TSysMenu> allMenuList = JpaTool.search(TSysMenu.class, TSysMenu_.deleted, false,
				JpaOrder.asc(TSysMenu_.orderNum));
		List<TSysMenu> rootMenuList = new ArrayList<TSysMenu>();
		for (TSysMenu sysMenu : allMenuList) {
			if (StringUtils.isBlank(sysMenu.getParentId())) {
				rootMenuList.add(sysMenu);
			}
		}
		return processMenus(rootMenuList);
	}

	@Override
	public List<TreeNode<TSysMenu>> getSubMenus(String userId, String parentId) {
		List<TreeNode<TSysMenu>> allMenus = getAllMenus(userId);
		for (TreeNode<TSysMenu> treeNode : allMenus) {
			List<TreeNode<TSysMenu>> subMenus = findSubMenus(treeNode, parentId);
			if (subMenus != null) {
				return subMenus;
			}
		}
		return new ArrayList<TreeNode<TSysMenu>>(0);
	}

	//TODO 公用
	private List<TreeNode<TSysMenu>> findSubMenus(TreeNode<TSysMenu> node, String parentId) {
		if (parentId.equals(node.getObject().getId())) {
			return node.getChildren();
		} else {
			List<TreeNode<TSysMenu>> children = node.getChildren();
			for (TreeNode<TSysMenu> treeNode : children) {
				findSubMenus(treeNode, parentId);
			}
		}
		return null;
	}

	@Override
	public List<TSysMenu> getMenuPath(String menuId) {
		List<TSysMenu> paths = new ArrayList<TSysMenu>(4);
		findMenuPath(null, paths, menuId);
		return ListTool.reverse(paths);
	}
	
	//TODO 公用
	private void findMenuPath(List<TSysMenu> allMenuList, List<TSysMenu> paths, String menuId) {
		TSysMenu sysMenu = JpaTool.get(TSysMenu.class, menuId);
		paths.add(sysMenu);
		String parentId = sysMenu.getParentId();
		if (StringTool.isNotBlank(parentId)) {
			findMenuPath(allMenuList, paths, parentId);
		}
	}

}

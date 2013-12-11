package com.kvc.joy.core.sysres.menu.service;

import java.util.List;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.core.sysres.menu.model.po.TSysMenu;


public interface ISysMenuService {
	
	/**
	 * 根据用户的ID，取得其能看到的菜单，并按orderNum从小到大排序
	 * @param userId 用户ID
	 * @return 树结点列表
	 */
	List<TreeNode<TSysMenu>> getAllMenus(String userId);
	
	/**
	 * 
	 * 
	 * @param userId
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月5日 下午5:25:18
	 */
	List<TreeNode<TSysMenu>> getRootMenus(String userId);
	
	/**
	 * 
	 * 
	 * @param userId
	 * @param parentId
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月5日 下午5:25:18
	 */
	List<TreeNode<TSysMenu>> getSubMenus(String userId, String parentId);
	
	/**
	 * 获取指定菜单的完整路径
	 * 
	 * @param menuId 菜单
	 * @return List<菜单对象>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月14日 下午8:43:25
	 */
	List<TSysMenu> getMenuPath(String menuId);

}

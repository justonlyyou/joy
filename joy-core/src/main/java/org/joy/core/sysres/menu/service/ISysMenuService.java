package org.joy.core.sysres.menu.service;

import org.joy.commons.tree.TreeNode;
import org.joy.core.sysres.menu.model.po.TSysMenu;

import java.util.List;

/**
 * 系统菜单服务接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月5日 下午5:25:18
 */
public interface ISysMenuService {
	
	/**
	 * 根据用户的ID，取得其能看到的菜单，并按orderNum从小到大排序
	 * @param userId 用户ID
	 * @return List<TreeNode<系统菜单实体>>
     * @since 1.0.0
     * @author Kevice
     * @time 2013年10月5日 下午5:25:18
	 */
	List<TreeNode<TSysMenu>> getAllMenus(String userId);
	
	/**
	 * 返回用户能查看的菜单的根
	 * 
	 * @param userId 用户id
	 * @return List<TreeNode<系统菜单实体>>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午5:25:18
	 */
	List<TreeNode<TSysMenu>> getRootMenus(String userId);
	
	/**
	 * 返回用户能查看的子菜单
	 * 
	 * @param userId 用户id
	 * @param parentId 父菜单id
	 * @return List<TreeNode<系统菜单实体>>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午5:25:18
	 */
	List<TreeNode<TSysMenu>> getSubMenus(String userId, String parentId);
	
	/**
	 * 获取指定菜单的完整路径
	 * 
	 * @param menuId 菜单
	 * @return List<系统菜单实体>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月14日 下午8:43:25
	 */
	List<TSysMenu> getMenuPath(String menuId);

}

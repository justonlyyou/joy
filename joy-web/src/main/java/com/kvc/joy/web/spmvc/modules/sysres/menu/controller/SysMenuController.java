package com.kvc.joy.web.spmvc.modules.sysres.menu.controller;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.commons.data.json.JsonTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.sysres.menu.model.po.TSysMenu;
import com.kvc.joy.plugin.security.erbac.support.utils.UserTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月5日 下午4:10:18
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

	/**
	 * 获取当前用户能查看的所有系统菜单
	 * 
	 * @return json串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午4:31:05
	 */
	@RequestMapping("/fetchAllMenus")
	@ResponseBody
	public String fetchAllMenus() {
		String userId = UserTool.getCurrentUser().getId();
		List<TreeNode<TSysMenu>> menuNodeList = CoreBeanFactory.getSysMenuService().getAllMenus(userId);
		return JsonTool.toJson(menuNodeList);
	}
	
	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午5:12:59
	 */
	@RequestMapping("/fetchRootMenus")
	@ResponseBody
	public String fetchRootMenus() {
		String userId = UserTool.getCurrentUser().getId();
		List<TreeNode<TSysMenu>> menuNodeList = CoreBeanFactory.getSysMenuService().getRootMenus(userId);
		return JsonTool.toJson(menuNodeList);
	}
	
	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月5日 下午5:12:59
	 */
	@RequestMapping("/fetchSubMenus")
	@ResponseBody
	public String fetchSubMenus(@RequestParam("parentId") String parentId) {
		String userId = UserTool.getCurrentUser().getId();
		List<TreeNode<TSysMenu>> menuNodeList = CoreBeanFactory.getSysMenuService().getSubMenus(userId, parentId);
		return JsonTool.toJson(menuNodeList);
	}
	
}

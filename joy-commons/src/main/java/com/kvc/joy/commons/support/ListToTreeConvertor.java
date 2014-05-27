package com.kvc.joy.commons.support;

import com.kvc.joy.commons.bean.TreeNode;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 列表到树结构的转换器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2012-5-5 下午9:00:33
 */
public class ListToTreeConvertor {
	
	protected static final Log logger = LogFactory.getLog(ListToTreeConvertor.class);
	
	private ListToTreeConvertor() {
	}
	
	/**
	 * 将列表结构转为树结构
	 * 
	 * @param objectList 结点对象列表
	 * @return List<树根结点>
	 * @author Kevice
	 * @time 2012-5-5 下午9:00:33
	 */
	public static <T> List<TreeNode<IListToTreeRestrict<T>>> convert(List<? extends IListToTreeRestrict<T>> objectList) {
		Map<T, TreeNode<IListToTreeRestrict<T>>> treeNodeMap = new HashMap<T, TreeNode<IListToTreeRestrict<T>>>(objectList.size());
		for (IListToTreeRestrict<T> object : objectList) {
			treeNodeMap.put(object.getId(),  new TreeNode<IListToTreeRestrict<T>>(object));
		}

		List<TreeNode<IListToTreeRestrict<T>>> treeNodeList = new ArrayList<TreeNode<IListToTreeRestrict<T>>>();
		for (IListToTreeRestrict<T> group : objectList) {
			TreeNode<IListToTreeRestrict<T>> node = treeNodeMap.get(group.getId());
			T pId = group.getParentId();
			if (pId == null || "".equals(pId)) { // 根
				treeNodeList.add(node);
			} else {
				TreeNode<IListToTreeRestrict<T>> pNode = treeNodeMap.get(pId);
				if (pNode != null) { // 存在父结点
					node.setParentObject(pNode.getObject());
					pNode.getChildren().add(node);
				} else {
					logger.error("结点#" + node.getObject().getId() + "的父结点#" + pId + "不存在！");
				}
			}
		}
		return treeNodeList;
	}

}

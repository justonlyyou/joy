/**
 * 
 */
package org.joy.web.support.extjs;

/**
 * 
 * @author Kevice
 * @time 2012-5-5 下午10:13:53
 */
public interface IExtTreeNodeAdapter<T> {

	void adapt(ExtTreeNode treeNode, T object);
	
}

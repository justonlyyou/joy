package org.joy.core.persistence.support.entity;

import org.joy.commons.bean.IEntity;

/**
 * 支持C(创建)、R(读取)、U(更新)、D(删除)操作的实体接口
 * 
 * @author Kevice
 * @time 2012-12-15 上午12:19:51
 * @since 1.0.0
 */
public interface ICrudEntity<T> extends IEntity<T> {

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:26:35
     * @since 1.0.0
	 */
	String getCreateTime();

	/**
	 * 设置创建时间
	 * 
	 * @param createTime 创建时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:27:24
     * @since 1.0.0
	 */
	void setCreateTime(String createTime);

	/**
	 * 获取创建用户的ID
	 * 
	 * @return 创建用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:03
     * @since 1.0.0
	 */
	String getCreateUser();

	/**
	 * 设置创建用户的ID
	 * 
	 * @param createUser 创建用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:28
     * @since 1.0.0
	 */
	void setCreateUser(String createUser);

	/**
	 * 获取创建用户所属的部门/机构ID
	 * 
	 * @return 创建用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:10
     * @since 1.0.0
	 */
	String getCreateDept();

	/**
	 * 设置创建用户所属的部门/机构ID
	 * 
	 * @param createDept 创建用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:44
     * @since 1.0.0
	 */
	void setCreateDept(String createDept);

	/**
	 * 获取更新时间
	 * 
	 * @return 更新时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:26:35
     * @since 1.0.0
	 */
	String getUpdateTime();

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime 更新时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:27:24
     * @since 1.0.0
	 */
	void setUpdateTime(String updateTime);

	/**
	 * 获取更新用户的ID
	 * 
	 * @return 更新用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:03
     * @since 1.0.0
	 */
	String getUpdateUser();

	/**
	 * 设置更新用户的ID
	 * 
	 * @param updateUser 更新用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:28
     * @since 1.0.0
	 */
	void setUpdateUser(String updateUser);

	/**
	 * 获取更新用户所属的部门/机构ID
	 * 
	 * @return 更新用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:10
     * @since 1.0.0
	 */
	String getUpdateDept();

	/**
	 * 设置更新用户所属的部门/机构ID
	 * 
	 * @param updateDept 更新用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:44
     * @since 1.0.0
	 */
	void setUpdateDept(String updateDept);

	/**
	 * 获取删除时间
	 * 
	 * @return 删除时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:26:35
     * @since 1.0.0
	 */
	String getDeleteTime();

	/**
	 * 设置删除时间
	 * 
	 * @param deleteTime 删除时间
	 * @author Kevice
	 * @time 2012-12-15 上午12:27:24
     * @since 1.0.0
	 */
	void setDeleteTime(String deleteTime);

	/**
	 * 获取删除用户的ID
	 * 
	 * @return 删除用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:03
     * @since 1.0.0
	 */
	String getDeleteUser();

	/**
	 * 设置删除用户的ID
	 * 
	 * @param deleteUser 删除用户的ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:28:28
     * @since 1.0.0
	 */
	void setDeleteUser(String deleteUser);

	/**
	 * 获取删除用户所属的部门/机构ID
	 * 
	 * @return 删除用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:10
     * @since 1.0.0
	 */
	String getDeleteDept();

	/**
	 * 设置删除用户所属的部门/机构ID
	 * 
	 * @param deleteDept 删除用户所属的部门/机构ID
	 * @author Kevice
	 * @time 2012-12-15 上午12:31:44
     * @since 1.0.0
	 */
	void setDeleteDept(String deleteDept);

	/**
	 * 是否已被删除
	 * 
	 * @return 是否已被删除
	 * @author Kevice
	 * @time 2012-12-15 上午12:21:14
     * @since 1.0.0
	 */
	String getDeleted();
	
	/**
	 * 是否已被删除
	 * 
	 * @return 是否已被删除
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月16日 下午8:15:01
     * @since 1.0.0
	 */
	boolean deleted();

	/**
	 * 设置是否已被删除
	 * 
	 * @param delete 是否已被删除
	 * @author Kevice
	 * @time 2012-12-15 上午12:21:21
     * @since 1.0.0
	 */
	void setDeleted(String delete);
	
	/**
	 * 是否启用
	 * 
	 * @return 是否启用
	 * @author Kevice
	 * @time 2013-2-22 下午10:35:03
     * @since 1.0.0
	 */
	String getActive();
	
	/**
	 * 是否启用
	 * 
	 * @return 是否启用
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月16日 下午8:14:50
     * @since 1.0.0
	 */
	boolean active();
	
	/**
	 * 设置启用状态
	 * 
	 * @param active 启用状态
	 * @author Kevice
	 * @time 2013-2-22 下午10:35:14
     * @since 1.0.0
	 */
	void setActive(String active);

	/**
	 * 是否为系统内置
	 * 
	 * @return 是否为系统内置
	 * @author Kevice
	 * @time 2012-12-17 下午11:04:03
     * @since 1.0.0
	 */
	String getBuiltIn();
	
	/**
	 * 是否为系统内置
	 * 
	 * @return 是否为系统内置
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月16日 下午8:14:30
     * @since 1.0.0
	 */
	boolean builtIn();

	/**
	 * 设置是否为系统内置
	 * 
	 * @param builtIn 是否为系统内置
	 * @author Kevice
	 * @time 2012-12-17 下午11:04:34
     * @since 1.0.0
	 */
	void setBuiltIn(String builtIn);

}

package com.kvc.joy.core.persistence.entity;

import com.kvc.joy.commons.bean.IEntity;

/**
 * 支持C(创建)、R(读取)、U(更新)、D(删除)操作的实体接口
 * 
 * @author 唐玮琳
 * @time 2012-12-15 上午12:19:51
 */
public interface ICrudEntity<T> extends IEntity<T> {

	/**
	 * 获取创建时间
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:26:35
	 */
	String getCreateTime();

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:27:24
	 */
	void setCreateTime(String createTime);

	/**
	 * 获取创建用户的ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:03
	 */
	String getCreateUser();

	/**
	 * 设置创建用户的ID
	 * 
	 * @param createUser
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:28
	 */
	void setCreateUser(String createUser);

	/**
	 * 获取创建用户所属的部门/机构ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:10
	 */
	String getCreateDept();

	/**
	 * 设置创建用户所属的部门/机构ID
	 * 
	 * @param createDept
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:44
	 */
	void setCreateDept(String createDept);

	/**
	 * 获取更新时间
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:26:35
	 */
	String getUpdateTime();

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:27:24
	 */
	void setUpdateTime(String updateTime);

	/**
	 * 获取更新用户的ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:03
	 */
	String getUpdateUser();

	/**
	 * 设置更新用户的ID
	 * 
	 * @param updateUser
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:28
	 */
	void setUpdateUser(String updateUser);

	/**
	 * 获取更新用户所属的部门/机构ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:10
	 */
	String getUpdateDept();

	/**
	 * 设置更新用户所属的部门/机构ID
	 * 
	 * @param updateDept
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:44
	 */
	void setUpdateDept(String updateDept);

	/**
	 * 获取删除时间
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:26:35
	 */
	String getDeleteTime();

	/**
	 * 设置删除时间
	 * 
	 * @param deleteTime
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:27:24
	 */
	void setDeleteTime(String deleteTime);

	/**
	 * 获取删除用户的ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:03
	 */
	String getDeleteUser();

	/**
	 * 设置删除用户的ID
	 * 
	 * @param deleteUser
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:28:28
	 */
	void setDeleteUser(String deleteUser);

	/**
	 * 获取删除用户所属的部门/机构ID
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:10
	 */
	String getDeleteDept();

	/**
	 * 设置删除用户所属的部门/机构ID
	 * 
	 * @param deleteDept
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:31:44
	 */
	void setDeleteDept(String deleteDept);

	/**
	 * 是否已被删除
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:21:14
	 */
	boolean isDeleted();

	/**
	 * 设置是否已被删除
	 * 
	 * @param delete
	 * @author 唐玮琳
	 * @time 2012-12-15 上午12:21:21
	 */
	void setDeleted(boolean delete);
	
	/**
	 * 是否启用
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-22 下午10:35:03
	 */
	boolean isActive();
	
	/**
	 * 设置启用状态
	 * 
	 * @param active
	 * @author 唐玮琳
	 * @time 2013-2-22 下午10:35:14
	 */
	void setActive(boolean active);

	/**
	 * 是否为系统内置
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-12-17 下午11:04:03
	 */
	boolean isBuiltIn();

	/**
	 * 设置是否为系统内置
	 * 
	 * @param builtIn
	 * @author 唐玮琳
	 * @time 2012-12-17 下午11:04:34
	 */
	void setBuiltIn(boolean builtIn);

}

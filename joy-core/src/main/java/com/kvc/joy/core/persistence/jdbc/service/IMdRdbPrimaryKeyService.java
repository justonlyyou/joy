package com.kvc.joy.core.persistence.jdbc.service;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:23:22
 */
public interface IMdRdbPrimaryKeyService {

	/**
	 * 
	 * 
	 * @param dsId
	 * @param tableName
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:24:38
	 */
	MdRdbPrimaryKey getPrimaryKey(String dsId, String tableName);
	
	/**
	 * 
	 * 
	 * @param jndi
	 * @param tableName
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月11日 上午12:27:47
	 */
//	MdRdbPrimaryKey getPrimaryKeyByJndi(String jndi, String tableName);
	
	
}

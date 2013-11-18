package com.kvc.joy.core.persistence.jdbc.service;

import java.util.Map;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;


/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:22:12
 */
public interface IMdRdbColumnService {

	/**
	 * 
	 * @param jndi
	 * @param dsId
	 * @param tableName
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:25:26
	 */
	Map<String, MdRdbColumn> getColumns(String dsId, String tableName);
	
	/**
	 * 
	 * 
	 * @param jndi
	 * @param tableName
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月11日 上午12:15:05
	 */
//	Map<String, MdRdbColumn> getColumnsByJndi(String jndi, String tableName);

}

package com.kvc.joy.core.persistence.jdbc.service;

import java.util.Map;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:36:36
 */
public interface IMdRdbTableService {
	
	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:43:46
	 */
	Map<String, MdRdbTable> getAllTables();
	
	/**
	 * 
	 * 
	 * @param key "datasourceId-tableName"
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-14 下午8:56:13
	 */
	MdRdbTable getTableByDatasourceId(String key);
	
	/**
	 * 
	 * 
	 * @param key "jndi-tableName"
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月11日 上午12:38:01
	 */
	MdRdbTable getTableByJndi(String key);

}

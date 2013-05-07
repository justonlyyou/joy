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
	Map<String, MdRdbTable> get();
	
	/**
	 * 
	 * 
	 * @param key "datasourceId-tableName"
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-14 下午8:56:13
	 */
	MdRdbTable get(String key);

}

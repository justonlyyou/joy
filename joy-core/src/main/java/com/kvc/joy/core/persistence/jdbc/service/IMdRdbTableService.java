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
	 * @param dsId
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月15日 下午11:10:58
	 */
	Map<String, MdRdbTable> getTables(String dsId);
	
	/**
	 * 
	 * 
	 * @param jndi
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月15日 下午11:11:02
	 */
//	Map<String, MdRdbTable> getTablesByJndi(String jndi);
	
}

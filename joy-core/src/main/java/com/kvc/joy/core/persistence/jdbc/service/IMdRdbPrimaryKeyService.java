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
	 * @param datasourceId
	 * @param tableName
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:24:38
	 */
	MdRdbPrimaryKey getPrimaryKey(String datasourceId, String tableName);
	
}

package com.kvc.joy.core.persistence.jdbc.service;

import java.util.List;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;


/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:22:12
 */
public interface IMdRdbColumnService {

	/**
	 * 
	 * 
	 * @param datasourceId
	 * @param tableName
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:25:26
	 */
	List<MdRdbColumn> getColumns(String datasourceId, String tableName);

}

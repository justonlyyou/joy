package com.kvc.joy.core.persistence.jdbc.service;

import java.io.Serializable;
import java.util.Map;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;


/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:22:12
 */
public interface IMdRdbColumnService extends Serializable {

	/**
	 * 
	 * @param connection
	 * @param tableName
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-3 下午9:25:26
	 */
	Map<String, MdRdbColumn> getColumns(RdbConnection connection, String tableName);
	
}

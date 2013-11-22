package com.kvc.joy.core.persistence.jdbc.service;

import java.util.Map;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:36:36
 */
public interface IMdRdbTableService {
	
	/**
	 * 
	 * 
	 * @param conntion
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月15日 下午11:10:58
	 */
	Map<String, MdRdbTable> getTables(RdbConnection conntion);
	
}

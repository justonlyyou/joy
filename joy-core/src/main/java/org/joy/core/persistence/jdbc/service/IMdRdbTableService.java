package org.joy.core.persistence.jdbc.service;

import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-2-3 下午9:36:36
 */
public interface IMdRdbTableService extends Serializable {
	
	/**
	 * 
	 * 
	 * @param conntion
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月15日 下午11:10:58
	 */
	Map<String, MdRdbTable> getTables(RdbConnection conntion);
	
}

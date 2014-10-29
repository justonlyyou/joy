package org.joy.core.persistence.jdbc.service;

import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;

import java.io.Serializable;
import java.util.Map;

/**
 * 关系型数据库表元数据信息服务接口
 * 
 * @author Kevice
 * @time 2013-2-3 下午9:36:36
 * @since 1.0.0
 */
public interface IMdRdbTableService extends Serializable {
	
	/**
	 * 根据指定的连接，获取对应数据库下所有表的元数据信息，<br>
     * 注意：不包括表中列的信息
	 * 
	 * @param conntion 关系型数据库连接
	 * @return Map<表名, 表元数据信息对象></表名,>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月15日 下午11:10:58
	 */
	Map<String, MdRdbTable> getTables(RdbConnection conntion);
	
}

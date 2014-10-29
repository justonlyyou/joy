package org.joy.core.persistence.jdbc.service;

import org.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;

import java.io.Serializable;

/**
 * 关系型数据库主键元数据信息服务接口
 * 
 * @author Kevice
 * @time 2013-2-3 下午9:23:22
 * @since 1.0.0
 */
public interface IMdRdbPrimaryKeyService extends Serializable {

	/**
	 * 根据指定的连接和表名，获取对应表的主键元数据信息
	 * 
	 * @param connection 关系型数据库连接
	 * @param tableName 表名
	 * @return 主键元数据信息对象
	 * @author Kevice
	 * @time 2013-2-3 下午9:24:38
     * @since 1.0.0
	 */
	MdRdbPrimaryKey getPrimaryKey(RdbConnection connection, String tableName);
	
}

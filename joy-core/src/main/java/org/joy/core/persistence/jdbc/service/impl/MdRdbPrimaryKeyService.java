package org.joy.core.persistence.jdbc.service.impl;

import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;
import org.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import org.joy.core.persistence.jdbc.support.db.DbSupport;
import org.joy.core.persistence.jdbc.support.db.DbSupportFactory;
import org.joy.core.persistence.jdbc.support.utils.MdRdbTool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 关系型数据库主键元数据信息服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-1-2 下午11:50:15
 */
public class MdRdbPrimaryKeyService implements IMdRdbPrimaryKeyService {

	protected static final Log logger = LogFactory.getLog(MdRdbPrimaryKeyService.class);

	@Override
	public MdRdbPrimaryKey getPrimaryKey(RdbConnection connection, String tableName) {
		logger.info("加载表字段主键的元数据信息，datasourceId: " + connection.getDsId() + ", tableName: " + tableName);
		MdRdbPrimaryKey primaryKey = null;
		Connection conn = connection.getConnection();
		try {
            DbSupport dbSupport = DbSupportFactory.createDbSupport(conn);
            DatabaseMetaData metaData = conn.getMetaData();
			List<String> pks = getPrimaryKey(dbSupport, metaData, tableName);
			for (String pk : pks) {
				if (primaryKey == null) {
					primaryKey = new MdRdbPrimaryKey();
				}
				MdRdbColumn column = MdRdbTool.getColumn(connection, tableName, pk);
				primaryKey.getColumns().add(column);
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return primaryKey;
	}

	/**
	 * 获取指定数据源的某张表的主键信息
	 * 
	 * @param metaData
	 * @param tableName 表名
	 * @return 主键字段名数组
	 * @author Kevice
	 * @throws SQLException
	 * @time 2012-11-9 上午10:22:21
	 */
	static List<String> getPrimaryKey(DbSupport dbSupport, DatabaseMetaData metaData, String tableName) throws SQLException {
		List<String> pks = new ArrayList<String>(1);
        String schema = dbSupport.getCurrentSchema().getName();
        String[] tables = {tableName.toLowerCase(), tableName.toUpperCase(), tableName};
        for(String t : tables) {
            ResultSet primaryKeys = metaData.getPrimaryKeys(null, schema, t);
            while (primaryKeys.next()) {
                pks.add(primaryKeys.getString("COLUMN_NAME"));
            }
            if (!pks.isEmpty()) {
                break;
            }
        }
		return pks;
	}

}

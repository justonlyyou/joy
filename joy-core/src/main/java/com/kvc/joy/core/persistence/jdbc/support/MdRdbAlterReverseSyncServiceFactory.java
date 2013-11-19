/**
 * 
 */
package com.kvc.joy.core.persistence.jdbc.support;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.persistence.jdbc.model.vo.DbMetaData;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbAlterReverseSyncService;
import com.kvc.joy.core.persistence.jdbc.support.enums.RdbType;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-11-8 下午9:45:41
 */
public class MdRdbAlterReverseSyncServiceFactory {

	protected static final Log logger = LogFactory.getLog(MdRdbAlterReverseSyncServiceFactory.class);

	private MdRdbAlterReverseSyncServiceFactory() {
	}

	public static IMdRdbAlterReverseSyncService getInstance() {
		DbMetaData dbMetaData = JdbcTool.getDbMetaData(JoyPropeties.DB_DATASOURCEID);
		RdbType databaseType = dbMetaData.getDatabaseType();
		IMdRdbAlterReverseSyncService generator = null;
		switch (databaseType) {
		case MYSQL:
			generator = CoreBeanFactory.getMySqlAlterReverseSyncService();
			break;
		case ORACLE:
			generator = CoreBeanFactory.getOracleAlterReverseSyncService();
			break;
		default:
			logger.error("不支持的数据库类型： " + databaseType);
			break;
		}
		return generator;
	}

}

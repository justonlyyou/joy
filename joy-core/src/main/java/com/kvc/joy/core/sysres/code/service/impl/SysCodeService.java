package com.kvc.joy.core.sysres.code.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.sysres.code.po.TSysCodeTable;
import com.kvc.joy.core.sysres.code.service.ISysCodeService;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-8 下午2:15:46
 */
public class SysCodeService implements ISysCodeService {

	protected static final Log logger = LogFactory.getLog(SysCodeService.class);

	public Map<String, String> get(String codeTableId) {
		logger.info("加载代码表#" + codeTableId + "的数据...");
		Map<String, String> map = new HashMap<String, String>();
		TSysCodeTable codeDic = JpaTool.get(TSysCodeTable.class, codeTableId);
		TSysDataSrc dataSrc = codeDic.getDataSrc();

		String sqlPattern = "select {0}, {1} from {2}";
		String codeFieldName = codeDic.getCodeField();
		String transFieldName = codeDic.getTransField();
		String sql = MessageFormat.format(sqlPattern, codeFieldName, transFieldName, codeDic.getTableName());
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = JdbcTool.getConnection(dataSrc);
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String code = rs.getString(codeFieldName);
				String trans = rs.getString(transFieldName);
				map.put(code, trans);
			}
			logger.info("加载代码表#{0}数据共{1}条。", codeTableId, map.size());
		} catch (SQLException e) {
			logger.error(e, "代码表#{0}数据加载失败！", codeTableId);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					logger.error(ex.getMessage(), ex);
				}
			}
			JdbcTool.closeConnection(conn);
		}
		return map;
	}
}

package com.kvc.joy.core.sysres.code.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.jdbc.support.utils.JdbcTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable;
import com.kvc.joy.core.sysres.code.model.vo.CodeRecord;
import com.kvc.joy.core.sysres.code.service.ISysCodeService;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-8 下午2:15:46
 */
public class SysCodeService implements ISysCodeService {

	protected static final Log logger = LogFactory.getLog(SysCodeService.class);

	public Map<String, CodeRecord> get(String codeTableId) {
		logger.info("加载代码表#" + codeTableId + "的数据...");
		Map<String, CodeRecord> map = new LinkedHashMap<String, CodeRecord>();
		TSysCodeTable codeDic = JpaTool.get(TSysCodeTable.class, codeTableId);
		TSysDataSrc dataSrc = codeDic.getDataSrc();
		
		String sqlPattern = "SELECT * FROM {0} ORDER BY {1} DESC";
		String codeFieldName = codeDic.getCodeField();
		String transFieldName = codeDic.getTransField();
		String parentCodeField = codeDic.getParentField();
		String orderByField = StringTool.isBlank(codeDic.getOrderField()) ? codeFieldName : codeDic.getOrderField();
		String sql = MessageFormat.format(sqlPattern, codeDic.getTableName(), orderByField);
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = JdbcTool.getConnection(dataSrc);
			statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String code = rs.getString(codeFieldName);
				String trans = rs.getString(transFieldName);
				String parentCode = StringTool.isBlank(parentCodeField) ? null : rs.getString(parentCodeField);
				String order = rs.getString(orderByField);
				CodeRecord codeRecord = new CodeRecord(code, trans, parentCode, order);
				map.put(code, codeRecord);
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

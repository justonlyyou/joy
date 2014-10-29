package org.joy.core.sysres.code.service.impl;

import org.joy.commons.collections.CollectionQueryTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.query.sort.Order;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;
import org.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.sysres.code.dao.TSysCodeTableDao;
import org.joy.core.sysres.code.model.po.TSysCodeTable;
import org.joy.core.sysres.code.model.vo.CodeRecord;
import org.joy.core.sysres.code.service.ISysCodeService;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.*;

/**
 * 系统代码字典服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-8 下午2:15:46
 */
public class SysCodeService implements ISysCodeService {

	protected TSysCodeTableDao sysCodeTableDao;
	protected static final Log logger = LogFactory.getLog(SysCodeService.class);

	public Map<String, CodeRecord> get(String codeTableId) {
		logger.info("加载代码表#" + codeTableId + "的数据...");
		TSysCodeTable codeDic = JpaTool.get(TSysCodeTable.class, codeTableId);
		List<CodeRecord> codes = null;
		if (codeDic == null) {
			List<TSysCodeTable> groupingCodeTables = sysCodeTableDao.getGroupingCodeTables();
			for (TSysCodeTable codeTable : groupingCodeTables) {
				codes = searchCodes(codeTableId, codeTable);
				if (codes.isEmpty() == false) {
					break;
				}
			}
			
			// 存在排序值为空默认按编码升序排序
			if (codes != null && codes.isEmpty() == false) {
				Map<String, Object> propMap = new HashMap<String, Object>(2);
				propMap.put("order", null);
				propMap.put("order", "");
				List<CodeRecord> list = CollectionQueryTool.orQuery(codes, propMap);
				if (list.isEmpty() == false) {
					codes = CollectionQueryTool.order(codes, Order.asc("code"));
				}
			}
		} else {
			codes = searchCodes(codeTableId, codeDic);
		}
		Map<String, CodeRecord> map = new LinkedCaseInsensitiveMap<CodeRecord>(codes.size());
		for (CodeRecord codeRecord : codes) {
			map.put(codeRecord.getCode(), codeRecord);
		}
		if (map.isEmpty()) {
			logger.warn("代码表#" + codeTableId + "不存在或数据为空！");	
		}
		return map;
	}
	
	private String getTransFieldName(TSysCodeTable codeDic, Connection conn) {
		Locale locale = Locale.getDefault();
		String language = locale.getLanguage();
		String country = locale.getCountry();
		String transFieldPrefix = codeDic.getTransField();
		String transField = transFieldPrefix + "_" + language + "_" + country;
		String tableName = codeDic.getTableName();
		RdbConnection rdbConn = new RdbConnection(codeDic.getDataSrc().getId(), conn);
		MdRdbColumn column = MdRdbTool.getColumn(rdbConn, tableName, transField);
		if (column == null) {
			transField = transFieldPrefix;
		}
		return transField;
	}
	
	private List<CodeRecord> searchCodes(String codeTableId, TSysCodeTable codeDic) {
		List<CodeRecord> codes = new ArrayList<CodeRecord>();
		Connection conn = null;
		TSysDataSrc dataSrc = codeDic.getDataSrc();
		String sqlPattern = "SELECT * FROM {0} WHERE {1} ORDER BY {2} ASC";
		String codeFieldName = codeDic.getCodeField();
		String parentCodeField = codeDic.getParentField();
		String orderByField = StringTool.isBlank(codeDic.getOrderField()) ? codeFieldName : codeDic.getOrderField();
		String codeCategory = null;
		String groupingCommentField = codeDic.getGroupingCommentField();
		String where = "1=1";
		String activeField = codeDic.getActiveField();
		if (StringTool.isNotBlank(activeField)) {
			where += " AND " + activeField + " = '1'";
		}
		String deletedField = codeDic.getDeletedField();
		if (StringTool.isNotBlank(deletedField)) {
			where += " AND " + deletedField + " = '0'";
		}
		if (codeDic.getId().equals(codeTableId) == false) {
			String groupingField = codeDic.getGroupingField();
			where += " AND " + groupingField + " = '" + codeTableId + "'";
		} else {
			codeCategory = codeDic.getTableComment();
		}
		String sql = MessageFormat.format(sqlPattern, codeDic.getTableName(), where, orderByField);
		PreparedStatement statement = null;
		try {
			conn = JdbcTool.getConnection(dataSrc);
			statement = conn.prepareStatement(sql);
			String transFieldName = getTransFieldName(codeDic, conn);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String code = rs.getString(codeFieldName);
				String trans = rs.getString(transFieldName);
				String parentCode = StringTool.isBlank(parentCodeField) ? null : rs.getString(parentCodeField);
				if(codeCategory == null && StringTool.isNotBlank(groupingCommentField)) {
					codeCategory = rs.getString(groupingCommentField);
				}
				String order = rs.getString(orderByField);
				CodeRecord codeRecord = new CodeRecord(code, trans, parentCode, order, codeCategory);
				codes.add(codeRecord);
			}
			if (codes.isEmpty() == false || codeDic.getId().equals(codeTableId)) {
				logger.info("加载代码表#{0}数据共{1}条。", codeTableId, codes.size());
			}
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
		return codes;
	}
	
	public void setSysCodeTableDao(TSysCodeTableDao sysCodeTableDao) {
		this.sysCodeTableDao = sysCodeTableDao;
	}
	
}

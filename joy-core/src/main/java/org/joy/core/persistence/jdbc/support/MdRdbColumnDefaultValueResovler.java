package org.joy.core.persistence.jdbc.support;

import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.support.db.DbSupport;

/**
 * 关系型数据库列默认值处理器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 下午3:18:51
 */
public class MdRdbColumnDefaultValueResovler {
	
	private MdRdbColumnDefaultValueResovler() {
	}

    /**
     * 获取指定列的默认值，以字符串形式返回
     *
     * @param dbSupport 关系型数据库支持
     * @param column 列元数据信息
     * @return 默认值的字符串形式
     */
	public static String getDefaultValue(DbSupport dbSupport, MdRdbColumn column) {
		String defaultValue = column.getDefaultValue();
		if (StringTool.isNotBlank(defaultValue)) {
			if (defaultValue.matches("\\$now\\(.+\\)")) {
				String javaDateFormat = defaultValue.substring(5, defaultValue.length() - 1);
				String nowFunc = dbSupport.getSysDateFunction();
				defaultValue = dbSupport.getDateToStringFunction(nowFunc, javaDateFormat);
				defaultValue = defaultValue.replace("'", "''");
			}
		}
		return defaultValue;
	}

}

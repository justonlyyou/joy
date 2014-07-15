package org.joy.core.persistence.jdbc.support;

import org.joy.commons.lang.string.StringTool;
import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.support.db.DbSupport;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月24日 下午3:18:51
 */
public class MdRdbColumnDefaultValueResovler {
	
	private MdRdbColumnDefaultValueResovler() {
	}

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

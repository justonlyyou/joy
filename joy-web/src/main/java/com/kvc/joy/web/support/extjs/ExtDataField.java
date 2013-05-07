package com.kvc.joy.web.support.extjs;

/**
 * Extjs数据字段类(Ext.data.Field)对应的POJO
 * @author 唐玮琳
 */
public class ExtDataField {

	private String convert;
	private String dateFormat;
	private Object defaultValue;
	private String mapping;
	private String name;
	private Boolean persist;
	private String sortDir;
	private String sortType;
	private String type;
	private Boolean useNull;
	
	public ExtDataField(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getConvert() {
		return convert;
	}

	public void setConvert(String convert) {
		this.convert = convert;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Object getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getPersist() {
		return persist;
	}

	public void setPersist(Boolean persist) {
		this.persist = persist;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getUseNull() {
		return useNull;
	}

	public void setUseNull(Boolean useNull) {
		this.useNull = useNull;
	}

}

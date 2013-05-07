package com.kvc.joy.web.support.extjs;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.web.support.utils.HttpRequestUtils;

/**
 * Extjs模型Action，主要提供将POJO转为Extjs的Model，这样不用每个POJO都写一个Extjs的Model
 * @author 唐玮琳
 */
public class ExtModelAction {

	private transient static final Map<String, List<ExtDataField>> fieldMap = new HashMap<String, List<ExtDataField>>();
	private transient Logger logger = LoggerFactory.getLogger(getClass());

	public List<ExtDataField> fetchFields() {
		String className = HttpRequestUtils.getParameter("className"); // // extjs模型javascript类名，也即POJO全类名
		List<ExtDataField> fieldList = null;
		if (StringTool.isNotBlank(className)) {
			fieldList = fieldMap.get(className);
			if (fieldList == null) {
				fieldList = getFieldsByReflection(className);
			}
		} else {
			logger.error("modelName参数为空！");
		}
		return fieldList;
	}
	
	private List<ExtDataField> getFieldsByReflection(String className) {
		List<ExtDataField> fieldList = null;
		try {
			Class<?> clazz = Class.forName(className);
			BeanInfo entity = Introspector.getBeanInfo(clazz);
			PropertyDescriptor propDescs[] = entity.getPropertyDescriptors();
			fieldList = new ArrayList<ExtDataField>(propDescs.length);
			for (PropertyDescriptor propDesc : propDescs) {
				String fieldName = propDesc.getName();
				String extFieldType = ExtDataFieldTypeConvertor.getExtType(propDesc.getPropertyType());
				fieldList.add(new ExtDataField(fieldName, extFieldType));
			}
			fieldMap.put(className, fieldList);
		} catch (Exception e) {
			logger.error("getModelFields()出错！", e);
		}
		return fieldList;
	}

}

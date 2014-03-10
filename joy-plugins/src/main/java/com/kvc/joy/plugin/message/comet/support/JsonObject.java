package com.kvc.joy.plugin.message.comet.support;

import com.kvc.joy.commons.data.json.JsonTool;

import java.util.HashMap;

/**
 * @author XiaohangHu
 * */
public class JsonObject extends HashMap<Object, Object> {

	/**
	 *
	 */
	private static final long serialVersionUID = -1493269365377697547L;

	@Override
	public Object put(Object name, Object value) {
		if (null == name) {
			throw new IllegalArgumentException("name must not be null!");
		}
		return super.put(name, value);
	}

	/**
	 * 转化为json格式字符串
	 * */
	@Override
	public String toString() {
        return JsonTool.toJson(this);
	}

}

package com.kvc.joy.core.sysres.code.service;

import com.kvc.joy.core.sysres.code.model.vo.CodeRecord;

import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-2-8 下午7:52:45
 */
public interface ISysCodeCacheService {
	
	/**
	 * 根据代码表id，获取该表所有代码记录
	 * 
	 * @param codeId 代码表id
	 * @return Map<代码, 代码记录>
	 * @author Kevice
	 * @time 2013-2-8 下午2:23:24
	 */
	Map<String, CodeRecord> get(String codeId);
	
	/**
	 * 根据代码表id和代码值，获取对应代码记录
	 * 
	 * @param codeTableId
	 * @param code
	 * @return
	 * @author Kevice
	 * @time 2013-2-8 下午2:24:40
	 */
	CodeRecord get(String codeTableId, String code);

}

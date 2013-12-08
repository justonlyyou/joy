package com.kvc.joy.core.sysres.code.service;

import java.util.Map;

import com.kvc.joy.core.sysres.code.model.vo.CodeRecord;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-8 下午2:15:24
 */
public interface ISysCodeService {
	
	/**
	 * 根据代码表id，获取该表所有代码及译文
	 * 
	 * @param codeTableId 代码表id
	 * @return Map<代码, 编码记录>
	 * @author 唐玮琳
	 * @time 2013-2-8 下午2:23:24
	 */
	Map<String, CodeRecord> get(String codeTableId);
	
}

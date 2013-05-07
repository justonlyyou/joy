package com.kvc.joy.core.sysres.code.service;

import java.util.Map;

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
	 * @return Map<代码, 译文>
	 * @author 唐玮琳
	 * @time 2013-2-8 下午2:23:24
	 */
	Map<String, String> get(String codeTableId);
	
//	/**
//	 * 根据代码表id和代码值，获取对应译文
//	 * 
//	 * @param codeId
//	 * @param code
//	 * @return
//	 * @author 唐玮琳
//	 * @time 2013-2-8 下午2:24:40
//	 */
//	String get(String codeId, String code);

}

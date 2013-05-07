package com.kvc.joy.core.sysres.code.service.impl;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import com.kvc.joy.core.sysres.code.service.ISysCodeCacheService;
import com.kvc.joy.core.sysres.code.service.ISysCodeService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-8 下午2:17:17
 */
public class SysCodeCacheService implements ISysCodeCacheService {

	public static final String SYS_CODE = "SYS_CODE";
	private ISysCodeService sysCodeService;

	@Cacheable(value=SYS_CODE, key="#codeTableId")
	public Map<String, String> get(String codeTableId) {
		return sysCodeService.get(codeTableId);
	}

	public String get(String codeId, String code) {
		Map<String, String> map = CoreBeanFactory.getSysCodeCacheService().get(codeId);
		return map.get(code);
	}
	
	public void setSysCodeService(ISysCodeService sysCodeService) {
		this.sysCodeService = sysCodeService;
	}

}

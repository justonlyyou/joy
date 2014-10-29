package org.joy.core.sysres.code.service.impl;

import org.joy.core.spring.utils.CoreBeanFactory;
import org.joy.core.sysres.code.model.vo.CodeRecord;
import org.joy.core.sysres.code.service.ISysCodeCacheService;
import org.joy.core.sysres.code.service.ISysCodeService;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * 系统代码字典缓存服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-8 下午2:17:17
 */
public class SysCodeCacheService implements ISysCodeCacheService {

	public static final String SYS_CODE = "SYS_CODE";
	private ISysCodeService sysCodeService;

	@Cacheable(value=SYS_CODE, key="#codeTableId")
	public Map<String, CodeRecord> get(String codeTableId) {
		return sysCodeService.get(codeTableId);
	}

	public CodeRecord get(String codeId, String code) {
		Map<String, CodeRecord> map = CoreBeanFactory.getSysCodeCacheService().get(codeId);
		return map.get(code);
	}
	
	public void setSysCodeService(ISysCodeService sysCodeService) {
		this.sysCodeService = sysCodeService;
	}

}

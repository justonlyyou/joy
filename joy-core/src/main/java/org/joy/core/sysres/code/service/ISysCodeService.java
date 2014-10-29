package org.joy.core.sysres.code.service;

import org.joy.core.sysres.code.model.vo.CodeRecord;

import java.util.Map;

/**
 * 系统代码字典服务接口
 *
 * @author Kevice
 * @time 2013-2-8 下午2:15:24
 * @since 1.0.0
 */
public interface ISysCodeService {
	
	/**
	 * 根据代码表id，获取该表所有代码及译文
	 * 
	 * @param codeTableId 代码表id
	 * @return Map<代码, 编码记录>
	 * @author Kevice
	 * @time 2013-2-8 下午2:23:24
     * @since 1.0.0
	 */
	Map<String, CodeRecord> get(String codeTableId);
	
}

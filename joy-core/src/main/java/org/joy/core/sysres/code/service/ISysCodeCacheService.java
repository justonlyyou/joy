package org.joy.core.sysres.code.service;

import org.joy.core.sysres.code.model.vo.CodeRecord;

import java.util.Map;

/**
 * 系统代码字典缓存服务接口
 *
 * @author Kevice
 * @time 2013-2-8 下午7:52:45
 * @since 1.0.0
 */
public interface ISysCodeCacheService {

    /**
     * 根据代码表id，获取该表所有代码记录
     *
     * @param codeId 代码表id
     * @return Map<代码, 代码记录>
     * @author Kevice
     * @time 2013-2-8 下午2:23:24
     * @since 1.0.0
     */
    Map<String, CodeRecord> get(String codeId);

    /**
     * 根据代码表id和代码值，获取对应代码记录
     *
     * @param codeTableId 代码表id
     * @param code        代码值
     * @return 代码记录
     * @author Kevice
     * @time 2013-2-8 下午2:24:40
     * @since 1.0.0
     */
    CodeRecord get(String codeTableId, String code);

}

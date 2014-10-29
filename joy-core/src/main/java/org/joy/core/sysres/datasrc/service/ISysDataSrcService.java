package org.joy.core.sysres.datasrc.service;

import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;

import java.util.List;

/**
 * 系统数据源服务接口
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月28日 下午4:53:28
 */
public interface ISysDataSrcService {

	/**
	 * 返回所有数据源
	 * 
	 * @return List<数据源实体>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月28日 下午4:54:22
	 */
	List<TSysDataSrc> getAllDataSrc();
	
}

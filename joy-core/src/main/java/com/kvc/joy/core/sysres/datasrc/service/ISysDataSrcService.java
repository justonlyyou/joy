package com.kvc.joy.core.sysres.datasrc.service;

import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月28日 下午4:53:28
 */
public interface ISysDataSrcService {

	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月28日 下午4:54:22
	 */
	List<TSysDataSrc> getAllDataSrc();
	
}

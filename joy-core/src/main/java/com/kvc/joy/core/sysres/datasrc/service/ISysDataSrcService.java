package com.kvc.joy.core.sysres.datasrc.service;

import java.util.List;

import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月28日 下午4:53:28
 */
public interface ISysDataSrcService {

	/**
	 * 
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年11月28日 下午4:54:22
	 */
	List<TSysDataSrc> getAllDataSrc();
	
}

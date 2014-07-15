package org.joy.core.sysres.datasrc.service.impl;

import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc_;
import org.joy.core.sysres.datasrc.service.ISysDataSrcService;

import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月28日 下午4:53:47
 */
public class SysDataSrcService implements  ISysDataSrcService {

	@Override
	public List<TSysDataSrc> getAllDataSrc() {
		return JpaTool.search(TSysDataSrc.class, TSysDataSrc_.deleted, "0");
	}

}

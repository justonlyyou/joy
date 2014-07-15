package org.joy.web.spmvc.modules.sysres.datasrc.controller;

import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.joy.web.spmvc.core.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月15日 下午11:27:05
 */
@Controller
@RequestMapping("/sysDataSrc")
public class SysDataSrcController extends BaseCrudController<TSysDataSrc> {

	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/sysres/dataSrc/sysDataSrc";
	}

}

package org.joy.web.spmvc.modules.sysres.code.controller;

import org.joy.core.sysres.code.model.po.TSysCodeTable;
import org.joy.web.spmvc.core.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月15日 下午10:43:37
 */
@Controller
@RequestMapping("/sysCodeTable")
public class SysCodeTableController extends BaseCrudController<TSysCodeTable> {

	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/sysres/code/sysCodeTable";
	}

}

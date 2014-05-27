package com.kvc.joy.web.spmvc.modules.sysres.code.controller;

import com.kvc.joy.core.sysres.code.model.po.TSysCodeTable;
import com.kvc.joy.web.spmvc.core.BaseCrudController;
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
public class SysCodeTableCrudController extends BaseCrudController<TSysCodeTable> {

	@Override
	protected String getCurrentViewName() {
		return "joy/core/sysres/code/sysCodeTable";
	}

}

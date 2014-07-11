package com.kvc.joy.web.spmvc.modules.monitor.security.controller;

import com.kvc.joy.plugin.security.user.model.po.TUserLoginLog;
import com.kvc.joy.web.spmvc.core.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月30日 下午11:29:09
 */
@Controller
@RequestMapping("/userLoginLog")
public class UserLoginLogController extends BaseCrudController<TUserLoginLog> {

	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/monitor/security/userLoginLog";
	}

}

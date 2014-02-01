package com.kvc.joy.web.spmvc.modules.monitor.log.controller;

import com.kvc.joy.core.persistence.flyway.model.po.TSysDbSchemaVersion;
import com.kvc.joy.web.spmvc.core.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月30日 下午11:29:09
 */
@Controller
@RequestMapping("/sqlScriptInstallLog")
public class SqlScriptInstallLogController extends BaseController<TSysDbSchemaVersion> {

	@Override
	protected String getCurrentViewName() {
		return "joy/core/monitor/log/sqlScriptInstallLog";
	}

}

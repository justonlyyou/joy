package org.joy.web.spmvc.modules.monitor.log.controller;

import org.joy.core.persistence.flyway.model.po.TSysDbSchemaVersion;
import org.joy.web.spmvc.core.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月30日 下午11:29:09
 */
@Controller
@RequestMapping("/sqlScriptInstallLog")
public class SqlScriptInstallLogController extends BaseCrudController<TSysDbSchemaVersion> {

	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/monitor/log/sqlScriptInstallLog";
	}

}

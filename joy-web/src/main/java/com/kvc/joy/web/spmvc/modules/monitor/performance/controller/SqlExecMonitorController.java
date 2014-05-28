package com.kvc.joy.web.spmvc.modules.monitor.performance.controller;

import com.kvc.joy.plugin.monitor.jdbc.jwebap.model.po.TSysSqlLog;
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
@RequestMapping("/sqlExecMonitor")
public class SqlExecMonitorController extends BaseCrudController<TSysSqlLog> {

	@Override
	protected String getCurrentViewName() {
		return "joy/core/monitor/performance/sqlExecMonitor";
	}

}

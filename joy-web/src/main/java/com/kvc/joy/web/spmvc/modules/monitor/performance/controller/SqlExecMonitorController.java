package com.kvc.joy.web.spmvc.modules.monitor.performance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvc.joy.plugin.monitor.jdbc.model.po.TSysSqlLog;
import com.kvc.joy.web.spmvc.core.BaseController;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月30日 下午11:29:09
 */
@Controller
@RequestMapping("/sqlExecMonitor")
public class SqlExecMonitorController extends BaseController<TSysSqlLog> {

	@Override
	protected String getCurrentViewName() {
		return "joy/core/monitor/performance/sqlExecMonitor";
	}

}

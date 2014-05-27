package com.kvc.joy.web.spmvc.modules.plugins.schedule.quartz.controller;

import com.kvc.joy.plugin.schedule.quartz.model.po.TQrtzJobPlan;
import com.kvc.joy.web.spmvc.core.BaseCrudController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月15日 下午11:50:26
 */
@Controller
@RequestMapping("/qrtzJobPlan")
public class QrtzJobPlanCrudController extends BaseCrudController<TQrtzJobPlan> {

	@Override
	protected String getCurrentViewName() {
		return "joy/plugins/schedule/quartz/qrtzJobPlan";
	}

}

package com.kvc.joy.web.spmvc.modules.sysres.param.controller;

import com.kvc.joy.core.sysres.param.model.po.TSysParam;
import com.kvc.joy.core.sysres.param.model.po.TSysParam_;
import com.kvc.joy.web.spmvc.core.BaseCrudController;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月6日 下午8:46:40
 */
@Controller
@RequestMapping("/sysParam")
public class SysParamCrudController extends BaseCrudController<TSysParam> {
	
	@Override
	protected String getCurrentViewName() {
		return "joy/core/sysres/param/sysParam";
	}
	
	@Override
	protected Order[] getDefaultOrders() {
		return new Order[] { new Order(Direction.ASC, TSysParam_.paramName.getName())};
	}
	
}

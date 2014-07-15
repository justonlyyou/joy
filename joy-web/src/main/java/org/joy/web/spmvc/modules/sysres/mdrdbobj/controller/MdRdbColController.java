package org.joy.web.spmvc.modules.sysres.mdrdbobj.controller;

import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.joy.web.spmvc.core.BaseCrudController;
import org.joy.web.support.utils.HttpRequestTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月30日 下午9:21:23
 */
@Controller
@RequestMapping("/mdRdbCol")
public class MdRdbColController extends BaseCrudController<MdRdbColumn> {

	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/sysres/mdRdbObj/mdRdbCol";
	}
	
	@Override
	protected MdRdbColumn loadEntity(Model model) {
		String id = HttpRequestTool.getParameter("id");
		String[] parts = id.split("-");
		String dsId = parts[0];
		model.addAttribute("dsId", dsId);
		TSysDataSrc dataSrc = JpaTool.get(TSysDataSrc.class, dsId);
		model.addAttribute("dsName", dataSrc.getName());
		String objName = parts[1];
		model.addAttribute("objName", objName);
		MdRdbTable table = MdRdbTool.getRelationalObject(dsId, objName);
		model.addAttribute("objComment", table.getComment());
		String colName = parts[2];
		return MdRdbTool.getColumn(dsId, objName, colName);
	}
	

}

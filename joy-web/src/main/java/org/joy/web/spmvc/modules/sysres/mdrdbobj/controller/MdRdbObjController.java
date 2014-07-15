package org.joy.web.spmvc.modules.sysres.mdrdbobj.controller;

import org.joy.commons.collections.CollectionQueryTool;
import org.joy.commons.query.QueryLogics;
import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.rp.pagestore.PageStore;
import org.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import org.joy.web.spmvc.core.BaseCrudController;
import org.joy.web.support.utils.HttpRequestTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年11月28日 下午3:23:34
 */
@Controller
@RequestMapping("/mdRdbObj")
public class MdRdbObjController extends BaseCrudController<MdRdbTable> {
	
	@Override
	protected void queryByPageStore(PageStore pageStore) {
		QueryLogics queryLogics = pageStore.getQueryLogics();
		String dsId = HttpRequestTool.getParameter("dsId");
		Map<String, MdRdbTable> tables = MdRdbTool.getTables(dsId);
		List<MdRdbTable> results = CollectionQueryTool.pagingQuery(tables.values(), queryLogics);
		pageStore.setResult(results);
	}
	
	@Override
	protected MdRdbTable loadEntity(Model model) {
		String id = HttpRequestTool.getParameter("id");
		String[] parts = id.split("-");
		String dsId = parts[0];
		String objName = parts[1];
		TSysDataSrc dataSrc = JpaTool.get(TSysDataSrc.class, dsId);
		model.addAttribute("dsName", dataSrc.getName());
		MdRdbTable table = MdRdbTool.getRelationalObject(dsId, objName);
		MdRdbTool.getColumns(dsId, table.getName());
		return table;
	}
	
	@Override
	protected String getCurrentViewName() {
		return "joy/commons/core/sysres/mdRdbObj/mdRdbObj";
	}

}

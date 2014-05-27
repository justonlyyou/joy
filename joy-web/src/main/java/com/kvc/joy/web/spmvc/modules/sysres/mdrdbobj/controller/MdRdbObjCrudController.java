package com.kvc.joy.web.spmvc.modules.sysres.mdrdbobj.controller;

import com.kvc.joy.commons.collections.CollectionQueryTool;
import com.kvc.joy.commons.query.QueryLogics;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.rp.pagestore.PageStore;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;
import com.kvc.joy.web.spmvc.core.BaseCrudController;
import com.kvc.joy.web.support.utils.HttpRequestTool;
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
public class MdRdbObjCrudController extends BaseCrudController<MdRdbTable> {
	
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
		return "joy/core/sysres/mdRdbObj/mdRdbObj";
	}

}

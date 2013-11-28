package com.kvc.joy.web.spmvc.modules.sysres.mdrdbobj.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvc.joy.commons.collections.CollectionQueryTool;
import com.kvc.joy.commons.query.QueryLogics;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.support.utils.MdRdbTool;
import com.kvc.joy.core.rp.pagestore.PageStore;
import com.kvc.joy.web.spmvc.core.BaseController;
import com.kvc.joy.web.support.utils.HttpRequestTool;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年11月28日 下午3:23:34
 */
@Controller
@RequestMapping("/mdRdbObj")
public class MdRdbObjController extends BaseController<MdRdbTable> {
	
	@Override
	protected void queryByPageStore(PageStore pageStore) {
		QueryLogics queryLogics = pageStore.getQueryLogics();
		String dsId = HttpRequestTool.getParameter("dsId");
		Map<String, MdRdbTable> tables = MdRdbTool.getTables(dsId);
		List<MdRdbTable> results = CollectionQueryTool.pagingQuery(tables.values(), queryLogics);
		pageStore.setResult(results);
	}
	
	@Override
	protected MdRdbTable loadEntity() {
		String id = HttpRequestTool.getParameter("id");
		String[] parts = id.split("-");
		String dsId = parts[0];
		String objName = parts[1];
		return MdRdbTool.getRelationalObject(dsId, objName);
	}
	
	@Override
	protected String getCurrentViewName() {
		return "joy/core/sysres/mdRdbObj/mdRdbObj";
	}

}

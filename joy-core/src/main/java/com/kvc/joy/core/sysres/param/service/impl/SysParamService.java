package com.kvc.joy.core.sysres.param.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.sysres.param.po.TSysParam;
import com.kvc.joy.core.sysres.param.po.TSysParam_;
import com.kvc.joy.core.sysres.param.service.ISysParamService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-7 下午10:37:10
 */
public class SysParamService implements ISysParamService {

	public Map<String, TSysParam> get() {
		 List<TSysParam> list = JpaTool.search(TSysParam.class, TSysParam_.deleted, false);
		 Map<String, TSysParam> map = new HashMap<String, TSysParam>(list.size());
		 for (TSysParam sysParam : list) {
			 map.put(sysParam.getParamName(), sysParam);
		}
		 return map;
	}

	public TSysParam get(String paramName) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(TSysParam_.paramName.getName(), paramName);
		map.put(TSysParam_.deleted.getName(), Boolean.valueOf(false));
		List<TSysParam> results = JpaTool.andSearch(TSysParam.class, map);
		if(results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	@Transactional
	public void put(TSysParam sysParam) {
		JpaTool.persist(sysParam);
	}

}

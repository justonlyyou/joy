package org.joy.core.sysres.param.service.impl;

import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.core.sysres.param.model.po.TSysParam;
import org.joy.core.sysres.param.model.po.TSysParam_;
import org.joy.core.sysres.param.service.ISysParamService;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统参数服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-7 下午10:37:10
 */
public class SysParamService implements ISysParamService {

	public Map<String, TSysParam> get() {
		 List<TSysParam> list = JpaTool.search(TSysParam.class, TSysParam_.deleted, "0");
		 Map<String, TSysParam> map = new HashMap<String, TSysParam>(list.size());
		 for (TSysParam sysParam : list) {
			 map.put(sysParam.getParamName(), sysParam);
		}
		 return map;
	}

    @Override
    public Map<String, TSysParam> getProperties() {
        Map<String, TSysParam> paramMap = get();
        Map<String, TSysParam> propertyMap = new HashMap<String, TSysParam>();
        Set<Map.Entry<String,TSysParam>> entries = paramMap.entrySet();
        for (Map.Entry<String,TSysParam> entry : entries) {
            TSysParam param = entry.getValue();
            if ("1".equals(param.getEncrypt())) {
                propertyMap.put(entry.getKey(), param);
            }
        }
        return propertyMap;
    }

    public TSysParam get(String paramName) {
		Map<String, Object> map = new HashMap<String, Object>(2);
		map.put(TSysParam_.paramName.getName(), paramName);
		map.put(TSysParam_.deleted.getName(), false);
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

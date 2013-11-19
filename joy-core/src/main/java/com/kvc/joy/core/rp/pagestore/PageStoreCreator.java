package com.kvc.joy.core.rp.pagestore;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.query.Paging;
import com.kvc.joy.commons.query.QueryLogic;
import com.kvc.joy.commons.query.QueryLogicOperator;
import com.kvc.joy.commons.query.QueryLogics;
import com.kvc.joy.commons.query.sort.Sort;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-21 下午11:29:45
 */
public class PageStoreCreator {

	protected static final Log logger = LogFactory.getLog(PageStoreCreator.class);
	private Map<String, String> paramMap;

	public PageStoreCreator(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	public PageStore create() {
		return createPageStore();
	}

	private PageStore createPageStore() {
		PageStore store = new PageStore();
		Map<String, String> orders = createSort();
		Paging paging = createPaging();
		store.setPaging(paging);
		QueryLogics queryLogics = createQueryLogics();
		queryLogics.setPaging(paging);
		queryLogics.setOrderMap(orders);
		if (orders.isEmpty() == false) {
			paging.setSort(new Sort(queryLogics.getOrders()));
		}
		store.setQueryLogics(queryLogics);
		return store;
	}

	private QueryLogics createQueryLogics() {
		QueryLogics logics = new QueryLogics();
		
		Map<String, QueryLogic> queryLogicMap = new LinkedHashMap<String, QueryLogic>();
		for (String key : paramMap.keySet()) {
			if (key.startsWith(QueryLogic.OPERATOR_PARA_NAME_PREFIX)) {
				String property = StringTool.substringAfter(key, QueryLogic.OPERATOR_PARA_NAME_PREFIX);
				if (paramMap.containsKey(property) == false) {
					logger.warn("页面配置错误！属性【"+property+"】有配置操作符但找不到输入域，已忽略该条件！");
				} else {
					String operatorStr = paramMap.get(key);
					QueryLogicOperator operator = QueryLogicOperator.enumOf(operatorStr);
					if (operator == null) {
						logger.warn("页面配置错误！属性【" + property + "】配置的操作符【" + operatorStr + "】非法！"
								+ "合法的操作符取值请参考枚举类: " + QueryLogicOperator.class);
					} else {
						QueryLogic queryLogic = new QueryLogic(operator, paramMap.get(property));
						queryLogicMap.put(property, queryLogic);	
					}
				}
			}
		}
		logics.setConditions(queryLogicMap);
		
		return logics;
	}
	
	private Map<String, String> createSort() {
		Set<String> keySet = paramMap.keySet();
		Map<String, String> orders = new HashMap<String, String>(0);
		for (String key : keySet) {
			if(key.startsWith(QueryLogics.KEY_PREFIX_ORDER_BY)) {
				String property = StringTool.substringAfter(key, QueryLogics.KEY_PREFIX_ORDER_BY);
				String orderKey = QueryLogics.KEY_PREFIX_ORDER + property;
				String order = paramMap.get(orderKey);
				if (StringTool.isNotBlank(order)) {
					if(!order.equalsIgnoreCase("ASC") && !order.equalsIgnoreCase("DESC")) {
						logger.error("属性【" + property + "】配置的排序值非法：【" + order + "】，已忽略该排序！");
					} else {
						orders.put(property, order);	
					}
				} else {
					String defaultOrderKey = QueryLogics.KEY_PREFIX_ORDER_DEFAULT + property;
					String defaultOrder = paramMap.get(defaultOrderKey);
					if (StringTool.isNotBlank(defaultOrder)) {
						if(!defaultOrder.equalsIgnoreCase("ASC") && !defaultOrder.equalsIgnoreCase("DESC")) {
							logger.error("页面配置的属性【" + property + "】的排序值非法：【" + defaultOrder + "】，已忽略该排序！");
						} else {
							orders.put(property, defaultOrder);	
						}
					}
				}
			}
		}
		return orders;
	}

	private Paging createPaging() {
		Paging paging = new Paging();
		String pageNumber = paramMap.get(Paging.KEY_PAGE_NUMBER);
		String pageSize = paramMap.get(Paging.KEY_PAGE_SIZE);
		if (StringTool.isNotBlank(pageNumber) && StringTool.isNotBlank(pageSize)) {
			paging.setPageNumber(Integer.valueOf(pageNumber));
			paging.setPageSize(Integer.valueOf(pageSize));
			paging.setOffset((paging.getPageNumber() - 1) * paging.getPageSize());
		}
		return paging;
	}
	
}

package org.joy.core.rp.pagestore;

import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.commons.query.Paging;
import org.joy.commons.query.QueryLogic;
import org.joy.commons.query.QueryLogicOperator;
import org.joy.commons.query.QueryLogics;
import org.joy.commons.query.sort.Sort;

import java.util.*;

/**
 * 
 * @author Kevice
 * @time 2012-6-21 下午11:29:45
 */
public class PageStoreCreator {

	protected static final Log logger = LogFactory.getLog(PageStoreCreator.class);
	private final Map<String, String> paramMap;
	private final Map<String, String> returnMap = new HashMap<String, String>(0);

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
		
		List<QueryLogic> queryLogics = new ArrayList<QueryLogic>();
		for (String key : paramMap.keySet()) {
			if (key.startsWith(QueryLogic.TIME_START_PROP_PREFIX)) {
				String value = paramMap.get(key);
				if (StringTool.isNotBlank(value)) {
					returnMap.put(key, value);
					String property = StringTool.substringAfter(key, QueryLogic.TIME_START_PROP_PREFIX);
					String operatorStr = paramMap.get(QueryLogic.OPERATOR_PARAM_PREFIX + key);
					if (StringTool.isBlank(operatorStr)) {
						queryLogics.add(new QueryLogic(property, QueryLogicOperator.GE, value));
					}
				}
			} else if (key.startsWith(QueryLogic.TIME_END_PROP_PREFIX)) {
				String value = paramMap.get(key);
				if (StringTool.isNotBlank(value)) {
					returnMap.put(key, value);
					String property = StringTool.substringAfter(key, QueryLogic.TIME_END_PROP_PREFIX);
					String operatorStr = paramMap.get(QueryLogic.OPERATOR_PARAM_PREFIX + key);
					if (StringTool.isBlank(operatorStr)) {
						queryLogics.add(new QueryLogic(property, QueryLogicOperator.LT, value));
					}
				}
			} else if (key.startsWith(QueryLogic.OPERATOR_PARAM_PREFIX)) {
				String property = StringTool.substringAfter(key, QueryLogic.OPERATOR_PARAM_PREFIX);
				if (paramMap.containsKey(property) == false) {
					logger.warn("页面配置错误！属性【"+property+"】有配置操作符但找不到输入域，已忽略该条件！");
				} else {
					String operatorStr = paramMap.get(key);
					QueryLogicOperator operator = QueryLogicOperator.enumOf(operatorStr);
					if (operator == null) {
						logger.warn("页面配置错误！属性【" + property + "】配置的操作符【" + operatorStr + "】非法！"
								+ "合法的操作符取值请参考枚举类: " + QueryLogicOperator.class);
					} else {
						queryLogics.add(new QueryLogic(property, operator, paramMap.get(property)));
					}
				}
			}
		}
		logics.setConditions(queryLogics);
		
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
	
	public Map<String, String> getReturnMap() {
		return returnMap;
	}
	
}

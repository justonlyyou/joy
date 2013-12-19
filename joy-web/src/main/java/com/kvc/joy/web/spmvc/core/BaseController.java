package com.kvc.joy.web.spmvc.core;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Entity;

import org.springframework.data.domain.Sort.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.AnnotationTool;
import com.kvc.joy.commons.lang.GenericTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.rp.pagestore.PageStore;
import com.kvc.joy.core.rp.pagestore.PageStoreCreator;
import com.kvc.joy.web.support.utils.HttpRequestTool;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月7日 上午8:50:53
 */
public abstract class BaseController<T> {

	protected static final Log logger = LogFactory.getLog(BaseController.class);
	
	@RequestMapping("")
	public String page(Model model) {
		return getCurrentViewName();
	}

	@RequestMapping("/list")
	public String list(Model model,  @ModelAttribute("command") T command) {
		PageStore pageStore = getPageStore(model);
		queryByPageStore(pageStore);
		pageStore.getPaging().cal();
		model.addAttribute("pageStore", pageStore);
		addAttributes(model);
		return getCurrentViewName();
	}
	
	@RequestMapping("/get")
	public String get(Model model) {
		Object m = loadEntity(model);
		model.addAttribute("model", m);
		return getDetailViewName();
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return getEditViewName();
	}
	
	@RequestMapping("/edit")
	public String edit(Model model) {
		return getEditViewName();
	}
	
	@RequestMapping("/delete")
	public String delete(Model model) {
		return null; //TODO
	}
	
	protected abstract String getCurrentViewName();

	protected String getDetailViewName() {
		return   getCurrentViewName() + "Detail";
	}

	protected String getEditViewName() {
		return getCurrentViewName() + "Edit";
	}
	
	protected void queryByPageStore(PageStore pageStore) {
		pageStore.query(getEntityClass());
	}

	protected Object loadEntity(Model model) {
		String id = HttpRequestTool.getParameter("id");
		if (StringTool.isBlank(id)) {
			throw new SystemException("加载实体时id参数必须指定！");
		}
		Class<?> entityClass = getEntityClass();
		if (IEntity.class.isAssignableFrom(entityClass) == false) {
			throw new SystemException("如果不想重写BaseController类的loadEntity方法，"
					+ "继承BaseController的子类的泛型参数类型必须为实现IEntity接口的实体类！");
		}
		return JpaTool.get(entityClass, id);
	}

	protected void addAttributes(Model model) {
		Map<String, String[]> parameterMap = HttpRequestTool.getParameterMap();
		for (Entry<String, String[]> entry : parameterMap.entrySet()) {
			String[] values = entry.getValue();
			Object value = null;
			if (values.length == 0) {
				value = "";
			} else if (values.length == 1) {
				value = values[0];
			} else {
				value = values;
			}
			model.addAttribute(entry.getKey(), value);
		}
	}

	protected PageStore getPageStore(Model model) {
		Map<String, String> paramMap = HttpRequestTool.getParameters();
		PageStoreCreator pageStoreCreator = new PageStoreCreator(paramMap);
		PageStore store = pageStoreCreator.create();
		Map<String, String> returnMap = pageStoreCreator.getReturnMap();
		for (String key : returnMap.keySet()) {
			model.addAttribute(key, returnMap.get(key));
		}
		Order[] defaultOrders = getDefaultOrders();
		if (defaultOrders != null) {
			Map<String, String> orderMap = store.getQueryLogics().getOrderMap();
			for (Order order : defaultOrders) {
				if (orderMap.containsKey(order.getProperty()) == false) {
					orderMap.put(order.getProperty(), order.getDirection().name());
				}
			}
		}
		return store;
	}

	protected Class<?> getEntityClass() {
		Class<T> type = getGenericType();
		if(IEntity.class.isAssignableFrom(type)) {
			return AnnotationTool.getClassUpHierarchy(type, Entity.class);
		} else {
			return type;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericType() {
		Class<?> clazz = GenericTool.getSuperClassGenricType(getClass());
		if (clazz.equals(Object.class)) {
			throw new SystemException("继承BaseController的子类必须指定泛型参数！");
//		} else if (IEntity.class.isAssignableFrom(clazz) == false) {
//			throw new SystemException("继承BaseController的子类的泛型参数类型必须为实现IEntity接口的实体类！");
		}
		return (Class<T>) clazz;
	}

	protected Order[] getDefaultOrders() {
		return null;
	}

}

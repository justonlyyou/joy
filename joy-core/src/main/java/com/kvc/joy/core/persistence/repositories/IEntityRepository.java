package com.kvc.joy.core.persistence.repositories;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import org.springframework.data.domain.Sort.Order;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.rp.pagestore.PageStore;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-26 下午7:27:33
 */
public interface IEntityRepository<T extends IEntity<ID>, ID extends Serializable> {

	/**
	 * 
	 * 
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:19:33
	 */
	Iterable<T> searchAll(Order... orders);

	/**
	 * 
	 * 
	 * @param attr
	 * @param values
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:19:28
	 */
	Iterable<T> inSearch(String attr, Collection<?> values, Order... orders);

	/**
	 * 
	 * 
	 * @param values
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:26:33
	 */
	Iterable<T> inSearch(Collection<?> values, Order... orders);

	/**
	 * 
	 * 
	 * @param attr
	 * @param value
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:50:02
	 */
	Iterable<T> search(String attr, Object value, Order... orders);

	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:51:24
	 */
	Iterable<T> pagingSearch(PageStore pageStore);

	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:53:27
	 */
	Iterable<T> andSearch(Map<String, Object> attrMap, Order... orders);
	
	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:54:03
	 */
	Iterable<T> orSearch(Map<String, Object> attrMap, Order... orders);
	
}

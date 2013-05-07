package com.kvc.joy.core.persistence.orm.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.metamodel.SingularAttribute;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.core.persistence.repositories.IEntityRepository;
import com.kvc.joy.core.rp.pagestore.PageStore;

/**
 * 
 * @author 唐玮琳
 * @time 2012-6-26 下午10:36:28
 */
public interface IJpaEntityRepository<T extends IEntity<ID>, ID extends Serializable> extends IEntityRepository<T, ID>, JpaRepository<T, ID> {

	/**
	 * 
	 * 
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:19:33
	 */
	List<T> searchAll(Order... orders);

	/**
	 * 
	 * 
	 * @param attr
	 * @param values
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:19:28
	 */
	List<T> inSearch(String attr, Collection<?> values, Order... orders);

	/**
	 * 
	 * 
	 * @param values
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:26:33
	 */
	List<T> inSearch(Collection<?> values, Order... orders);

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
	List<T> search(String attr, Object value, Order... orders);

	/**
	 * 
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:51:24
	 */
	List<T> pagingSearch(PageStore pageStore);

	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:53:27
	 */
	List<T> andSearch(Map<String, Object> attrMap, Order... orders);

	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-26 下午8:54:03
	 */
	List<T> orSearch(Map<String, Object> attrMap, Order... orders);

	/**
	 * 
	 * 
	 * @param attr
	 * @param values
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:12:08
	 */
	<E> List<T> inSearch(SingularAttribute<? super T, E> attr, Collection<E> values, Order... orders);

	/**
	 * 
	 * 
	 * @param attr
	 * @param value
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:17:44
	 */
	<F> List<T> search(SingularAttribute<? super T, F> attr, F value, Order... orders);

	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:18:37
	 */
	<E> List<T> andQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders);

	/**
	 * 
	 * 
	 * @param attrMap
	 * @param orders
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:20:01
	 */
	<E> List<T> orQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders);

	/**
	 * 
	 * 
	 * @param sql
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:21:17
	 */
	List<?> findBySql(String sql);

	/**
	 * 查询某序列的下一个值(ORACLE ONLY)
	 * 
	 * @param sequence
	 * @return
	 * @author 唐玮琳
	 * @time 2012-6-27 下午9:22:00
	 */
	long querySequence(final String sequence);

}

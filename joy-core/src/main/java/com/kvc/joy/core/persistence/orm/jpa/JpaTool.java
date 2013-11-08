package com.kvc.joy.core.persistence.orm.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Order;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.support.GroupExecutor;
import com.kvc.joy.core.persistence.support.QueryLogics;
import com.kvc.joy.core.rp.pagestore.PageStore;
import com.kvc.joy.core.spring.utils.SpringBeanTool;

@Repository
@SuppressWarnings("rawtypes")
public class JpaTool extends BaseJpaDao {

	private static Logger logger = LoggerFactory.getLogger(JpaTool.class);
	
	private JpaTool() {
	}

	private static JpaTool getInstance() {
		return SpringBeanTool.getBean(JpaTool.class);
	}

	public static void persist(Object engity) {
		jpaTemplate().persist(engity);
	}

	public static <T> T merge(T engity) {
		return jpaTemplate().merge(engity);
	}

	public static void remove(Object engity) {
		jpaTemplate().remove(engity);
	}

	public static void flush() {
		jpaTemplate().flush();
	}

	public static void refresh(Object engity) {
		jpaTemplate().refresh(engity);
	}

	/**
	 * 通过主键加载实体对象
	 * 
	 * @param entityClass 实体类
	 * @param id 主键值
	 * @return 实体对象
	 */
	public static <T> T get(Class<T> entityClass, Object id) {
		return jpaTemplate().find(entityClass, id);
	}

	// /**
	// * 通过主键加载实体代理
	// *
	// * @param entityClass 实体类
	// * @param id 主键值
	// * @return 实体代理
	// */
	// public static <T> T load(Class<T> entityClass, Object id) {
	// return jpaTemplate().load(entityClass, id);
	// }

	/**
	 * in查询
	 * 
	 * @param entityClass 实体类
	 * @param attr 字段
	 * @param values 字段值集合
	 * @return 指定类名对象的结果列表
	 */
	public static <T, E> List<T> inSearch(final Class<T> entityClass, final SingularAttribute<? super T, E> attr, final Collection<E> values, final Order... orders) {
		return inSearch(entityClass, attr.getName(), values, (Order[]) orders);
	}

	/**
	 * in查询
	 * 
	 * @param entityClass 实体类
	 * @param attr 字段
	 * @param values 字段值集合
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> inSearch(final Class<T> entityClass, final String attr, Collection<?> values, final Order... orders) {
		if (values == null || values.isEmpty()) {
			return new ArrayList<T>(0);
		}
		Set<Object> set = new HashSet<Object>(values);
		final List<T> resultList = new ArrayList<T>(set.size());
		try {
			new GroupExecutor<Object>(set) {

				@Override
				public void groupExecute(List<Object> subList) {
					CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
					Root<T> root = criteriaQuery.from(entityClass);
					if (attr == null) {
						criteriaQuery.where(root.get("id").in(subList));
					} else {
						criteriaQuery.where(root.get(attr).in(subList));
					}
					TypedQuery<T> typedQuery = getEntityMgr().createQuery(criteriaQuery);
					resultList.addAll(typedQuery.getResultList());
				}

			}.execute();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return resultList;
	}

	/**
	 * in查询
	 * 
	 * @param entityClass 实体类
	 * @param attr 字段
	 * @param values 字段值集合
	 * @return 指定类名对象的结果列表
	 */
	public static <T extends IEntity<?>, E> List<T> inSearch(final Class<T> entityClass, final Collection<E> values, final Order... orders) {
		return inSearch(entityClass, (String) null, values, orders);
	}

	// /**
	// * in查询, 查找主键
	// *
	// * @param clazz 实体类
	// * @param collection 字段值集合
	// * @return 指定类名对象的结果列表
	// */
	// @SuppressWarnings("rawtypes")
	// public static <T extends IEntity, E> List<T> inSearch(final Class<T>
	// clazz, final Collection<E> collection) {
	// return inSearch(clazz, "id", collection);
	// }

	/**
	 * 查询指定类的所有结果
	 * 
	 * @param entityClass 实体类
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> searchAll(final Class<T> entityClass, final Order... orders) {
		CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(root);
		criteriaQuery.orderBy(processOrder(root, orders));
		TypedQuery<T> typedQuery = getEntityMgr().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	// public static <T> List<T> searchAll(final Class<T> entityClass, final
	// Order... orders) {
	// return searchAll(entityClass, (Order[]) orders);
	// }

	/**
	 * 根据类和单个字段查询
	 * 
	 * @param entityClass 实体类
	 * @param attr 字段
	 * @param value 字段值
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T, F> List<T> search(final Class<T> entityClass, final SingularAttribute<? super T, F> attr, final F value, final Order... orders) {
		return search(entityClass, attr.getName(), value, (Order[]) orders);
	}

	/**
	 * 根据类和单个字段查询
	 * 
	 * @param entityClass 实体类
	 * @param attr 字段
	 * @param value 字段值
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> search(Class<T> entityClass, String attr, Object value, Order... orders) {
		CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		Predicate predicate = getCB().equal(root.get(attr), value);
		criteriaQuery.where(predicate);
		criteriaQuery.orderBy(processOrder(root, orders));
		TypedQuery<T> typedQuery = getEntityMgr().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	public static <T> List<T> pagingSearch(final Class<T> entityClass, PageStore pageStore) {
		CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		QueryLogics queryLogics = pageStore.getQueryLogics();
		Predicate[] predicates = JpaQueryLogicConvertor.convert(getCB(), root, queryLogics);
		criteriaQuery.where(predicates);
		Order[] orders = pageStore.getQueryLogics().getOrderArray();
		criteriaQuery.orderBy(processOrder(root, orders));
		List<T> result = JpaPagingSupport.paging(getEntityMgr(), criteriaQuery, root, pageStore);
		pageStore.setResult(result);
		return result;
	}

	/**
	 * 根据类和多个字段查询,
	 * 
	 * @param entityClass 实体类
	 * @param attrMap Map<SingularAttribute，字段值>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T, E> List<T> andQuery(final Class<T> entityClass, final Map<SingularAttribute<? super T, E>, E> attrMap, final Order... orders) {
		return andSearch(entityClass, convertMap(attrMap), (Order[]) orders);
	}

	/**
	 * 根据类和多个字段查询,
	 * 
	 * @param entityClass 实体类
	 * @param attrMap Map<SingularAttribute，字段值>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> andSearch(final Class<T> entityClass, final Map<String, Object> attrMap, final Order... orders) {
		CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		Predicate[] predicates = new Predicate[attrMap.size()];
		int idx = 0;
		for (Entry<String, Object> entry : attrMap.entrySet()) {
			predicates[idx] = getCB().equal(root.get(entry.getKey()), entry.getValue());
			idx++;
		}
		criteriaQuery.where(getCB().and(predicates));
		criteriaQuery.orderBy(processOrder(root, orders));
		TypedQuery<T> typedQuery = getEntityMgr().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	/**
	 * 根据类和多个字段查询进行or条件查询
	 * 
	 * @param clazz 实体类
	 * @param fieldMap Map<字段名，字段名>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T, E> List<T> orQuery(final Class<T> entityClass, final Map<SingularAttribute<? super T, E>, E> attrMap, final Order... orders) {
		return orSearch(entityClass, convertMap(attrMap), (Order[]) orders);
	}

	/**
	 * 根据类和多个字段查询进行or条件查询
	 * 
	 * @param clazz 实体类
	 * @param fieldMap Map<字段名，字段名>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> orSearch(final Class<T> entityClass, final Map<String, Object> attrMap, final Order... orders) {
		CriteriaQuery<T> criteriaQuery = getCB().createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		Predicate[] predicates = new Predicate[attrMap.size()];
		int idx = 0;
		for (Map.Entry<String, Object> entry : attrMap.entrySet()) {
			predicates[idx] = getCB().equal(root.get(entry.getKey()), entry.getValue());
			idx++;
		}
		criteriaQuery.where(getCB().and(predicates));
		criteriaQuery.orderBy(processOrder(root, orders));
		TypedQuery<T> typedQuery = getEntityMgr().createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	// /**
	// * 查询指定类的所有结果，返回指定属性的值
	// *
	// * @param entityClass 实体类
	// * @param field 属性
	// * @param orders 排序规则
	// * @return List<Object>
	// */
	// @SuppressWarnings({ "rawtypes" })
	// public static <T, E> List search(final Class entityClass, final
	// SingularAttribute<T, E> field, final Order... orders) {
	// List<SingularAttribute<T, E>> fieldList = new
	// ArrayList<SingularAttribute<T, E>>(1);
	// fieldList.add(field);
	// return search(entityClass, fieldList, orders);
	// }
	//
	// /**
	// * 查询指定类的所有结果，返回指定属性集合的值
	// *
	// * @param clazz Hibernate模型的类
	// * @param fieldList 属性名称集合
	// * @param orders 排序规则
	// * @return 单个字段：List<Object>， 多个字段：List<Object[]<>>
	// */
	// @SuppressWarnings({ "rawtypes" })
	// public static <T, E> List search(final Class<T> entityClass, final
	// List<SingularAttribute<T, E>> fieldList, final Order... orders) {
	// CriteriaBuilder criteriaBuilder = getEntityMgr().getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	//
	//
	// DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
	// ProjectionList projectionList = Projections.projectionList();
	// for (String fieldName : fieldList) {
	// projectionList.add(Projections.property(fieldName));
	// }
	// criteria.setProjection(projectionList);
	// addOrder(criteria, orders);
	// return hibernateTemplate().findByCriteria(criteria);
	// }

	/**
	 * 查询某序列的下一个值(ORACLE ONLY)
	 * 
	 * @param sequence 序列名
	 * @return 序列的下一个值
	 */
	public static long querySequence(final String sequence) {
		List<?> resultList = findBySql("select " + sequence + ".nextval from dual");
		return ((BigDecimal) resultList.get(0)).longValue();
	}

	/**
	 * 通过Sql语句查询
	 * 
	 * @param sql sql查询语句
	 * @return 查询结果列表
	 */
	public static List<?> findBySql(final String sql) {
		return jpaTemplate().execute(new JpaCallback<List>() {

			public List doInJpa(EntityManager em) throws PersistenceException {
				Query query = em.createNativeQuery(sql);
				return query.getResultList();
			}
		});
	}

	private static <T, E> Map<String, Object> convertMap(Map<SingularAttribute<? super T, E>, E> attrMap) {
		Map<String, Object> map = new HashMap<String, Object>(attrMap.size());
		for (Entry<SingularAttribute<? super T, E>, E> entry : attrMap.entrySet()) {
			map.put(entry.getKey().getName(), entry.getValue());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	private static final <T> List<javax.persistence.criteria.Order> processOrder(final Root<T> root, final Order... orders) {
		return getInstance().convertOrder(root, orders);
	}

	public static final JpaTemplate jpaTemplate() {
		return getInstance().getJpaTemplate();
	}

	public static final EntityManager getEntityMgr() {
		return getInstance().em;
	}

	private static final CriteriaBuilder getCB() {
		return getEntityMgr().getCriteriaBuilder();
	}

}

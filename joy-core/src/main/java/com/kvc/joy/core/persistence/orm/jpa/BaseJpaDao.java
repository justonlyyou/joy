package com.kvc.joy.core.persistence.orm.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import com.kvc.joy.commons.bean.Pair;
import com.kvc.joy.commons.exception.ExceptionTool;
import com.kvc.joy.commons.lang.GenericTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.commons.query.sort.Order;

public abstract class BaseJpaDao<T> extends JpaDaoSupport {

	protected static final Log log = LogFactory.getLog(BaseJpaDao.class);

	@PersistenceContext
	protected EntityManager em;

	public BaseJpaDao() {
	}

	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass() {
		return (Class<T>) GenericTool.getSuperClassGenricType(getClass());
	}

	protected List<T> doQuery(JPACallBack<T> callBack) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		Class<T> entityClass = getEntityClass();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.where(callBack.getRestriction(criteriaBuilder, root));
		
		// 排序
		Order[] orders = callBack.getOrders();
		if (orders != null) {
			criteriaQuery.orderBy(convertOrder(root, orders));
		}
		
		TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
		
		// 分页
		Pair<Integer, Integer> range = callBack.getPageRange();
		if (range != null) {
			typedQuery = typedQuery.setFirstResult(range.getFirst()).setMaxResults(range.getSecond());	
		}
		
		return typedQuery.getResultList();
	}
	
	/**
	 * count查询
	 * 
	 * @param callBack 回调
	 * @return 结果数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月1日 上午12:13:21
	 */
	protected long count(JPACallBack<T> callBack) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(getEntityClass());
		criteriaQuery.where(callBack.getRestriction(criteriaBuilder, root));
		criteriaQuery.select(criteriaBuilder.countDistinct(root));
		TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery.getSingleResult();
	}
	

	/**
	 * 查询唯一结果
	 * 
	 * @param typedQuery
	 * @return
	 */
	protected T uniqueResult(final TypedQuery<T> typedQuery) {
		List<T> resultList = typedQuery.getResultList();
		return uniqueResult(resultList);
	}

	public static <T> T uniqueResult(final List<T> resultList) {
		T result = null;
		if (resultList.size() == 1) {
			result = resultList.get(0);
		} else if (resultList.size() > 1) {
			log.error("期望获取唯一记录，实际查询到" + resultList.size() + "条记录，方法调用轨迹为：");
			ExceptionTool.printStackTrace();
		}
		return result;
	}

	@Resource
	public void setJPATemplate(JpaTemplate jpaTemplate) {
		super.setJpaTemplate(jpaTemplate);
	}

	protected abstract class JPACallBack<T> {
		
		public abstract Expression<Boolean> getRestriction(CriteriaBuilder cb, Root<T> root);
		
		public Pair<Integer, Integer> getPageRange() {
			return null;
		}
		
		public Order[] getOrders() {
			return null;
		}
	}

	// public void persist(Object engity) {
	// getJpaTemplate().persist(engity);
	// }
	//
	// public T merge(T engity) {
	// return getJpaTemplate().merge(engity);
	// }
	//
	// public void remove(Object engity) {
	// getJpaTemplate().remove(engity);
	// }
	//
	// public void flush() {
	// getJpaTemplate().flush();
	// }
	//
	// public void refresh(Object engity) {
	// getJpaTemplate().refresh(engity);
	// }
	//
	// /**
	// * 通过主键加载实体对象
	// *
	// * @param entityClass 实体类
	// * @param id 主键值
	// * @return 实体对象
	// */
	// public T get(Class<T> entityClass, Object id) {
	// return getJpaTemplate().find(entityClass, id);
	// }
	//
	// // /**
	// // * 通过主键加载实体代理
	// // *
	// // * @param entityClass 实体类
	// // * @param id 主键值
	// // * @return 实体代理
	// // */
	// // public <T> T load(Class<T> entityClass, Object id) {
	// // return getJpaTemplate().load(entityClass, id);
	// // }
	//
	// /**
	// * in查询
	// *
	// * @param entityClass 实体类
	// * @param attr 字段
	// * @param values 字段值集合
	// * @return 指定类名对象的结果列表
	// */
	// public <E> List<T> inSearch(final Class<T> entityClass, final
	// SingularAttribute<T, E> attr, final Collection<E> values) {
	// if (values == null || values.isEmpty()) {
	// return new ArrayList<T>(0);
	// }
	// Set<E> set = new HashSet<E>(values);
	// final List<T> resultList = new ArrayList<T>(set.size());
	// try {
	// new GroupExecutor<E>(set) {
	//
	// @Override
	// public void groupExecute(List<E> subList) {
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	// criteriaQuery.where(root.get(attr).in(values));
	// TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
	// resultList.addAll(typedQuery.getResultList());
	// }
	//
	// }.execute();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return resultList;
	// }
	//
	// // /**
	// // * in查询, 查找主键
	// // *
	// // * @param clazz 实体类
	// // * @param collection 字段值集合
	// // * @return 指定类名对象的结果列表
	// // */
	// // @SuppressWarnings("rawtypes")
	// // public <T extends IEntity, E> List<T> inSearch(final Class<T>
	// // clazz, final Collection<E> collection) {
	// // return inSearch(clazz, "id", collection);
	// // }
	//
	// /**
	// * 查询指定类的所有结果
	// *
	// * @param entityClass 实体类
	// * @param orders 排序规则
	// * @return 指定类名对象的结果列表
	// */
	// public List<T> searchAll(final Class<T> entityClass, final JpaOrder...
	// orders) {
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	// criteriaQuery.select(root);
	// criteriaQuery.orderBy(convertOrder(root, orders));
	// TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
	// return typedQuery.getResultList();
	// }
	//
	// private final List<Order> convertOrder(final Root<T> root, final
	// JpaOrder... orders) {
	// if (orders == null || orders.length == 0) {
	// return new ArrayList<Order>(0);
	// }
	// List<Order> orderList = new ArrayList<Order>(orders.length);
	// for (JpaOrder jpaOrder : orders) {
	// String propertyName = jpaOrder.getPropertyName();
	// Path<T> path = root.get(propertyName);
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// if (jpaOrder.isAscending()) {
	// orderList.add(criteriaBuilder.asc(path));
	// } else {
	// orderList.add(criteriaBuilder.desc(path));
	// }
	// }
	// return orderList;
	// }
	//
	// /**
	// * 根据类和单个字段查询
	// *
	// * @param entityClass 实体类
	// * @param attr 字段
	// * @param value 字段值
	// * @param orders 排序规则
	// * @return 指定类名对象的结果列表
	// */
	// public <F> List<T> search(final Class<T> entityClass, final
	// SingularAttribute<T, F> attr, final F value, final JpaOrder... orders) {
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	// criteriaQuery.where(criteriaBuilder.equal(root.get(attr), value));
	// criteriaQuery.orderBy(convertOrder(root, orders));
	// TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
	// return typedQuery.getResultList();
	// }
	//
	// /**
	// * 根据类和多个字段查询,
	// *
	// * @param entityClass 实体类
	// * @param attrMap Map<SingularAttribute，字段值>
	// * @param orders 排序规则
	// * @return 指定类名对象的结果列表
	// */
	// public <E> List<T> search(final Class<T> entityClass, final
	// Map<SingularAttribute<T, E>, E> attrMap, final JpaOrder... orders) {
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	// List<Predicate> predicates = new ArrayList<Predicate>(attrMap.size());
	// for (Entry<SingularAttribute<T, E>, E> entry : attrMap.entrySet()) {
	// predicates.add(criteriaBuilder.equal(root.get(entry.getKey()),
	// entry.getValue()));
	// }
	// criteriaQuery.where(criteriaBuilder.and((Predicate[])
	// predicates.toArray()));
	// criteriaQuery.orderBy(convertOrder(root, orders));
	// TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
	// return typedQuery.getResultList();
	// }
	//
	// /**
	// * 根据类和多个字段查询进行or条件查询
	// *
	// * @param clazz 实体类
	// * @param fieldMap Map<字段名，字段名>
	// * @param orders 排序规则
	// * @return 指定类名对象的结果列表
	// */
	// public <E> List<T> orSearch(final Class<T> entityClass, final
	// Map<SingularAttribute<T, E>, E> attrMap, final JpaOrder... orders) {
	// return orSearch(entityClass, attrMap.entrySet(), orders);
	// }
	//
	// /**
	// * 根据类和多个字段查询进行or条件查询
	// *
	// * @param entityClass 实体类
	// * @param fields Collection<Map.Entry<字段名，字段名>>
	// * @param orders 排序规则
	// * @return 指定类名对象的结果列表
	// */
	// public <E> List<T> orSearch(final Class<T> entityClass, final
	// Collection<Map.Entry<SingularAttribute<T, E>, E>> fields, final
	// JpaOrder... orders) {
	// CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// CriteriaQuery<T> criteriaQuery =
	// criteriaBuilder.createQuery(entityClass);
	// Root<T> root = criteriaQuery.from(entityClass);
	// List<Predicate> predicates = new ArrayList<Predicate>(fields.size());
	// for (Map.Entry<SingularAttribute<T, E>, E> entry : fields) {
	// predicates.add(criteriaBuilder.equal(root.get(entry.getKey()),
	// entry.getValue()));
	// }
	// criteriaQuery.where(criteriaBuilder.and((Predicate[])
	// predicates.toArray()));
	// criteriaQuery.orderBy(convertOrder(root, orders));
	// TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
	// return typedQuery.getResultList();
	// }
	//
	// // /**
	// // * 查询指定类的所有结果，返回指定属性的值
	// // *
	// // * @param entityClass 实体类
	// // * @param field 属性
	// // * @param orders 排序规则
	// // * @return List<Object>
	// // */
	// // @SuppressWarnings({ "rawtypes" })
	// // public <T, E> List search(final Class entityClass, final
	// // SingularAttribute<T, E> field, final JpaOrder... orders) {
	// // List<SingularAttribute<T, E>> fieldList = new
	// // ArrayList<SingularAttribute<T, E>>(1);
	// // fieldList.add(field);
	// // return search(entityClass, fieldList, orders);
	// // }
	// //
	// // /**
	// // * 查询指定类的所有结果，返回指定属性集合的值
	// // *
	// // * @param clazz Hibernate模型的类
	// // * @param fieldList 属性名称集合
	// // * @param orders 排序规则
	// // * @return 单个字段：List<Object>， 多个字段：List<Object[]<>>
	// // */
	// // @SuppressWarnings({ "rawtypes" })
	// // public <T, E> List search(final Class<T> entityClass, final
	// // List<SingularAttribute<T, E>> fieldList, final JpaOrder... orders) {
	// // CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	// // CriteriaQuery<T> criteriaQuery =
	// // criteriaBuilder.createQuery(entityClass);
	// // Root<T> root = criteriaQuery.from(entityClass);
	// //
	// //
	// // DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
	// // ProjectionList projectionList = Projections.projectionList();
	// // for (String fieldName : fieldList) {
	// // projectionList.add(Projections.property(fieldName));
	// // }
	// // criteria.setProjection(projectionList);
	// // addOrder(criteria, orders);
	// // return hibernateTemplate().findByCriteria(criteria);
	// // }
	//
	// /**
	// * 查询某序列的下一个值(ORACLE ONLY)
	// *
	// * @param sequence 序列名
	// * @return 序列的下一个值
	// */
	// public long querySequence(final String sequence) {
	// List<?> resultList = findBySql("select " + sequence +
	// ".nextval from dual");
	// return ((BigDecimal) resultList.get(0)).longValue();
	// }
	//
	// /**
	// * 通过Sql语句查询
	// *
	// * @param sql sql查询语句
	// * @return 查询结果列表
	// */
	// public List<?> findBySql(final String sql) {
	// return getJpaTemplate().execute(new JpaCallback<List<?>>() {
	//
	// public List<?> doInJpa(EntityManager em) throws PersistenceException {
	// Query query = em.createNativeQuery(sql);
	// return query.getResultList();
	// }
	// });
	// }

	protected CriteriaBuilder getCriteriaBuilder() {
		return em.getCriteriaBuilder();
	}

	protected List<javax.persistence.criteria.Order> convertOrder(final Root<T> root, final Order... orders) {
		if (orders == null || orders.length == 0) {
			return new ArrayList<javax.persistence.criteria.Order>(0);
		}
		List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>(orders.length);
		for (Order order : orders) {
			String propertyName = order.getProperty();
			Path<Object> path = root.get(propertyName);
			if (order.isAscending()) {
				orderList.add(getCriteriaBuilder().asc(path));
			} else {
				orderList.add(getCriteriaBuilder().desc(path));
			}
		}
		return orderList;
	}

}

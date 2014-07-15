package org.joy.plugin.persistence.hibernate;

import org.joy.commons.bean.IEntity;
import org.joy.commons.collections.MapTool;
import org.joy.commons.lang.ArrayTool;
import org.joy.commons.support.GroupExecutor;
import org.joy.core.persistence.support.entity.UuidEntity;
import org.joy.core.spring.utils.SpringBeanTool;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * Hibernate工具类
 * 
 * @author <b>Kevice</b>
 */
@Transactional
public class HibernateTool extends BaseHibernateDao {

	// private static Logger logger = Logger.getLogger(HibernateUtils.class);

	// private HibernateUtils() {
	// }

	private static HibernateTool getInstance() {
		return (HibernateTool) SpringBeanTool.getBean("hibernateTool");
	}

	public static <T> T get(Class<T> clazz, Serializable id) {
		return getInstance().getObjById(clazz, id);
	}

	public <T> T getObjById(Class<T> clazz, Serializable id) {
		T result;
		// try {
		result = (T) getSession().get(clazz, id);
		// } catch (ObjectNotFoundException e) {
		// // 虽然都说用get方法加载不到对象时会返回null，但有时还是抛这个异常了，原因还不明
		// logger.warn("用session.get()加载对象失败！");
		// }
		return result;
	}

	public static void persist(Object entity) {
		UuidEntity.setUuid(entity);
        getSession().saveOrUpdate(entity);
	}

    @Transactional
    public void persistWithTx(Object entity) {
        persist(entity);
    }

    public static void batchPersist(Collection<?> entities) {
        for(Object entity : entities) {
            persist(entity);
        }
    }

    @Transactional
    public void batchPersistWithTx(Collection<?> entities) {
        batchPersist(entities);
    }

	public static void remove(Object obj) {
		getInstance().delete(obj);
	}

    @Transactional
    public void removeWithTx(Object entity) {
        remove(entity);
    }

    public static void batchRemove(Collection<?> entities) {
        for(Object entity : entities) {
            remove(entity);
        }
    }

    @Transactional
    public void batchRemoveWithTx(Collection<?> entities) {
        batchRemove(entities);
    }

	public void delete(Object obj) {
		getSession().delete(obj);
	}

	public static void evict(Object obj) {
		getInstance().evictEntity(obj);
	}

	public void evictEntity(Object obj) {
		getSession().evict(obj);
	}

	public static void flush() {
		getInstance().flushSession();
	}

	public void flushSession() {
		getSession().flush();
	}

	public static void refresh(Object obj) {
		getInstance().refreshEntity(obj);
	}

	public void refreshEntity(Object obj) {
		getSession().refresh(obj);
	}

	/**
	 * 批量保存对象，默认每1000条提交一次并清空缓存
	 * 
	 * @param objects 待保存的对象集合
	 * @author Kevice
	 * @date 2012-5-16 下午02:16:16
	 */
	public static void batchSave(Collection<?> objects) {
		batchSave(objects, 1000);
	}

	/**
	 * 批量保存对象，按指定批量大小提交一次并清空缓存
	 * 
	 * @param objects 待保存的对象集合
	 * @author Kevice
	 * @date 2012-5-16 下午02:17:44
	 */
	public static void batchSave(Collection<?> objects, int batchSize) {
		// TODO
		for (Object object : objects) {
			persist(object);
		}
	}

	/**
	 * 批量删除对象
	 * 
	 * @param objects 待删除的对象集合
	 * @author Kevice
	 * @date 2012-5-16 下午02:22:37
	 */
	public static void batchDelete(Collection<?> objects) {
		for (Object object : objects) {
			remove(object);
		}
	}

	/**
	 * in查询
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldName 字段名
	 * @param collection 字段值集合
	 * @return 指定类名对象的结果列表
	 */
	public static <T, E> List<T> inSearch(Class<T> clazz, String fieldName, Collection<E> collection) {
		return getInstance().in(clazz, fieldName, collection);
	}

	public <T, E> List<T> in(final Class<T> clazz, final String fieldName, final Collection<E> collection) {
		if (collection == null || collection.isEmpty()) {
			return new ArrayList<T>(0);
		}
		Set<E> set = new HashSet<E>(collection);
		final List<T> resultList = new ArrayList<T>(set.size());
		new GroupExecutor<E>(set) {

			@Override
			@SuppressWarnings("unchecked")
			public void groupExecute(List<E> subList) {
				DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
				criteria.add(Restrictions.in(fieldName, subList));
				List<T> subResultList = find(criteria);
				resultList.addAll(subResultList);
			}

		}.execute();
		return resultList;
	}

	/**
	 * in查询, 查找主键
	 * 
	 * @param clazz Hibernate模型的类
	 * @param collection 字段值集合
	 * @return 指定类名对象的结果列表
	 */
	@SuppressWarnings("rawtypes")
	public static <T extends IEntity, E> List<T> inSearch(final Class<T> clazz, Collection<E> collection) {
		return inSearch(clazz, "id", collection);
	}

	/**
	 * 查询指定类的所有结果
	 * 
	 * @param clazz Hibernate模型的类
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> search(final Class<T> clazz, final Order... orders) {
		return getInstance().searchAll(clazz, orders);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> searchAll(final Class<T> clazz, final Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		addOrder(criteria, orders);
		return find(criteria);
	}

	/**
	 * 根据类和单个字段查询
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldName 字段名
	 * @param fieldValue 字段值
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> search(final Class<T> clazz, final String fieldName, final Object fieldValue) {
		Map<String, Object> fieldMap = new HashMap<String, Object>(1);
		fieldMap.put(fieldName, fieldValue);
		return andSearch(clazz, fieldMap);
	}

	/**
	 * 根据类和多个字段查询进行and条件查询
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldMap Map<字段名，字段名>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> andSearch(final Class<T> clazz, final Map<String, Object> fieldMap, final Order... orders) {
		return getInstance().andQuery(clazz, fieldMap, orders);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> andQuery(final Class<T> clazz, final Map<String, Object> fieldMap, final Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		for (Entry<String, Object> entry : fieldMap.entrySet()) {
			Object value = entry.getValue();
			Criterion criterion;
			if (value == null) {
				criterion = Restrictions.isNull(entry.getKey());
			} else {
				criterion = Restrictions.eq(entry.getKey(), value);
			}
			criteria.add(criterion);
		}
		addOrder(criteria, orders);
		return find(criteria);
	}

	/**
	 * 根据类和多个字段查询进行or条件查询
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldMap Map<字段名，字段名>
	 * @param orders 排序规则
	 * @return 指定类名对象的结果列表
	 */
	public static <T> List<T> orSearch(final Class<T> clazz, final Map<String, Object> fieldMap, final Order... orders) {
		return getInstance().orQuery(clazz, fieldMap, orders);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> orQuery(final Class<T> clazz, final Map<String, Object> fieldMap, final Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		Criterion[] orCriterions = new Criterion[fieldMap.size()];
		int i = 0;
		for (Entry<String, Object> entry : fieldMap.entrySet()) {
			orCriterions[i] = Restrictions.eq(entry.getKey(), entry.getValue());
			i++;
		}
		criteria.add(getInstance().appendOrCriterions(orCriterions));
		addOrder(criteria, orders);
		return find(criteria);
	}

	/**
	 * 查询指定类的所有结果，返回指定属性的值
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldName 属性名
	 * @param orders 排序规则
	 * @return List<Object>
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List search(final Class clazz, final String fieldName, final Order... orders) {
		List<String> fieldNameList = new ArrayList<String>(1);
		fieldNameList.add(fieldName);
		return search(clazz, fieldNameList, orders);
	}

	/**
	 * 查询指定类的所有结果，返回指定属性集合的值
	 * 
	 * @param clazz Hibernate模型的类
	 * @param fieldNameList 属性名称集合
	 * @param orders 排序规则
	 * @return 单个字段：List<Object>， 多个字段：List<Object[]<>>
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List search(final Class<?> clazz, final List<String> fieldNameList, final Order... orders) {
		return getInstance().query(clazz, fieldNameList, orders);
	}

	@SuppressWarnings({ "rawtypes" })
	public List query(final Class<?> clazz, final List<String> fieldNameList, final Order... orders) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		ProjectionList projectionList = Projections.projectionList();
		for (String fieldName : fieldNameList) {
			projectionList.add(Projections.property(fieldName));
		}
		addOrder(criteria, orders);
		criteria.setProjection(projectionList);
		return find(criteria);
	}

	/**
	 * 查询某序列的下一个值
	 * 
	 * @param sequence 序列名
	 * @return 序列的下一个值
	 */
	public static long querySequence(String sequence) {
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
		return getInstance().findUsingSql(sql);
	}

	public List<?> findUsingSql(String sql) {
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	/**
	 * 执行更新或删除的hql语句
	 * 
	 * @param hql 更新或删除的hql语句
	 * @param params 参数值可变数组
	 * @return 成功修改或删除的数据条数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月2日 下午10:34:06
	 */
	public static int executeByHql(final String hql, final Object... params) {
		return getInstance().executeUsingHql(hql, params);
	}

	public int executeUsingHql(final String hql, final Object... params) {
		Query query = getSession().createQuery(hql);
		if (ArrayTool.isNotEmpty(params)) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	/**
	 * 执行更新或删除的hql语句
	 * 
	 * @param hql 更新或删除的hql语句
	 * @param params 参数名与值的对应关系
	 * @return 成功修改或删除的数据条数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年11月2日 下午10:34:06
	 */
	public static int executeByHql(final String hql, final Map<String, Object> params) {
		return getInstance().executeUsingHql(hql, params);
	}

	public int executeUsingHql(final String hql, final Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		if (MapTool.isNotEmpty(params)) {
			query.setProperties(params);
		}
		return query.executeUpdate();
	}

	/**
	 * 添加排序规则
	 * 
	 * @param criteria Criteria
	 * @param orders 排序规则
	 * @author Kevice
	 * @date 2012-3-2 下午06:57:03
	 */
	private static void addOrder(final DetachedCriteria criteria, final Order... orders) {
		if (orders != null) {
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
	}

	public static Session getSession() {
		return getInstance().session();
	}

	@SuppressWarnings("rawtypes")
	public static List findByCriteria(DetachedCriteria detachedCriteria) {
		return getInstance().find(detachedCriteria);
	}

}

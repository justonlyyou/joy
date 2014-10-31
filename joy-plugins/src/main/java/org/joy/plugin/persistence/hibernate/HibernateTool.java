package org.joy.plugin.persistence.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.joy.commons.bean.IEntity;
import org.joy.commons.collections.MapTool;
import org.joy.commons.lang.ArrayTool;
import org.joy.commons.support.GroupExecutor;
import org.joy.core.persistence.support.entity.UuidEntity;
import org.joy.core.spring.utils.SpringBeanTool;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

/**
 * hibernate工具类(spring bean)
 *
 * @author Kevice
 * @time 2012-5-16 下午02:16:16
 * @since 1.0.0
 */
@Transactional
public class HibernateTool extends BaseHibernateDao {

    // private static Logger logger = Logger.getLogger(HibernateUtils.class);

    // private HibernateUtils() {
    // }

    private static HibernateTool getInstance() {
        return (HibernateTool) SpringBeanTool.getBean("hibernateTool");
    }

    /**
     * 返回主键对应的实体
     *
     * @param clazz 实体类
     * @param id    主键值
     * @param <T>   实体类
     * @return 实体对象，不存在时返回null
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static <T> T get(Class<T> clazz, Serializable id) {
        return (T) getSession().get(clazz, id);
    }

    /**
     * 持久化实体，如果是UuidEntity的实体，如果没有主键将自动设置
     *
     * @param entity 实体对象
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void persist(Object entity) {
        UuidEntity.setUuid(entity);
        getSession().saveOrUpdate(entity);
    }

    /**
     * 持久化实体(带有事务)，如果是UuidEntity的实体，如果没有主键将自动设置
     *
     * @param entity 实体对象
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    @Transactional
    public void persistWithTx(Object entity) {
        persist(entity);
    }

    /**
     * 持久化实体，如果是UuidEntity的实体，如果没有主键将自动设置
     *
     * @param entities 实体对象集合
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void batchPersist(Collection<?> entities) {
        for (Object entity : entities) {
            persist(entity);
        }
    }

    /**
     * 持久化实体(带有事务)，如果是UuidEntity的实体，如果没有主键将自动设置
     *
     * @param entities 实体对象集合
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    @Transactional
    public void batchPersistWithTx(Collection<?> entities) {
        batchPersist(entities);
    }

    /**
     * 删除实体
     *
     * @param entity 实体
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void remove(Object entity) {
        getSession().delete(entity);
    }

    /**
     * 删除实体(带有事务)
     *
     * @param entity 实体
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    @Transactional
    public void removeWithTx(Object entity) {
        remove(entity);
    }

    /**
     * 批量删除实体
     *
     * @param entities 实体集合
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void batchRemove(Collection<?> entities) {
        for (Object entity : entities) {
            remove(entity);
        }
    }

    /**
     * 批量删除实体(带有事务)
     *
     * @param entities 实体集合
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    @Transactional
    public void batchRemoveWithTx(Collection<?> entities) {
        batchRemove(entities);
    }

    /**
     * 从session中踢除实体
     *
     * @param entity 实体对象
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void evict(Object entity) {
        getSession().evict(entity);
    }

    /**
     * 刷新session
     *
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void flush() {
        getSession().flush();
    }

    /**
     * 刷新实体
     *
     * @param entity 实体对象
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void refresh(Object entity) {
        getSession().refresh(entity);
    }

    /**
     * 批量保存实体，默认每1000条提交一次并清空缓存
     *
     * @param entities 待保存的实体对象集合
     * @author Kevice
     * @time 2012-5-16 下午02:16:16
     * @since 1.0.0
     */
    public static void batchSave(Collection<?> entities) {
        batchSave(entities, 1000);
    }

    /**
     * 批量保存实体，按指定批量大小提交一次并清空缓存
     *
     * @param entities  待保存的实体对象集合
     * @param batchSize 每批大小(多少条刷新一次session)
     * @author Kevice
     * @time 2012-5-16 下午02:17:44
     * @since 1.0.0
     */
    public static void batchSave(Collection<?> entities, int batchSize) {
        // TODO
        for (Object object : entities) {
            persist(object);
        }
    }

    /**
     * 批量删除实体
     *
     * @param entities 待删除的实体对象集合
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static void batchDelete(Collection<?> entities) {
        for (Object entity : entities) {
            remove(entity);
        }
    }

    /**
     * in查询
     *
     * @param clazz        实体类
     * @param propertyName 属性名
     * @param collection   属性值集合
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static <T, E> List<T> inSearch(final Class<T> clazz, final String propertyName, Collection<E> collection) {
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
                criteria.add(Restrictions.in(propertyName, subList));
                List<T> subResultList = getInstance().find(criteria);
                resultList.addAll(subResultList);
            }

        }.execute();
        return resultList;
    }

    /**
     * in查询, 查找主键
     *
     * @param clazz      实体类
     * @param collection 属性值集合
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    @SuppressWarnings("rawtypes")
    public static <T extends IEntity, E> List<T> inSearch(final Class<T> clazz, Collection<E> collection) {
        return inSearch(clazz, "id", collection);
    }

    /**
     * 查询实体类所有结果
     *
     * @param clazz  实体类
     * @param orders 排序规则可变数组
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static <T> List<T> search(final Class<T> clazz, final Order... orders) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        addOrder(criteria, orders);
        return getInstance().find(criteria);
    }

    /**
     * 根据类和单个属性查询
     *
     * @param clazz         实体类
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static <T> List<T> search(final Class<T> clazz, final String propertyName, final Object propertyValue) {
        Map<String, Object> propertyMap = new HashMap<>(1);
        propertyMap.put(propertyName, propertyValue);
        return andSearch(clazz, propertyMap);
    }

    /**
     * 根据实体类和多个属性查询进行and条件查询
     *
     * @param clazz       实体类
     * @param propertyMap Map<属性名，属性名>
     * @param orders      排序规则可变数组
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static <T> List<T> andSearch(final Class<T> clazz, final Map<String, Object> propertyMap, final Order... orders) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        for (Entry<String, Object> entry : propertyMap.entrySet()) {
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
        return getInstance().find(criteria);
    }

    /**
     * 根据实体类和多个属性查询进行or条件查询
     *
     * @param clazz       实体类
     * @param propertyMap Map<属性名，属性名>
     * @param orders      排序规则可变数组
     * @return 实体对象列表
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static <T> List<T> orSearch(final Class<T> clazz, final Map<String, Object> propertyMap, final Order... orders) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        Criterion[] orCriterions = new Criterion[propertyMap.size()];
        int i = 0;
        for (Entry<String, Object> entry : propertyMap.entrySet()) {
            orCriterions[i] = Restrictions.eq(entry.getKey(), entry.getValue());
            i++;
        }
        criteria.add(getInstance().appendOrCriterions(orCriterions));
        addOrder(criteria, orders);
        return getInstance().find(criteria);
    }

    /**
     * 查询实体类所有结果，返回指定属性的值
     *
     * @param clazz        实体类
     * @param propertyName 属性名
     * @param orders       排序规则可变数组
     * @return List<Object>
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    @SuppressWarnings({"rawtypes"})
    public static List search(final Class clazz, final String propertyName, final Order... orders) {
        List<String> propertyNameList = new ArrayList<String>(1);
        propertyNameList.add(propertyName);
        return search(clazz, propertyNameList, orders);
    }

    /**
     * 查询实体类所有结果，返回指定属性集合的值
     *
     * @param clazz            实体类
     * @param propertyNameList 属性名称集合
     * @param orders           排序规则可变数组
     * @return 单个属性：List<Object>， 多个属性：List<Object[]<>>
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    @SuppressWarnings({"rawtypes"})
    public static List search(final Class<?> clazz, final List<String> propertyNameList, final Order... orders) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        ProjectionList projectionList = Projections.projectionList();
        for (String propertyName : propertyNameList) {
            projectionList.add(Projections.property(propertyName));
        }
        addOrder(criteria, orders);
        criteria.setProjection(projectionList);
        return getInstance().find(criteria);
    }

    /**
     * 查询某序列的下一个值
     *
     * @param sequence 序列名
     * @return 序列的下一个值
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
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
     * @author Kevice
     * @time 2012-5-16 下午02:22:37
     * @since 1.0.0
     */
    public static List<?> findBySql(final String sql) {
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        return sqlQuery.list();
    }

    /**
     * 执行更新或删除的hql语句
     *
     * @param hql    更新或删除的hql语句
     * @param params 参数值可变数组
     * @return 成功修改或删除的数据条数
     * @author Kevice
     * @time 2013年11月2日 下午10:34:06
     * @since 1.0.0
     */
    public static int executeByHql(final String hql, final Object... params) {
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
     * @param hql    更新或删除的hql语句
     * @param params 参数名与值的对应关系
     * @return 成功修改或删除的数据条数
     * @author Kevice
     * @time 2013年11月2日 下午10:34:06
     * @since 1.0.0
     */
    public static int executeByHql(final String hql, final Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        if (MapTool.isNotEmpty(params)) {
            query.setProperties(params);
        }
        return query.executeUpdate();
    }

    /**
     * 添加排序规则可变数组
     *
     * @param criteria Criteria
     * @param orders   排序规则可变数组
     * @author Kevice
     * @time 2012-3-2 下午06:57:03
     * @since 1.0.0
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

    /**
     * 根据指定的DetachedCriteria执行查询
     *
     * @param detachedCriteria DetachedCriteria
     * @return List<Object>
     * @author Kevice
     * @time 2012-3-2 下午06:57:03
     * @since 1.0.0
     */
    @SuppressWarnings("rawtypes")
    public static List findByCriteria(DetachedCriteria detachedCriteria) {
        return getInstance().find(detachedCriteria);
    }

}

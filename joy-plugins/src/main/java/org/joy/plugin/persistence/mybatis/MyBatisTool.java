package org.joy.plugin.persistence.mybatis;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.joy.commons.bean.IEntity;
import org.joy.commons.query.QueryLogic;
import org.joy.commons.query.QueryLogics;
import org.joy.commons.query.sort.Order;
import org.joy.commons.support.GroupExecutor;
import org.joy.core.persistence.support.entity.UuidEntity;
import org.joy.core.rp.pagestore.PageStore;
import org.joy.core.spring.utils.SpringBeanTool;
import org.joy.plugin.persistence.mybatis.service.IEntityMappingHolder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Connection;
import java.util.*;

/**
 * MyBatis工具类，主要提供以面向对象方式使用MyBatis的功能
 *
 * @author Kevice
 * @time 2014年10月15日 下午22:36:42
 * @since 1.0.0
 */
public class MyBatisTool {

    private SqlSessionTemplate sqlSessionTemplate;
    private IEntityMappingHolder entityMappingHolder;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void setEntityMappingHolder(IEntityMappingHolder entityMappingHolder) {
        this.entityMappingHolder = entityMappingHolder;
    }

    public static Connection getConnection() {
//        sqlSession().getConnection() //! 得到的连接是已关闭的
        SqlSessionTemplate st = (SqlSessionTemplate) sqlSession();
        Connection connection = SqlSessionUtils.getSqlSession(
                st.getSqlSessionFactory(), st.getExecutorType(),
                st.getPersistenceExceptionTranslator()).getConnection();
        return connection;
    }

    public IEntityMappingHolder getEntityMappingHolder() {
        return entityMappingHolder;
    }

    private static IEntityMappingHolder entityMappingHolder() {
//        return getInstance().entityMappingHolder; //! 结果是null
        return getInstance().getEntityMappingHolder();
    }

    private static MyBatisTool getInstance() {
        return (MyBatisTool) SpringBeanTool.getBean("myBatisTool");
    }

    public static SqlSession sqlSession() {
        return getInstance().getSqlSessionTemplate();
    }

    public static void save(IEntity entity) {
        Class<? extends IEntity> clazz = entity.getClass();
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        UuidEntity.setUuid(entity);
        mapper._insert(clazz, entity);
    }

    public static void update(IEntity entity) {
        Class<? extends IEntity> clazz = entity.getClass();
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        mapper._update(clazz, entity);
    }

    public static void remove(IEntity entity) {
        Class<? extends IEntity> clazz = entity.getClass();
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        mapper._delete(clazz, (Serializable) entity.getId());
    }

    public static void remove(Class<? extends IEntity> clazz, Serializable id) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        mapper._delete(clazz, id);
    }

    @Transactional
    public void persistWithTx(IEntity entity) {
        save(entity);
    }

    public static void batchSave(Collection<? extends IEntity> entities) {
        for (IEntity entity : entities) {
            save(entity);
        }
    }

    @Transactional
    public void batchSaveWithTx(Collection<? extends IEntity> entities) {
        batchSave(entities);
    }

    @Transactional
    public void removeWithTx(IEntity entity) {
        remove(entity);
    }

    public static void batchRemove(Collection<? extends IEntity> entities) {
        for (IEntity entity : entities) {
            remove(entity);
        }
    }

    @Transactional
    public void batchRemoveWithTx(Collection<? extends IEntity> entities) {
        batchRemove(entities);
    }

    /**
     * 根据MyBatis实体类和主键，获取实体对象
     *
     * @param clazz MyBatis实体类
     * @param id    主键值
     * @return 实体对象
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    public static <T extends IEntity> T get(Class<T> clazz, Serializable id) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return (T) mapper._get(clazz, id);
    }

    /**
     * in查询, 查找主键
     *
     * @param clazz      实体类
     * @param collection 字段值集合
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity, E> List<T> inSearch(Class<T> clazz, Collection<E> collection) {
        return inSearch(clazz, "id", collection);
    }

    /**
     * in查询，返回实体类对象列表
     *
     * @param clazz        实体类
     * @param propertyName 属性名
     * @param collection   in条件值集合
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity, E> List<T> inSearch(Class<T> clazz, String propertyName, Collection<E> collection) {
        return getInstance().in(clazz, propertyName, collection, null, null);
    }

    /**
     * in查询，只返回指定的单个属性的值
     *
     * @param clazz          实体类
     * @param propertyName   属性名
     * @param collection     in条件值集合
     * @param returnProperty 要返回的属性名
     * @return 指定属性的值列表
     */
    public static <E> List<E> inSearch(Class<? extends IEntity> clazz, String propertyName, Collection<E> collection, String returnProperty) {
        return getInstance().in(clazz, propertyName, collection, returnProperty, null);
    }

    /**
     * in查询，只返回指定属性的值
     *
     * @param clazz            实体类
     * @param propertyName     属性名
     * @param collection       in条件值集合
     * @param returnProperties 要返回的属性名集合
     * @return List<Map<指定的属性名, 属性值>>
     */
    public static <E> List<Map<String, E>> inSearch(Class<? extends IEntity> clazz, String propertyName, Collection<E> collection, Collection<String> returnProperties) {
        return getInstance().in(clazz, propertyName, collection, null, returnProperties);
    }

    private <E> List in(final Class<? extends IEntity> clazz, final String propertyName, Collection<E> collection,
                       final String returnProperty, final Collection<String> returnProperties) {
        if (collection == null || collection.isEmpty()) {
            return new ArrayList<IEntity>(0);
        }
        final BaseMapper mapper = entityMappingHolder.getMapper(clazz);
        Set<E> set = new HashSet<E>(collection);
        final List resultList = new ArrayList(set.size());
        new GroupExecutor<E>(set) {

            @Override
            @SuppressWarnings("unchecked")
            public void groupExecute(List<E> subList) {
                Map<String, Object> elemMap = new HashMap<>(subList.size());
                for (int i = 0; i < subList.size(); i++) {
                    elemMap.put("" + i, subList.get(i));
                }
                List subResultList = null;
                if (returnProperty != null) {
                    subResultList = mapper._inSearchReturnProperty(clazz, propertyName, returnProperty, elemMap);
                } else if (returnProperties != null) {
                    subResultList = mapper._inSearchReturnProperties(clazz, propertyName, returnProperties, elemMap);
                } else {
                    subResultList = mapper._inSearch(clazz, propertyName, elemMap);
                }
                resultList.addAll(subResultList);
            }

        }.execute();
        return resultList;
    }

    /**
     * 查询指定类的所有结果
     *
     * @param clazz  实体类
     * @param orders 排序规则
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity> List<T> allSearch(Class<T> clazz, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._search(clazz, orders);
    }

    /**
     * 查询指定类的所有结果，只返回指定的单个属性的列表
     *
     * @param clazz        实体类
     * @param propertyName 属性名
     * @param orders       排序规则
     * @return List<Object>
     */
    public static List allSearch(Class<? extends IEntity> clazz, String propertyName, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._searchReturnProperty(clazz, propertyName, orders);
    }

    /**
     * 查询指定类的所有结果，只返回指定属性的列表
     *
     * @param clazz      实体类
     * @param properties 属性名称集合
     * @param orders     排序规则
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> allSearch(Class<? extends IEntity> clazz, Collection<String> properties, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._searchReturnProperties(clazz, properties, orders);
    }

    /**
     * 根据类和单个字段查询
     *
     * @param clazz         实体类
     * @param propertyName  属性名
     * @param propertyValue 属性值
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity> List<T> search(Class<T> clazz, String propertyName, Object propertyValue) {
        Map<String, Object> fieldMap = new HashMap<String, Object>(1);
        fieldMap.put(propertyName, propertyValue);
        return andSearch(clazz, fieldMap);
    }

    /**
     * 根据类和多个属性进行and条件查询，返回实体类对象的列表
     *
     * @param clazz       实体类
     * @param propertyMap Map<属性名，属性名>
     * @param orders      排序规则
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity> List<T> andSearch(Class<T> clazz, Map<String, Object> propertyMap, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._andSearch(clazz, propertyMap, orders);
    }

    /**
     * 根据类和多个属性进行and条件查询，只返回指定的单个属性的列表
     *
     * @param clazz          实体类
     * @param propertyMap    Map<属性名，属性名>
     * @param returnProperty 要返回的属性名
     * @param orders         排序规则
     * @return List<指定的属性的值>
     */
    public static List andSearch(Class<? extends IEntity> clazz, Map<String, Object> propertyMap, String returnProperty, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._andSearchReturnProperty(clazz, propertyMap, returnProperty, orders);
    }

    /**
     * 根据类和多个属性进行and条件查询，只返回指定属性的列表
     *
     * @param clazz            实体类
     * @param propertyMap      Map<属性名，属性名>
     * @param returnProperties 要返回的属性名集合
     * @param orders           排序规则
     * @return List<Map<指定的属性名，属性值>>
     */
    public static List<Map<String, Object>> andSearch(Class<? extends IEntity> clazz, Map<String, Object> propertyMap, Collection<String> returnProperties, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._andSearchReturnProperties(clazz, propertyMap, returnProperties, orders);
    }

    /**
     * 根据类和多个属性进行or条件查询，返回实体类对象的列表
     *
     * @param clazz       实体类
     * @param propertyMap Map<属性名，属性名>
     * @param orders      排序规则
     * @return 指定类名对象的结果列表
     */
    public static <T extends IEntity> List<T> orSearch(Class<T> clazz, Map<String, Object> propertyMap, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._orSearch(clazz, propertyMap, orders);
    }

    /**
     * 根据类和多个属性进行or条件查询，只返回指定的单个属性的列表
     *
     * @param clazz          实体类
     * @param propertyMap    Map<属性名，属性名>
     * @param returnProperty 要返回的属性名
     * @param orders         排序规则
     * @return List<指定的属性的值>
     */
    public static <T extends IEntity> List<T> orSearch(Class<T> clazz, Map<String, Object> propertyMap, String returnProperty, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._orSearchReturnProperty(clazz, propertyMap, returnProperty, orders);
    }

    /**
     * 根据类和多个属性进行or条件查询，只返回指定的属性的列表
     *
     * @param clazz            实体类
     * @param propertyMap      Map<属性名，属性名>
     * @param returnProperties 要返回的属性名集合
     * @param orders           排序规则
     * @return List<Map<指定的属性名，属性值>>
     */
    public static <T extends IEntity> List<T> orSearch(Class<T> clazz, Map<String, Object> propertyMap, Collection<String> returnProperties, Order... orders) {
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._orSearchReturnProperties(clazz, propertyMap, returnProperties, orders);
    }

    /**
     * 分页查询，返回实体类对象列表
     *
     * @param clazz 实体类
     * @param pageStore PageStore
     * @return List<实体类对象>
     */
    public static <T extends IEntity> List<T> pagingSearch(Class<T> clazz, PageStore pageStore) {
        QueryLogics queryLogics = pageStore.getQueryLogics();
        List<QueryLogic> conditions = queryLogics.getConditions();
        Map<String, QueryLogic> map = new HashMap<>();
        for (int i = 0; i < conditions.size(); i++) {
            map.put(""+i, conditions.get(i));
        }
        BaseMapper mapper = entityMappingHolder().getMapper(clazz);
        return mapper._pagingSearch(clazz, pageStore.getPaging(), map, queryLogics.getOrderArray());
    }

    public static int delete(String sqlStatement) {
        return sqlSession().delete(sqlStatement);
    }

    public static int delete(String sqlStatement, Object parameter) {
        return sqlSession().delete(sqlStatement, parameter);
    }

    public static int insert(String sqlStatement, Object parameter) {
        return sqlSession().insert(sqlStatement, parameter);
    }

    public static int insert(String sqlStatement) {
        return sqlSession().insert(sqlStatement);
    }


    public static void select(String sqlStatement, Object parameter, ResultHandler handler) {
        sqlSession().select(sqlStatement, parameter, handler);
    }

    public static void select(String sqlStatement, ResultHandler handler) {
        sqlSession().select(sqlStatement, handler);
    }

    public static <T> List<T> selectList(String sqlStatement, Object parameter) {
        return sqlSession().<T>selectList(sqlStatement, parameter);
    }

    public static <T> List<T> selectList(String sqlStatement) {
        return sqlSession().<T>selectList(sqlStatement);
    }

    public static Map<String, Object> selectMap(String sqlStatement, Object parameter, String mapKey) {
        return sqlSession().selectMap(sqlStatement, parameter, mapKey);
    }

    public static Map<String, Object> selectMap(String sqlStatement, String mapKey) {
        return sqlSession().selectMap(sqlStatement, mapKey);
    }

    public static <T> T selectOne(String sqlStatement, Object parameter) {
        return sqlSession().<T>selectOne(sqlStatement, parameter);
    }

    public static <T> T selectOne(String sqlStatement) {
        return sqlSession().<T>selectOne(sqlStatement);
    }

    public static int update(String sqlStatement, Object parameter) {
        return sqlSession().update(sqlStatement, parameter);
    }

    public static int update(String sqlStatement) {
        return sqlSession().update(sqlStatement);
    }

}

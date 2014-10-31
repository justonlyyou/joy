package org.joy.plugin.persistence.mybatis;

import org.apache.ibatis.annotations.*;
import org.joy.commons.bean.IEntity;
import org.joy.commons.query.Paging;
import org.joy.commons.query.QueryLogic;
import org.joy.commons.query.sort.Order;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础Mapper <br>
 * 所有期望使用MyBatisTool类的基于对象操作数据库的工具方法的Mapper接口都应该继承该接口，并提供泛型参数 <br>
 * 不要直接使用该接口中定义的方法，应该通过MyBatisTool工具类调用!
 *
 * @author Kevice
 * @time 2014年10月20日 上午11:36:42
 * @since 1.0.0
 */
public interface BaseMapper<T extends IEntity> {

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
    @SelectProvider(type = SqlStatementProvider.class, method = "get")
    T _get(@Param("clazz") Class<T> clazz,
           @Param("id") Serializable id);

    /**
     * 保存新增的实体
     *
     * @param clazz  MyBatis实体类
     * @param entity 实体对象
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @InsertProvider(type = SqlStatementProvider.class, method = "insert")
    void _insert(@Param("clazz") Class<T> clazz,
                 @Param("entity") T entity);

    /**
     * 更新实体
     *
     * @param clazz  MyBatis实体类
     * @param entity 实体对象
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @UpdateProvider(type = SqlStatementProvider.class, method = "update")
    void _update(@Param("clazz") Class<T> clazz,
                 @Param("entity") T entity);

    /**
     * 删除主键对应的实体
     *
     * @param clazz MyBatis实体类
     * @param id    实体主键值
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @DeleteProvider(type = SqlStatementProvider.class, method = "delete")
    void _delete(@Param("clazz") Class<T> clazz,
                 @Param("id") Serializable id);

    /**
     * in查询，返回实体类对象列表
     *
     * @param clazz    实体类
     * @param property 属性名
     * @param elems    Map<顺序值，属性值>
     * @return 指定类名对象的结果列表
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "inSearch")
    List<T> _inSearch(@Param("clazz") Class<T> clazz,
                      @Param("property") String property,
                      @Param("elems") Map<String, Object> elems);

    /**
     * in查询，只返回指定的单个属性的值
     *
     * @param clazz          实体类
     * @param property       属性名
     * @param returnProperty 要返回的属性名
     * @param elems          Map<顺序值，属性值>
     * @return 指定属性的值列表
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "inSearchReturnProperty")
    List _inSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                 @Param("property") String property,
                                 @Param("returnProperty") String returnProperty,
                                 @Param("elems") Map<String, Object> elems);

    /**
     * in查询，只返回指定属性的值
     *
     * @param clazz            实体类
     * @param property         属性名
     * @param returnProperties 要返回的属性名集合
     * @param elems            Map<顺序值，属性值>
     * @return List<Map<指定的属性名, 属性值>>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "inSearchReturnProperties")
    List<Map<String, Object>> _inSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                        @Param("property") String property,
                                                        @Param("returnProperties") Collection<String> returnProperties,
                                                        @Param("elems") Map<String, Object> elems);

    /**
     * 查询指定类的所有结果
     *
     * @param clazz  实体类
     * @param orders 排序规则可变数组
     * @return 指定类名对象的结果列表
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "search")
    List<T> _search(@Param("clazz") Class<T> clazz,
                    @Param("orders") Order... orders);

    /**
     * 查询指定类的所有结果，只返回指定的单个属性的列表
     *
     * @param clazz    实体类
     * @param property 属性名
     * @param orders   排序规则可变数组
     * @return List<Object>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "searchReturnProperty")
    List _searchReturnProperty(@Param("clazz") Class<T> clazz,
                               @Param("returnProperty") String property,
                               @Param("orders") Order... orders);

    /**
     * 查询指定类的所有结果，只返回指定属性的列表
     *
     * @param clazz      实体类
     * @param properties 属性名称集合
     * @param orders     排序规则可变数组
     * @return List<Map<String, Object>>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "searchReturnProperties")
    List<Map<String, Object>> _searchReturnProperties(@Param("clazz") Class<T> clazz,
                                                      @Param("properties") Collection<String> properties,
                                                      @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行and条件查询，返回实体类对象的列表
     *
     * @param clazz      实体类
     * @param properties Map<属性名，属性名>
     * @param orders     排序规则可变数组
     * @return 指定类名对象的结果列表
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "andSearch")
    List<T> _andSearch(@Param("clazz") Class<T> clazz,
                       @Param("properties") Map<String, Object> properties,
                       @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行and条件查询，只返回指定的单个属性的列表
     *
     * @param clazz          实体类
     * @param properties     Map<属性名，属性名>
     * @param returnProperty 要返回的属性名
     * @param orders         排序规则可变数组
     * @return List<指定的属性的值>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "andSearchReturnProperty")
    List _andSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                  @Param("properties") Map<String, Object> properties,
                                  @Param("returnProperty") String returnProperty,
                                  @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行and条件查询，只返回指定属性的列表
     *
     * @param clazz            实体类
     * @param properties       Map<属性名，属性名>
     * @param returnProperties 要返回的属性名集合
     * @param orders           排序规则可变数组
     * @return List<Map<指定的属性名，属性值>>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "andSearchReturnProperties")
    List<Map<String, Object>> _andSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                         @Param("properties") Map<String, Object> properties,
                                                         @Param("returnProperties") Collection<String> returnProperties,
                                                         @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行or条件查询，返回实体类对象的列表
     *
     * @param clazz       实体类
     * @param properties Map<属性名，属性名>
     * @param orders      排序规则可变数组
     * @return 指定类名对象的结果列表
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "orSearch")
    List<T> _orSearch(@Param("clazz") Class<T> clazz,
                      @Param("properties") Map<String, Object> properties,
                      @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行or条件查询，只返回指定的单个属性的列表
     *
     * @param clazz          实体类
     * @param properties     Map<属性名，属性名>
     * @param returnProperty 要返回的属性名
     * @param orders         排序规则可变数组
     * @return List<指定的属性的值>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "orSearchReturnProperty")
    List _orSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                 @Param("properties") Map<String, Object> properties,
                                 @Param("returnProperty") String returnProperty,
                                 @Param("orders") Order... orders);

    /**
     * 根据类和多个属性进行or条件查询，只返回指定的属性的列表
     *
     * @param clazz            实体类
     * @param properties       Map<属性名，属性名>
     * @param returnProperties 要返回的属性名集合
     * @param orders           排序规则可变数组
     * @return List<Map<指定的属性名，属性值>>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "orSearchReturnProperties")
    List<Map<String, Object>> _orSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                        @Param("properties") Map<String, Object> properties,
                                                        @Param("returnProperties") Collection<String> returnProperties,
                                                        @Param("orders") Order... orders);

    /**
     * 分页查询，返回实体类对象列表
     *
     * @param clazz  实体类
     * @param paging 分页信息
     * @param logics 查询逻辑条件
     * @param orders 排序规则可变数组
     * @return List<实体类对象>
     * @author Kevice
     * @time 2014年10月22日 下午15:30:09
     * @since 1.0.0
     */
    @SelectProvider(type = SqlStatementProvider.class, method = "pagingSearch")
    List<T> _pagingSearch(@Param("clazz") Class<T> clazz,
                          @Param("paging") Paging paging,
                          @Param("logics") Map<String, QueryLogic> logics,
                          @Param("orders") Order... orders);

}

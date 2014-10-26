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
 * 基础Mapper，所有期望使用MyBatisTool类的基于对象操作数据库的工具方法的Mapper接口都应该继承该接口，并提供泛型参数
 *
 * @author Kevice
 * @time 2014年10月20日 上午11:36:42
 * @since 1.0.0
 */
public interface BaseMapper<T extends IEntity> {

    @SelectProvider(type = SqlStatementProvider.class, method = "get")
    T _get(@Param("clazz") Class<T> clazz,
           @Param("id") Serializable id);

    @InsertProvider(type = SqlStatementProvider.class, method = "insert")
    void _insert(@Param("clazz") Class<T> clazz,
                 @Param("entity") T entity);

    @UpdateProvider(type = SqlStatementProvider.class, method = "update")
    void _update(@Param("clazz") Class<T> clazz,
                 @Param("entity") T entity);

    @DeleteProvider(type = SqlStatementProvider.class, method = "delete")
    void _delete(@Param("clazz") Class<T> clazz,
                 @Param("id") Serializable id);

    @SelectProvider(type = SqlStatementProvider.class, method = "inSearch")
    List<T> _inSearch(@Param("clazz") Class<T> clazz,
                      @Param("property") String property,
                      @Param("elems") Map<String, Object> elems);

    @SelectProvider(type = SqlStatementProvider.class, method = "inSearchReturnProperty")
    List _inSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                 @Param("property") String property,
                                 @Param("returnProperty") String returnProperty,
                                 @Param("elems") Map<String, Object> elems);

    @SelectProvider(type = SqlStatementProvider.class, method = "inSearchReturnProperties")
    List<Map<String, Object>> _inSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                        @Param("property") String property,
                                                        @Param("returnProperties") Collection<String> returnProperties,
                                                        @Param("elems") Map<String, Object> elems);

    @SelectProvider(type = SqlStatementProvider.class, method = "search")
    List<T> _search(@Param("clazz") Class<T> clazz,
                    @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "searchReturnProperty")
    List _searchReturnProperty(@Param("clazz") Class<T> clazz,
                               @Param("returnProperty") String property,
                               @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "searchReturnProperties")
    List<Map<String, Object>> _searchReturnProperties(@Param("clazz") Class<T> clazz,
                                                      @Param("properties") Collection<String> properties,
                                                      @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "andSearch")
    List<T> _andSearch(@Param("clazz") Class<T> clazz,
                       @Param("properties") Map<String, Object> properties,
                       @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "andSearchReturnProperty")
    List _andSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                  @Param("properties") Map<String, Object> properties,
                                  @Param("returnProperty") String property,
                                  @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "andSearchReturnProperties")
    List<Map<String, Object>> _andSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                         @Param("properties") Map<String, Object> properties,
                                                         @Param("returnProperties") Collection<String> returnProperties,
                                                         @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "orSearch")
    List<T> _orSearch(@Param("clazz") Class<T> clazz,
                      @Param("properties") Map<String, Object> properties,
                      @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "orSearchReturnProperty")
    List _orSearchReturnProperty(@Param("clazz") Class<T> clazz,
                                 @Param("properties") Map<String, Object> properties,
                                 @Param("returnProperty") String property,
                                 @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "orSearchReturnProperties")
    List<Map<String, Object>> _orSearchReturnProperties(@Param("clazz") Class<T> clazz,
                                                        @Param("properties") Map<String, Object> properties,
                                                        @Param("returnProperties") Collection<String> returnProperties,
                                                        @Param("orders") Order... orders);

    @SelectProvider(type = SqlStatementProvider.class, method = "pagingSearch")
    List<T> _pagingSearch(@Param("clazz") Class<T> clazz,
                          @Param("paging") Paging paging,
                          @Param("logics") Map<String, QueryLogic> logics,
                          @Param("orders") Order... orders);

}

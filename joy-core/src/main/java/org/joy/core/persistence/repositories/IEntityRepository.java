package org.joy.core.persistence.repositories;

import org.joy.commons.bean.IEntity;
import org.joy.commons.query.sort.Order;
import org.joy.core.rp.pagestore.PageStore;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 实体仓库接口
 *
 * @author Kevice
 * @time 2012-6-26 下午7:27:33
 * @since 1.0.0
 */
public interface IEntityRepository<T extends IEntity<ID>, ID extends Serializable> {

    /**
     * 查询所有实体
     *
     * @param orders 排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:19:33
     * @since 1.0.0
     */
    Iterable<T> searchAll(Order... orders);

    /**
     * 对指定属性进行in查询
     *
     * @param attr   属性名
     * @param values 属性值集合
     * @param orders 排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:19:28
     * @since 1.0.0
     */
    Iterable<T> inSearch(String attr, Collection<?> values, Order... orders);

    /**
     * 对id属性进行in查询
     *
     * @param values 属性值集合
     * @param orders 排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:26:33
     * @since 1.0.0
     */
    Iterable<T> inSearch(Collection<?> values, Order... orders);

    /**
     * 查询满足指定属性所有实体，
     *
     * @param attr   属性名
     * @param value  属性值
     * @param orders 排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:50:02
     * @since 1.0.0
     */
    Iterable<T> search(String attr, Object value, Order... orders);

    /**
     * 分页查询
     *
     * @param pageStore PageStore
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:51:24
     * @since 1.0.0
     */
    Iterable<T> pagingSearch(PageStore pageStore);

    /**
     * 多个属性进行and查询
     *
     * @param attrMap Map<属性名，属性值>
     * @param orders  排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:53:27
     * @since 1.0.0
     */
    Iterable<T> andSearch(Map<String, Object> attrMap, Order... orders);

    /**
     * 多个属性进行or查询
     *
     * @param attrMap Map<属性名，属性值>
     * @param orders  排序规则
     * @return 实体对象迭代器
     * @author Kevice
     * @time 2012-6-26 下午8:54:03
     * @since 1.0.0
     */
    Iterable<T> orSearch(Map<String, Object> attrMap, Order... orders);

}

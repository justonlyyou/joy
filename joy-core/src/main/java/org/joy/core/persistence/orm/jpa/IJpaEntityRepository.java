package org.joy.core.persistence.orm.jpa;

import org.joy.commons.bean.IEntity;
import org.joy.commons.query.sort.Order;
import org.joy.core.persistence.repositories.IEntityRepository;
import org.joy.core.rp.pagestore.PageStore;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * JPA实体仓库接口
 *
 * @author Kevice
 * @time 2012-6-26 下午10:36:28
 * @since 1.0.0
 */
public interface IJpaEntityRepository<T extends IEntity<ID>, ID extends Serializable> extends IEntityRepository<T, ID>, JpaRepository<T, ID> {

    /**
     * 查找所有实体，支持指定排序
     *
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:19:33
     * @since 1.0.0
     */
    List<T> searchAll(Order... orders);

    /**
     * 对指定属性进行in查询
     *
     * @param attr   属性名
     * @param values 值集合
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:19:28
     * @since 1.0.0
     */
    List<T> inSearch(String attr, Collection<?> values, Order... orders);

    /**
     * 对id属性进行in查询
     *
     * @param values 值集合
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:26:33
     * @since 1.0.0
     */
    List<T> inSearch(Collection<?> values, Order... orders);

    /**
     * 查询符合单个属性值的记录
     *
     * @param attr   属性
     * @param value  属性值
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:50:02
     * @since 1.0.0
     */
    List<T> search(String attr, Object value, Order... orders);

    /**
     * 分页查询
     *
     * @param pageStore PageStore
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:51:24
     * @since 1.0.0
     */
    List<T> pagingSearch(PageStore pageStore);

    /**
     * 多属性进行and查询
     *
     * @param attrMap Map<属性名，属性值>
     * @param orders  排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:53:27
     * @since 1.0.0
     */
    List<T> andSearch(Map<String, Object> attrMap, Order... orders);

    /**
     * 多属性进行or查询
     *
     * @param attrMap Map<属性名，属性值>
     * @param orders  排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-26 下午8:54:03
     * @since 1.0.0
     */
    List<T> orSearch(Map<String, Object> attrMap, Order... orders);

    /**
     * 对指定属性进行in查询
     *
     * @param attr   jpa静态元模型属性
     * @param values 值集合
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-27 下午9:12:08
     * @since 1.0.0
     */
    <E> List<T> inSearch(SingularAttribute<? super T, E> attr, Collection<E> values, Order... orders);

    /**
     * 查询符合单个属性值的记录
     *
     * @param attr   jpa静态元模型属性
     * @param value  属性值
     * @param orders 排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-27 下午9:17:44
     * @since 1.0.0
     */
    <F> List<T> search(SingularAttribute<? super T, F> attr, F value, Order... orders);

    /**
     * 多属性进行and查询
     *
     * @param attrMap Map<jpa静态元模型属性，属性值>
     * @param orders  排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-27 下午9:18:37
     * @since 1.0.0
     */
    <E> List<T> andQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders);

    /**
     * 多属性进行or查询
     *
     * @param attrMap Map<jpa静态元模型属性，属性值>
     * @param orders  排序可变数组
     * @return 实体列表
     * @author Kevice
     * @time 2012-6-27 下午9:20:01
     * @since 1.0.0
     */
    <E> List<T> orQuery(Map<SingularAttribute<? super T, E>, E> attrMap, Order... orders);

    /**
     * 通过sql查询
     *
     * @param sql sql语句
     * @return List<Object>
     * @author Kevice
     * @time 2012-6-27 下午9:21:17
     * @since 1.0.0
     */
    List<?> findBySql(String sql);

    /**
     * 查询某序列的下一个值(ORACLE ONLY)
     *
     * @param sequence 序列名
     * @return 下一个序列
     * @author Kevice
     * @time 2012-6-27 下午9:22:00
     * @since 1.0.0
     */
    long querySequence(String sequence);

}

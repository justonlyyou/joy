/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.table.filter;

import javax.swing.RowFilter;

/**
 * 提供回调方法，供过滤组件回调。
 * 由多虑组件确合适调用对应的方法。
 * @author ckcs
 */
public interface FilterSetter {

    /**
     * 设置行过滤器
     * 这个方法可用于设置单独的过滤条件
     */
    public void setRowFilter(RowFilter rowFilter);

    /**
     * 过滤器准备好了
     * 这个方法可用于设置组合的过滤条件
     */
    public void rowFilterReady();

    /**
     * 清除其他的过滤器的值
     * @param currentFilter 当前调用的过滤器,设置为null的时候将清除所有的过滤器的值。
     */
    public void clearOtherFilterValue(FilterComponent currentFilter);

    /**
     * 清除某个过滤器组件的过滤条件
     * @param currentFilter 要清楚过滤条件的组件
     */
    public void clearFilter(FilterComponent currentFilter);

    /**
     * 清除所有的过滤条件,并清除过滤器组件上的值。
     */
    public void clearAllFilter();
}

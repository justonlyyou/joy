/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 * 默认的过滤设置实现，子类可以提供组件的实现。
 * @author ckcs
 */
public class DefaultFilterSetter implements FilterSetter {

    //过滤所在的表格
    private JTable table;

    public DefaultFilterSetter(JTable table) {
        this.table = table;
    }

    /**
     * @param rowFilter 行过滤
     */
    @Override
    public void setRowFilter(RowFilter rowFilter) {
        if (table != null) {
            TableRowSorter shorter = (TableRowSorter) table.getRowSorter();
            if (shorter != null) {
                shorter.setRowFilter(rowFilter);
            }
        }
    }

    @Override
    public void rowFilterReady() {
        TableRowSorter shorter = (TableRowSorter) table.getRowSorter();
        if (table != null & shorter != null) {
            //获得所有的显示的过滤器，组合成与操作。
            List<RowFilter<Object, Object>> filterList = new ArrayList<RowFilter<Object, Object>>();
            List<FilterComponent> filters = ((TopTableColumnModel) table.getColumnModel()).getFilters();
            RowFilter filter = null;
            for (FilterComponent filterComponent : filters) {
                filter = filterComponent.getFilterOfModelIndex();
                if (filter != null) {
                    filterList.add(filter);
                }
            }
            shorter.setRowFilter(RowFilter.andFilter(filterList));
        }
    }

    @Override
    public void clearOtherFilterValue(FilterComponent currentFilter) {
        if (table.getColumnModel() != null) {
            List<FilterComponent> filters = ((TopTableColumnModel) table.getColumnModel()).getFilters();
            for (FilterComponent filterComponent : filters) {
                if (currentFilter == null || filterComponent != currentFilter) {
                    filterComponent.setFilterValue(null);
                }
            }
        }
    }

    @Override
    public void clearAllFilter() {
        clearOtherFilterValue(null);
        setRowFilter(null);
    }

    @Override
    public void clearFilter(FilterComponent currentFilter) {
        currentFilter.setFilterValue(null);
        TableRowSorter shorter = (TableRowSorter) table.getRowSorter();
        if (table != null & shorter != null) {
            //获得所有的显示的过滤器，组合成与操作。
            List<RowFilter<Object, Object>> filteList = new ArrayList<RowFilter<Object, Object>>();
            List<FilterComponent> filters = ((TopTableColumnModel) table.getColumnModel()).getFilters();
            for (FilterComponent filterComponent : filters) {
                if (currentFilter == null || filterComponent != currentFilter) {
                    if (filterComponent != currentFilter && filterComponent.getFilterOfModelIndex() != null) {
                        filteList.add(filterComponent.getFilterOfModelIndex());
                    }
                }
            }
            shorter.setRowFilter(RowFilter.andFilter(filteList));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * 表格列模型
 * 负责同步列，过滤器，列组
 * @author ckcs
 */
public class TopTableColumnModel extends DefaultTableColumnModel {

    //表格头部
    private JTable table;
    //过滤器设置
    private FilterSetter filterSetter;

    public TopTableColumnModel(JTable table) {
        this.table = table;
        table.addPropertyChangeListener(this);
        filterSetter = new DefaultFilterSetter(table);
    }

    @Override
    public void addColumn(TableColumn aColumn) {
        if (aColumn == null) {
            throw new IllegalArgumentException("aColumn == null");
        }
        TopTableColumn topTableColumn = null;
        if (!(aColumn instanceof TopTableColumn)) {
            topTableColumn = new TopTableColumn(null, aColumn);

        } else {
            topTableColumn = (TopTableColumn) aColumn;
        }
        addColumn(topTableColumn);
    }

    //添加的过滤器,并将过滤组件添加的表头中
    //只有在列及header同时存在的时候才构造过滤器，并添加到表头
    public void addColumn(TopTableColumn aColumn) {
        super.addColumn(aColumn);
        TopTableColumn topTableColumn = (TopTableColumn) aColumn;
        //过滤器
        if (table.getTableHeader() != null) {
            TopTableHeader header = (TopTableHeader) table.getTableHeader();
            if (aColumn.getFilterComponent() == null) {
                aColumn.setFilterComponent(createFilterComponent(aColumn));
            }
            //表头存在的时候才设置过滤器
            header.add(topTableColumn.getFilterComponent());
        }
    }

    public FilterSetter getFilterSetter() {
        return filterSetter;
    }

    public void setFilterSetter(FilterSetter filterSetter) {
        this.filterSetter = filterSetter;
    }

    private FilterComponent createFilterComponent(TableColumn aColumn) {
        FilterComponent filterComponent = FilterEditorFactory.createDefaultRowFilter(aColumn.getModelIndex(),
                table.getModel().getColumnClass(aColumn.getModelIndex()), filterSetter);
        return filterComponent;
    }

    /**
     * 重新构建所有列的过滤器
     */
    public void flushColumnFilter() {
        TopTableColumn topTableColumn = null;
        FilterComponent filterComponent = null;
        //移除
        for (TableColumn tableColumn : tableColumns) {
            topTableColumn = (TopTableColumn) tableColumn;
            if (topTableColumn.getFilterComponent() != null) {
                table.getTableHeader().remove(topTableColumn.getFilterComponent());
            }
        }
        //添加
        for (TableColumn tableColumn : tableColumns) {
            filterComponent = createFilterComponent(tableColumn);
            topTableColumn = (TopTableColumn) tableColumn;
            topTableColumn.setFilterComponent(filterComponent);
            table.getTableHeader().add(filterComponent);
        }
    }


    //并将过滤组件从表头中移除
    @Override
    public void removeColumn(TableColumn column) {
        TopTableColumn topTableColumn = null;
        if (column instanceof TopTableColumn) {
            super.removeColumn(column);
            topTableColumn = (TopTableColumn) column;

        } else {
            for (TableColumn tableColumn : tableColumns) {
                topTableColumn = (TopTableColumn) tableColumn;
                if (topTableColumn.getTableColumn() == column) {
                    super.removeColumn(topTableColumn);
                    break;

                } else {
                    topTableColumn = null;
                }
            }
        }
        //移除过滤器
        if (topTableColumn != null && table.getTableHeader() != null) {
            TopTableHeader header = (TopTableHeader) table.getTableHeader();
            FilterComponent filter = topTableColumn.getFilterComponent();
            if (filter != null) {
                header.remove(filter);
                filterSetter.clearFilter(filter);
            }
        }
    }

    /**
     * 清除所有的列并触法适当的事件
     */
    public void clearTableColumn() {
        while (tableColumns.size() > 0) {
            removeColumn(tableColumns.get(0));
        }
    }

    /**
     * 获得最大的列的高度
     * @return
     */
    public int getMaxFilterHeight() {
        int heigth = 0;
        for (TableColumn tableColumn : tableColumns) {
            if (((TopTableColumn) tableColumn).getFilterComponent() != null) {
                heigth = Math.max(heigth, ((TopTableColumn) tableColumn).getFilterComponent().getPreferredSize().height);
            }

        }
        return heigth;
    }

    /**
     * 是否包含模列
     * @param column 要检查的列
     * @return
     */
    public boolean containColumn(TableColumn column) {
        for (TableColumn tableColumn : tableColumns) {
            if (tableColumn == column) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得所有的过滤器
     * @return
     */
    public List<FilterComponent> getFilters() {
        List<FilterComponent> filterList = new ArrayList<FilterComponent>();
        for (TableColumn tableColumn : tableColumns) {
            if (((TopTableColumn) tableColumn).getFilterComponent() != null) {
                filterList.add(((TopTableColumn) tableColumn).getFilterComponent());
            }
        }
        return filterList;
    }
}

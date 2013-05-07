/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.table.filter;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.Border;

/**
 * 过滤组件
 * @author ckcs
 */
public abstract class FilterComponent<T> extends JPanel {

    //确定过滤器对应的在模型中的列
    protected int modelIndex;
    //过滤设置
    protected FilterSetter filterSetter;

    /**
     * 提供过滤组件对应的列
     * 包外外部不可使用。
     * @param modelIndex 对应的模型中的列
     * @param filterSetter 过滤设置者
     */
    FilterComponent(int modelIndex, FilterSetter filterSetter) {
        this.modelIndex = modelIndex;
        this.filterSetter = filterSetter;
        setBorder(new FilterBorder());
        configVisible();
    }

    /**
     * 设置初始的可见性
     * 默认不显示
     */
    protected void configVisible() {
        setVisible(false);
    }

    /**
     * 提供过滤组件对应的列
     * @param column
     */
    public FilterComponent(int modelIndex) {
        this(modelIndex, null);
    }

    public void setFilterSetter(FilterSetter filterSetter) {
        this.filterSetter = filterSetter;
    }

    public FilterSetter getFilterSetter() {
        return filterSetter;
    }

    public int getModelIndex() {
        return modelIndex;
    }

    /**
     * 设置顾虑组件当前的值
     * @param value 值
     */
    public abstract void setFilterValue(T value);

    /**
     * 获得当前过滤组件的值
     * @return 值
     */
    public abstract T getFilterValue();

    /**
     * 获得某列对应的值
     * @param modelIndex 过滤组件对应的模型中列索引
     * @param filterValue 过滤值
     * @return 行过滤器
     */
    public abstract RowFilter getFilter(int modelIndex, T filterValue);

    /**
     * 获得过滤组件对应的模型索引的过滤器
     * @return
     */
    public RowFilter getFilterOfModelIndex() {
        return getFilter(modelIndex, getFilterValue());
    }

    //过滤器边框
    private class FilterBorder implements Border {

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Color color = UIManager.getColor("Table.gridColor");
            g.setColor(color);
            g.drawLine(x + width - 1, y, x + width - 1, y + height);
            g.setColor(c.getBackground().darker());
            g.drawLine(x, y + height - 1, x + width, y + height - 1);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 2, 1);
        }

        public boolean isBorderOpaque() {
            return true;
        }
    }
}

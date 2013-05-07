/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.table.filter;

import java.util.Vector;
import com.kvc.joy.swing.table.group.groupableheader.ColumnGroup;

/**
 * 表格配置
 * @author ckcs
 */
public class TableConfig {

    //是否记录配置需要记录
    private boolean needRecordConfig = true;
    //已经被隐藏的列
    private int[] hidedColumns;
    //显示的列
    private int[][] visibleColumnsWidth;
    //设置的列组
    private Vector<ColumnGroup> columnGroups;

    public TableConfig() {
    }

    public TableConfig(boolean needRecordConfig, int[][] visibleColumnsWidth, int[] hidedColumns,
            Vector<ColumnGroup> columnGroups) {
        this.needRecordConfig = needRecordConfig;
        this.visibleColumnsWidth = visibleColumnsWidth;
        this.hidedColumns = hidedColumns;
        this.columnGroups = columnGroups;
    }

    public Vector<ColumnGroup> getColumnGroups() {
        return columnGroups;
    }

    public void setColumnGroups(Vector<ColumnGroup> columnGroups) {
        this.columnGroups = columnGroups;
    }

    public int[] getHidedColumns() {
        return hidedColumns;
    }

    public void setHidedColumns(int[] hidedColumns) {
        this.hidedColumns = hidedColumns;
    }

    public int[][] getVisibleColumnsWidth() {
        return visibleColumnsWidth;
    }

    public void setVisibleColumnsWidth(int[][] visibleColumnsWidth) {
        this.visibleColumnsWidth = visibleColumnsWidth;
    }

    public boolean isNeedRecordConfig() {
        return needRecordConfig;
    }

    public void setNeedRecordConfig(boolean needRecordConfig) {
        this.needRecordConfig = needRecordConfig;
    }
}

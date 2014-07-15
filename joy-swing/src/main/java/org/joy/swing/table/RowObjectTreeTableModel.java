/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table;

import java.util.Vector;

import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

/**
 * 
 * @author Kevice
 */
public class RowObjectTreeTableModel<T> extends DefaultTreeTableModel {

    /**
     * 列名数组
     */
    protected String[] columnNames;
    /**
     * 行对象Vector
     */
    protected Vector<T> rowObjectVector;
    /**
     * 列对应的get方法名数组
     */
    protected String[] getterNames;
    /**
     * 列对应的set方法名数组
     */
    protected String[] setterNames;
    /**
     * 可以编辑的列数组
     */
    protected int[] editableColumnIndexes;

    public RowObjectTreeTableModel() {
        this(new String[]{});
    }

    /**
     * 构造函数
     * @param columns 列名
     */
    public RowObjectTreeTableModel(String... columnNames) {
        this.columnNames = columnNames;
        int columnCount = (columnNames == null ? 0 : columnNames.length);
        rowObjectVector = new Vector<T>(columnCount);
    }

    /**
     * Constructs ...
     * @param columnNames
     * @param editableColumnIndexes
     */
    public RowObjectTreeTableModel(String[] columnNames, int... editableColumnIndexes) {
        this(columnNames);
        this.editableColumnIndexes = editableColumnIndexes;
    }

    /**
     * 构造函数
     * @param columnNames 列名
     * @param getterNames 取得属性的方法名
     */
    public RowObjectTreeTableModel(String[] columnNames, String[] getterNames) {
        this(columnNames);
        this.getterNames = getterNames;
    }

    /**
     * 构造函数
     * @param columnNames  列名
     * @param getterNames get的方法名
     * @param editableColumnIndexes  可编辑的列
     */
    public RowObjectTreeTableModel(String[] columnNames, String[] getterNames, int... editableColumnIndexes) {
        this(columnNames, getterNames);
        this.editableColumnIndexes = editableColumnIndexes;
    }

    /**
     * 构造方法
     * @param columnNames 列名
     * @param getterNames get的方法名
     * @param setterNames set的方法名
     * @param editableColumnIndexes  可编辑的列
     */
    public RowObjectTreeTableModel(String[] columnNames, String[] getterNames, String[] setterNames, int... editableColumnIndexes) {
        this(columnNames, getterNames, editableColumnIndexes);
        this.setterNames = setterNames;
    }

    @Override
    public Object getValueAt(Object node, int columnIndex) {
        
        return super.getValueAt(node, columnIndex);
    }

    @Override
    public void setValueAt(Object value, Object node, int columnIndex) {
        super.setValueAt(value, node, columnIndex);
    }
}

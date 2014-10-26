/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.beans.PropertyChangeListener;

/**
 * 一个列的实现
 * 封装直接与之关联的，过滤器，列组
 * @author ckcs
 */
public class TopTableColumn extends TableColumn {

    //过滤组件
    private FilterComponent filterComponent;
    //表格列
    private TableColumn tableColumn;

    public TopTableColumn(FilterComponent filterComponent, TableColumn tableColumn) {
        this.filterComponent = filterComponent;
        this.tableColumn = tableColumn;
    }

    public TopTableColumn() {
        tableColumn = new TableColumn(0);
    }

    public TopTableColumn(int modelIndex) {
        tableColumn = new TableColumn(modelIndex, 75, null, null);
    }

    public TopTableColumn(int modelIndex, int width) {
        tableColumn = new TableColumn(modelIndex, width, null, null);
    }

    public TopTableColumn(int modelIndex, int width, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        tableColumn = new TableColumn(modelIndex, width, cellRenderer, cellEditor);
    }

    public TableColumn getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(TableColumn tableColumn) {
        this.tableColumn = tableColumn;
    }

    public FilterComponent getFilterComponent() {
        return filterComponent;
    }

    public void setFilterComponent(FilterComponent filterComponent) {
        this.filterComponent = filterComponent;
    }

    @Override
    public void sizeWidthToFit() {
        tableColumn.sizeWidthToFit();
    }

    @Override
    public void setWidth(int width) {
        tableColumn.setWidth(width);
    }

    @Override
    public void setResizable(boolean isResizable) {
        tableColumn.setResizable(isResizable);
    }

    @Override
    public void setPreferredWidth(int preferredWidth) {
        tableColumn.setPreferredWidth(preferredWidth);
    }

    @Override
    public void setModelIndex(int modelIndex) {
        tableColumn.setModelIndex(modelIndex);
    }

    @Override
    public void setMinWidth(int minWidth) {
        tableColumn.setMinWidth(minWidth);
    }

    @Override
    public void setMaxWidth(int maxWidth) {
        tableColumn.setMaxWidth(maxWidth);
    }

    @Override
    public void setIdentifier(Object identifier) {
        tableColumn.setIdentifier(identifier);
    }

    @Override
    public void setHeaderValue(Object headerValue) {
        tableColumn.setHeaderValue(headerValue);
    }

    @Override
    public void setHeaderRenderer(TableCellRenderer headerRenderer) {
        tableColumn.setHeaderRenderer(headerRenderer);
    }

    @Override
    public void setCellRenderer(TableCellRenderer cellRenderer) {
        tableColumn.setCellRenderer(cellRenderer);
    }

    @Override
    public void setCellEditor(TableCellEditor cellEditor) {
        tableColumn.setCellEditor(cellEditor);
    }

    @Override
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        tableColumn.removePropertyChangeListener(listener);
    }

    @Override
    public int getWidth() {
        return tableColumn.getWidth();
    }

    @Override
    public boolean getResizable() {
        return tableColumn.getResizable();
    }

    @Override
    public synchronized PropertyChangeListener[] getPropertyChangeListeners() {
        return tableColumn.getPropertyChangeListeners();
    }

    @Override
    public int getPreferredWidth() {
        return tableColumn.getPreferredWidth();
    }

    @Override
    public int getModelIndex() {
        return tableColumn.getModelIndex();
    }

    @Override
    public int getMinWidth() {
        return tableColumn.getMinWidth();
    }

    @Override
    public int getMaxWidth() {
        return tableColumn.getMaxWidth();
    }

    @Override
    public Object getIdentifier() {
        return tableColumn.getIdentifier();
    }

    @Override
    public Object getHeaderValue() {
        return tableColumn.getHeaderValue();
    }

    @Override
    public TableCellRenderer getHeaderRenderer() {
        return tableColumn.getHeaderRenderer();
    }

    @Override
    public TableCellRenderer getCellRenderer() {
        return tableColumn.getCellRenderer();
    }

    @Override
    public TableCellEditor getCellEditor() {
        return tableColumn.getCellEditor();
    }

    @Override
    public void enableResizedPosting() {
        tableColumn.enableResizedPosting();
    }

    @Override
    public void disableResizedPosting() {
        tableColumn.disableResizedPosting();
    }

    @Override
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        tableColumn.addPropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return tableColumn == null ? "null" : tableColumn.getHeaderValue().toString();
    }
}

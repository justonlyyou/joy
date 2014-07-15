/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.group;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.joy.swing.table.filter.TopTableHeaderUI;

/**
 * 组合表头的ui
 * @author ckcs
 */
public class TopGroupTableHeadUI extends TopTableHeaderUI {

    public TopGroupTableHeadUI() {
    }

    /**
     * 绘制表头
     * @param g
     * @param c
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        if (header.getColumnModel().getColumnCount() <= 0) {
            return;
        }
        boolean ltr = header.getComponentOrientation().isLeftToRight();

        Rectangle clip = g.getClipBounds();
        Point left = clip.getLocation();
        Point right = new Point(clip.x + clip.width - 1, clip.y);
        TableColumnModel cm = header.getColumnModel();
        int cMin = header.columnAtPoint(ltr ? left : right);
        int cMax = header.columnAtPoint(ltr ? right : left);
        // This should never happen.
        if (cMin == -1) {
            cMin = 0;
        }
        // If the table does not have enough columns to fill the view we'll get -1.
        // Replace this with the index of the last column.
        if (cMax == -1) {
            cMax = cm.getColumnCount() - 1;
        }

        TableColumn draggedColumn = header.getDraggedColumn();
        Rectangle cellRect = getHeaderContentRect(ltr ? cMin : cMax);
        int columnWidth = 0;
        int totalHeight = cellRect.height;
        TableColumn tableColumn = null;
        Vector<SimpleColumnGroup> groups = null;
        //如果某个组已经绘制过了，则不再绘制
        List<SimpleColumnGroup> paintedGroup = new ArrayList();
        if (ltr) {
            for (int column = cMin; column <= cMax; column++) {
                tableColumn = cm.getColumn(column);
                columnWidth = tableColumn.getWidth();
                groups = header.getColumnGroups(tableColumn);
                if (groups != null) {
                    //每列的高度
                    for (SimpleColumnGroup group : groups) {
                        cellRect.height = getGroupRenderer(group).getPreferredSize().height;
                        cellRect.width = calculateColumnsWidth(group);
                        if (!paintedGroup.contains(group)) {
                            paintGroup(g, cellRect, tableColumn, group);
                            paintedGroup.add(group);
                        }
                        cellRect.y += cellRect.height;
                    }
                }
                cellRect.width = tableColumn.getWidth();
                cellRect.height = totalHeight - cellRect.y;
                paintCell(g, cellRect, column);
                cellRect.x += columnWidth;
                cellRect.y = 0;
            }

        } else {
            for (int column = cMin; column <= cMax; column++) {
                tableColumn = cm.getColumn(column);
                columnWidth = tableColumn.getWidth();
                groups = header.getColumnGroups(tableColumn);
                if (groups != null) {
                    //每列的高度
                    for (SimpleColumnGroup group : groups) {
                        cellRect.height = getGroupRenderer(group).getPreferredSize().height;
                        cellRect.width = calculateColumnsWidth(group);
                        if (!paintedGroup.contains(group)) {
                            paintGroup(g, cellRect, tableColumn, group);
                            paintedGroup.add(group);
                        }
                        cellRect.y += cellRect.height;
                    }
                }
                cellRect.width = tableColumn.getWidth();
                cellRect.height = totalHeight - cellRect.y;
                if (tableColumn != draggedColumn) {
                    paintCell(g, cellRect, column);
                }
                cellRect.x += columnWidth;
                cellRect.y = 0;
            }
        }

        // Remove all components in the rendererPane.
        rendererPane.removeAll();
    }

    private void paintGroup(Graphics g, Rectangle cellRect, TableColumn column, SimpleColumnGroup cGroup) {
        Component component = getGroupRenderer(cGroup);
        rendererPane.add(component);
        rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
    }

    //组的渲染器
    private Component getGroupRenderer(SimpleColumnGroup group) {
        TableCellRenderer renderer = group.getHeaderRenderer();
        if (renderer == null) {
            return getHeaderRenderer(group.getHeaderValue());

        } else {
            return renderer.getTableCellRendererComponent(header.getTable(),
                    group.getHeaderValue(), false, false, -1, -1);
        }
    }

    //列的渲染器
    protected Component getHeaderRenderer(String value) {
        TableCellRenderer renderer = header.getDefaultRenderer();
        boolean hasFocus = !header.isPaintingForPrint() && header.hasFocus();
        return renderer.getTableCellRendererComponent(header.getTable(),
                value, false, hasFocus, -1, -1);
    }

    //获得内容区域的高度
    //组的将被认为是内容
    @Override
    protected int getHeaderContentHeight() {
        int height = 0;
        TableColumnModel columnModel = header.getColumnModel();
        //高度
        for (int column = 0; column < columnModel.getColumnCount(); column++) {
            TableColumn aColumn = columnModel.getColumn(column);
            //获得组的深度
            Vector<SimpleColumnGroup> groups = header.getColumnGroups(aColumn);
            int groupHeight = 0;
            if (groups != null) {
                groupHeight = getGroupRenderer(groups.get(0)).getPreferredSize().height * header.getColumnGroups(aColumn).size();
            }
            Component comp = getColumnRenderer(column);
            //最后高度 = 列高度 + 组高度
            int rendererHeight = comp.getPreferredSize().height + groupHeight;
            height = Math.max(height, rendererHeight);

        }
        return height;
    }

    //获得内容区域的高度
    protected int getColumnContentHeight() {
        int height = 0;
        TableColumnModel columnModel = header.getColumnModel();
        //高度
        for (int column = 0; column < columnModel.getColumnCount(); column++) {
            //获得组的深度
            Component comp = getColumnRenderer(column);
            height = Math.max(height, comp.getPreferredSize().height);

        }
        return height;
    }

    //计算单前组的宽度
    //确定组中直接间接包含的列在模型中是否存在就可以确定单前组中几列已经被隐藏了
    //从而未被隐藏的列的总宽度即为组的宽度。
    private int calculateColumnsWidth(SimpleColumnGroup group) {
        int width = 0;
        List<Integer> columnList = new ArrayList<Integer>();
        group.getInculdeColumnModelIndex(columnList);
        int count = header.getColumnModel().getColumnCount();
        for (Integer modelIndex : columnList) {
            for (int index = 0; index < count; index++) {
                if (header.getColumnModel().getColumn(index).getModelIndex() == modelIndex) {
                    width += header.getColumnModel().getColumn(index).getWidth();
                    break;
                }
            }
        }
        return width;
    }
}

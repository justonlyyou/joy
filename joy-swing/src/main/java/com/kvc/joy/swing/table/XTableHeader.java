/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.table;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import com.kvc.joy.swing.XComponent;
import com.kvc.joy.swing.XComponentListener;

/**
 * 基本表格头部
 * @author zy
 */
public class XTableHeader extends JTableHeader implements XComponent {

    /**
     * Constructs ...
     *
     */
    public XTableHeader() {
        super();
    }

    /**
     * Constructs ...
     *
     *
     * @param columnModel
     */
    public XTableHeader(TableColumnModel columnModel) {
        super(columnModel);
        addListener();
    }

    /**
     * Method description
     *
     *
     * @param listener
     */
    @Override
    public void addXComponentListener(XComponentListener listener) {
        listenerList.add(XComponentListener.class, listener);
    }

    /**
     * Method description
     *
     *
     * @param listener
     */
    @Override
    public void removeXComponentListener(XComponentListener listener) {
        listenerList.remove(XComponentListener.class, listener);
    }

//    /**
//     * Method description
//     *
//     *
//     * @param g
//     */
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        SwingUtility.fireComponentAftrePaint(listenerList);
//    }
//
    /**
     * Method description
     *
     */
    private void addListener() {
        MouseAdapter mouseHandler = new MouseAdapter() {

//            private Map<Integer, Boolean> sortableMap = new HashMap<Integer, Boolean>(getColumnModel().getColumnCount());

            //ToptableHead 内部判断
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                int column = columnAtPoint(e.getPoint());
//
//                column = table.convertColumnIndexToModel(column);
//
//                RowSorter rowSorter = getTable().getRowSorter();
//
//                if ((column >= 0) && (rowSorter instanceof TableRowSorter)) {
//                    if (isSortable(column)) {
//                        ((TableRowSorter) rowSorter).setSortable(column, getCursor().getType() != Cursor.E_RESIZE_CURSOR);
//                    }
//                }
//            }
//
//            protected boolean isSortable(int column) {
//                Boolean sortable = sortableMap.get(column);
//
//                if ((sortable == null) && (getTable().getRowSorter() instanceof TableRowSorter)) {
//                    sortable = ((TableRowSorter) getTable().getRowSorter()).isSortable(column);
//                    sortableMap.put(column, sortable);
//                }
//
//                return (sortable == null) ? false : sortable.booleanValue();
//            }
//
//            protected void retoreSortable(int column) {
//                Boolean sortable = sortableMap.get(column);
//
//                if ((sortable != null) && sortable) {
//                    ((TableRowSorter) getTable().getRowSorter()).setSortable(column, true);
//                }
//            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    if (getCursor().getType() == Cursor.E_RESIZE_CURSOR) {
                        int column = getResizingColumnIndex(e.getPoint());
                        if (column < 0) {
                            return;
                        }

                        int columnWidth = TableUtil.getMaxColumnWidth(getTable(), column, true, 5);
                        if (getTable().getAutoResizeMode() == JTable.AUTO_RESIZE_OFF) {
                            if (columnWidth > getColumnModel().getColumn(column).getWidth()) {
                                changedColumnForOffMode(columnWidth - getColumnModel().getColumn(column).getWidth());
                            }
                            getColumnModel().getColumn(column).setPreferredWidth(columnWidth);

                        } else {
                            getColumnModel().getColumn(column).setPreferredWidth(columnWidth);
                            int newColumnWidth = getColumnModel().getColumn(column).getWidth();
                            if ((newColumnWidth < columnWidth) && (getColumnModel().getColumnCount() > 1)) {
                                int perReduceWidth = ((columnWidth - newColumnWidth) / (getColumnModel().getColumnCount() - 1)) + 2;
                                for (int index = 0, size = getColumnModel().getColumnCount(); index < size; index++) {
                                    if (index != column) {
                                        TableColumn tableColumn = getColumnModel().getColumn(index);
                                        tableColumn.setPreferredWidth(tableColumn.getWidth() - perReduceWidth);
                                    }
                                }
                                getColumnModel().getColumn(column).setPreferredWidth(columnWidth);
                            }
                        }

                        SwingUtilities.invokeLater(new Runnable() {

                            public void run() {
                                revalidate();
                                repaint();
                            }
                        });
                    }
                }
            }

            private int getResizingColumnIndex(Point p) {
                return getResizingColumnIndex(p, columnAtPoint(p));
            }

            private int getResizingColumnIndex(Point p, int column) {
                if (column == -1) {
                    return -1;
                }

                Rectangle r = getHeaderRect(column);

                r.grow(-3, 0);

                if (r.contains(p)) {
                    return -1;
                }

                int midPoint = r.x + r.width / 2;
                int columnIndex;

                if (getComponentOrientation().isLeftToRight()) {
                    columnIndex = (p.x < midPoint) ? column - 1 : column;
                } else {
                    columnIndex = (p.x < midPoint) ? column : column - 1;
                }

                if (columnIndex == -1) {
                    return -1;
                }

                return columnIndex;
            }

            private void changedColumnForOffMode(int diff) {
                Container container;
                JTable table;

                if ((getParent() == null) || ((container = getParent().getParent()) == null) || !(container instanceof JScrollPane) || ((table = getTable()) == null)) {
                    return;
                }

                JViewport viewport = ((JScrollPane) container).getViewport();
                int viewportWidth = viewport.getWidth();
                int newHeaderWidth = table.getWidth() + diff;

                /* Resize a table */
                Dimension tableSize = table.getSize();

                tableSize.width += diff;
                table.setSize(tableSize);

                /*
                 *  If this table is in AUTO_RESIZE_OFF mode and
                 * has a horizontal scrollbar, we need to update
                 * a view's position.
                 */
                if ((newHeaderWidth >= viewportWidth) && (table.getAutoResizeMode() == JTable.AUTO_RESIZE_OFF)) {
                    Point p = viewport.getViewPosition();
                    p.x = Math.max(0, Math.min(newHeaderWidth - viewportWidth, p.x + diff));
                    viewport.setViewPosition(p);
                }
            }
        };

        addMouseMotionListener(mouseHandler);
        addMouseListener(mouseHandler);
    }
}

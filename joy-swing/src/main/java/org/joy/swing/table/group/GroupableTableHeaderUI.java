
/* (swing1.1beta3) */
package org.joy.swing.table.group;

//~--- non-JDK imports --------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------



import javax.swing.plaf.basic.BasicTableHeaderUI;

/**
 *  GroupableTableHeaderUI
 * @version 1.0 pre 2004
 * @author Nobuo Tamemasa
 */
public class GroupableTableHeaderUI extends BasicTableHeaderUI {

//    /**
//     * Constructs ...
//     *
//     *
//     * @param header
//     */
//    public GroupableTableHeaderUI(JTableHeader header) {
//        super();
//        this.header = header;
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param columnGroup
//     *
//     * @return
//     */
//    protected Dimension getGroupRendererSize(ColumnGroup columnGroup) {
//        Component component = getTableCellRendererComponent(columnGroup.getHeaderValue(), columnGroup.getWidth(), -1);
//        int hight = component.getSize().height;
//        if (hight == 0) {
//            hight = component.getPreferredSize().height;
//        }
//
//        return new Dimension(columnGroup.getWidth(), hight);
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param g
//     * @param c
//     */
//    @Override
//    public void paint(Graphics g, JComponent c) {
//        Rectangle clipBounds = g.getClipBounds();
//
//        if (header.getColumnModel() == null) {
//            return;
//
//        // ((GroupableTableHeader)header).setColumnMargin();
//        }
//
//        int column = 0;
//        Dimension size = header.getSize();
//        Rectangle cellRect = new Rectangle(0, 0, size.width, size.height);
//        Hashtable h = new Hashtable();
//
//        // int columnMargin = header.getColumnModel().getColumnMargin();
//        Enumeration enumeration = header.getColumnModel().getColumns();
//
//        while (enumeration.hasMoreElements()) {
//            cellRect.height = size.height;
//            cellRect.y = 0;
////            System.out.println("#########################3");
//
//            TableColumn aColumn = (TableColumn) enumeration.nextElement();
//            Enumeration cGroups = ((GroupableTableHeader) header).getColumnGroups(aColumn);
////            System.out.println("column = " + aColumn.getHeaderValue());
//            if ((cGroups != null) && cGroups.hasMoreElements()) {
//                int groupHeight = 0;
//                while (cGroups.hasMoreElements()) {
//                    ColumnGroup cGroup = (ColumnGroup) cGroups.nextElement();
////                    System.out.println("group = " + cGroup);
//                    Rectangle groupRect = (Rectangle) h.get(cGroup);
//                    if (groupRect == null) {
//                        groupRect = new Rectangle(cellRect);
//                        Dimension d = getGroupRendererSize(cGroup);
//                        groupRect.width = d.width;
//                        groupRect.height = d.height;
//                        h.put(cGroup, groupRect);
//                    }
//
//                    paintCell(g, groupRect, cGroup);
//                    groupHeight += groupRect.height;
//                    cellRect.height = size.height - groupHeight;
//                    cellRect.y = groupHeight;
////                    System.out.println("group name = " + cGroup.getHeaderValue() + " groupHeight =  " + groupHeight + " headSize.height = " + size.height);
//                }
//            }
////            System.out.println("column height = " + aColumn.getHeaderValue() + " y = " + cellRect.y);
////            System.out.println("----------------------------------------------------------------");
//            cellRect.width = aColumn.getWidth();    // + columnMargin;
//            if (cellRect.intersects(clipBounds)) {
//                paintCell(g, cellRect, column);
//            }
//            cellRect.x += cellRect.width;
//            column++;
//        }
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param g
//     * @param cellRect
//     * @param columnIndex
//     */
//    private void paintCell(Graphics g, Rectangle cellRect, int columnIndex) {
//        TableColumn aColumn = header.getColumnModel().getColumn(columnIndex);
//        Component component = getTableCellRendererComponent(aColumn.getHeaderValue(), -1, columnIndex);
//
//        rendererPane.add(component);
//        rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param g
//     * @param cellRect
//     * @param cGroup
//     */
//    private void paintCell(Graphics g, Rectangle cellRect, ColumnGroup cGroup) {
////        System.out.println("paint group = " + cGroup.getHeaderValue() + " cellRect = " + cellRect);
//        Component component = getTableCellRendererComponent(cGroup.getHeaderValue(), cGroup.getWidth(), -1);
//
//        rendererPane.add(component);
//        rendererPane.paintComponent(g, component, header, cellRect.x, cellRect.y, cellRect.width, cellRect.height, true);
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @return
//     */
//    private int getHeaderHeight() {
//        int height = 0;
//        TableColumnModel columnModel = header.getColumnModel();
//        if (header.getDefaultRenderer() instanceof ColourfulTableHeadCellRenderer) {
//            ColourfulTableHeadCellRenderer renderer = (ColourfulTableHeadCellRenderer) header.getDefaultRenderer();
//
//            for (int column = 0; column < columnModel.getColumnCount(); column++) {
//                TableColumn aColumn = columnModel.getColumn(column);
//                Enumeration enums = ((GroupableTableHeader) header).getColumnGroups(aColumn);
//                int cHeight = 0;
//
//                if (enums != null) {
//                    cHeight = renderer.getColumnHight(column);
//                    while (enums.hasMoreElements()) {
//                        ColumnGroup cGroup = (ColumnGroup) enums.nextElement();
//                        Dimension size = getGroupRendererSize(cGroup);
//                        cHeight += size.height;
//                    }
//                    height = Math.max(height, cHeight);
//
//                } else if (height < 1) {
//                    Component component = getTableCellRendererComponent(aColumn.getHeaderValue(), aColumn.getWidth(), -1);
//                    cHeight = component.getSize().height;
//                    if (cHeight == 0) {
//                        cHeight = component.getPreferredSize().height;
//                    }
//                }
//                height = Math.max(height, cHeight);
//            }
//            height = renderer.getMaxHight(height);
//
//        } else {
//            for (int column = 0; column < columnModel.getColumnCount(); column++) {
//                TableColumn aColumn = columnModel.getColumn(column);
//                Component comp = getTableCellRendererComponent(aColumn.getHeaderValue(), -1, column);
//                int cHeight = comp.getPreferredSize().height;
//                Enumeration enums = ((GroupableTableHeader) header).getColumnGroups(aColumn);
//                if (enums != null) {
//                    while (enums.hasMoreElements()) {
//                        ColumnGroup cGroup = (ColumnGroup) enums.nextElement();
//                        cHeight += getGroupRendererSize(cGroup).height;
//                    }
//                }
//
//                height = Math.max(height, cHeight);
//            }
//        }
//
//        return height;
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param width
//     *
//     * @return
//     */
//    private Dimension createHeaderSize(long width) {
//        TableColumnModel columnModel = header.getColumnModel();
//        width += columnModel.getColumnMargin() * columnModel.getColumnCount();
//        if (width > Integer.MAX_VALUE) {
//            width = Integer.MAX_VALUE;
//        }
//        return new Dimension((int) width, getHeaderHeight());
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param c
//     *
//     * @return
//     */
//    public Dimension getPreferredSize(JComponent c) {
//        long width = 0;
//        Enumeration enumeration = header.getColumnModel().getColumns();
//
//        while (enumeration.hasMoreElements()) {
//            TableColumn aColumn = (TableColumn) enumeration.nextElement();
//
//            width = width + aColumn.getPreferredWidth();
//        }
//
//        return createHeaderSize(width);
//    }
//
//    /**
//     * Method description
//     *
//     *
//     * @param headValue
//     * @param columnWidth
//     * @param columnIndex
//     *
//     * @return
//     */
//    protected Component getTableCellRendererComponent(Object headValue, int columnWidth, int columnIndex) {
//        TableCellRenderer renderer = header.getDefaultRenderer();
//        Component comp = null;
//
//        if (renderer instanceof ColourfulTableHeadCellRenderer) {
//            comp = ((ColourfulTableHeadCellRenderer) renderer).getDefaultTableCellRendererComponent(headValue.toString(), columnWidth, columnIndex);
//
//        } else {
//            if (columnIndex == -1) {
//                columnIndex = 0;
//            }
//
//            comp = renderer.getTableCellRendererComponent(header.getTable(), headValue, false, false, -1, columnIndex);
//        }
//
//        return comp;
//    }
}

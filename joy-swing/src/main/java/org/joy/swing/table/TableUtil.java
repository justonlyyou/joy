package org.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author
 */
public class TableUtil {

    /** Field description */
    private static int DEFAULT_COLUMN_PADDING = 5;

    /** Creates a new instance of AdjustColumnUtil */
    private TableUtil() {
    }

    /*
     * @param JTable aTable, the JTable to autoresize the columns on
     * @param boolean includeColumnHeaderWidth, use the Column Header width as a minimum width
     * @returns The table width, just in case the caller wants it...
     */
    /**
     * Method description
     *
     *
     * @param aTable
     * @param includeColumnHeaderWidth
     *
     * @return
     */
    public static int autoResizeTable(JTable aTable, boolean includeColumnHeaderWidth) {
        return (autoResizeTable(aTable, includeColumnHeaderWidth, DEFAULT_COLUMN_PADDING));
    }

    /*
     * @param JTable aTable, the JTable to autoresize the columns on
     * @param boolean includeColumnHeaderWidth, use the Column Header width as a minimum width
     * @param int columnPadding, how many extra pixels do you want on the end of each column
     * @returns The table width, just in case the caller wants it...
     */
    /**
     * Method description
     *
     *
     * @param aTable
     * @param includeColumnHeaderWidth
     * @param columnPadding
     *
     * @return
     */
    public static int autoResizeTable(JTable aTable, boolean includeColumnHeaderWidth, int columnPadding) {
        int columnCount = aTable.getColumnCount();
        int tableWidth = 0;
        Dimension cellSpacing = aTable.getIntercellSpacing();

        if (columnCount > 0) // must have columns !
        {
            // STEP ONE : Work out the column widths
            int columnWidth[] = new int[columnCount];
            TableColumnModel tableColumnModel = aTable.getColumnModel();
            TableColumn tableColumn;
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                columnWidth[columnIndex] = getMaxColumnWidth(aTable, columnIndex, includeColumnHeaderWidth, columnPadding);
                tableWidth += columnWidth[columnIndex];
                tableColumn = tableColumnModel.getColumn(columnIndex);
                tableColumn.setPreferredWidth(columnWidth[columnIndex]);
            }
            // account for cell spacing too
            tableWidth += ((columnCount - 1) * cellSpacing.width);
            aTable.invalidate();
            aTable.doLayout();
            aTable.repaint();
        }
        return (tableWidth);
    }

    /*
     * @param JTable aTable, the JTable to autoresize the columns on
     * @param int columnNo, the column number, starting at zero, to calculate the maximum width on
     * @param boolean includeColumnHeaderWidth, use the Column Header width as a minimum width
     * @param int columnPadding, how many extra pixels do you want on the end of each column
     * @returns The table width, just in case the caller wants it...
     */
    /**
     * Method description
     *
     *
     * @param aTable
     * @param columnNo
     * @param includeColumnHeaderWidth
     * @param columnPadding
     *
     * @return
     */
    protected static int getMaxColumnWidth(JTable aTable, int columnNo, boolean includeColumnHeaderWidth, int columnPadding) {
        TableColumn column = aTable.getColumnModel().getColumn(columnNo);
        Component rendererComponent = null;
        int maxWidth = 0;

        if (includeColumnHeaderWidth) {
            TableCellRenderer headerRenderer = column.getHeaderRenderer();

            if (headerRenderer != null) {
                rendererComponent = headerRenderer.getTableCellRendererComponent(aTable, column.getHeaderValue(), false, false, 0, columnNo);

                if (rendererComponent instanceof JTextComponent) {
                    JTextComponent jtextComp = (JTextComponent) rendererComponent;
                    String text = jtextComp.getText();
                    Font font = jtextComp.getFont();
                    FontMetrics fontMetrics = jtextComp.getFontMetrics(font);

                    maxWidth = SwingUtilities.computeStringWidth(fontMetrics, text);
                    System.out.println("XXXX---" + maxWidth);
                } else {
                    maxWidth = rendererComponent.getPreferredSize().width;
                }
            } else {
                try {
                    String headerText = (String) column.getHeaderValue();
                    JLabel defaultLabel = new JLabel(headerText);
                    Font font = defaultLabel.getFont();
                    FontMetrics fontMetrics = defaultLabel.getFontMetrics(font);

                    maxWidth = SwingUtilities.computeStringWidth(fontMetrics, headerText);
                } catch (ClassCastException ce) {

                    // Can't work out the header column width..
                    maxWidth = 0;
                }
            }
        }

        TableCellRenderer tableCellRenderer;

        // Component comp;
        int cellWidth = 0;

        for (int rowIndex = 0; rowIndex < aTable.getRowCount(); rowIndex++) {
            tableCellRenderer = aTable.getCellRenderer(rowIndex, columnNo);
            rendererComponent = tableCellRenderer.getTableCellRendererComponent(aTable, aTable.getValueAt(rowIndex, columnNo), false, false, rowIndex, columnNo);

            if (rendererComponent instanceof JTextComponent) {
                JTextComponent jtextComp = (JTextComponent) rendererComponent;
                String text = jtextComp.getText();
                Font font = jtextComp.getFont();
                FontMetrics fontMetrics = jtextComp.getFontMetrics(font);
                int textWidth = SwingUtilities.computeStringWidth(fontMetrics, text);

                maxWidth = Math.max(maxWidth, textWidth);
            } else {
                cellWidth = rendererComponent.getPreferredSize().width;

                // maxWidth = Math.max ( headerWidth, cellWidth );
                maxWidth = Math.max(maxWidth, cellWidth);
            }
        }

        return (maxWidth + columnPadding);
    }

    /**
     * 动态显示表头
     * @param jTable
     */
    public void addTableHeaderSelect(final JTable jTable) {
        int columnCounts = jTable.getColumnCount();
        final JPopupMenu jPopupMenu = new JPopupMenu();
        final Map<String, Integer> tableColumnPrefersize = new HashMap<String, Integer>();
        for (int index = 0; index < columnCounts; index++) {
            JCheckBoxMenuItem jCheckBoxMenuItem = new JCheckBoxMenuItem(
                    jTable.getColumnName(index));
            jCheckBoxMenuItem.setState(true);
            jCheckBoxMenuItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBoxMenuItem item = (JCheckBoxMenuItem) e.getSource();
                    System.out.println("item statu:" + item.getState());
                    if (item.getState()) {
                        TableColumn tableColumn = jTable.getColumn(item.getText());
                        tableColumn.setWidth(tableColumnPrefersize.get(item.getText()));
                        tableColumn.setMaxWidth(2147483647);
                        tableColumn.setPreferredWidth(75);
                        tableColumn.setMinWidth(15);
                    } else {
                        TableColumn tableColumn = jTable.getColumn(item.getText());
                        tableColumnPrefersize.put(item.getText(), tableColumn.getWidth());
                        tableColumn.setMinWidth(0);
                        tableColumn.setPreferredWidth(0);
                        tableColumn.setMaxWidth(0);
                        tableColumn.setWidth(0);
                    }
                }
            });
            jPopupMenu.add(jCheckBoxMenuItem);
        }

        jTable.getTableHeader().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    jPopupMenu.show(jTable, e.getX(), e.getY() - 15);
                }
            }
        });
    }

    /**
     * 使右键也有选中当前行
     */
    public static void makeButton3CanSelectCurRow(final JTable table) {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                if (evt.getButton() == MouseEvent.BUTTON3) {
                    if (table.getSelectedRows().length < 2) {
                        boolean rowSel = false;
                        boolean colSel = false;
                        int row = table.rowAtPoint(evt.getPoint());
                        int col = table.columnAtPoint(evt.getPoint());
                        int[] selRows = table.getSelectedRows();
                        int[] selCols = table.getSelectedColumns();
                        for (int i = 0; i < selRows.length; i++) {
                            for (int j = 0; j < selCols.length; j++) {
                                if (selRows[i] == row) {
                                    rowSel = true;
                                }
                                if (selCols[j] == col) {
                                    colSel = true;
                                }
                                if (colSel && rowSel) {
                                    break;
                                }
                            }
                        }
                        if (!rowSel) {
                            table.setRowSelectionInterval(row, row);
                        }
                        if (!colSel) {
                            table.setColumnSelectionInterval(col, col);
                        }
                        table.requestFocus();
                    }
                }
            }
        });
    }

    /**
     * 添加快捷键
     * @param component   要添加快捷键的组件 必须extends from JComponent
     * @param inputMapKey 组件的inputMap的类型,一般为 JComponent.WHEN_IN_FOCUSED_WINDOW
     * @param keyStroke   快捷键的组合键
     * @param actionKey   响应动作的key,用于指明actionMap中的key
     * @param action      响应动作action
     */
    public void addComponentKeyStroke(JComponent component, int inputMapKey,
            KeyStroke keyStroke, String actionKey, Action action) {
        component.getInputMap(inputMapKey).put(keyStroke, actionKey);
        component.getActionMap().put(actionKey, action);
    }

    /**
     * "新增"快捷键
     * @param component 要添加快捷键的组件必须extends from JComponent
     * @param action    响应动作action
     */
    public void addComponentNewKeyStroke(JComponent component, Action action) {
        addComponentKeyStroke(component, JComponent.WHEN_IN_FOCUSED_WINDOW,
                KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK), "new", action);
    }

    /**
     * "保存"快捷键
     * @param component 要添加快捷键的组件必须extends from JComponent
     * @param action    响应动作action
     */
    public void addComponentSaveKeyStroke(JComponent component, Action action) {
        addComponentKeyStroke(component, JComponent.WHEN_IN_FOCUSED_WINDOW,
                KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), "save", action);
    }

    /**
     * "删除"快捷键
     * @param component 要添加快捷键的组件必须extends from JComponent
     * @param action    响应动作action
     */
    public void addComponentDeleteKeyStroke(JComponent component, Action action) {
        addComponentKeyStroke(component, JComponent.WHEN_IN_FOCUSED_WINDOW,
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete", action);
    }

    /**
     * "帮助"快捷键
     * @param component 要添加快捷键的组件必须extends from JComponent
     * @param action    响应动作action
     */
    public void addComponentHelpKeyStroke(JComponent component, Action action) {
        addComponentKeyStroke(component, JComponent.WHEN_IN_FOCUSED_WINDOW,
                KeyStroke.getKeyStroke("F1"), "help", action);
    }

    public static void showMsgDlgOnSuccess(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg);
    }

//    /**
//     * 设置系统的字体
//     * @param f
//     */
//    public void setUIFont(javax.swing.plaf.FontUIResource f) {
//        this.f = f;
//        java.util.Enumeration keys = UIManager.getDefaults().keys();
//        while (keys.hasMoreElements()) {
//            Object key = keys.nextElement();
//            Object value = UIManager.get(key);
//            if (value instanceof javax.swing.plaf.FontUIResource) {
//                UIManager.put(key, f);
//            }
//        }
//    }
    /**
     * create a table by html tag (no border)
     * This static method just provide a very simple table, a html table but not a JTable, it is always used <br>
     * in JOptionPane.showMessageDialog(...) for showing two dimensional message, insteads of JTable, it provide <br>
     * a easy way and a high performance method to represent two dimensional message.  <br>
     * Do remember: if you need a more complex table, please use JTable directly.
     * @param tableData data shown in table(can include column names)
     * @param firstRowIsColumnName is first row of the tableData column name <br>
     *         (if true, the colum names will be shown in the center of each column header,<br>
     *          else they will be consider as the ordinary table data)
     * @return A simple table string generating by html
     * @author Kevice
     */
    public static String createHTMLTable(String[][] tableData, boolean firstRowIsColumnName) {
        StringBuffer htmlTableStr = new StringBuffer();
        htmlTableStr.append("<HTML>");
        for (int row = 0; row < tableData.length; row++) {
            if (firstRowIsColumnName && row == 0) {
                htmlTableStr.append("<TR align=center>");
            } else {
                htmlTableStr.append("<TR>");
            }
            String[] rowData = tableData[row];
            for (String cellData : rowData) {
                htmlTableStr.append("<TD>");
                htmlTableStr.append(cellData);
                htmlTableStr.append("</TD>");
            }
            htmlTableStr.append("</TR>");
        }
        htmlTableStr.append("</HTML>");
        return htmlTableStr.toString();
    }
}

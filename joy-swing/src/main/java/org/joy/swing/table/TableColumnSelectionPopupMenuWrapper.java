package org.joy.swing.table;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.MenuElement;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.joy.commons.lang.string.I18nTool;

/**
 * <title>选择表头显示的列弹出菜单封装器.</title>
 * <p>重排列列的时候，将使用最原始的位置；即当移动列的时候，将恢复原始位置。</p>
 * <p>使用方法：</p>
 * <code>JTable table = ...</code>
 * <code>TableColumnSelectionPopupMenuWrapper wrapper = new TableColumnSelectionPopupMenuWrapper(table);</code>
 * <code>......</code>
 * <code>JPopupMenu popupMenu = wrapper.createPopupMenu();</code>
 * <p>特别注意：当Table的TableColumnModel重建的时候，必须调用rebuild()。</p>
 * @author zy
 */
public class TableColumnSelectionPopupMenuWrapper implements MouseListener, ItemListener {

    /** Field description */
    private boolean selecting = false;

    /** Field description */
    private JPopupMenu columnSelectionPopupMenu;

    /** Field description */
    private JTable table;

    /** Field description */
    private TableColumnModel tableColumnModel;

    /**
     *
     * @param table
     */
    public TableColumnSelectionPopupMenuWrapper(JTable table) {
        if (table == null) {
            throw new NullPointerException("Table is not be null!");
        }

        this.table = table;
        rebuild();
        table.getTableHeader().addMouseListener(this);
    }

    /**
     * 过时
     * @param table
     * @param init
     * @deprecated
     */
    @Deprecated
    public TableColumnSelectionPopupMenuWrapper(JTable table, boolean init) {
        this(table);
    }

    /**
     * Constructs ...
     *
     *
     * @param table
     * @param hideColumns
     */
    public TableColumnSelectionPopupMenuWrapper(JTable table, int... hideColumns) {
        this(table);
        initHideColumn(hideColumns);
    }

    /**
     * 创建默认的表格列控制器
     * @param table
     * @return
     */
    public static TableColumnSelectionPopupMenuWrapper createTableColumnPopuControler(JTable table) {
        return new TableColumnSelectionPopupMenuWrapper(table);
    }

    /**
     * 创建默认的表格列控制器
     * @param table 要控制的列
     * @param initHideIndies 初始隐藏的列
     * @return 返回控制器的示例
     */
    public static TableColumnSelectionPopupMenuWrapper createTableColumnPopuControler(JTable table, int... initHideIndies) {
        return new TableColumnSelectionPopupMenuWrapper(table, initHideIndies);
    }

    /**
     *  当TableColumnModel重建时候需要触发
     */
    public void rebuild() {
        TableColumnModel columnModel = table.getColumnModel();

        this.tableColumnModel = columnModel;
        this.resetTableColumnModel(columnModel);
    }

    /**
     * Method description
     *
     *
     * @param tableColumnModel
     */
    private void resetTableColumnModel(TableColumnModel tableColumnModel) {
        if (tableColumnModel == null) {
            columnSelectionPopupMenu = null;

            return;
        }

        initPopuMenu();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public JPopupMenu createPopupMenu() {
        if (columnSelectionPopupMenu == null) {
            initPopuMenu();
        }

        return columnSelectionPopupMenu;
    }

    /**
     * Method description
     *
     */
    private void initPopuMenu() {
        if (tableColumnModel == null) {
            return;
        }

        columnSelectionPopupMenu = new JPopupMenu();

        for (int index = 0, size = tableColumnModel.getColumnCount(); index < size; index++) {
            TableColumn tableColumn = tableColumnModel.getColumn(index);

            if (tableColumn == null) {
                continue;
            }

            JCheckBoxMenuItem tableColumnCheckBoxMenuItem = new TableColumnMenuItem(tableColumn);

            tableColumnCheckBoxMenuItem.setSelected(true);
            tableColumnCheckBoxMenuItem.addItemListener(this);
            columnSelectionPopupMenu.add(tableColumnCheckBoxMenuItem);
        }
    }

    /**
     * Method description
     *
     *
     * @param columnIndies
     */
    private void initHideColumn(int... columnIndies) {
        for (int columnIndex : columnIndies) {
            ((TableColumnMenuItem) columnSelectionPopupMenu.getSubElements()[columnIndex]).setSelected(false);
        }
    }

    /**
     * 重新排列表头
     */
    private void arrangeTableColumns() {
        TableColumnMenuItem menuItem;

        for (MenuElement meunE : columnSelectionPopupMenu.getSubElements()) {
            menuItem = (TableColumnMenuItem) meunE;
            tableColumnModel.removeColumn(menuItem.getTableColumn());
        }

        for (MenuElement meunE : columnSelectionPopupMenu.getSubElements()) {
            menuItem = (TableColumnMenuItem) meunE;

            if (menuItem.isSelected()) {
                tableColumnModel.addColumn(menuItem.getTableColumn());
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.isMetaDown()) {
            if (tableColumnModel != table.getColumnModel()) {
                rebuild();
            }

            columnSelectionPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (!selecting) {
            TableColumnMenuItem checkBoxMenuItem = (TableColumnMenuItem) e.getSource();
            TableColumn tableColumn = checkBoxMenuItem.getTableColumn();

            if (e.getStateChange() == ItemEvent.SELECTED) {
                arrangeTableColumns();
            } else if (tableColumnModel.getColumnCount() > 1) {
                tableColumnModel.removeColumn(tableColumn);
            } else {
                JOptionPane.showMessageDialog(table, I18nTool.getLocalStr("TheTableShoudKeepOneColumnAtLeast"));
                selecting = true;
                checkBoxMenuItem.setSelected(!checkBoxMenuItem.isSelected());
                selecting = false;
            }

            columnMenuItemStateChanged(tableColumn, checkBoxMenuItem.isSelected());
        }
    }

    /**
     * Method description
     *
     *
     * @param changedColumn
     * @param isSelected
     */
    protected void columnMenuItemStateChanged(TableColumn changedColumn, boolean isSelected) {}

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private class TableColumnMenuItem extends JCheckBoxMenuItem {

        /** Field description */
        private TableColumn tableColumn;

        /**
         * Constructs ...
         *
         *
         * @param tableColumn
         */
        public TableColumnMenuItem(TableColumn tableColumn) {
            super(String.valueOf(tableColumn.getHeaderValue()));
            this.tableColumn = tableColumn;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        public TableColumn getTableColumn() {
            return this.tableColumn;
        }
    }
}

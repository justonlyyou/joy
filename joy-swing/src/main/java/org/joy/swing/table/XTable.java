/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table;

import org.joy.commons.lang.DateTool;
import org.joy.commons.lang.string.I18nTool;
import org.joy.swing.SwingUtility;
import org.joy.swing.XComponent;
import org.joy.swing.XComponentListener;
import org.joy.swing.XSwingUtil;
import org.joy.swing.button.XButton;
import org.joy.swing.dialog.BatchSettingDialog;
import org.joy.swing.table.filter.HideableColumnNode;
import org.joy.swing.table.filter.TableConfig;
import org.joy.swing.table.filter.TopTableColumnModel;
import org.joy.swing.table.filter.TopTableHeader;
import org.joy.swing.table.group.SimpleColumnGroup;
import org.joy.swing.table.group.TopGroupTableHeadUI;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Date;

/**
 * 基本表格
 * @author zy
 * @author ckcs
 */
public class XTable
        extends JTable
        implements XComponent {

    /**
     * 加载数据的状态条
     */
    private JProgressBar loadingDataProgressBar;
    /**
     * 显示索引的行头组件
     */
    private JComponent indexRowHeader;
    /**
     * 最后修改的行总数
     */
    private int lastChanedRowCount = -1;
//    private boolean autoCreateTableFilter = true;
    private boolean loadingData = false;
    //是否和统计行一起使用
    private boolean useWithTotalRow;
    /**
     * 是否相应模型的变化(应用于批量设置)
     */
    protected boolean disableAction = false;
    /**
     * 是否列宽根据内容自适应
     */
    protected boolean autoAdjustColumnWidth = false;
    //过滤按钮控制面板
    private JPanel filterButtonPanel;
    private XButton clearFilterButton;
    private XButton filterButton;

    public XTable() {
        this(null, true);
    }

    public XTable(TableModel model) {
        this(model, true);
    }

    /**
     * 如果想用其他的headerrenderer,请继承ColourfulTableHeadCellRenderer
     * @param model
     * @param tableHeaderRenderer
     */
    public XTable(TableModel model, boolean autoCreateTableFilter) {
        super(model);
//        this.autoCreateTableFilter = autoCreateTableFilter;
        addTableModelListener();
        setAutoCreateRowSorter(true);
        createTableFilterControlPanel();
    }

    private void addTableModelListener() {
        if (dataModel != null) {
            dataModel.addTableModelListener(new TableModelListener() {

                @Override
                public void tableChanged(TableModelEvent e) {
                    if (e.getType() == TableModelEvent.INSERT) {
                        adjustTableColumnWidths();
                    }

                }
            });
        }
    }

    @Override
    public void setModel(TableModel dataModel) {
        super.setModel(dataModel);
        addTableModelListener();
    }

    public void installColourfulTableHeadCellRenderer() {
        ColourfulTableHeadCellRenderer.install(this);
    }

    /**
     * 设置列宽根据内容自适应
     * @param autoFit 是否自适应, 默认为false
     */
    public void setAutoAjustColumnWidth(boolean autoFitColumnWidth) {
        this.autoAdjustColumnWidth = autoFitColumnWidth;
    }

    /**
     * 创建表格过滤器
     * 这个方法必须在将表格添加到过滚动面板前调用。
     */
    private void createTableFilterControlPanel() {
        //过滤按钮面板
        filterButtonPanel = new JPanel();
        filterButtonPanel.setVisible(false);
        filterButtonPanel.setLayout(new BorderLayout(0, 0));

        //过滤按钮
        Icon icon = XSwingUtil.getIconInJar("Filter2.gif");
        filterButton = new XButton("", icon);
        filterButton.setFocusable(false);
        filterButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        SwingUtility.getInstance().toolFaceForButton(filterButton, "ctrl F3");
        //过滤清除按钮
        clearFilterButton = new XButton("", XSwingUtil.getIconInJar("Search_clear.gif"));
        clearFilterButton.setFocusable(false);
        clearFilterButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        clearFilterButton.setVisible(false);
        SwingUtility.getInstance().toolFaceForButton(clearFilterButton, "ctrl shift F3");
        filterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //延时创建过滤器,并且数据不为0才创建。
                if (getModel().getRowCount() == 0) {
                    return;
                }
                doClickFilterButton();
            }
        });

        clearFilterButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTableHeader header = getTableHeader();
                if (header instanceof TopTableHeader) {
                    TopTableHeader topHeader = ((TopTableHeader) header);
                    ((TopTableColumnModel) topHeader.getColumnModel()).getFilterSetter().clearAllFilter();
                }
                filterButtonPanel.revalidate();
                filterButtonPanel.repaint();
            }
        });

        filterButtonPanel.add(filterButton, BorderLayout.NORTH);
        filterButtonPanel.add(clearFilterButton, BorderLayout.CENTER);
        Insets inset = filterButton.getInsets();
        filterButtonPanel.setPreferredSize(new Dimension(icon.getIconWidth() + inset.left + inset.right, icon.getIconHeight() + inset.top + inset.bottom));
    }
    //点击过滤按钮

    private void doClickFilterButton() {
        clearFilterButton.setVisible(!clearFilterButton.isVisible());
        JTableHeader header = getTableHeader();
        if (header instanceof TopTableHeader) {
            TopTableHeader topHeader = ((TopTableHeader) header);
            topHeader.setShowFilters(clearFilterButton.isVisible());
            if (!topHeader.isShowFilters()) {
                ((TopTableColumnModel) topHeader.getColumnModel()).getFilterSetter().clearAllFilter();
            }
            header.revalidate();
            header.repaint();
        }
        filterButtonPanel.revalidate();
        filterButtonPanel.repaint();
    }

    @Override
    public void setAutoCreateRowSorter(boolean autoCreateRowSorter) {
        super.setAutoCreateRowSorter(autoCreateRowSorter);
        if (!autoCreateRowSorter) {
            setRowSorter(null);
        }
    }

    /**
     * 清除过滤
     */
    public void clearTableFilter() {
        TopTableColumnModel topColumnModel = (TopTableColumnModel) getColumnModel();
        topColumnModel.getFilterSetter().clearAllFilter();
    }

    @Override
    public void sorterChanged(RowSorterEvent e) {
        super.sorterChanged(e);
        if (indexRowHeader != null) {
            resizeViewPort();
        }
    }

    @Override
    protected TableColumnModel createDefaultColumnModel() {
        return new TopTableColumnModel(this);
    }

    @Override
    protected JTableHeader createDefaultTableHeader() {
        if (columnModel != null && columnModel instanceof TopTableColumnModel) {
            return new TopTableHeader((TopTableColumnModel) columnModel);
        } else {
            return new TopTableHeader((TopTableColumnModel) createDefaultColumnModel());
        }
    }

    /**
     * 添加例组
     * @param guoups
     */
    public void addColumnGroup(SimpleColumnGroup... guoups) {
        if (!(tableHeader.getUI() instanceof TopGroupTableHeadUI)) {
            tableHeader.setUI(new TopGroupTableHeadUI());
        }
        TopTableHeader header = (TopTableHeader) getTableHeader();
        header.addColumnGroup(guoups);
    }

    /**
     * 当重键列组合表头的时候，必须调用
     * 这个方法，清空无用的组信息
     */
    public void clearHeaderGroup() {
        TopTableHeader header = (TopTableHeader) getTableHeader();
        header.clearHeaderGroup();
    }

    @Override
    public void setRowHeight(int rowHeight) {
        super.setRowHeight(rowHeight);
        if (indexRowHeader != null) {
            ((JList) indexRowHeader).setFixedCellHeight(rowHeight);
            indexRowHeader.revalidate();
            indexRowHeader.repaint();
        }
    }

    @Override
    protected void createDefaultRenderers() {
        super.createDefaultRenderers();
        // Dates
        String c = XTable.class.getName() + "$" + DateRenderer.class.getSimpleName();
        defaultRenderersByColumnClass.put(Date.class, new UIDefaults.ProxyLazyValue(c));
    }

    protected JProgressBar getLoadingDataBar() {
        if (loadingDataProgressBar == null) {
            loadingDataProgressBar = new JProgressBar();
            loadingDataProgressBar.setStringPainted(true);
            loadingDataProgressBar.setString(I18nTool.getLocalStr("Loading"));
        }
        return loadingDataProgressBar;
    }

    //加载数据
    public void setloadingDataStyle(boolean loading) {
        if (loadingData == loading) {
            return;
        }
        loadingData = loading;
        Container p = getParent();
        if (p instanceof JViewport) {
            Container gp = p.getParent();
            if (gp instanceof JScrollPane) {
                JScrollPane scroll = ((JScrollPane) gp);
                scroll.setCursor(loading ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : Cursor.getDefaultCursor());
                setCursor(loading ? Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR) : Cursor.getDefaultCursor());
                getLoadingDataBar().setIndeterminate(loading);
                getLoadingDataBar().setVisible(loading);
                setVisible(!loading);
                scroll.getHorizontalScrollBar().setEnabled(!loading);
                scroll.getVerticalScrollBar().setEnabled(!loading);
                if (!loading) {
                    scroll.setViewportView(this);
                }
                if (indexRowHeader != null) {
                    if (loading) {
                        scroll.setRowHeaderView(null);
                        lastChanedRowCount = -1;

                    } else {
                        scroll.setRowHeaderView(indexRowHeader);
                        scroll.revalidate();
                        scroll.repaint();
                    }
                }
                ((JScrollPane) gp).setColumnHeaderView(loading ? loadingDataProgressBar : getTableHeader());
            }
        }
    }

    /**
     * 获得父滚动窗格
     * @return
     */
    public JScrollPane getParentScrollPane() {
        Container p = getParent();
        if (p instanceof JViewport) {
            Container gp = p.getParent();
            if (gp instanceof JScrollPane) {
                return (JScrollPane) gp;
            }
        }
        return null;
    }

    private void resizeViewPort() {
        if (getTableHeader() != null && lastChanedRowCount != getRowCount()) {
            JScrollPane scrollPanel = getParentScrollPane();
            if (scrollPanel != null) {
                JViewport viewPort = scrollPanel.getRowHeader();
                if (viewPort != null) {
                    if (viewPort.getView() == indexRowHeader || viewPort.getView() == null) {
                        Dimension dimension = viewPort.getSize();
                        dimension.width = rowHeaderChanged();
                    }
                }
            }
            lastChanedRowCount = getRowCount();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        if (!disableAction) {
            super.tableChanged(e);
            if (indexRowHeader != null) {
                resizeViewPort();
            }
        }
        //过滤组件
        if (filterButtonPanel != null) {
            if (getModel() != null && getModel().getRowCount() == 0) {
                //将要隐藏过滤组件了
                if (filterButtonPanel.isVisible()) {
                    if (((TopTableHeader) getTableHeader()).isShowFilters()) {
                        doClickFilterButton();
                    }
                    filterButtonPanel.setVisible(false);
                }
            } else {
                if (!filterButtonPanel.isVisible()) {
                    filterButtonPanel.setVisible(true);
                }
            }
        }
    }

    /**
     * bastable只能使用TopTableHeader
     * @param tableHeader
     */
    @Override
    public void setTableHeader(JTableHeader tableHeader) {
        if (tableHeader instanceof TopTableHeader) {
            super.setTableHeader(tableHeader);
        }
    }

    public void setDisableAction(boolean enableAction) {
        this.disableAction = enableAction;
    }

    public boolean isDisableAction() {
        return disableAction;
    }

    public boolean isUseWithTotalRow() {
        return useWithTotalRow;
    }

    public void setUseWithTotalRow(boolean useWithTotalRow) {
        this.useWithTotalRow = useWithTotalRow;
    }

    //调整行标题的宽度
    protected int rowHeaderChanged() {
        JList rowHeader = (JList) indexRowHeader;
        rowHeader.setFixedCellHeight(getRowHeight());
        int cellWidth = 1;
        if (getModel().getRowCount() > 0) {
            cellWidth = rowHeader.getFontMetrics(getTableHeader().getFont()).stringWidth(String.valueOf(getRowCount() - 1) + 3);
            cellWidth = Math.max(cellWidth, 24);
        }
        rowHeader.setFixedCellWidth(cellWidth);
        rowHeader.revalidate();
        rowHeader.repaint();
        return cellWidth;
    }

    /**
     * 生成行索引
     * @return
     */
    protected JComponent createIndexRowHeader() {
        if (indexRowHeader != null) {
            return indexRowHeader;
        }
        ListModel lm = new AbstractListModel() {

            @Override
            public int getSize() {
                return getRowCount();
            }

            @Override
            public Object getElementAt(int index) {
                return index + 1;
            }
        };
        indexRowHeader = new JList(lm);
        indexRowHeader.setEnabled(false);
        indexRowHeader.setFocusable(false);
        indexRowHeader.setOpaque(false);
        indexRowHeader.setBackground(UIManager.getColor("TableHeader.background"));
        ((JList) indexRowHeader).setCellRenderer(new RowHeaderRenderer());
        return indexRowHeader;
    }

    @Override
    protected void configureEnclosingScrollPane() {
        super.configureEnclosingScrollPane();
        JScrollPane parent = getParentScrollPane();
        //只有不为空的时候我才设置
        if (parent != null && filterButtonPanel != null && parent.getCorner(JScrollPane.UPPER_LEADING_CORNER) == null) {
            parent.setCorner(JScrollPane.UPPER_LEADING_CORNER, filterButtonPanel);
        }
        if (parent != null && parent.getRowHeader() == null) {
            parent.setRowHeaderView(createIndexRowHeader());
            resizeViewPort();
        }
    }

    @Override
    public void addXComponentListener(XComponentListener listener) {
        listenerList.add(XComponentListener.class, listener);
    }

    @Override
    public void removeXComponentListener(XComponentListener listener) {
        listenerList.remove(XComponentListener.class, listener);
    }

    /**
     * 根据表格的配置恢复表格的配置，
     * 在表格的恢复中要注意表格的恢复顺序。
     * @param tableConfig
     */
    public void resumeTableByConfig(TableConfig tableConfig) {
        TopTableHeader header = (TopTableHeader) getTableHeader();
        //确定是否需要记录配置
        header.setRecordConfig(tableConfig.isNeedRecordConfig());
        //恢复列组信息

        //隐藏的列
        if (tableConfig.getHidedColumns() != null) {
            setHideTableColumnsByModelIndex(tableConfig.getHidedColumns());
        }

        //列的宽度恢复
        if (tableConfig.getVisibleColumnsWidth() != null) {
            int count = columnModel.getColumnCount();
            int[][] widhts = tableConfig.getVisibleColumnsWidth();
            for (int index = 0; index < widhts.length; index++) {
                for (int columnIndex = 0; columnIndex < count; columnIndex++) {
                    if (widhts[index][0] == columnModel.getColumn(columnIndex).getModelIndex()) {
                        columnModel.getColumn(columnIndex).setPreferredWidth(widhts[index][1]);
                    }
                }
            }
        }
    }

    /**
     * 获得表格的配置
     * @return
     */
    public TableConfig getTableConfig() {
        TableConfig tableConfig = new TableConfig();
        TopTableHeader header = (TopTableHeader) getTableHeader();
        //是否要保存配置
        tableConfig.setNeedRecordConfig(header.isRecordConfig());
        //显示的列
        int count = columnModel.getColumnCount();
        int[][] widths = new int[count][2];
        for (int index = 0; index < count; index++) {
            widths[index][0] = columnModel.getColumn(index).getModelIndex();
            widths[index][1] = columnModel.getColumn(index).getWidth();
        }
        tableConfig.setVisibleColumnsWidth(widths);
        //隐藏的列
        int[] hideColumns = header.getModelIndexsByVisibleType(HideableColumnNode.UNSELECTED);
        tableConfig.setHidedColumns(hideColumns);
        //当前的组信息

        return tableConfig;
    }

    /**
     * 设置隐藏的表格列
     * 这个方法必须在表格初始化好之后调用
     * @param columns
     */
    public void setHideTableColumnsByModelIndex(int... columns) {
        TopTableHeader header = (TopTableHeader) getTableHeader();
        header.changeVisibleByModelIndexs(HideableColumnNode.UNSELECTED, columns);
    }

    //行头绚烂
    private class RowHeaderRenderer extends JLabel implements ListCellRenderer {

        RowHeaderRenderer() {
            JTableHeader header = getTableHeader();
            setOpaque(true);
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(CENTER);
            setForeground(header.getForeground());
            setBackground(header.getBackground());
            setFont(header.getFont());
        }

        @Override
        public Component getListCellRendererComponent(JList list,
                Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText("" + (index + 1));
            return this;
        }
    }

    protected static class DateRenderer extends DefaultTableCellRenderer.UIResource {

        public DateRenderer() {
            super();
        }

        @Override
        public void setValue(Object value) {
            setText((value == null) ? "" : DateTool.formatDate((Date) value, DateTool.FMT_HYPHEN_DAY_CLN_SECOND));
        }
    }

    /**
     * 根据单元格内容自动调整列的宽度
     */
    private void adjustTableColumnWidths() {
        if (autoAdjustColumnWidth) {
            setAutoResizeMode(AUTO_RESIZE_OFF);
            JTableHeader header = getTableHeader(); //表头
            int rowCount = getRowCount(); //表格的行数
            TableColumnModel cm = getColumnModel(); //表格的列模型

            for (int i = 0; i < cm.getColumnCount(); i++) { //循环处理每一列
                TableColumn column = cm.getColumn(i); //第i个列对象
                int width = (int) header.getDefaultRenderer().getTableCellRendererComponent(this, column.getIdentifier(), false, false, -1, i).getPreferredSize().getWidth(); //用表头的绘制器计算第i列表头的宽度
                for (int row = 0; row < rowCount; row++) { //循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格宽度
                    int preferedWidth = (int) getCellRenderer(row, i).getTableCellRendererComponent(this, getValueAt(row, i), false, false, row, i).getPreferredSize().getWidth();
                    width = Math.max(width, preferedWidth); //取最大的宽度
                }
                column.setPreferredWidth(width + getIntercellSpacing().width); //设置第i列的首选宽度
            }
//            doLayout(); //按照刚才设置的宽度重新布局各个列
        }
    }

        /**
     * 安装批量编辑器
     * @param table 要批设的表格
     * @param menu 表格菜单，如果没有或者已经绑定在表格中就传NULL
     * @param unSetColumnIndex 不需要批设的列索引（可编辑但是又不应该批设）
     */
    public static void installBatchEditor(final JTable table, JPopupMenu menu, final int... unSetColumnIndex) {
        installBatchEditor(table, menu, true, unSetColumnIndex);
    }

    /**
     * 安装批量编辑器
     * @param table 要批设的表格
     * @param menu 表格菜单，如果没有或者已经绑定在表格中就传NULL
     * @param initSeparator 是否要自动创建分格符
     * @param unSetcolumnIndex 不需要批设的列索引（可编辑但是又不应该批设）
     */
    public static void installBatchEditor(final JTable table, JPopupMenu menu, boolean initSeparator, final int... unSetcolumnIndex) {
        final JMenuItem batchSetMenu = new JMenuItem(I18nTool.getLocalStr("BatchRevision"));

        batchSetMenu.setActionCommand("-1");
        batchSetMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int column = Integer.valueOf(e.getActionCommand());
                TableCellEditor cellEditor = table.getCellEditor(table.getSelectedRow(), column);

                if (cellEditor != null) {
                    Component component = cellEditor.getTableCellEditorComponent(table, null, table.isCellSelected(row, column), row, column);
                    BatchSettingDialog settingDailog = BatchSettingDialog.showDialog(cellEditor, component, table.getColumnName(column));

                    if (settingDailog.getAction() == BatchSettingDialog.ACTION.OK_ACTION) {
                        for (TableModelListener lis : ((AbstractTableModel) table.getModel()).getTableModelListeners()) {
                            if (lis instanceof XTable) {
                                ((XTable) lis).setDisableAction(true);
                            }
                        }
                        for (int setRowIndex : table.getSelectedRows()) {
                            if (table.isCellEditable(setRowIndex, column)) {
                                table.setValueAt(settingDailog.getValue(), setRowIndex, column);
                            }
                        }
                        for (TableModelListener lis : ((AbstractTableModel) table.getModel()).getTableModelListeners()) {
                            if (lis instanceof XTable) {
                                ((XTable) lis).setDisableAction(false);
                                ((XTable) lis).repaint();
                            }
                        }
                    }
                }
            }
        });

        if (menu == null) {
            menu = table.getComponentPopupMenu();
        }

        if (menu == null) {
            menu = createTablePopuMenu(table, batchSetMenu);
        } else {
            if (initSeparator) {
                menu.addSeparator();
            }

            menu.add(batchSetMenu);
        }

        menu.addPopupMenuListener(new PopupMenuListener() {

            private boolean validSelectedColumn(int columnIndex) {
                if ((unSetcolumnIndex == null) || (unSetcolumnIndex.length == 0)) {
                    return true;
                }
                return Arrays.binarySearch(unSetcolumnIndex, table.convertColumnIndexToModel(columnIndex)) < 0;
            }

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                int columnAtPoint = -1;
                if (table.getMousePosition() != null) {
                    columnAtPoint = table.columnAtPoint(table.getMousePosition());
                }
                batchSetMenu.setActionCommand(String.valueOf(columnAtPoint));
                batchSetMenu.setEnabled((columnAtPoint >= 0) && (table.getSelectedRow() >= 0) && validSelectedColumn(columnAtPoint) && table.isCellEditable(table.getSelectedRow(), columnAtPoint));
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

       /**
     * 创建表格菜单，此方法创建一个弹出菜单，默认实现右键点击的时候选中表格。
     * @param table 表格
     * @param menuItems 菜单项
     * @return 返回弹出菜单
     */
    public static JPopupMenu createTablePopuMenu(final JTable table, JMenuItem... menuItems) {
        JPopupMenu menu = new JPopupMenu();
        MouseAdapter mouselis = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                if (evt.isMetaDown()) {
                    ListSelectionModel selectionModel = table.getSelectionModel();
                    int rowOfPoint = table.rowAtPoint(evt.getPoint());

                    if (rowOfPoint >= 0) {
                        if (evt.isShiftDown()) {
                            int selectIndex = selectionModel.getAnchorSelectionIndex();

                            if (selectIndex < 0) {
                                selectIndex = rowOfPoint;
                            }

                            selectionModel.setSelectionInterval(selectIndex, rowOfPoint);
                        } else if (evt.isControlDown()) {
                            if (selectionModel.isSelectedIndex(rowOfPoint)) {
                                selectionModel.removeSelectionInterval(rowOfPoint, rowOfPoint);
                            } else {
                                selectionModel.addSelectionInterval(rowOfPoint, rowOfPoint);
                            }
                        } else {
                            if (!table.isRowSelected(rowOfPoint)) {
                                selectionModel.setSelectionInterval(rowOfPoint, rowOfPoint);
                            }
                        }
                    }

                    ListSelectionModel columnSelectionModel = table.getColumnModel().getSelectionModel();
                    int columnOfPoint = table.columnAtPoint(evt.getPoint());

                    if (columnOfPoint >= 0) {
                        if (!table.isColumnSelected(columnOfPoint)) {
                            columnSelectionModel.setSelectionInterval(columnOfPoint, columnOfPoint);
                        }
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                if (evt.isMetaDown()) {
                    int rowOfPoint = table.rowAtPoint(evt.getPoint());
                    int selectIndex = table.getSelectionModel().getAnchorSelectionIndex();

                    if ((selectIndex >= 0) && (rowOfPoint >= 0)) {
                        if (evt.isControlDown()) {
                            if (table.isRowSelected(selectIndex)) {
                                table.addRowSelectionInterval(selectIndex, rowOfPoint);
                            } else {
                                table.removeRowSelectionInterval(selectIndex, rowOfPoint);
                            }
                        } else {
                            table.setRowSelectionInterval(selectIndex, rowOfPoint);
                        }
                    }

                    ListSelectionModel columnSelectionModel = table.getColumnModel().getSelectionModel();
                    int columnOfPoint = table.columnAtPoint(evt.getPoint());

                    if (columnOfPoint >= 0) {
                        if (!table.isColumnSelected(columnOfPoint)) {
                            columnSelectionModel.setSelectionInterval(columnOfPoint, columnOfPoint);
                        }
                    }
                }
            }
        };

        table.addMouseListener(mouselis);
        table.addMouseMotionListener(mouselis);

        for (JMenuItem menuItem : menuItems) {
            menu.add(menuItem);
        }

        table.setComponentPopupMenu(menu);

        return menu;
    }

}

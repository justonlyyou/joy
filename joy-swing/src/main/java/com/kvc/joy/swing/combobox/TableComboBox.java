package com.kvc.joy.swing.combobox;

//~--- non-JDK imports --------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.XSwingUtil;
import com.kvc.joy.swing.list.CheckBoxListRenderer;
import com.kvc.joy.swing.table.PropertyTableModel;
import com.kvc.joy.swing.table.RowStringFilter;
import com.kvc.joy.swing.table.TableUtil;
import com.kvc.joy.swing.table.XTable;

/**
 * 在ComboBox弹出的面板中内置Table的组件。这个组件支持对视图中的第一列进行过滤。
 * 同时它也支持在面板中的"AddNew"按钮进行事件的外挂处理。<BR/>
 * <B>DEMO :</B><BR/>
 * <CODE>
 *         //创建数据<br/>
 *         java.util.List dataList=new ArrayList();<br/>
 *         for (int i = 0; i < 10000; i++) {<br/>
 *            dataList.add(new TestObject());<br/>
 *        }<br/>
 *        //生成模型<br/>
 *        ComboBoxTableModel dataModel=new ComboBoxTableModel(<br/>
 *                    dataList, //包含数据的集合<br/>
 *                    new String[]{"d1","d2","d3","d4"}, // 要显示的属性名字，注意大小写<br/>
 *                    new String[]{"One","二","★"});    // 表列头显示的文字，如果少于属性则出现“未命名”<br/>
 *        JTableComboBox table=new JTableComboBox(dataModel);<br/>
 *
 *        //设置一个动态显示的编辑器 “d2”是显示对象的属性名称<br/>
 *        tableComboBox.setEditor(new DynamicComboBoxEditor("d2"));<br/>
 *        JFrame f=new JFrame("Test!");<br/>
 *        f.getContentPane().add(table);<br/>
 *        f.pack();<br/>
 *        f.setVisible(true);<br/>
 * </CODE>
 *  <br/> You can run  <I>com.bas.util.client.swing.TestObject</I>
 */
public class TableComboBox extends JComboBox implements MouseMotionListener, MouseListener, KeyListener, PopupMenuListener, Comparator<String> {

    private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
    // 存放“新增”等按钮的面板
    /** Field description */
    private JPanel actionPanel = null;

    // 上次匹配到的key
    /** Field description */
    private String lastKey = "";

    // 过滤器
    /** Field description */
    private TableRowSorter tableRowSorter = null;
    /** Field description */
    private RowStringFilter rowStringFilter = new RowStringFilter();

    // 处理"AddNew"按钮的事件
    /** Field description */
    private ActionListener addNewActionListener;

    // A comboPopu contain the ScrollPane
    /** Field description */
    private BasicComboPopup popUpComponent;

    // A inner table in ScrollPane to show data
    /** Field description */
    private PopuTable popUpTable;
    /** Field description */
    private JScrollPane scrollPane;

    // A common Model that is used between ComboBox and JTable
    /** Field description */
    private PropertyTableModel tableComboBoxModel;
    private Collection selectedItems;
    private volatile boolean autoSizing;

    /**
     * Constructs ...
     *
     */
    public TableComboBox() {
        super();
    }

    /**
     * 构造一个JTableComboBox
     * @param comboTableModel 输入的数据模型必须是com.bas.util.client.swing.ComboBoxTableModel 对象
     */
    public TableComboBox(ComboBoxTableModel comboTableModel) {
        this(comboTableModel, null);
    }

    public TableComboBox(ComboBoxTableModel comboTableModel, int selectionMode) {
        super(comboTableModel);
        this.selectionMode = selectionMode;
        this.setEditable(true);
        popUpComponent = getComboPopup();
        tableComboBoxModel = comboTableModel;
        initialTable(comboTableModel);
        createInnerScrollPane();
        setMaximumRowCount(0);
        autoSizePopup();
        addPopupMenuListener(this);
    }

    /**
     * 创建一个JTableComboBox组件实体。
     * @param comboTableModel 输入的数据模型必须是com.bas.util.client.swing.ComboBoxTableModel 对象
     * @param addNewActionListener 处理点击"AddNew"按钮的事件。如果这个参数为空，那么面板中不会出现“AddNew”按钮。
     */
    public TableComboBox(ComboBoxTableModel comboTableModel, ActionListener addNewActionListener) {
        super(comboTableModel);
        this.addNewActionListener = addNewActionListener;
        this.setEditable(true);
        popUpComponent = getComboPopup();
        tableComboBoxModel = comboTableModel;
        initialTable(comboTableModel);
        createInnerScrollPane();
        setMaximumRowCount(0);
        autoSizePopup();
        addPopupMenuListener(this);
    }

    /**
     * 取得comboBox中的表格
     * @return table
     */
    public JTable getTable() {
        return popUpTable;
    }

    /**
     * 构造函数
     * @param propertiesName 含有数据对象的Vector，这个Vector中的每个对象类型必须是一致的。
     * @param dataList 包含有统一对象类型的List
     * @param columnNames 显示在表头中的文体
     */
    public TableComboBox(java.util.List dataList, String[] propertiesName, String[] columnNames, int selectionMode) {
        this(new ComboBoxTableModel(dataList, propertiesName, columnNames), selectionMode);
    }

    /**
     * 构造函数
     * @param propertiesName 含有数据对象的Vector，这个Vector中的每个对象类型必须是一致的。
     * @param dataList 包含有统一对象类型的List
     * @param columnNames 显示在表头中的文体
     */
    public TableComboBox(java.util.List dataList, String[] propertiesName, String[] columnNames) {
        this(dataList, propertiesName, columnNames, null);
    }

    /**
     * 构造函数
     * @param addNewActionListener 处理点击"AddNew"按钮的事件。如果这个参数为空，那么面板中不会出现“AddNew”按钮。
     * @param propertiesName 含有数据对象的Vector，这个Vector中的每个对象类型必须是一致的。
     * @param dataList 包含有统一对象类型的List
     * @param columnNames 显示在表头中的文体
     */
    public TableComboBox(java.util.List dataList, String[] propertiesName, String[] columnNames, ActionListener addNewActionListener) {
        this(new ComboBoxTableModel(dataList, propertiesName, columnNames), addNewActionListener);
    }

    /**
     * {@link javax.swing.JTable.moveColumn() }
     * @param viewIndexStart 要移动的列在试图上的序列号
     * @param viewIndexMoveTo 要移动到哪个列号上
     */
    public void moveColumn(int viewIndexStart, int viewIndexMoveTo) {
        this.popUpTable.moveColumn(viewIndexStart, viewIndexMoveTo);
    }

    /**
     * 根据数组中包含的列头的显示名称来重序列。如果这个数据小于当前的列的总数，<BR/>
     * 那么只按照这个数组中已含有的列头名排序。
     * @param newColumnNameIndex 包含的列头的显示名称的数组
     */
    public void reIndexColumn(String[] newColumnNameIndex) {
        for (int columnNameIndex = 0; columnNameIndex < newColumnNameIndex.length - 1; columnNameIndex++) {
            String columnName = newColumnNameIndex[columnNameIndex];
            int currentColumnIndex = popUpTable.getColumnModel().getColumnIndex(columnName);

            if (currentColumnIndex > -1) {
                popUpTable.moveColumn(currentColumnIndex, columnNameIndex);
            }
        }
    }

    // 创建一个内部的ScrollPane ，用于实现拖放
    /**
     * Method description
     *
     */
    private void createInnerScrollPane() {
        scrollPane = new JScrollPane(popUpTable);
        JLabel iconLable = new JLabel(XSwingUtil.getIconInJar("ResizeGripper.gif"));
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, iconLable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JComponent upComponent = null;
        if (selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) {
            JCheckBox checkbox = new JCheckBox();
            checkbox.setBorderPainted(true);
            checkbox.setHorizontalAlignment(JCheckBox.CENTER);
            checkbox.addItemListener(popUpTable);
            upComponent = checkbox;
        } else {
            upComponent = new JLabel();
        }
        upComponent.setFocusable(false);
        upComponent.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, upComponent);
        setPopupComponent(scrollPane);
        iconLable.addMouseMotionListener(this);
    }

    /**
     * Method description
     *
     */
    @Override
    public void updateUI() {
        super.updateUI();

        if ((popUpComponent != getComboPopup()) && (scrollPane != null)) {
            setPopupComponent(scrollPane);
        }
    }

    /**
     * 促使化弹出面板中的Table
     * @param comboTableModel 数据的模型
     */
    private void initialTable(ComboBoxTableModel comboTableModel) {
        popUpTable = new PopuTable(comboTableModel);
        tableRowSorter = new TableRowSorter(comboTableModel);
        popUpTable.setRowSorter(tableRowSorter);
        popUpTable.addMouseListener(this);
        popUpTable.addMouseMotionListener(this);
        popUpTable.setFocusable(false);
        TableUtil.autoResizeTable(popUpTable, true);
    }

    @Override
    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        if (scrollPane.getWidth() < getWidth()) {
            Dimension prefreDimension = scrollPane.getPreferredSize();
            prefreDimension.width = getWidth();
            scrollPane.setPreferredSize(prefreDimension);
        }
        //自动排序
        sortFirstColumnIfString();
    }

    @Override
    public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        if (selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) {
            setSelectedItem(getSelectedItems());
        }
    }

    @Override
    public void popupMenuCanceled(PopupMenuEvent e) {
    }

    /**
     * @return the selectedItems
     */
    public Collection getSelectedItems() {
        if (selectedItems == null) {
            selectedItems = new ArrayList() {

                @Override
                public String toString() {
                    String collectionInfo = super.toString();
                    if (collectionInfo.equals("[]")) {
                        collectionInfo = "";
                    } else {
                        collectionInfo = collectionInfo.substring(1, collectionInfo.length() - 1);
                    }
                    return collectionInfo;
                }
            };
        }
        selectedItems.clear();
        selectedItems.addAll(popUpTable.getSelectedItems());
        return selectedItems;
    }

    /**
     * @param selectedItems the selectedItems to set
     */
    public void setSelectedItems(Collection selectedItems) {
        popUpTable.selectItems(selectedItems);
    }

    private class PopuTable extends XTable implements ItemListener {

        private JList list;

        public PopuTable(TableModel model) {
            super(model, false);
            createIndexRowHeader();
        }

        @Override
        public void tableChanged(TableModelEvent e) {
            super.tableChanged(e);
            if (!autoSizing && popUpTable != null) {
                autoSizing = true;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        autoSizePopup();
                    }
                });
            }
        }

        @Override
        protected int rowHeaderChanged() {
            int cellWidth = super.rowHeaderChanged();
            if (selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION && popUpTable.getRowCount() > 0) {
                list.setFixedCellWidth(cellWidth + 16);
            }
            return cellWidth;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            super.valueChanged(e);
            if (!e.getValueIsAdjusting()) {
                scrollRectToVisible(getCellRect(getSelectedRow(), 0, true));
            }
        }

        @Override
        protected JComponent createIndexRowHeader() {
            list = (JList) super.createIndexRowHeader();

            list.setModel(new AbstractListModel() {

                @Override
                public int getSize() {
                    return getRowCount();
                }

                @Override
                public Object getElementAt(int index) {
                    return tableComboBoxModel.getRowObject(convertRowIndexToModel(index));
                }
            });
            list.setSelectionModel(getSelectionModel());
            if (selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) {
                CheckBoxListRenderer renderer = new CheckBoxListRenderer() {

                    @Override
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        setText("" + (index + 1));
                        return this;
                    }
                };
                JTableHeader header = getTableHeader();
                renderer.setOpaque(true);
                renderer.setBorderPainted(true);
                renderer.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                renderer.setHorizontalAlignment(JLabel.LEFT);
                renderer.setForeground(header.getForeground());
                renderer.setBackground(header.getBackground());
                renderer.setFont(header.getFont());
                list.setCellRenderer(renderer);
                list.setEnabled(true);
                for (MouseListener mouseListener : list.getMouseListeners()) {
                    list.removeMouseListener(mouseListener);
                }
                for (MouseMotionListener mouseListener : list.getMouseMotionListeners()) {
                    list.removeMouseMotionListener(mouseListener);
                }
                MouseAdapter mouselis = new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent evt) {
                        ListSelectionModel selectionModel = list.getSelectionModel();
                        int rowOfPoint = list.locationToIndex(evt.getPoint());

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
                                if ((evt.getX() > 18 || evt.getX() < 3) || !list.isSelectedIndex(rowOfPoint)) {
                                    selectionModel.setSelectionInterval(rowOfPoint, rowOfPoint);
                                }
                            }
                        }
                    }

                    @Override
                    public void mouseDragged(MouseEvent evt) {
                        int rowOfPoint = list.locationToIndex(evt.getPoint());
                        int selectIndex = list.getSelectionModel().getAnchorSelectionIndex();
                        if ((selectIndex >= 0) && (rowOfPoint >= 0)) {
                            if (evt.isControlDown()) {
                                if (list.isSelectedIndex(selectIndex)) {
                                    list.addSelectionInterval(selectIndex, rowOfPoint);
                                } else {
                                    list.removeSelectionInterval(selectIndex, rowOfPoint);
                                }
                            } else {
                                list.setSelectionInterval(selectIndex, rowOfPoint);
                            }
                        }
                    }
                };
                list.addMouseMotionListener(mouselis);
                list.addMouseListener(mouselis);
            }
            return list;
        }

        public Collection getSelectedItems() {
            return ((CheckBoxListRenderer) list.getCellRenderer()).getCheckedItems();
        }

        public void selectItems(Collection items) {
            ((CheckBoxListRenderer) list.getCellRenderer()).clearItems();
            ((CheckBoxListRenderer) list.getCellRenderer()).checkItems(items, true);
        }

        public void addSelectedItem(Object item) {
            ((CheckBoxListRenderer) list.getCellRenderer()).checkItem(item, true);
        }

        public void selectItem(Object item) {

            if (selectionMode == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) {
                ((CheckBoxListRenderer) list.getCellRenderer()).clearItems();
                if (item != null) {
                    ((CheckBoxListRenderer) list.getCellRenderer()).checkItem(item, true);
                }
            }
            if (item != null) {
                list.setSelectedValue(item, true);
            }
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            ((CheckBoxListRenderer) list.getCellRenderer()).checkAll(e.getStateChange() == ItemEvent.SELECTED);
        }
    }

    /**
     * This method tries to access the combobox' ComboPopup object.
     * It does so by looking for an instance of
     * @return the combobox' ComboPopup object
     */
    protected javax.swing.plaf.basic.BasicComboPopup getComboPopup() {
        for (int childComponentIndex = 0, n = getUI().getAccessibleChildrenCount(this); childComponentIndex < n; childComponentIndex++) {
            Object component = getUI().getAccessibleChild(this, childComponentIndex);

            if (component instanceof javax.swing.plaf.basic.BasicComboPopup) {
                return (javax.swing.plaf.basic.BasicComboPopup) component;
            }
        }

        return null;
    }

    /**
     * Sets the popUpComponent that is displayed inside the combobox' popup menu.
     * @param component the popUpComponent that should be displayed inside the popup menu
     */
    protected void setPopupComponent(JComponent component) {
        popUpComponent = getComboPopup();

        if (popUpComponent != null) {
            popUpComponent.removeAll();
            popUpComponent.add(component);

            if (this.addNewActionListener != null) {
                actionPanel = new ActionPanel();
                popUpComponent.add(actionPanel);
            }
        }
    }

    protected JComponent getPopupComponent() {
        if (popUpComponent == null) {
            return null;
        }
        return (JComponent) popUpComponent.getComponent(0);
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!(e.getSource() instanceof javax.swing.JTable)) {
            ((JComponent) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        }

        int row = popUpTable.rowAtPoint(e.getPoint());
        int column = popUpTable.columnAtPoint(e.getPoint());

        if ((row > -1) && (column > -1)) {
            this.popUpTable.setRowSelectionInterval(row, row);
        }

        repaint();
    }

    /**
     * Method description
     *
     */
    public void autoSizePopup() {
        TableUtil.autoResizeTable(popUpTable, true);
        int deafultHeight = (popUpTable.getRowHeight() + popUpTable.getRowMargin()) * 5 + popUpTable.getTableHeader().getPreferredSize().height;
        double width = Math.max(popUpTable.getPreferredSize().getWidth() + scrollPane.getVerticalScrollBar().getPreferredSize().width + popUpTable.rowHeaderChanged(), this.getWidth());
        scrollPane.setPreferredSize(new Dimension((int) width + 2, deafultHeight));
        popUpComponent.pack();
        autoSizing = false;
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        // 计算最大和最小的高度
        int minHeight = (popUpTable.getRowHeight() + popUpTable.getRowMargin()) * 3 + popUpTable.getTableHeader().getHeight();
        int maxHeight = (popUpTable.getRowHeight() + popUpTable.getRowMargin()) * 20 + popUpTable.getTableHeader().getHeight();

        JViewport viewPort = scrollPane.getViewport();

        // 当前的大小
        int viewPortWidth = scrollPane.getSize().width;
        int viewPortHight = scrollPane.getSize().height;
        Point endPoint = e.getLocationOnScreen();
        Point startPoint = scrollPane.getLocationOnScreen();

        // 计算操作后ViewPort的大小
        int operateHeight = (int) endPoint.getY() - startPoint.y;
        int operateWidth = (int) endPoint.getX() - startPoint.x;
        if (operateHeight > maxHeight) {
            viewPortHight = maxHeight;
        } else {
            viewPortHight = Math.max(operateHeight, minHeight);
        }

        // 计算最小的宽度,与ComboBox同宽
        int minWidth = (int) getSize().getWidth() - scrollPane.getVerticalScrollBar().getWidth();

        viewPortWidth = operateWidth;
        viewPortWidth = Math.max(viewPortWidth, minWidth);
        Dimension size = viewPort.getPreferredSize();
        size.width = viewPortWidth + 5;
        size.height = viewPortHight + 5;
//        viewPort.setPreferredSize(new Dimension(viewPortWidth + 2, viewPortHight + 20));
        scrollPane.setPreferredSize(size);
        scrollPane.revalidate();
        popUpComponent.pack();
    }

    /**
     * Method description
     *
     */
    @Override
    public void removeAllItems() {
        tableComboBoxModel.clearData();
        super.removeAllItems();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

//      this.popUpTable.requestFocus();
        int rowIndex = ((JTable) e.getSource()).getSelectedRow();
        if (rowIndex > -1) {
            rowIndex = popUpTable.convertRowIndexToModel(rowIndex);
            if (selectionMode == ListSelectionModel.SINGLE_SELECTION) {
                TableComboBox.this.setSelectedItem(tableComboBoxModel.getRowObject(rowIndex));
            } else {
                popUpTable.addSelectedItem(tableComboBoxModel.getRowObject(rowIndex));
            }
        }
        hidePopup();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        JTextComponent textEditor = (JTextComponent) e.getSource();

        if (tableComboBoxModel.getRowCount() > 0) {
            String key = textEditor.getText().trim();

            if (isValidKey(e.getKeyCode())) {
                updateFilter(key);
            }
        } else {
            textEditor.setText("");
        }
        if ((e.getKeyCode() != KeyEvent.VK_ENTER) && !isPopupVisible()) {
            showPopup();
        }
    }

    /**
     * Method description
     *
     *
     * @param keyCode
     *
     * @return
     */
    private boolean isValidKey(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
                return false;
        }

        return true;
    }

    /**
     * Method description
     *
     */
    private void returnLastFilter() {
        JTextComponent textComponentEditor = (JTextComponent) this.getEditor().getEditorComponent();

        textComponentEditor.setText(lastKey);

        if (filting(lastKey) <= 0) {
            textComponentEditor.setText("");
        }
    }

    /**
     * 过滤
     * @param key 过滤的字符
     * @return filteredRowCount
     */
    private int filting(String key) {
        int currentFirstColumnIndex = popUpTable.convertColumnIndexToModel(0);

        rowStringFilter.setSubString(key);
        rowStringFilter.setIndices(currentFirstColumnIndex);
        tableRowSorter.setRowFilter(rowStringFilter);

        return popUpTable.getRowCount();
    }

    /**
     * 根据关键字过滤弹出表格的数据
     * @param key 关键字
     */
    private void updateFilter(String key) {
        int allRowCount = (tableComboBoxModel == null) ? 0 : tableComboBoxModel.getRowCount();

        tableRowSorter.setSortKeys(Collections.emptyList());

        int matchRowCount = filting(key);
        int column = popUpTable.convertColumnIndexToModel(0);
        Class columnClass = popUpTable.getColumnClass(0);
        boolean stringSort = columnClass.equals(String.class) || columnClass.equals(Object.class);

        if ((matchRowCount <= 0) && (matchRowCount < allRowCount)) {
            returnLastFilter();
        } else {
            lastKey = key;

            if (stringSort && (tableRowSorter.getComparator(column) != this)) {
                tableRowSorter.setComparator(column, this);
            }
        }
    }

    /**
     * 默认第一列排序
     */
    protected void sortFirstColumnIfString() {
        int column = popUpTable.convertColumnIndexToModel(0);
        java.util.List sortKeys = tableRowSorter.getSortKeys();
        if (sortKeys == null || sortKeys.isEmpty()) {
            Class columnClass = popUpTable.getColumnClass(0);
            boolean stringSort = columnClass.equals(String.class) || columnClass.equals(Object.class);
            if (stringSort) {
                tableRowSorter.setSortKeys(Collections.singletonList(new RowSorter.SortKey(column, SortOrder.ASCENDING)));
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param aModel
     */
    @Override
    public void setModel(ComboBoxModel aModel) {
        super.setModel(aModel);

        if ((aModel != null) && (aModel instanceof ComboBoxTableModel)) {
            ComboBoxTableModel comboTableModel = (ComboBoxTableModel) aModel;

            tableComboBoxModel = comboTableModel;

            if (popUpTable != null) {
                popUpTable.setModel(comboTableModel);
                tableRowSorter.setModel(comboTableModel);
                autoSizePopup();
                scrollPane.revalidate();
                popUpComponent.pack();
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param anObject
     */
    @Override
    public void setSelectedItem(Object anObject) {
        if (lastKey == null || !lastKey.isEmpty()) {
            this.lastKey = "";
            updateFilter(this.lastKey);
        }
        this.editor.setItem(anObject);
        super.setSelectedItem(anObject);
        if (anObject instanceof Collection) {
            popUpTable.selectItems(selectedItems);
        } else {
            popUpTable.selectItem(anObject);
        }
    }

    /**
     *
     * @param anObject
     */
    public void setSelectedItemOfModelItem(Object anObject) {
        if (lastKey == null || !lastKey.isEmpty()) {
            this.lastKey = "";
            updateFilter(this.lastKey);
        }
        if (anObject == null) {
            getModel().setSelectedItem(null);
        } else {
            boolean found = false;
            Object oldSelection = selectedItemReminder;

            if ((oldSelection == null) || !oldSelection.equals(anObject)) {
                for (int i = 0; i < dataModel.getSize(); i++) {
                    Object element = dataModel.getElementAt(i);

                    if (anObject.equals(element)) {
                        getModel().setSelectedItem(element);
                        found = true;

                        break;
                    }
                }

                if (!found) {
                    super.setSelectedItem(anObject);
                }
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param anEditor
     */
    @Override
    public void setEditor(ComboBoxEditor anEditor) {
        super.setEditor(anEditor);

        if (anEditor == null) {
            return;
        }

        JTextComponent textComponentEditor = (JTextComponent) anEditor.getEditorComponent();

        textComponentEditor.addKeyListener(this);
        if (textComponentEditor instanceof JTextField) {
            ((JTextField) textComponentEditor).addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if ((popUpTable.getRowCount() > 0) && !((JTextField) e.getSource()).getText().trim().isEmpty()) {
                        RowStringFilter filter = (RowStringFilter) tableRowSorter.getRowFilter();

                        if ((filter != null) && !filter.getSubString().isEmpty()) {
                            setSelectedItem(tableComboBoxModel.getRowObject(popUpTable.convertRowIndexToModel(0)));
                        }
                    }
                }
            });
        }
    }

    /**
     * Method description
     *
     *
     * @param columnClass
     * @param cellRenderer
     */
    public void setTableCellRenderer(Class columnClass, TableCellRenderer cellRenderer) {
        popUpTable.setDefaultRenderer(columnClass, cellRenderer);
    }

    /**
     * Method description
     *
     *
     * @param columnIndex
     * @param cellRenderer
     */
    public void setTableCellRenderer(int columnIndex, TableCellRenderer cellRenderer) {
        popUpTable.getColumnModel().getColumn(columnIndex).setCellRenderer(cellRenderer);
    }

    /**
     * Method description
     *
     *
     * @param bg
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);

        if (getEditor() != null) {
            getEditor().getEditorComponent().setBackground(bg);
        }
    }

    /**
     * Method description
     *
     *
     * @param name
     * @param anotherName
     *
     * @return
     */
    @Override
    public int compare(String name, String anotherName) {
        if ((lastKey == null) || lastKey.isEmpty()) {
            return name.compareTo(anotherName);
        }

        // 完全匹配
        if (lastKey.equals(name)) {
            return -1;
        }

        if (lastKey.equals(anotherName)) {
            return 1;
        }

        // 忽略的完全匹配
        if (lastKey.equalsIgnoreCase(name)) {
            return -1;
        }

        if (lastKey.equalsIgnoreCase(anotherName)) {
            return 1;
        }

        // 开始匹配
        if (name.startsWith(lastKey)) {
            return -1;
        }

        if (anotherName.startsWith(lastKey)) {
            return 1;
        }

        // 开始的忽略匹配
        if (name.toLowerCase().startsWith(lastKey.toLowerCase())) {
            return -1;
        }

        if (anotherName.toLowerCase().startsWith(lastKey.toLowerCase())) {
            return 1;
        }

        // 完全包含
        if (name.contains(lastKey)) {
            return -1;
        }

        if (anotherName.contains(lastKey)) {
            return 1;
        }

        // 忽略包含
        if (name.toLowerCase().contains(lastKey)) {
            return -1;
        }

        if (anotherName.toLowerCase().contains(lastKey)) {
            return 1;
        }

        return name.compareTo(anotherName);
    }

//  public Object getSelectedItem() {
//      Object originalSelectedItem = super.getSelectedItem();
//      Object selectedItem = originalSelectedItem;
//
//      // 根据不同的取值策略取值
//      if (originalSelectedItem != null) {
//          switch (editPolicy) {
//              case EDIT_AUTO_MATCH :
//
//                  if ((originalSelectedItem.toString().length()>0) && (popUpTable.getRowCount() > 0)) {
//                      int rowIndex = popUpTable.convertRowIndexToModel(0);
//
//                      selectedItem = tableComboBoxModel.getRowObject(rowIndex);
//                      JTableComboBox.this.setSelectedItem(selectedItem);
//                  } else {
//                      selectedItem = null;
//                  }
//
//                  break;
//
//              case EDIT_CUSTOM :
//
//                  // do nothing
//                  break;
//
//              case EDIT_NOT_CUSTOM :
//                  if (originalSelectedItem instanceof String) {
//                      selectedItem = null;
//                  }
//
//                  break;
//
//              default :
//                  selectedItem = null;
//
//                  break;
//          }
//      }
//      return selectedItem;
//  }
    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    class ActionPanel extends javax.swing.JPanel {

//      private Dimension buttonSize = new Dimension(15, 15);
        /**
         * Constructs ...
         *
         */
        public ActionPanel() {

//          setLayout(new java.awt.GridLayout(1, 3, 2, 0));
            this.add(createButton("AddNew"));

//          this.add(createButton("Search"));
        }

        /**
         * Method description
         *
         *
         * @param keyName
         *
         * @return
         */
        private JButton createButton(String keyName) {
            JButton newButton = new javax.swing.JButton(
            		I18nTool.getLocalStr(keyName));

//          newButton.setPreferredSize(buttonSize);
            newButton.addActionListener(addNewActionListener);

//          newButton.setActionCommand(keyName);
            return newButton;
        }
    }
}

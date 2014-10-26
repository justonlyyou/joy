/*
 * NewJPanel.java
 *
 * Created on 2007年4月23日, 下午2:14
 */
package org.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 可固定列的表格
 * @author zy
 */
public class FixedColumnTable extends javax.swing.JScrollPane implements PropertyChangeListener {

    // 显示固定列的表
    /** Field description */
    protected JTable fixedTable = null;
    /** Field description */
    private JComponent indexRowHeader = null;
    /**
     * 固定表的头部
     */
    private JPanel headerPanel;
    /**
     * 主表格
     */
    protected FixedTable mainTable = null;
    /**
     * 固定表格容器
     */
    private JPanel fixedTablePanel;

    /**
     * Constructs ...
     *
     */
    public FixedColumnTable() {
    }

    /**
     * 固定列的表格
     * @param tableModel 表格模型
     * @param fixedCount 要固定的列总数
     */
    public FixedColumnTable(TableModel tableModel, int fixedCount) {
        initFixedTable(tableModel, fixedCount);
    }

    /**
     * 获取表格模型
     * @return 返回表格模型
     */
    public TableModel getTableModel() {
        return this.mainTable.getModel();
    }

    /**
     *
     * 获取表格
     * @return 返回表格
     */
    public JTable getTable() {
        return this.mainTable;
    }

    /**
     *
     * 获取固定列的表格
     * @return 返回表格
     */
    public JTable getFixedTable() {
        return this.fixedTable;
    }

    /**
     *
     * 初始化表格
     * @param tableModel 表格模型
     * @param fixedCount 固定的列总数
     */
    public void initFixedTable(TableModel tableModel, int fixedCount) {
        mainTable = new FixedTable(tableModel, fixedCount, false);
        fixedTable = new FixedTable(tableModel, fixedCount, true);
        mainTable.addPropertyChangeListener("rowHeight", this);

        // 同步过滤器
        mainTable.setAutoCreateRowSorter(true);
        fixedTable.setRowSorter(mainTable.getRowSorter());

        fixedTablePanel = new JPanel(new BorderLayout());
        fixedTablePanel.add(fixedTable);
        fixedTablePanel.add(createIndexRowHeader(), BorderLayout.WEST);
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(fixedTable.getTableHeader(), BorderLayout.CENTER);

        // 拼装整个大的面板
        this.setRowHeaderView(fixedTablePanel);
        this.setViewportView(mainTable);
        this.setCorner(JScrollPane.UPPER_LEADING_CORNER, headerPanel);
        mainTable.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                if (!mainTable.isDisableAction()) {
                    rowHeaderChanged();
                }
            }
        });
        mainTable.getRowSorter().addRowSorterListener(new RowSorterListener() {

            @Override
            public void sorterChanged(RowSorterEvent e) {
                rowHeaderChanged();
            }
        });

        fixedTable.getTableHeader().addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                ColourfulTableHeadCellRenderer mainTableHeaderRender = (ColourfulTableHeadCellRenderer) mainTable.getTableHeader().getDefaultRenderer();
//                int maxHight = ((ColourfulTableHeadCellRenderer) fixedTable.getTableHeader().getDefaultRenderer()).getMaxHight(0);
//
//                mainTableHeaderRender.setRelationHight(maxHight);
            }
        });
    }

    public void setLoadingStyle(boolean loading) {
        Component rowComponent = null;
        Component header = null;
        if (!loading) {
            rowComponent = fixedTablePanel;
            header = headerPanel;
        }
        setRowHeaderView(rowComponent);
        setCorner(JScrollPane.UPPER_LEFT_CORNER, header);
        ((XTable) mainTable).setloadingDataStyle(loading);
    }

    protected int rowHeaderChanged() {
        JList list = (JList) indexRowHeader;
        int cellWidth = 0;
        if (mainTable.getRowCount() > 0) {
            cellWidth = list.getFontMetrics(mainTable.getTableHeader().getFont()).stringWidth(String.valueOf(mainTable.getRowCount()) + 3);
        }
        list.setFixedCellWidth(cellWidth);
//                             + table.getIntercellSpacing().height);
        Component box = Box.createRigidArea(new Dimension(cellWidth, headerPanel.getHeight()));
        headerPanel.add(box, BorderLayout.WEST);
        headerPanel.doLayout();
        list.repaint();
        return cellWidth;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    protected JComponent createIndexRowHeader() {
        if (indexRowHeader != null) {
            return indexRowHeader;
        }

        ListModel lm = new AbstractListModel() {

            @Override
            public int getSize() {
                return mainTable.getRowCount();
            }

            @Override
            public Object getElementAt(int index) {
                return index + 1;
            }
        };

        indexRowHeader = new JList(lm);
        ((JList) indexRowHeader).setFixedCellHeight(mainTable.getRowHeight());

        JTableHeader header = mainTable.getTableHeader();
        final JLabel indexRowHeaderRenderer = new JLabel();

        indexRowHeaderRenderer.setOpaque(true);
        indexRowHeaderRenderer.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        indexRowHeaderRenderer.setHorizontalAlignment(JLabel.CENTER);
        indexRowHeaderRenderer.setForeground(header.getForeground());
        indexRowHeaderRenderer.setBackground(header.getBackground());
        indexRowHeader.setBackground(header.getBackground());
        indexRowHeaderRenderer.setFont(header.getFont());
        ((JList) indexRowHeader).setCellRenderer(new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                indexRowHeaderRenderer.setText((value == null) ? "" : value.toString());

                return indexRowHeaderRenderer;
            }
        });

        return indexRowHeader;
    }

    /**
     *
     * 添加鼠标监听器
     *
     * @param mouseListener
     */
    @Override
    public void addMouseListener(MouseListener mouseListener) {
        super.addMouseListener(mouseListener);
        if (mainTable != null) {
            this.mainTable.addMouseListener(mouseListener);
        }
    }

    /**
     * Method description
     *
     *
     * @param evt
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("rowHeight")) {
            this.fixedTable.setRowHeight((Integer) evt.getNewValue());
        }
    }

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    protected class FixedTable extends XTable {

        /**
         * 是否固定
         */
        private boolean fix;
        /**
         * 可见的列总数
         */

//      private int visibleColumnCount;
        /**
         * 固定的列总数
         */
        private int fixedColumnCount;

        /**
         * Constructs ...
         *
         */
        public FixedTable() {
            super();
        }

        /**
         *
         * @param tableModel
         * @param fixedColumnCount
         * @param fixOrMain
         */
        public FixedTable(TableModel tableModel, int fixedColumnCount, boolean fixOrMain) {
            super(tableModel, false);
            this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            this.fix = fixOrMain;
            this.fixedColumnCount = fixedColumnCount;

//          visibleColumnCount = this.getColumnCount() - fixedColumnCount;
            if (fix) {
                setFixProperty();
            }

            hideColumn();
        }

        @Override
        public void setRowHeight(int rowHeight) {
            super.setRowHeight(rowHeight);
            if (indexRowHeader != null) {
                ((JList) indexRowHeader).setFixedCellHeight(rowHeight);
            }
        }

        /**
         * Method description
         *
         */
        private void setFixProperty() {
            this.setEnabled(false);

            Color bg = UIManager.getColor("Panel.background");

            this.setBackground(bg);
        }

        /**
         * 隐藏表头
         */
        private void hideColumn() {
            TableColumnModel tableColumnModel = this.getColumnModel();

            if (fix) {
                for (int index = this.fixedColumnCount; index < tableColumnModel.getColumnCount();) {
                    this.removeColumn(tableColumnModel.getColumn(index));
                }
            } else if (tableColumnModel.getColumnCount() > 0) {
                for (int index = 0; index < this.fixedColumnCount; index++) {
                    this.removeColumn(tableColumnModel.getColumn(0));
                }
            }
        }

        /**
         * 表格变化的时候隐藏表头
         * @param e
         */
        @Override
        public void tableChanged(TableModelEvent e) {
            if (!disableAction) {
                super.tableChanged(e);
                if (this.getColumnCount() > (this.fix ? fixedColumnCount : ((TableModel) e.getSource()).getColumnCount() - fixedColumnCount)) {
                    hideColumn();
                }
            }
        }
    }
}

package org.joy.swing.table;

import org.joy.commons.lang.string.I18nTool;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.Vector;

/**
 * 有总计行的表格
 * @author zy
 */
public class TotalTable extends javax.swing.JPanel {

    /**
     * 主表格模型
     */
    private TableModel mainTableModel;
    /**
     * 默认高
     */
    public static final int DEFAULT_HIGHT = 38;
    /**
     * 默认的总计行高
     */
    private static final int DEFAULT_TOTAL_ROW_HIGHT = 21;
    /**
     * 要总计的列索引
     */
    private int[] countColumnIndex;
    /**
     * total table's scroll
     */
    private JScrollPane totalRowTableScroll;
    private JLabel label = null;
    private boolean valueChanged = false;
    private JTable totalRowTable = null;

    /** Creates new form NewJPanel */
    public TotalTable() {
        this(null);
    }

    /**
     * 构造有指定表格模型的总计表格
     * @param tableModel 表格模型
     * @param toCountColumnIndex 要总计的列索引
     */
    public TotalTable(TableModel tableModel, int... toCountColumnIndex) {
        initComponents();
        integrateTotalTable();
        setTableModel(tableModel);
        setCountColumns(toCountColumnIndex);
    }

    /**
     * 设置表格模型
     * @param tableModel 表格模型
     */
    public void setTableModel(TableModel tableModel) {
        if (tableModel != null) {
            mainTable.setModel(tableModel);
        }
        mainTableModel = mainTable.getModel();
        if (!(totalRowTable.getModel() instanceof TotalTableModel)) {
            totalRowTable.setModel(new TotalTableModel());
        } else {
            ((TotalTableModel) totalRowTable.getModel()).initModel();
        }
        initTableProperties();
    }

    /**
     * 获得主表格
     * @return 返回主表格的实例
     */
    public JTable getTable() {
        return this.mainTable;
    }

    public void setTable(MainTotalTable outTable) {
        if (mainTable != outTable) {
            //将旧的视口替换
            JScrollPane scrollPane = ((XTable) mainTable).getParentScrollPane();
            //设置的时候需要重建过滤模型
            mainTable = outTable;
            setTableModel(mainTable.getModel());
            scrollPane.setViewportView(mainTable);
            initTableProperties();
        }
    }

    protected void initTableProperties() {
        totalRowTable.setTableHeader(mainTable.getTableHeader());
        totalRowTable.setColumnModel(mainTable.getColumnModel());
        totalRowTable.setAutoResizeMode(mainTable.getAutoResizeMode());
        mainTable.getTableHeader().setTable(mainTable);
        //和统计行一起使用
        ((XTable) mainTable).setUseWithTotalRow(true);
        mainTableModel.addTableModelListener((TotalTableModel) totalRowTable.getModel());
    }

    /**
     * 设置总计行的渲染器
     * @param toSetRenderer 要设置的绘制器
     */
    public void setTotalRenderer(TableCellRenderer toSetRenderer) {
        this.totalRowTable.setDefaultRenderer(TotalTable.class, toSetRenderer);
    }

    /**
     * 设置总计行默认绘制器的参数
     * @param floatOrPrecent 是浮点或者百分号类型显示总计
     * @param precision 浮点的精度
     * @param groupingUsed 是否要用逗号分割
     */
    public void setTotalRendererProperties(Boolean floatOrPrecent, Integer precision, Boolean groupingUsed) {
        FloatRenderer renderer = (FloatRenderer) this.totalRowTable.getDefaultRenderer(TotalTable.class);
        if (floatOrPrecent != null) {
            renderer.setViewType(floatOrPrecent.booleanValue() ? FloatRenderer.FLOAT_NUMBER : FloatRenderer.PERCENT_NUMBER);
        }
        if (precision != null) {
            renderer.setPrecision(precision);
        }
        if (groupingUsed != null) {
            renderer.setGroupingUsed(groupingUsed);
        }
    }

    /**
     * 获得总计的值
     * @param columnIndex 要获取的值的列索引
     * @return 返回该索引的列的总计值
     */
    public Object getTotalValue(int columnIndex) {
        return getColumnValueTotal(columnIndex);
    }

    protected JLabel createTotalLabel() {
        return new JLabel() {

            @Override
            public Dimension getPreferredSize() {
                Dimension dimension = super.getPreferredSize();
                if (mainTableScroll.getRowHeader() != null) {
                    dimension.width = mainTableScroll.getRowHeader().getWidth();
                }
                return dimension;
            }
        };

    }

    protected XTable createMainTable() {
        return new MainTotalTable();
    }

    /**
     * 初始化面板
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainTableScroll = new javax.swing.JScrollPane();
        mainTable = createMainTable();

        setLayout(new java.awt.BorderLayout());

        mainTableScroll.setPreferredSize(new java.awt.Dimension(400, 300));

        mainTable.setAutoCreateRowSorter(true);
        mainTableScroll.setViewportView(mainTable);

        add(mainTableScroll, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JTable mainTable;
    javax.swing.JScrollPane mainTableScroll;
    // End of variables declaration//GEN-END:variables

    /**
     * 设置自动调整列宽模式
     * @param tableResizeMode 表格调整列宽的模式
     */
    public void setAutoResizeMode(int tableResizeMode) {
        mainTable.setAutoResizeMode(tableResizeMode);
        totalRowTable.setAutoResizeMode(tableResizeMode);
        if (tableResizeMode == JTable.AUTO_RESIZE_OFF) {
            totalRowTableScroll.setPreferredSize(new Dimension(mainTableScroll.getPreferredSize().width, DEFAULT_HIGHT));
            totalRowTableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        }
    }

    /**
     * 整合主表格与合计行表格
     */
    private void integrateTotalTable() {
        totalRowTable = new JTable();
        totalRowTable.setEnabled(false);
        mainTableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //初始化合计行表格
        totalRowTable.setRowHeight(DEFAULT_TOTAL_ROW_HIGHT);
        totalRowTable.setBackground(UIManager.getColor("Panel.background"));
        totalRowTableScroll = new JScrollPane(totalRowTable) {

            @Override
            public void setColumnHeaderView(Component view) {
            }
        };

        totalRowTableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        final JScrollBar bar1 = mainTableScroll.getHorizontalScrollBar();
        JScrollBar bar2 = totalRowTableScroll.getHorizontalScrollBar();

        bar2.addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                bar1.setValue(e.getValue());
            }
        });
        JScrollBar vBar = mainTableScroll.getVerticalScrollBar();
        vBar.addComponentListener(new ComponentAdapter() {

            @Override
            public void componentShown(ComponentEvent e) {
                totalRowTableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            }

            /**
             * Invoked when the component has been made invisible.
             */
            @Override
            public void componentHidden(ComponentEvent e) {
                totalRowTableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            }
        });
        totalRowTableScroll.setPreferredSize(new Dimension(mainTableScroll.getPreferredSize().width, DEFAULT_TOTAL_ROW_HIGHT + 2));
        if (label == null) {
            label = createTotalLabel();
            label.setText(I18nTool.getLocalStr("Total"));
            label.setBorder(mainTable.getTableHeader().getBorder());
            label.setHorizontalAlignment(JLabel.RIGHT);
            label.setVerticalAlignment(JLabel.TOP);
        }
        totalRowTableScroll.setRowHeaderView(label);
        this.add(totalRowTableScroll, BorderLayout.SOUTH);
        totalRowTable.setDefaultRenderer(TotalTable.class, new FloatRenderer(FloatRenderer.FLOAT_NUMBER, 6));
    }

    /**
     * 设置总计的列索引
     * @param columnIndex 要总计的列索引
     */
    public void setCountColumns(int... columnIndex) {
        this.countColumnIndex = columnIndex;
    }

    /**
     * 获得列的总计值
     * @param columnIndex 列索引
     * @return 返回值
     */
    private Object getColumnValueTotal(int columnIndex) {
        if (this.countColumnIndex != null && countColumnIndex.length > 0 && Arrays.binarySearch(this.countColumnIndex, columnIndex) >= 0) {
            return getCountValueOfColumn(mainTable.convertColumnIndexToView(columnIndex), this.mainTable);
        } else if (isTotalable(columnIndex)) {
            return getCountValueOfColumn(mainTable.convertColumnIndexToView(columnIndex), mainTable);
        }
        return "Invalid";
    }

    protected boolean isTotalable(int column) {
        return false;
    }

    /**
     * 获取列的总计值的方法，如果有需要可以重写此方法，
     * 默认只计算NUMBER类型的表格数据。
     * @param columnIndex 列索引
     * @param toCountTable 要计算的表格
     * @return 返回总计后的值
     */
    protected Object getCountValueOfColumn(int columnIndex, JTable toCountTable) {
        Double value = null;
        for (int index = 0; index < toCountTable.getRowCount(); index++) {
            Object objectValue = toCountTable.getValueAt(index, columnIndex);
            if (objectValue instanceof Number) {
                Double doubleValue = ((Number) objectValue).doubleValue();
                value = (value == null ? doubleValue : value + doubleValue);
            }
        }
        return value;
    }

    /**
     * 总计行的表格模型
     */
    private class TotalTableModel extends AbstractTableModel implements TableModelListener {

        private Vector totalVector = new Vector();

        public TotalTableModel() {
            initModel();
        }

        public void initModel() {
            for (int index = 0, count = getColumnCount(); index < count; index++) {
                totalVector.add(null);
            }
        }

        /**
         * 获取行的总数
         * @return 返回行总数
         */
        @Override
        public int getRowCount() {
            return 1;
        }

        /**
         * 列总数
         * @return 返回列总数
         */
        @Override
        public int getColumnCount() {
            return mainTableModel.getColumnCount();
        }

        /**
         * 获取单元格的值
         * @param rowIndex 行索引
         * @param columnIndex 列索引
         * @return 返回单元格的值
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                if (mainTableScroll.getRowHeader() == null || label.getPreferredSize().width < 2) {
                    return label.getText();
                }
            }
            Object value = totalVector.get(columnIndex);
//            return value == null ? null : NumberTool.getFmtDbl(Double.valueOf(value.toString()));
            return value == null ? null : Double.valueOf(value.toString());
        }

        /**
         * 监听主表的值改变
         * @param e
         */
        @Override
        public void tableChanged(TableModelEvent e) {
            final int column = e.getColumn();
            if (!valueChanged) {
                valueChanged = true;
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        if (column == -1 && countColumnIndex != null && countColumnIndex.length > 0) {
                            for (int countColumn : countColumnIndex) {
                                Object value = getColumnValueTotal(countColumn);
                                if (!"Invalid".equals(value)) {
                                    totalVector.set(countColumn, value);
                                }
                            }
                            fireTableRowsUpdated(0, 0);
                        } else {
                            Object value = getColumnValueTotal(column);
                            if (!"Invalid".equals(value)) {
                                totalVector.set(column, value);
                                fireTableCellUpdated(0, column);
                            }
                        }
                        totalRowTableScroll.setRowHeaderView(label);
                        doLayout();
                        valueChanged = false;
                    }
                });
            }
        }

        /**
         * 返回本类类型，以设置所有单元格的渲染器都一样
         * @param columnIndex 列索引
         * @return 类型
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return TotalTable.class;
        }
    }

    public static class MainTotalTable extends XTable {

        @Override
        protected int rowHeaderChanged() {
            int totalWidth = super.rowHeaderChanged();
            if (totalWidth < 30 && totalWidth > 1) {
                totalWidth = 30;
                JList rowHeader = (JList) createIndexRowHeader();
                rowHeader.setFixedCellWidth(totalWidth);
            }
            return totalWidth;
        }
    }

    public void setLoadingStyle(boolean loading) {
        ((XTable) mainTable).setloadingDataStyle(loading);
        totalRowTableScroll.setViewportView(loading ? null : totalRowTable);
        totalRowTableScroll.setRowHeaderView(loading ? null : label);
    }
}

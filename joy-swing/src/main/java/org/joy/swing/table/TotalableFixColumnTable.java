package org.joy.swing.table;

import org.joy.commons.lang.string.I18nTool;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;

/**
 * �ܼƲ��ɹ̶��еı��
 * @author  zy
 */
public class TotalableFixColumnTable extends javax.swing.JPanel implements TableModelListener, RowSorterListener {

    private int[] countColumnIndex;
    private TableModel mainTableModel = null;
    private int fixCount;
    /**
     * Ĭ�ϸ�
     */
    public static final int DEFAULT_HIGHT = 35;
    /**
     * Ĭ�ϵ��ܼ��и�
     */
    private static final int DEFAULT_TOTAL_ROW_HIGHT = 21;
    private JLabel totalLabel;
    private FixedTable fixedTable;
    private JComponent indexRowHeader;
    private JPanel headerPanel;
    private JPanel fixedTablePanel;

    /**
     * ��Ӧ���пյĹ��캯��
     * �˷��������ڷ���ֱ����������
     */
    public TotalableFixColumnTable() {
    }

    /**
     * �̶��е��ܼƱ��
     * @param tableModel ���ģ��
     * @param fixCount �̶����ܼ�
     * @param totalCountIndexs ��Ҫ�ܼƵ���
     */
    public TotalableFixColumnTable(TableModel tableModel, int fixCount, int[] totalCountIndexs) {
        init(tableModel, fixCount, totalCountIndexs);
    }

    public void init(TableModel tableModel, int fixCount, int[] totalCountIndexs) {
        mainTableModel = tableModel;
        this.fixCount = fixCount;
        initComponents();
        initTables();
        countColumnIndex = totalCountIndexs;
    }

    public void setLoadingStyle(boolean loading) {
        Component rowComponent = null;
        Component header = null;
        Component total = null;
        if (!loading) {
            rowComponent = fixedTablePanel;
            header = headerPanel;
            total = totalTable;
        }
        mainScrollPane.setRowHeaderView(rowComponent);
        mainScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, header);
        ((XTable) mainTable).setloadingDataStyle(loading);
        totalScrollPane.setViewportView(total);
    }

    private void initTables() {
        totalTable.setColumnModel(mainTable.getColumnModel());
        mainTable.setTableHeader(totalTable.getTableHeader());
        mainScrollPane.setColumnHeaderView(mainTable.getTableHeader());
        fixedTable = new FixedTable(true);

        //ͬ��������
        fixedTable.setRowSorter(mainTable.getRowSorter());

        totalLabel = new JLabel(I18nTool.getLocalStr("Total"));
        totalLabel.setHorizontalAlignment(JLabel.RIGHT);

        final JScrollBar mainScroll = mainScrollPane.getHorizontalScrollBar();
        final JScrollBar totalScroll = totalScrollPane.getHorizontalScrollBar();

        AdjustmentListener adjustListener = new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar adjustScroll = e.getSource() == mainScroll ? totalScroll : mainScroll;
                if (adjustScroll.getValue() != e.getValue()) {
                    mainScroll.setValue(e.getValue());
                    totalScroll.setValue(e.getValue());
                }
            }
        };
        totalScroll.addAdjustmentListener(adjustListener);
        mainScroll.addAdjustmentListener(adjustListener);
        fixedTablePanel = new JPanel(new BorderLayout());
        fixedTablePanel.add(fixedTable);
        fixedTablePanel.add(createIndexRowHeader(), BorderLayout.WEST);
        mainScrollPane.setRowHeaderView(fixedTablePanel);

        totalScrollPane.setRowHeaderView(totalLabel);

        headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(fixedTable.getTableHeader(), BorderLayout.CENTER);

        fixedTable.getTableHeader().addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                ColourfulTableHeadCellRenderer mainTableHeaderRender = (ColourfulTableHeadCellRenderer) mainTable.getTableHeader().getDefaultRenderer();
                int maxHight = ((ColourfulTableHeadCellRenderer) fixedTable.getTableHeader().getDefaultRenderer()).getMaxHight(0);
                mainTableHeaderRender.setRelationHight(maxHight);
            }
        });
        mainScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, headerPanel);
        totalScrollPane.setPreferredSize(new Dimension(mainScrollPane.getPreferredSize().width, DEFAULT_HIGHT));
        totalLabel.setPreferredSize(new Dimension(headerPanel.getPreferredSize().width, DEFAULT_TOTAL_ROW_HIGHT));

        if (mainTableModel.getColumnCount() > 8) {
            mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            totalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
        mainTable.getRowSorter().addRowSorterListener(this);
        mainTableModel.addTableModelListener(this);
        totalTable.setDefaultRenderer(TotalTable.class, new FloatRenderer(FloatRenderer.FLOAT_NUMBER, 6));
    }

    public JTable getMainTable() {
        return mainTable;
    }

    public TableModel getTableModel() {
        return mainTableModel;
    }

    private JTable createMainTable() {
        return new FixedTable(false);
    }

    private JTable createTotalTable() {
        return new FixedTable(false);
    }

    protected JComponent createIndexRowHeader() {
        if (indexRowHeader != null) {
            return indexRowHeader;
        }
        ListModel lm = new AbstractListModel() {

            @Override
            public int getSize() {
                return getMainTable().getRowCount();
            }

            @Override
            public Object getElementAt(int index) {
                return index + 1;
            }
        };
        indexRowHeader = new JList(lm);
        ((JList) indexRowHeader).setFixedCellHeight(getMainTable().getRowHeight());
        JTableHeader header = getMainTable().getTableHeader();
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
     * ����е��ܼ�ֵ
     * @param columnIndex ������
     * @return ����ֵ
     */
    private Object getColumnValueTotal(int columnIndex) {
        if (this.countColumnIndex != null && countColumnIndex.length > 0 && Arrays.binarySearch(this.countColumnIndex, columnIndex) >= 0) {
            return getCountValueOfColumn(mainTable.convertColumnIndexToView(columnIndex), mainTable);
        } else if (isTotalable(columnIndex)) {
            return getCountValueOfColumn(mainTable.convertColumnIndexToView(columnIndex), mainTable);
        }
        return "Invalid";
    }

    /**
     * ��ȡ�е��ܼ�ֵ�ķ������������Ҫ������д�˷�����
     * Ĭ��ֻ����NUMBER���͵ı����ݡ�
     * @param viewColumnIndex ��ͼ������
     * @param toCountTable Ҫ����ı��
     * @return �����ܼƺ��ֵ
     */
    protected Object getCountValueOfColumn(int viewColumnIndex, JTable toCountTable) {
        Double value = null;
        for (int index = 0; index < toCountTable.getRowCount(); index++) {
            Object number = toCountTable.getValueAt(index, viewColumnIndex);
            if (number instanceof Number) {
                Double tableValue = ((Number) number).doubleValue();
                value = (value == null ? tableValue : value + tableValue);
            }
        }
        return value;
    }

    protected boolean isTotalable(int column) {
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainScrollPane = new javax.swing.JScrollPane();
        mainTable = createMainTable();
        totalScrollPane =         new JScrollPane() {

            @Override
            public void setColumnHeaderView(Component view) {
            }
        };
        totalTable = createTotalTable();

        setLayout(new java.awt.BorderLayout());

        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainTable.setAutoCreateRowSorter(true);
        mainScrollPane.setViewportView(mainTable);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);

        totalScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        totalScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        totalTable.setAutoCreateColumnsFromModel(false);
        totalTable.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        totalTable.setModel(new TotalTableModel());
        totalTable.setEnabled(false);
        totalScrollPane.setViewportView(totalTable);

        add(totalScrollPane, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JTable mainTable;
    private javax.swing.JScrollPane totalScrollPane;
    private javax.swing.JTable totalTable;
    // End of variables declaration//GEN-END:variables

    private class FixedTable extends XTable implements ComponentListener {

        /**
         * �̶���������
         */
        private int fixedColumnCount;
        /**
         * �Ƿ�̶�
         */
        private boolean fix;
        /**
         * �ɼ��������
         */
        private int visibleColumnCount;

        public FixedTable() {
            super();
        }

        /**
         * @param fixOrMain
         */
        public FixedTable(boolean fixOrMain) {

            super(mainTableModel,false);
            this.fix = fixOrMain;
            this.fixedColumnCount = fixCount;
            visibleColumnCount = this.getColumnCount() - fixedColumnCount;
            if (fix) {
                this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                setFixProperty();
            }
            addComponentListener(this);
            hideColumn();
        }

        private void setFixProperty() {
            this.setEnabled(false);
            Color bg = UIManager.getColor("Panel.background");
            this.setBackground(bg);
        }

        /**
         * ���ر�ͷ
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

        @Override
        public boolean isCellEditable(int row, int column) {
            if (fix) {
                return false;
            }
            return super.isCellEditable(row, column);
        }

        /**
         * ���仯��ʱ�����ر�ͷ
         * @param e
         */
        @Override
        public void columnAdded(TableColumnModelEvent e) {
            super.columnAdded(e);
            if (this.getColumnCount() > (this.fix ? fixedColumnCount : visibleColumnCount)) {
                hideColumn();
            }
        }

        @Override
        public void componentResized(ComponentEvent e) {
            if (fix) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        totalLabel.setPreferredSize(new Dimension(mainScrollPane.getCorner(JScrollPane.UPPER_LEFT_CORNER).getWidth(), totalLabel.getHeight()));
                        totalScrollPane.setRowHeaderView(totalLabel);
                        TotalableFixColumnTable.this.repaint();
                    }
                });
            }
        }

        @Override
        public void componentMoved(ComponentEvent e) {
        }

        @Override
        public void componentShown(ComponentEvent e) {
        }

        @Override
        public void componentHidden(ComponentEvent e) {
        }
    }

    /**
     * �ܼ��еı��ģ��
     */
    private class TotalTableModel extends AbstractTableModel implements TableModelListener {

        private Vector totalVector = new Vector();

        public TotalTableModel() {
            initModel();
            mainTableModel.addTableModelListener(this);
        }

        public void initModel() {
            for (int index = 0, count = getColumnCount(); index < count; index++) {
                totalVector.add(null);
            }
        }

        /**
         * ��ȡ�е�����
         * @return ����������
         */
        @Override
        public int getRowCount() {
            return 1;
        }

        /**
         * ������
         * @return ����������
         */
        @Override
        public int getColumnCount() {
            return mainTableModel.getColumnCount();
        }

        /**
         * ��ȡ��Ԫ���ֵ
         * @param rowIndex ������
         * @param columnIndex ������
         * @return ���ص�Ԫ���ֵ
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return totalVector.get(columnIndex);
        }

        /**
         * ���������ֵ�ı�
         * @param e
         */
        @Override
        public void tableChanged(TableModelEvent e) {
            final int column = e.getColumn();
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    if (column == -1) {
                        for (int countColumn = 0; countColumn < getColumnCount(); countColumn++) {
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
                }
            });
            this.fireTableDataChanged();
        }

        /**
         * ���ر������ͣ����������е�Ԫ�����Ⱦ����һ��
         * @param columnIndex ������
         * @return ����
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return TotalTable.class;
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        rowHeaderChanged();
    }

    protected int rowHeaderChanged() {
        JList rowHeader = (JList) indexRowHeader;
        int cellWidth = 0;
        if (getMainTable().getRowCount() > 0) {
            cellWidth = rowHeader.getFontMetrics(getMainTable().getTableHeader().getFont()).stringWidth(String.valueOf(getMainTable().getRowCount()) + 3);
        }
        rowHeader.setFixedCellWidth(cellWidth);
//                             + table.getIntercellSpacing().height);
        Component box = Box.createRigidArea(new Dimension(cellWidth, headerPanel.getHeight()));
        headerPanel.add(box, BorderLayout.WEST);
        headerPanel.doLayout();
        rowHeader.repaint();
        return cellWidth;
    }

    @Override
    public void sorterChanged(RowSorterEvent e) {
        rowHeaderChanged();
    }
}

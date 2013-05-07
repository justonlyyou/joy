package com.kvc.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.RowSorter.SortKey;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 * 可指定字体颜色以及背景色的表格头部绘制器
 *
 * <I>此绘制器兼换行功能，之所以不用SWING书里面介绍的用TextArea作为renderer,因为虽然其可以自动换行<br>
 * 但是自己写的renderer将不具备JDK默认的表格列头的各种功能，比如说排序的时候箭头显示。<br>
 * 本绘制器封装默认实现的绘制器，对其进行装饰，虽然实现比较复杂，但是可以兼容JDK的版本更新</I>
 * @author zy
 */
public class ColourfulTableHeadCellRenderer extends DefaultTableCellRenderer implements TableColumnModelListener {

    /** Field description */
    private TableColumnModel columnModel = null;
    /** Field description */
    private int defaultCellHight = 0;
    /** Field description */
    private JTableHeader header = null;
    /** Field description */
    private Map<Integer, ColumnInfo> initHight = null;
    /** Field description */
    private List<Integer> requiredIndies = new Vector<Integer>();
    /** Field description */
    private Color requiredColor = new Color(220, 0, 0);
    /** Field description */
    private List<? extends SortKey> sorkeys = null;
    /** Field description */
    private Stack<Integer> splitPositionList = new Stack();
    /** Field description */
    private Map<Integer, Color> colorMap;
    /** Field description */
    private TableCellRenderer defaultHeadRenderer;

    /**
     * Constructs ...
     *
     *
     * @param table
     * @param requiredIndies
     */
    public ColourfulTableHeadCellRenderer(JTable table, int... requiredIndies) {
        defaultHeadRenderer = table.getTableHeader().getDefaultRenderer();
        if (defaultHeadRenderer != null && defaultHeadRenderer.getClass().equals(this.getClass())) {
            if ((requiredIndies == null) || (requiredIndies.length == 0)) {
                return;
            }

            ((ColourfulTableHeadCellRenderer) defaultHeadRenderer).setRequiredForIndies(true, requiredIndies);
        } else {
            if (defaultHeadRenderer instanceof ColourfulTableHeadCellRenderer) {
                defaultHeadRenderer = ((ColourfulTableHeadCellRenderer) defaultHeadRenderer).defaultHeadRenderer;
            }
            table.getTableHeader().setDefaultRenderer(this);
            header = table.getTableHeader();
            setRequiredForIndies(true, requiredIndies);
            init();
        }
    }

    /**
     * Constructs ...
     *
     *
     * @param table
     * @param backgroundColorMap
     */
    protected ColourfulTableHeadCellRenderer(JTable table, Map<Integer, Color> backgroundColorMap) {
        defaultHeadRenderer = table.getTableHeader().getDefaultRenderer();
        table.getTableHeader().setDefaultRenderer(this);
        header = table.getTableHeader();
        this.colorMap = backgroundColorMap;
        init();
    }

    /**
     * 组装表头绘制器
     * @param table 表格
     * @param requiredIndies 必填的列
     * @return 返回绘制器
     */
    public static ColourfulTableHeadCellRenderer install(JTable table, int... requiredIndies) {
        return new ColourfulTableHeadCellRenderer(table, requiredIndies);
    }

    /**
     * 组装表头绘制器
     * @param table 表格
     * @param backgroundColorMap 背景色的MAP（列和颜色）
     * @return 返回绘制器
     */
    public static ColourfulTableHeadCellRenderer install(JTable table, Map<Integer, Color> backgroundColorMap) {
        return new ColourfulTableHeadCellRenderer(table, backgroundColorMap);
    }

    /**
     * Method description
     *
     */
    private void init() {
        header.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int column = header.getTable().columnAtPoint(e.getPoint());

                getHightMap().remove(column);

                if ((sorkeys != null) && !sorkeys.isEmpty()) {
                    getHightMap().remove(header.getTable().convertColumnIndexToView(sorkeys.get(0).getColumn()));
                }
            }
        });
    }

    /**
     * Method description
     *
     *
     * @param hight
     */
    protected void setRelationHight(int hight) {
        getHightMap().put(1000, new ColumnInfo(hight, new ArrayList(0)));
        fireColumnChanged(0);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    private Map<Integer, ColumnInfo> getHightMap() {
        if (initHight == null) {
            initHight = new HashMap<Integer, ColumnInfo>(header.getColumnModel().getColumnCount());
        }

        return initHight;
    }

    /**
     * Method description
     *
     *
     * @param viewColumn
     *
     * @return
     */
    private boolean isHightInit(final int viewColumn) {
        boolean isInit = getHightMap().get(viewColumn) != null;

        if (!isInit) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    //防止有的时候会出现 高计算错误 但是加上会导致第一列。默认宽有小问题。
//                    if (viewColumn == 0) {
//                        header.setResizingColumn(header.getColumnModel().getColumn(viewColumn));
//                    }

                    header.revalidate();
                }
            });
        }

        return isInit;
    }

    /**
     * Method description
     *
     *
     * @param labelRenderer
     */
    private void initHight(Component labelRenderer) {
        for (int index = 0, size = columnModel.getColumnCount(); index < size; index++) {
            setPreHight(labelRenderer, columnModel.getColumn(index).getHeaderValue().toString(), columnModel.getColumn(index).getWidth(), index);
        }
    }

    /**
     * Method description
     *
     *
     * @param hight
     *
     * @return
     */
    public int getMaxHight(int hight) {
        for (Map.Entry<Integer, ColumnInfo> entry : getHightMap().entrySet()) {
            if (entry.getKey() >= 0) {
                ColumnInfo eachColumn = entry.getValue();

                if ((eachColumn != null) && (eachColumn.getKey() != null) && (eachColumn.getKey() > hight)) {
                    hight = eachColumn.getKey().intValue();
                }
            }
        }

        return hight;
    }

    /**
     * 取得当前表格的最大高度(不包括和其他表格所关联的最大高度)
     * @return
     */
    public int getMaxHightWithoutRelation() {
        int hight = 0;
        for (Map.Entry<Integer, ColumnInfo> entry : getHightMap().entrySet()) {
            if (entry.getKey() >= 0 && entry.getKey().intValue() != 1000) {
                ColumnInfo eachColumn = entry.getValue();
                if ((eachColumn != null) && (eachColumn.getKey() != null) && (eachColumn.getKey() > hight)) {
                    hight = eachColumn.getKey().intValue();
                }
            }
        }
        return hight;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getDefaultColumnHight() {
        return defaultCellHight;
    }

    /**
     * Method description
     *
     *
     * @param column
     *
     * @return
     */
    public int getColumnHight(int column) {
        ColumnInfo columnInfo = getHightMap().get(column);

        if (columnInfo == null) {
            return getDefaultColumnHight();
        }

        return columnInfo.getKey();
    }

    /**
     * Method description
     *
     *
     * @param headValue
     * @param columnWidth
     * @param columnIndex
     *
     * @return
     */
    public Component getDefaultTableCellRendererComponent(String headValue, int columnWidth, int columnIndex) {
        JComponent component = (JComponent) getTableCellRendererComponent(header.getTable(), headValue, false, false, -1, columnIndex);

        if ((columnIndex < 0) && (columnWidth > 0)) {
            int hight = setPreHight(component, headValue, columnWidth, columnIndex);
            Dimension size = component.getSize();

            if (hight > 0) {
                newLineRendererText(component, headValue, columnIndex);
            } else {
                hight = getDefaultColumnHight();
            }

            component.setSize(size.width, hight);
        }

        return component;
    }

    /**
     * Method description
     *
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     *
     * @return
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (defaultHeadRenderer == null) {
            defaultHeadRenderer = table.getTableHeader().getDefaultRenderer();
        }

        Component component = defaultHeadRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getRowSorter() instanceof TableRowSorter) {
            TableRowSorter rowSort = (TableRowSorter) table.getRowSorter();

            if (sorkeys != rowSort.getSortKeys()) {
                sorkeys = rowSort.getSortKeys();
            }
        } else {
            sorkeys = null;
        }

        if (columnModel != table.getColumnModel()) {
            if (columnModel != null) {
                columnModel.removeColumnModelListener(this);
            }

            columnModel = table.getColumnModel();
            columnModel.addColumnModelListener(this);
            defaultCellHight = component.getPreferredSize().height;
            initHight(component);
        }

        Color color = getCurrentForeground(table.convertColumnIndexToModel(column));
        Color backColor = getCurrentBackground(table.convertColumnIndexToModel(column));

        if (color != null) {
            component.setForeground(color);
        }

        if (backColor != null) {
            component.setBackground(color);
        }

        if (column >= 0) {
            if (((header.getResizingColumn() != null) || (header.getDraggedColumn() != null) || !isHightInit(column))) {
                int height = setPreHight(component, null, columnModel.getColumn(column).getWidth(), column);
                if (height < 0) {
                    if (!isHightInit(column)) {
                        setSplitCount(height, column, Collections.EMPTY_LIST);
                    }
                    Dimension size = component.getPreferredSize();
                    height = getMaxHight(height);
                    if (height > 0 && height != size.height) {
                        size.height = height;
                        component.setPreferredSize(size);
                    }
                }
            }
            newLineRendererText(component, null, column);
        }
        return component;
    }

    /**
     * Method description
     *
     *
     * @param renderer
     *
     * @return
     */
    private String getComponentText(Component renderer) {
        if (renderer instanceof javax.swing.JLabel) {
            return ((javax.swing.JLabel) renderer).getText();
        } else {
            try {
                Method getTextMethod = renderer.getClass().getMethod("getText");

                if (getTextMethod != null) {
                    Object textValue = getTextMethod.invoke(renderer);

                    if (textValue instanceof String) {
                        return (String) textValue;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return "";
    }

    /**
     * Method description
     *
     *
     * @param rendererComponent
     * @param headValue
     * @param column
     */
    protected void newLineRendererText(Component rendererComponent, String headValue, int column) {
        String text = "";
        String labelText = headValue;

        if (labelText == null) {
            labelText = getComponentText(rendererComponent);
        }

        List<Integer> columnSplitCount = null;
        ColumnInfo columnInfo = getHightMap().get(column);

        if (columnInfo != null) {
            columnSplitCount = columnInfo.getValue();
        }

        if ((columnSplitCount != null) && !columnSplitCount.isEmpty()) {
            int index = 0;
            String split = "";
            String[] splits = labelText.split(split);

            for (String aText : splits) {
                if (columnSplitCount.contains(index)) {
                    text = text + aText + "<br>";
                } else {
                    text = text + aText + split;
                }

                index++;
            }
        } else {
            text = labelText;
        }

        text = "<html>" + text + "</html>";
        if (rendererComponent instanceof javax.swing.JLabel) {
            ((javax.swing.JLabel) rendererComponent).setText(text);
        } else {
            try {
                Method setTextMethod = rendererComponent.getClass().getMethod("setText", String.class);

                if (setTextMethod != null) {
                    setTextMethod.invoke(rendererComponent, text);
                }
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param renderer
     * @param text
     * @param columnWidth
     * @param viewColumn
     *
     * @return
     */
    private int setPreHight(Component renderer, String text, int columnWidth, int viewColumn) {
        if (text == null) {
            if (renderer instanceof javax.swing.JLabel) {
                text = ((javax.swing.JLabel) renderer).getText();
            } else {
                try {
                    Method getTextMethod = renderer.getClass().getMethod("getText");

                    if (getTextMethod != null) {
                        Object textValue = getTextMethod.invoke(renderer);

                        if (textValue instanceof String) {
                            text = (String) textValue;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if (text == null) {
                return -1;
            }

            if (text.isEmpty()) {
                text = " ";
            }
        }

        if (Locale.getDefault() == Locale.US) {
            if (!text.contains(" ")) {
                return -1;
            }
        }

        Font font = renderer.getFont();
        FontMetrics fontMetrics = renderer.getFontMetrics(font);
        int stringheight = fontMetrics.getHeight();
        int stringWidth = fontMetrics.stringWidth(text) + 6;

        if (sorkeys != null) {
            if (!sorkeys.isEmpty() && (header.getTable().convertColumnIndexToView(sorkeys.get(0).getColumn()) == viewColumn)) {
                if (columnWidth - 20 > fontMetrics.stringWidth(text.substring(0, 1))) {
                    columnWidth = columnWidth - 20;
                }
            }
        }

        List<Integer> result = calculateColumnHightMultiple(stringWidth, columnWidth, text, fontMetrics);
        int multiple = result.get(0);
        stringheight = stringheight * multiple + 2;
        result.remove(0);
        setSplitCount(stringheight, viewColumn, result);

        Dimension size = renderer.getPreferredSize();
        size.height = getMaxHight(stringheight);
        renderer.setPreferredSize(size);

        return stringheight;
    }

    /**
     * Method description
     *
     *
     * @param stringheight
     * @param viewColumn
     * @param splitCount
     */
    private void setSplitCount(int stringheight, int viewColumn, List<Integer> splitCount) {
        ColumnInfo columnInfo = getHightMap().get(viewColumn);

        if (columnInfo == null) {
            columnInfo = new ColumnInfo(stringheight, splitCount);
        } else {
            columnInfo.setHight(stringheight);
            columnInfo.setValue(splitCount);
        }
        getHightMap().put(viewColumn, columnInfo);
    }

    /**
     * Method description
     *
     *
     * @param stringWidth
     * @param columnWidth
     * @param text
     * @param fontMetrics
     *
     * @return
     */
    private List<Integer> calculateColumnHightMultiple(int stringWidth, int columnWidth, String text, FontMetrics fontMetrics) {
        int multiple = 1;
        boolean localeChina = Locale.getDefault() == Locale.CHINA;
        List<Integer> splitPosition = new ArrayList<Integer>();
        String split = localeChina ? "" : " ";
        String[] splits = text.split(split);

        if (splits.length == 0) {
            split = "";
            splits = text.split("");
        }

        splits = getAvtiveSplits(splits);
        stringWidth = fontMetrics.stringWidth(text);

        int moreWidth = stringWidth - columnWidth;
        List<String> splitsList = new ArrayList<String>();

        for (int splitIndex = splits.length - 1; splitIndex > -1; splitIndex--) {
            splitsList.add(splits[splitIndex]);
        }

        for (; moreWidth > 0;) {

            // 只要拆分结果大于1个串则必定要加高
            if (splitsList.size() > 1) {
                multiple++;
            }

            int splitWidth = 0;
            String lastString = "";

            // 循环计算字符串长度，以便精确计算需要换行的字符串是哪几个。
            for (int splitIndex = 0, splitCount = splitsList.size(); splitIndex < splitCount; splitIndex++) {
                String spliString = splitsList.get(splitIndex);

                lastString = lastString + spliString + split;
                splitWidth = fontMetrics.stringWidth(lastString);

                if (splitIndex == splitCount - 1) {
                    moreWidth = -1;

                    break;
                } // 多的宽度如果小于字符宽度，或者已经拆分到不能再拆分，则执行换行
                else if ((moreWidth < splitWidth) || (splitIndex == splitCount - 2)) {
                    moreWidth = splitWidth - columnWidth;
                    splits = getAvtiveSplits(lastString.split(split));

                    int fromIndex = 0;

                    splitPositionList.clear();

                    // 查找当前需要换行的字符串，纪录其位置，以便在其位置插入换行符。
                    for (int splitPosi = 0; splitPosi >= 0;) {
                        splitPosi = text.indexOf(split + spliString, fromIndex);

                        if (splitPosi >= 0) {
                            fromIndex = splitPosi + 1;
                            splitPositionList.push(splitPosi);
                        }
                    }

                    for (; !splitPositionList.empty();) {
                        Integer splitPosit = splitPositionList.pop();

                        if (!splitPosition.contains(splitPosit)) {
                            splitPosition.add(splitPosit);

                            break;
                        }
                    }

                    splitsList.clear();

                    for (String splitS : splits) {
                        splitsList.add(splitS);
                    }

                    if (splits.length == 1) {
                        moreWidth = -1;
                    }

                    break;
                }
            }
        }

        splitPosition.add(0, multiple);

        return splitPosition;
    }

    /**
     * Method description
     *
     *
     * @param splits
     *
     * @return
     */
    private String[] getAvtiveSplits(String[] splits) {
        if ((splits.length > 0) && splits[0].isEmpty()) {
            splits = Arrays.copyOfRange(splits, 1, splits.length);
        }

        return splits;
    }

    /**
     * Method description
     *
     *
     * @param modelColumn
     *
     * @return
     */
    private Color getCurrentForeground(int modelColumn) {
        if (getRequiredIndies().contains(Integer.valueOf(modelColumn))) {
            return requiredColor;
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param modelColumn
     *
     * @return
     */
    private Color getCurrentBackground(int modelColumn) {
        if ((colorMap != null) && (colorMap.get(modelColumn) != null)) {
            return colorMap.get(modelColumn);
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param isRequired
     * @param requiredIndex
     */
    public void setRequiredForIndies(boolean isRequired, int... requiredIndex) {
        if (isRequired) {
            for (int index : requiredIndex) {
                if (!requiredIndies.contains(Integer.valueOf(index))) {
                    getRequiredIndies().add(index);
                }
            }
        } else {
            for (int index : requiredIndex) {
                getRequiredIndies().remove(Integer.valueOf(index));
            }
        }

        header.repaint();
    }
    private boolean resizing = false;

    /**
     * 如果列头改变,调用此方法
     * @param column
     */
    public void fireColumnChanged(int column) {
        if (initHight != null) {
            initHight.remove(column);
        }
    }

    /**
     * Method description
     *
     */
    private void fireColumnChaned() {
        if (!resizing) {
            resizing = true;
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    getHightMap().clear();
                    resizing = false;
                }
            });
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void columnAdded(TableColumnModelEvent e) {
        fireColumnChaned();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void columnRemoved(TableColumnModelEvent e) {
        fireColumnChaned();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void columnMoved(TableColumnModelEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void columnMarginChanged(ChangeEvent e) {
        if (header.getResizingColumn() == null) {
            fireColumnChaned();
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void columnSelectionChanged(ListSelectionEvent e) {
    }

    /**
     * @return the requiredIndies
     */
    public List<Integer> getRequiredIndies() {
        return requiredIndies;
    }

    /**
     * @param requiredIndies the requiredIndies to set
     */
    public void setRequiredIndies(List<Integer> requiredIndies) {
        this.requiredIndies = requiredIndies;
    }

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private class ColumnInfo implements Map.Entry<Integer, List<Integer>> {

        /** Field description */
        private Integer hight;
        /** Field description */
        private List<Integer> splitPosition;

        /**
         * Constructs ...
         *
         *
         * @param hight
         * @param splitPosition
         */
        public ColumnInfo(Integer hight, List<Integer> splitPosition) {
            this.hight = hight;
            this.splitPosition = splitPosition;
        }

        /**
         * Method description
         *
         *
         * @param hight
         */
        public void setHight(Integer hight) {
            this.hight = hight;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        @Override
        public Integer getKey() {
            return hight;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        @Override
        public List<Integer> getValue() {
            return splitPosition;
        }

        /**
         * Method description
         *
         *
         * @param value
         *
         * @return
         */
        @Override
        public List<Integer> setValue(List<Integer> value) {
            List<Integer> oldPositions = splitPosition;

            splitPosition = value;

            return oldPositions;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.SwingConstants;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeModel;
import org.joy.swing.table.XTableHeader;
import org.joy.swing.table.group.SimpleColumnGroup;
import org.joy.swing.table.group.SimpleColumnGroup.GroupInfo;
import org.joy.swing.table.group.TopGroupTableHeadUI;

/**
 * 可安装过虑器并支持组合列的表头。
 * 负责保存组信息,隐藏列的popup的组装。
 * @author ckcs
 */
public class TopTableHeader extends XTableHeader {

    //是否建立隐藏列菜单
    private boolean createHideColumn;
    //列隐藏的弹出菜单
    private HideColumnPopup hideColumnPopup;
    private boolean flushHideColumn = true;
    //是否列的更改是由hideColumnPopup引起的
    private boolean isPopupTrigger;
    //包含顶层组消息
    private Vector<SimpleColumnGroup> columnGroups = null;
    //是否显示过滤器
    private boolean showFilters;
    //用户延时生成过滤器
    private boolean filterFlush = false;
    //一个组合的默认渲染器
    private TableCellRenderer groupRender = new JTableHeader().getDefaultRenderer();

    public TopTableHeader() {
        this(null);
    }

    public TopTableHeader(TopTableColumnModel model) {
        super(model);
        initListeners();
        columnGroups = new Vector<SimpleColumnGroup>();
        hideColumnPopup = new HideColumnPopup("列隐藏", this);
    }

    public boolean isShowFilters() {
        return showFilters;
    }

    public void setShowFilters(boolean showFilters) {
        this.showFilters = showFilters;
        //用于刷新过滤器，实现重设置
        if (!filterFlush) {
            ((TopTableColumnModel) columnModel).flushColumnFilter();
            filterFlush = true;
        }
        int count = columnModel.getColumnCount();
        for (int index = 0; index < count; index++) {
            ((TopTableColumn) columnModel.getColumn(index)).getFilterComponent().setVisible(showFilters);
        }
    }

    /**
     * 添加列组合
     * @param groups
     */
    public void addColumnGroup(SimpleColumnGroup... groups) {
        for (SimpleColumnGroup columnGroup : groups) {
            columnGroup.setHeaderRenderer(groupRender);
            columnGroups.add(columnGroup);
        }
    }

    /**
     * 当重键列组合表头的时候，必须调用
     * 这个方法，清空无用的组信息
     */
    public void clearHeaderGroup() {
        for (SimpleColumnGroup columnGroup : columnGroups) {
            columnGroup.clearCG();
        }
        columnGroups.clear();
    }

    /**
     * 返回某列所在的列组
     * @param col 列
     * @return
     */
    public Vector<SimpleColumnGroup> getColumnGroups(TableColumn col) {
        if (columnGroups == null) {
            return null;
        }
        Enumeration enums = columnGroups.elements();
        while (enums.hasMoreElements()) {
            SimpleColumnGroup columnGroup = (SimpleColumnGroup) enums.nextElement();
            Vector groupList = (Vector) columnGroup.getColumnGroups(col, new Vector());
            if (groupList != null) {
                return groupList;
            }
        }
        return null;
    }

    //初始化监听器
    private void initListeners() {
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    createHideAndShowPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    createHideAndShowPopup(e);
                }
            }
        });

    }

    @Override
    public void setColumnModel(TableColumnModel columnModel) {
        //清理旧模型
        //CKCS reflact 兼容 getColumnModel() != columnModel 是为了兼容
        //'TotalTable'将统计表的表头设置成和主表的表头相同。
        if (getColumnModel() != null && getColumnModel() != columnModel) {
            clearTableColumns(getColumnModel());
        }
        super.setColumnModel(columnModel);
    }

    /**
     * 清除表格的列模型,将旧的过滤器清除
     * @param oldModel 表格列模型
     */
    public void clearTableColumns(TableColumnModel oldModel) {
        TableColumn column = null;
        while (oldModel.getColumnCount() > 0) {
            column = oldModel.getColumn(0);
            oldModel.removeColumn(column);
        }
        hideColumnPopup.clearColumnNode();
    }

    //创建和显示列隐藏菜单
    private void createHideAndShowPopup(MouseEvent e) {
        //刷新隐藏列
        if (flushHideColumn) {
            hideColumnPopup.combinHideColumnPopup();
            Enumeration<TableColumn> columns = getColumnModel().getColumns();
            TableColumn column = null;
            //列及顶层组列表
            List<Object> columnObjectList = new ArrayList<Object>();
            boolean contain = false;
            while (columns.hasMoreElements()) {
                contain = false;
                column = columns.nextElement();
                for (SimpleColumnGroup group : columnGroups) {
                    if (group.containColumn(column)) {
                        if (!columnObjectList.contains(group)) {
                            columnObjectList.add(group);
                        }
                        contain = true;
                    }
                }
                if (!contain) {
                    columnObjectList.add(column);
                }
            }
            DefaultTreeModel treeModel = combineHideMenu(columnObjectList);
            hideColumnPopup.setHideColumnTreeModel(treeModel);
            flushHideColumn = false;
        }
        if (e != null) {
            hideColumnPopup.show(this, e.getX(), e.getY());
        }
    }

    //绑定扩展菜单
    private DefaultTreeModel combineHideMenu(List<Object> columnObjectList) {
        HideableColumnNode root = new HideableColumnNode("root", this);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        if (columnObjectList.size() > 0) {
            try {
                doCombineExtendsMenu(treeModel, root, columnObjectList);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return treeModel;
    }

    //处理dom节点的绑定
    private void doCombineExtendsMenu(DefaultTreeModel treeModel, HideableColumnNode parentNode, List columnList) {
        HideableColumnNode treeNode = null;
        for (Object object : columnList) {
            if (object instanceof TableColumn) {
                treeNode = new HideableColumnNode(object.toString(), this);
                //设置节点对应的列
                treeNode.setColumn((TableColumn) object);
                treeModel.insertNodeInto(treeNode, parentNode, treeModel.getChildCount(parentNode));

            } else if (object instanceof GroupInfo) {
                int count = columnModel.getColumnCount();
                TableColumn column = null;
                for (int index = 0; index < count; index++) {
                    column = columnModel.getColumn(index);
                    if (column.getModelIndex() == ((GroupInfo) object).getModelIndex()) {
                        treeNode = new HideableColumnNode(column.getHeaderValue(), this);
                        //设置节点对应的列
                        treeNode.setColumn(column);
                        treeModel.insertNodeInto(treeNode, parentNode, treeModel.getChildCount(parentNode));
                    }
                }

            } else {
                SimpleColumnGroup group = (SimpleColumnGroup) object;
                treeNode = new HideableColumnNode(group.getHeaderValue(), this);
                treeModel.insertNodeInto(treeNode, parentNode, treeModel.getChildCount(parentNode));
                doCombineExtendsMenu(treeModel, treeNode, group.getIncludeCGList());

            }
        }
    }

    /**
     * 设置过滤设置者,这将更新所有的过滤组件的filterSetter。
     * @param filterSetter
     */
    public void setFilterSetter(FilterSetter filterSetter) {
        if (filterSetter == null) {
            throw new IllegalArgumentException("filterSetter can't be null.");
        }
        Enumeration<TableColumn> filters = getColumnModel().getColumns();
        while (filters.hasMoreElements()) {
            TableColumn column = filters.nextElement();
            ((TopTableColumn) column).getFilterComponent().setFilterSetter(filterSetter);
        }
    }

    @Override
    public void setReorderingAllowed(boolean reorderingAllowed) {
        if (reorderingAllowed && getUI() instanceof TopGroupTableHeadUI) {
            return;
        }
        super.setReorderingAllowed(reorderingAllowed);
    }

    @Override
    public void setUI(TableHeaderUI newUI) {
        //不运行更改原来的组合表头ui
        if (getUI() instanceof TopGroupTableHeadUI) {
            return;
        }

        if (newUI instanceof TopTableHeaderUI) {
            super.setUI(newUI);
            setReorderingAllowed(newUI instanceof TopGroupTableHeadUI == false);
        } else {
            super.setUI(new TopTableHeaderUI());
            setReorderingAllowed(true);
        }
    }

    @Override
    public TableCellRenderer getDefaultRenderer() {
        TableCellRenderer tableCellRenderer = new JTableHeader().getDefaultRenderer();
        ((DefaultTableCellRenderer)tableCellRenderer).setHorizontalAlignment(SwingConstants.CENTER);
        return tableCellRenderer;
    }

    public boolean isCreateHideColumn() {
        return createHideColumn;
    }

    public void setCreateHideColumn(boolean createHideColumn) {
        this.createHideColumn = createHideColumn;
    }

    @Override
    public void columnAdded(TableColumnModelEvent e) {
        checkNeedRebuidColumnPopup();
        super.columnAdded(e);
    }

    @Override
    public void columnRemoved(TableColumnModelEvent e) {
        checkNeedRebuidColumnPopup();
        super.columnRemoved(e);
    }

    //检查是否要重建
    private void checkNeedRebuidColumnPopup() {
        //需要重建
        if (!isPopupTrigger) {
            //需要重建
            flushHideColumn = true;
            hideColumnPopup.recoverAllColumns();
        } else {
            isPopupTrigger = false;
        }
    }

    /**
     * 移除某一列并返回列的原始索引标记
     * @param column 列
     * @return
     */
    int doHideTableColumn(TableColumn column) {
        int hidedIndex = -1;
        isPopupTrigger = true;
        if (columnModel.getColumnCount() == 1) {
            return hidedIndex;
        }
        //如果是组合表头记录modelindex，because组合表头不支持重排列
        if (getUI() instanceof TopGroupTableHeadUI) {
            hidedIndex = column.getModelIndex();

        } else {
            hidedIndex = columnModel.getColumnIndex(column.getIdentifier());
            for (int index = 0; index < columnModel.getColumnCount(); index++) {
                if (columnModel.getColumn(index) == column) {
                    hidedIndex = index;
                    break;
                }
            }
        }
        columnModel.removeColumn(column);
        return hidedIndex;
    }

    /**
     * 获得具有指定选择类型的列的模型索引的集合
     * @param selectType 选择类型
     * @return
     */
    public int[] getModelIndexsByVisibleType(int selectType) {
        return hideColumnPopup.getModelIndexsByVisibleType(selectType);
    }

    /**
     * 根据列对应的模型index跟改列的可见性
     * @param selectType 选择类型
     * @param modelIndex 模型索引
     */
    public void changeVisibleByModelIndexs(int selectType, int... modelIndex) {
        //确保列隐藏模型已经建立
        if (flushHideColumn) {
            createHideAndShowPopup(null);
        }
        hideColumnPopup.changeVisibleByModelIndexs(selectType, modelIndex);
    }

    /**
     * 将某列添加到指定的位置
     * @param oldIndex 索引
     * @param column 列
     */
    void doShowTableColumn(int oldIndex, TableColumn column) {
        isPopupTrigger = true;
        columnModel.addColumn(column);
        //如果过滤是激活的，则要将不可见的过滤器设置为可见。
        TopTableColumn topColumn = (TopTableColumn) column;
        if (isShowFilters() && !topColumn.getFilterComponent().isVisible()) {
            topColumn.getFilterComponent().setVisible(true);
        }
        //组合表头传入的是模型index
        if (getUI() instanceof TopGroupTableHeadUI) {
            for (int index = 0; index < columnModel.getColumnCount(); index++) {
                if (columnModel.getColumn(index).getModelIndex() > oldIndex) {
                    columnModel.moveColumn(columnModel.getColumnCount() - 1, index);
                    break;
                }
            }

        } else {
            if (oldIndex >= 0 && oldIndex < columnModel.getColumnCount() - 1) {
                columnModel.moveColumn(columnModel.getColumnCount() - 1, oldIndex);
            }
        }
    }

    public boolean isRecordConfig() {
        return hideColumnPopup.isRecordTableConfig();
    }

    public void setRecordConfig(boolean recordConfig) {
        hideColumnPopup.setRecordTableConfig(recordConfig);
    }
}

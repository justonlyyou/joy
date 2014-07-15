/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.joy.commons.lang.string.I18nTool;
import org.joy.swing.table.group.TopGroupTableHeadUI;

/**
 * 隐藏列弹出菜单
 * 对隐藏节点的统一管理
 * @author ckcs
 */
public class HideColumnPopup extends JPopupMenu {

    private JTree hideColumnTree;
    private JTableHeader tableHeader;
    private JCheckBox recordConfigBox;
    //控制小面板
    private JPanel conifgPanel;
    private JRadioButton expandButton;
    private JRadioButton collapseButton;

    public HideColumnPopup(String label, JTableHeader tableHeader) {
        super(label);
        this.tableHeader = tableHeader;
        setLayout(new BorderLayout());
        hideColumnTree = new JTree();
        hideColumnTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        hideColumnTree.setCellRenderer(new HideColumnTreeCellRenderer());
        hideColumnTree.setShowsRootHandles(true);
        hideColumnTree.setRootVisible(false);

        //控制小面板
        conifgPanel = new JPanel();
        //组时添加折叠展开控制按钮
        conifgPanel.setLayout(new GridLayout(2, 2, 2, 1));
        ButtonGroup group = new ButtonGroup();
        expandButton = new JRadioButton(new AbstractAction(I18nTool.getLocalStr("Expand")) {

            public void actionPerformed(ActionEvent e) {
                expandColumnNodes();
            }
        });
        group.add(expandButton);
        collapseButton = new JRadioButton(new AbstractAction(I18nTool.getLocalStr("Collapse")) {

            public void actionPerformed(ActionEvent e) {
                collapseColumnNodes();
            }
        });
        collapseButton.setSelected(true);
        group.add(collapseButton);
        recordConfigBox = new JCheckBox(I18nTool.getLocalStr("RecordConfig"));
        recordConfigBox.setSelected(true);

        //组合面板
        combinHideColumnPopup();

        initListeners();
    }

    /**
     * 组合面板
     */
    public void combinHideColumnPopup() {
        conifgPanel.removeAll();
        if (tableHeader.getUI() instanceof TopGroupTableHeadUI) {
            conifgPanel.setLayout(new GridLayout(2, 2, 2, 1));
            conifgPanel.add(expandButton);
            conifgPanel.add(collapseButton);

        } else {
            conifgPanel.setLayout(new FlowLayout());
        }
        conifgPanel.add(recordConfigBox);
        add(conifgPanel, BorderLayout.SOUTH);
        add(hideColumnTree, BorderLayout.CENTER);
    }

    public HideColumnPopup(JTableHeader tableHeader) {
        this("", tableHeader);
    }

    private void initListeners() {
        hideColumnTree.addTreeExpansionListener(new TreeExpansionListener() {

            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                pack();
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                treeExpanded(null);
            }
        });

        hideColumnTree.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                int row = hideColumnTree.getClosestRowForLocation(e.getX(), e.getY());
                Rectangle rec = hideColumnTree.getRowBounds(row);
                if (rec.contains(e.getPoint())) {
                    TreePath path = hideColumnTree.getSelectionPath();
                    if (path != null) {
                        HideableColumnNode node = (HideableColumnNode) path.getLastPathComponent();
//                        System.out.println("path last node = " + node);
                        node.rotateNextSelectType();
                        node.notifySelectChanged(node.getSelectedType());
                        hideColumnTree.repaint();
                    }
                }
            }
        });

    }

    /**
     * 展开所有节点
     */
    public void expandColumnNodes() {
        int row = 0;
        while (row < hideColumnTree.getRowCount()) {
            hideColumnTree.expandRow(row);
            row++;
        }
    }

    /**
     * 折叠所有节点
     */
    public void collapseColumnNodes() {
        int row = hideColumnTree.getRowCount() - 1;
        while (row > 0) {
            hideColumnTree.collapseRow(row);
            row--;
        }
    }

    /**
     * 设置隐藏列树形模型，并展开所以节点。
     * @param treeModel
     */
    public void setHideColumnTreeModel(DefaultTreeModel treeModel) {
        hideColumnTree.setModel(treeModel);
    }

    /**
     * 清除列
     */
    public void clearColumnNode() {
        hideColumnTree.setModel(null);
    }

    /**
     * 更改节点的可见性
     * @param selectType
     * @param modelIndex
     */
    void changeVisibleByModelIndexs(int selectType, int... modelIndex) {
        DefaultTreeModel model = (DefaultTreeModel) hideColumnTree.getModel();
        if (model != null) {
            for (int index : modelIndex) {
                doChangeColumnsVisible(model.getRoot(), selectType, index);
            }
        }
    }

    private void doChangeColumnsVisible(Object hcNode, int selectType, int modelIndex) {
        if (hcNode != null) {
            if (hcNode instanceof HideableColumnNode) {
                HideableColumnNode tempNode = (HideableColumnNode) hcNode;
                if (tempNode.isLeaf() && tempNode.getSelectedType() != selectType) {
                    if (tempNode.getColumn() != null && tempNode.getColumn().getModelIndex() == modelIndex) {
                        tempNode.setSelectedType(selectType);
                        tempNode.notifySelectChanged(selectType);
                        return;
                    }
                }
                if (tempNode.getChildCount() > 0) {
                    doChangeColumnsVisible(tempNode.getFirstChild(), selectType, modelIndex);

                } else {
                    if (tempNode.getNextSibling() != null) {
                        doChangeColumnsVisible(tempNode.getNextSibling(), selectType, modelIndex);

                    } else {
                        doChangeColumnsVisible(((HideableColumnNode) tempNode.getParent()).getNextSibling(), selectType, modelIndex);
                    }
                }
            }
        }
    }

    /**
     * 获得某一选择类型的列的索引
     * @param selectType 选择类型
     * @return
     */
    public int[] getModelIndexsByVisibleType(int selectType) {
        List<Integer> columnList = new ArrayList<Integer>();
        DefaultTreeModel model = (DefaultTreeModel) hideColumnTree.getModel();
        if (model != null) {
            doGetModelIndexs(model.getRoot(), selectType, columnList);
        }
        int[] tempColumns = new int[columnList.size()];
        for (int index = 0; index < columnList.size(); index++) {
            tempColumns[index] = columnList.get(index);
        }
        return tempColumns;
    }

    private void doGetModelIndexs(Object hcNode, int selectType, List<Integer> columns) {
        if (hcNode != null) {
            if (hcNode instanceof HideableColumnNode) {
                HideableColumnNode tempNode = (HideableColumnNode) hcNode;
                if (tempNode.isLeaf() && tempNode.getSelectedType() == selectType) {
                    if (tempNode.getColumn() != null) {
                        columns.add(tempNode.getColumn().getModelIndex());
                    }
                }
                if (tempNode.getChildCount() > 0) {
                    doGetModelIndexs(tempNode.getFirstChild(), selectType, columns);

                } else {
                    if (tempNode.getNextSibling() != null) {
                        doGetModelIndexs(tempNode.getNextSibling(), selectType, columns);

                    } else {
                        doGetModelIndexs(((HideableColumnNode) tempNode.getParent()).getNextSibling(), selectType, columns);
                    }
                }
            }
        }
    }

    /**
     * 还原所有的列
     * 用于在旧列模型上添加新列时。
     */
    void recoverAllColumns() {
        DefaultTreeModel model = (DefaultTreeModel) hideColumnTree.getModel();
        if (model != null) {
            doRecover(model.getRoot());
        }
    }

    private void doRecover(Object hcNode) {
        if (hcNode != null) {
            if (hcNode instanceof HideableColumnNode) {
                HideableColumnNode tempNode = (HideableColumnNode) hcNode;
                if (tempNode.isLeaf() && tempNode.getSelectedType() == HideableColumnNode.UNSELECTED) {
                    tempNode.setSelectedType(HideableColumnNode.SELECTED);
                    tempNode.fireSelectedChange();
                }
                if (tempNode.getChildCount() > 0) {
                    doRecover(tempNode.getFirstChild());

                } else {
                    if (tempNode.getNextSibling() != null) {
                        doRecover(tempNode.getNextSibling());

                    } else {
                        doRecover(((HideableColumnNode) tempNode.getParent()).getNextSibling());
                    }
                }
            }
        }
    }

    /**
     * 是否记录表格配置
     * @return
     */
    public boolean isRecordTableConfig() {
        return recordConfigBox.isSelected();
    }

    /**
     * 设置是否记录配置
     * @param record 记录
     */
    public void setRecordTableConfig(boolean record) {
        recordConfigBox.setSelected(record);
    }

    //隐藏列的单元绚烂器
    private class HideColumnTreeCellRenderer extends JCheckBox implements TreeCellRenderer {

        //未完全选择
        private boolean unCompleted;

        public HideColumnTreeCellRenderer() {
            setSelected(true);
            setBackground(UIManager.getColor("Tree.background"));
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            setText(value == null ? "" : value.toString());
            if (tree.getPathForRow(row) != null) {
                Object obj = tree.getPathForRow(row).getLastPathComponent();
                if (obj instanceof HideableColumnNode) {
                    HideableColumnNode node = (HideableColumnNode) tree.getPathForRow(row).getLastPathComponent();
//                    System.out.println("node select type = " + node.getSelectedType() + " node name = " + node);
                    if (node.getSelectedType() == HideableColumnNode.SELECTED) {
                        setSelected(true);
                        unCompleted = false;

                    } else if (node.getSelectedType() == HideableColumnNode.UNSELECTED) {
                        setSelected(false);
                        unCompleted = false;

                    } else {
                        setSelected(false);
                        unCompleted = true;
                    }
                }
            }
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (unCompleted) {
                g.setColor(Color.GRAY);
                g.fillRect(7, 8, 7, 7);
            }
        }
    }
}

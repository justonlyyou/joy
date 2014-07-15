/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 可以隐藏的数的节点
 * 在自己的选择性发生跟改时通知父节点及子节点
 * @author ckcs
 */
public class HideableColumnNode extends DefaultMutableTreeNode {

    //选中
    public static final int SELECTED = 1;
    //为选
    public static final int UNSELECTED = 2;
    //不完全选中D
    public static final int UNCOMPLETED = 3;
    //当前选则类型
    private int selectedType = SELECTED;
    private TopTableHeader tableHeader;
    //被隐藏的列索引
    private int hidedIndex;
    //所代表的列
    private TableColumn column;

    public HideableColumnNode(Object userObject, TopTableHeader tableHeader) {
        super(userObject);
        this.tableHeader = tableHeader;
    }

    public HideableColumnNode() {
    }

    public void notifySelectChanged(int sType) {
        //叶子,通知叶子的父节点
        if (isLeaf()) {
//            System.out.println("处理叶子");
            //通知我取消选择了
            fireSelectedChange();
            doNotifyParent();

        //树干,通知树干的父树干，及子节点
        } else {
//            System.out.println("处理树干");
            doNotifyParent();
            doNotifyChild(sType);
        }
    }

    //通知叶子的父节点,及间接父节点
    private void doNotifyParent() {
        if (parent != null) {
            HideableColumnNode parentNode = (HideableColumnNode) parent;
            //设为无效选择
            parentNode.setSelectedType(0);
            int count = parentNode.getChildCount();
            for (int index = 0; index < count; index++) {
                HideableColumnNode childNode = (HideableColumnNode) parentNode.getChildAt(index);
                parentNode.setSelectedType(childNode.getSelectedType() | parentNode.getSelectedType());
            }
            parentNode.doNotifyParent();
        }
    }

    //及自己的子节点及间接子节点
    private void doNotifyChild(int sType) {
        int count = getChildCount();
        for (int index = 0; index < count; index++) {
            HideableColumnNode childNode = ((HideableColumnNode) getChildAt(index));
            int oldType = childNode.getSelectedType();
            childNode.setSelectedType(sType);
            if (!childNode.isLeaf()) {
                childNode.doNotifyChild(sType);

            //状态改变了
            } else if (oldType != childNode.getSelectedType()) {
                childNode.fireSelectedChange();
            }
        }
    }

    /**
     * 通知表头列显示性发生变化了
     */
    void fireSelectedChange() {
//        System.out.println("fireSelectedChange child = " + this + " select = " + selectedType);
        if (getSelectedType() == SELECTED) {
            tableHeader.doShowTableColumn(hidedIndex, column);

        } else if (getSelectedType() == UNSELECTED) {
            hidedIndex = tableHeader.doHideTableColumn(column);
            //不能移除
            if(hidedIndex == -1){
                setSelectedType(SELECTED);
                doNotifyParent();
            }
        }
    }

    public TableColumn getColumn() {
        return column;
    }

    public void setColumn(TableColumn column) {
        this.column = column;
    }

    /**
     * 进入下一个选择类型
     * 对应单个节点:
     * uncompleted --> selected --> unselected -->selected
     */
    public void rotateNextSelectType() {
        if (selectedType == UNSELECTED || selectedType == UNCOMPLETED) {
            setSelectedType(SELECTED);
        } else {
            setSelectedType(UNSELECTED);
        }
    }

    public int getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(int selectedType) {
        this.selectedType = selectedType;
    }
}

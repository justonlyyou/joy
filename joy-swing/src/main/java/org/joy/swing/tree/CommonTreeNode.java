package org.joy.swing.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * 分类节点
 * @author linju
 */
public class CommonTreeNode extends DefaultMutableTreeNode {

    /**
     * 字节点总数，子节点选择数
     */
    private int[] childrenSelectionCount = { 0, 0 };

    /**
     * 判断该节点是否已经探索过了
     */
    private boolean explored = false;

    /**
     * 判断该节点是否被选择过了
     */
    private boolean selected = false;

    /**
     * 节点是否可见
     */
    protected boolean viewState = true;

    /** Field description */
    protected boolean visible = true;

    /**
     * 子节点的选择变化
     */
    private boolean selectedChanged = false;

    /**
     * 节点上存放的数据
     */
    private List data;    // private Runnable getChildrenRunable;

    /**
     * 节点的图标
     */
    protected ImageIcon icon;

    /**
     * 构造一个CommonTreeNode
     */
    public CommonTreeNode() {

        // this(null);
        super();
    }

    /**
     * 构造存有指定对象的CommonTreeNode
     * @param userObject
     */
    public CommonTreeNode(ObjectNode userObject) {
        this(userObject, true, false, true, null);
    }

    /**
     * Constructs ...
     *
     *
     * @param objectNode
     * @param isSelected
     */
    public CommonTreeNode(ObjectNode objectNode, boolean isSelected) {
        super(objectNode, true);
        this.setSelected(isSelected);
    }

    /**
     * Constructs ...
     *
     *
     * @param userObject
     * @param icon
     */
    public CommonTreeNode(ObjectNode userObject, ImageIcon icon) {
        this(userObject, true, false, true, icon);
    }

    /**
     * Constructs ...
     *
     *
     * @param userObject
     * @param allowsChildren
     * @param visible
     */
    public CommonTreeNode(ObjectNode userObject, boolean allowsChildren, boolean visible) {
        this(userObject, true, false, visible, null);
    }

    /**
     *  构造一个存有指定对象，并且已经设置是否勾选和图标
     * @param userObject 指定对象
     * @param isSelected 是否勾选
     * @param icon 指定图标
     */
    public CommonTreeNode(ObjectNode userObject, boolean isSelected, ImageIcon icon) {
        this(userObject, true, isSelected, true, icon);
    }

    /**
     * 构造一个存有指定对象并设定其是否勾选，是否可见，指定图标的CommonTreeNode
     * @param userObject  存放的对象
     * @param allowsChildren 是否有子节点
     * @param isSelected 是否勾选
     * @param isVisible 是否可见
     * @param icon 指定图标
     */
    public CommonTreeNode(ObjectNode userObject, boolean allowsChildren, boolean isSelected, boolean isVisible, ImageIcon icon) {
        super(userObject, allowsChildren);
        this.selected = isSelected;
        this.visible = isVisible;
        this.icon = icon;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isLeaf() {
        return ((ObjectNode) getUserObject()).isLeaf();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isExplored() {
        return explored;
    }

    /**
     * Method description
     *
     *
     * @param explored
     */
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    /**
     * 判断该节点下的子节点是否与所给的name一样
     *
     * @param name
     *
     * @return
     */
    public boolean isNameExist(String name) {
        int childCount = this.getChildCount();

        for (int i = 0; i < childCount; i++) {
            TreeNode childNode = this.getChildAt(i);

            if (name.equals(childNode.toString())) {
                return true;
            }
        }

        return false;
    }

    /**
     * 探索此节点下的子节点
     *
     * @param model
     */
    public void explore(final DefaultTreeModel model) {
        if (!isExplored()) {
            new Thread(new Runnable() {
                public void run() {
                    final ObjectNode objectNode = (ObjectNode) getUserObject();
                    final java.util.List children = objectNode.getChildren();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            for (Iterator it = children.iterator(); it.hasNext(); ) {
                                ObjectNode childObjectNode = (ObjectNode) it.next();

                                add(new CommonTreeNode(childObjectNode, isSelected(), childObjectNode.isVisible()));
                                setViewState(childObjectNode.isVisible());
                            }

                            model.nodeStructureChanged(CommonTreeNode.this);
                        }
                    });
                }
            }).start();
            explored = true;
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean explore() {
        if (!isExplored()) {
            ObjectNode objectNode = (ObjectNode) getUserObject();

            if (!objectNode.isLeaf()) {
                List children = objectNode.getChildren();

                if (children == null) {
                    return false;
                }

                for (Iterator it = children.iterator(); it.hasNext(); ) {
                    ObjectNode childObjectNode = (ObjectNode) it.next();

                    add(new CommonTreeNode(childObjectNode, isSelected(), childObjectNode.isVisible()));
                    setViewState(childObjectNode.isVisible());
                }

                explored = true;
            }
        }

        return true;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean exploreAllChildren() {
        ObjectNode objectNode = (ObjectNode) getUserObject();

        if (!objectNode.isLeaf()) {
            if (!isExplored()) {
                List children = objectNode.getChildren();

                if (children == null) {
                    return false;
                }

                for (Iterator it = children.iterator(); it.hasNext(); ) {
                    ObjectNode childObjectNode = (ObjectNode) it.next();
                    CommonTreeNode childNode = new CommonTreeNode(childObjectNode, isSelected(), childObjectNode.isVisible());

                    add(childNode);
                    setViewState(childObjectNode.isVisible());
                    childNode.exploreAllChildren();
                }

                explored = true;
            } else {
                for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
                    CommonTreeNode childNode = (CommonTreeNode) getChildAt(childIndex);

                    childNode.exploreAllChildren();
                }
            }
        }

        return true;
    }

    /**
     * Method description
     *
     *
     * @param toFindObject
     *
     * @return
     */
    public List<CommonTreeNode> findChildNodeByNodeObject(ObjectNode toFindObject) {
        List<CommonTreeNode> childNodes = new ArrayList<CommonTreeNode>();

        if (getUserObject().equals(toFindObject)) {
            childNodes.add(this);
        }

        for (int childIndex = 0; childIndex < getChildCount(); childIndex++) {
            CommonTreeNode childNode = (CommonTreeNode) getChildAt(childIndex);

            if (childNode.getUserObject().equals(toFindObject)) {
                childNodes.add(childNode);
            }
        }

        return childNodes;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List getData() {
        if (data == null) {
            data = new ArrayList();
        }

        return data;
    }

    /**
     * Method description
     *
     *
     * @param data
     */
    public void setData(List data) {
        this.data = data;
    }

    /**
     * 设置节点是否可见
     * @param state 是否可见标识
     */
    public void setViewState(boolean state) {
        this.viewState = state;
    }

    /**
     * 获得节点当前状态是否可见
     * @return 返回节点是否可见状态
     */
    public boolean getViewState() {
        return this.viewState;
    }

    /**
     * 设置节点的图标
     * @param icon 要设置的图标
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * 获得节点的图标
     * @return 返回节点的图标
     */
    public ImageIcon getIcon() {
        return this.icon;
    }

    /**
     * 设置节点的勾选状态
     * @param isSelected 是否被勾选
     */
    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
        selectedChanged = true;

//      if ((children != null)) {
//          Enumeration enumer = children.elements();
//          while (enumer.hasMoreElements()) {
//              CommonTreeNode node = (CommonTreeNode)enumer.nextElement();
//              node.setSelected(isSelected);
//          }
//      }
    }

    /**
     * 是否被勾选
     * @return 是否被勾选的状态
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * 重写默认的获得字节点的方法
     * @param index 节点的索引
     * @return value 返回该索引节点
     */
    public TreeNode getChildAt(int index) {
        if (children == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }

        if (viewState) {
            return super.getChildAt(index);
        }

        int realIndex = -1;
        int visibleIndex = -1;
        Enumeration enumer = children.elements();

        while (enumer.hasMoreElements()) {
            CommonTreeNode node = (CommonTreeNode) enumer.nextElement();

            if (node.isVisible()) {
                visibleIndex++;
            }

            realIndex++;

            if (visibleIndex == index) {
                return (TreeNode) children.elementAt(realIndex);
            }
        }

        throw new ArrayIndexOutOfBoundsException("isndex unmatched \n" + "Tree Node =" + getUserObject() + " index =" + index);
    }

    /**
     * 重写获得子节点总数的方法
     * @return count 返回子节点的总数
     */
    public int getChildCount() {
        if (viewState) {
            return super.getChildCount();
        }

        if (children == null) {
            return 0;
        }

        int count = 0;
        Enumeration enumer = children.elements();

        while (enumer.hasMoreElements()) {
            CommonTreeNode node = (CommonTreeNode) enumer.nextElement();

            if (node.isVisible()) {
                count++;
            }
        }

        return count;
    }

    /**
     * Method description
     *
     *
     * @param aChild
     *
     * @return
     */
    public int getIndex(TreeNode aChild) {
        if (aChild instanceof CommonTreeNode) {
            return ((CommonTreeNode) aChild).getViewState() ? super.getIndex(aChild) : -1;
        }

        return super.getIndex(aChild);
    }

    /**
     * 设置节点是否可见
     * @param visible 是否可见的标识
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * 节点是否可见
     * @return 节点是否可见的状态
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int[] getChidrenSelectionCount() {
        if (selectedChanged) {
            childrenSelectionCount[0] = 0;
            childrenSelectionCount[1] = 0;

            Enumeration<CommonTreeNode> childrenEnum = children();
            CommonTreeNode childNode = null;

            for (; childrenEnum.hasMoreElements(); ) {
                childNode = childrenEnum.nextElement();

                if (childNode.isLeaf()) {
                    if (childNode.isSelected()) {
                        childrenSelectionCount[0]++;
                    }

                    childrenSelectionCount[1]++;
                } else {
                    int[] childrenSelcetion = childNode.getChidrenSelectionCount();

                    if (childrenSelcetion[1] == 0) {
                        childrenSelcetion[1] = 1;
                        childrenSelcetion[0] = childNode.isSelected() ? 1 : 0;
                    }

                    childrenSelectionCount[0] += childrenSelcetion[0];
                    childrenSelectionCount[1] += childrenSelcetion[1];
                }
            }
        }

        return childrenSelectionCount;
    }

    /**
     * Method description
     *
     */
    public void fireAllNodesSelectedStateChanged() {
        fireChildNodesSelectedStateChanged();
        fireParentNodesSelectedStateChanged();
    }

    /**
     * 选中所有的子节点
     */
    public void fireChildNodesSelectedStateChanged() {
        CommonTreeNode childNode = null;

        if (this.getChildCount() > 0) {
            for (int index = 0; index < this.getChildCount(); index++) {
                childNode = (CommonTreeNode) this.getChildAt(index);
                childNode.setSelected(this.isSelected());
                childNode.fireChildNodesSelectedStateChanged();
            }
        }
    }

    /**
     * 设置所有的子节点为是否可见
     *
     * @param viewState
     */
    public void changeAllChildNodesViewState(boolean viewState) {
        this.setViewState(viewState);

        if (this.getChildCount() > 0) {
            for (int index = 0; index < this.getChildCount(); index++) {
                CommonTreeNode ChildNode = (CommonTreeNode) this.getChildAt(index);

                ChildNode.changeAllChildNodesViewState(viewState);
            }
        }
    }

    /**
     * 如果子节点全选中，则父节点选中，子节点有一个未选中，父节点都为未选状态
     */
    public void fireParentNodesSelectedStateChanged() {
        CommonTreeNode parentNode = (CommonTreeNode) this.getParent();

        if (parentNode != null) {
            parentNode.setSelected(true);

            for (int childIndex = 0; childIndex < parentNode.getChildCount(); childIndex++) {
                CommonTreeNode childNode = (CommonTreeNode) parentNode.getChildAt(childIndex);

                if (childNode.isSelected() == false) {
                    parentNode.setSelected(false);

                    break;
                }
            }

            parentNode.fireParentNodesSelectedStateChanged();
        }
    }

    /**
     * 重写toString方法
     * @return 返回该节点存放的对象的toString值
     */
    public String toString() {
        return this.getUserObject().toString();
    }
}

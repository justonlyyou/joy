package org.joy.swing.tree;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;
import java.awt.dnd.*;

import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
public class DefaultDNDTree extends JTree {

    /** Field description */
    private Insets autoscrollInsets = new Insets(20, 20, 20, 20);    // insets

    /** Field description */
    private AbstractTreeTransferHandler transferHandler = null;

    /**
     * Constructs ...
     *
     */
    public DefaultDNDTree() {
        super();
        setAutoscrolls(true);
        setShowsRootHandles(false);    // to show the root icon
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);    // set single selection for the Tree
        setEditable(false);
        transferHandler = new DefaultTreeTransferHandler(this, DnDConstants.ACTION_COPY_OR_MOVE);
    }

    /**
     * Constructs ...
     *
     *
     * @param treemodel
     */
    public DefaultDNDTree(DefaultTreeModel treemodel) {
        this();
        setModel(treemodel);
    }

    /**
     * Constructs ...
     *
     *
     * @param treeNode
     */
    public DefaultDNDTree(TreeNode treeNode) {
        this(new DefaultTreeModel(treeNode));
    }

    /**
     * Method description
     *
     *
     * @param transferHandler
     */
    public void setTreeTransferHandler(AbstractTreeTransferHandler transferHandler) {
        this.transferHandler = transferHandler;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public AbstractTreeTransferHandler getTreeTransferHandler() {
        return this.transferHandler;
    }

    /**
     * Method description
     *
     *
     * @param cursorLocation
     */
    public void autoscroll(Point cursorLocation) {
        Insets insets = getAutoscrollInsets();
        Rectangle outer = getVisibleRect();
        Rectangle inner = new Rectangle(outer.x + insets.left, outer.y + insets.top, outer.width - (insets.left + insets.right), outer.height - (insets.top + insets.bottom));

        if (!inner.contains(cursorLocation)) {
            Rectangle scrollRect = new Rectangle(cursorLocation.x - insets.left, cursorLocation.y - insets.top, insets.left + insets.right, insets.top + insets.bottom);

            scrollRectToVisible(scrollRect);
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Insets getAutoscrollInsets() {
        return (autoscrollInsets);
    }

    /**
     * Method description
     *
     *
     * @param node
     *
     * @return
     */
    public static DefaultMutableTreeNode makeDeepCopy(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode copy = new DefaultMutableTreeNode(node.getUserObject());

        for (Enumeration e = node.children(); e.hasMoreElements(); ) {
            copy.add(makeDeepCopy((DefaultMutableTreeNode) e.nextElement()));
        }

        return (copy);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static DefaultMutableTreeNode createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("node1");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("node2");

        root.add(node1);
        root.add(node2);
        node1.add(new DefaultMutableTreeNode("sub1_1"));
        node1.add(new DefaultMutableTreeNode("sub1_2"));
        node1.add(new DefaultMutableTreeNode("sub1_3"));
        node2.add(new DefaultMutableTreeNode("sub2_1"));
        node2.add(new DefaultMutableTreeNode("sub2_2"));
        node2.add(new DefaultMutableTreeNode("sub2_3"));

        return (root);
    }

    /**
     * Method description
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            JFrame frame = new JFrame();
            Container contentPane = frame.getContentPane();

            contentPane.setLayout(new GridLayout(1, 2));

            DefaultMutableTreeNode root1 = DefaultDNDTree.createTree();
            DefaultDNDTree tree1 = new DefaultDNDTree(root1);
            DefaultMutableTreeNode root2 = DefaultDNDTree.createTree();
            DefaultDNDTree tree2 = new DefaultDNDTree(root2);

            tree1.getTreeTransferHandler().setDNDState(true);
            tree2.getTreeTransferHandler().setDNDState(true);

            // DefaultMutableTreeNode root1 = DNDTree.createTree();
//          DNDTree tree1 = new DNDTree( ModelUtil.getModelUtil().getDepartmentTreeModel());
            // DefaultMutableTreeNode root2 = DNDTree.createTree();
//          DNDTree tree2 = new DNDTree( ModelUtil.getModelUtil().getDepartmentTreeModel());
            contentPane.add(new JScrollPane(tree1));
            contentPane.add(new JScrollPane(tree2));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

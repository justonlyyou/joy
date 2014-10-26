/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.tree;

import org.joy.swing.PinYinKeySelectionManager;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Kevice
 */
public class XTree extends JTree {

    /**
     * Returns a <code>XTree</code> with a sample model.
     * The default model used by the tree defines a leaf node as any node
     * without children.
     *
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree() {
        super();
        addKeyListener();
    }

    private void addKeyListener() {
        new PinYinKeySelectionManager(this);
    }

    /**
     * Returns a <code>XTree</code> with each element of the
     * specified array as the
     * child of a new root node which is not displayed.
     * By default, the tree defines a leaf node as any node without
     * children.
     *
     * @param value  an array of <code>Object</code>s
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree(Object[] value) {
        super(value);
        addKeyListener();
    }

    /**
     * Returns a <code>XTree</code> with each element of the specified
     * <code>Vector</code> as the
     * child of a new root node which is not displayed. By default, the
     * tree defines a leaf node as any node without children.
     *
     * @param value  a <code>Vector</code>
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree(Vector<?> value) {
        super(value);
        addKeyListener();
    }

    /**
     * Returns a <code>XTree</code> created from a <code>Hashtable</code>
     * which does not display with root.
     * Each value-half of the key/value pairs in the <code>HashTable</code>
     * becomes a child of the new root node. By default, the tree defines
     * a leaf node as any node without children.
     *
     * @param value  a <code>Hashtable</code>
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree(Hashtable<?, ?> value) {
        super(value);
        addKeyListener();
    }

    /**
     * Returns a <code>XTree</code> with the specified
     * <code>TreeNode</code> as its root,
     * which displays the root node.
     * By default, the tree defines a leaf node as any node without children.
     *
     * @param root  a <code>TreeNode</code> object
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree(TreeNode root) {
        super(root);
        addKeyListener();
    }

    /**
     * Returns a <code>XTree</code> with the specified <code>TreeNode</code>
     * as its root, which
     * displays the root node and which decides whether a node is a
     * leaf node in the specified manner.
     *
     * @param root  a <code>TreeNode</code> object
     * @param asksAllowsChildren  if false, any node without children is a
     *              leaf node; if true, only nodes that do not allow
     *              children are leaf nodes
     * @see DefaultTreeModel#asksAllowsChildren
     */
    public XTree(TreeNode root, boolean asksAllowsChildren) {
        super(root, asksAllowsChildren);
        addKeyListener();
    }

    /**
     * Returns an instance of <code>XTree</code> which displays the root node
     * -- the tree is created using the specified data model.
     *
     * @param newModel  the <code>TreeModel</code> to use as the data model
     */
    public XTree(TreeModel newModel) {
        super(newModel);
        addKeyListener();
    }

}

package org.joy.swing.tree;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.dnd.DnDConstants;

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
public class DefaultTreeTransferHandler extends AbstractTreeTransferHandler {

    /**
     * Constructs ...
     *
     *
     * @param tree
     * @param action
     */
    public DefaultTreeTransferHandler(DefaultDNDTree tree, int action) {
        super(tree, action, true);
    }

    /**
     * Method description
     *
     *
     * @param target
     * @param draggedNode
     * @param action
     * @param location
     *
     * @return
     */
    public boolean canPerformAction(DefaultDNDTree target, DefaultMutableTreeNode draggedNode, int action, Point location) {
        TreePath pathTarget = target.getPathForLocation(location.x, location.y);

        if ((pathTarget == null) ||!getDragState()) {
            target.setSelectionPath(null);

            return (false);
        }

        target.setSelectionPath(pathTarget);
        target.expandPath(pathTarget);

        if (action == DnDConstants.ACTION_COPY) {
            return (true);
        } else if (action == DnDConstants.ACTION_MOVE) {
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) pathTarget.getLastPathComponent();

            if (draggedNode.isRoot() || (parentNode == draggedNode.getParent()) || draggedNode.isNodeDescendant(parentNode) || parentNode.isLeaf()) {
                return (false);
            } else {
                return (true);
            }
        } else {
            return (false);
        }
    }

    /**
     * Method description
     *
     *
     * @param target
     * @param draggedNode
     * @param newParentNode
     * @param action
     *
     * @return
     */
    public boolean executeDrop(DefaultDNDTree target, DefaultMutableTreeNode draggedNode, DefaultMutableTreeNode newParentNode, int action) {
        if (action == DnDConstants.ACTION_COPY) {
            DefaultMutableTreeNode newNode = target.makeDeepCopy(draggedNode);

            ((DefaultTreeModel) target.getModel()).insertNodeInto(newNode, newParentNode, newParentNode.getChildCount());

            TreePath treePath = new TreePath(newNode.getPath());

            target.scrollPathToVisible(treePath);
            target.setSelectionPath(treePath);

            return (true);
        }

        if (action == DnDConstants.ACTION_MOVE) {
            ((DefaultTreeModel) target.getModel()).removeNodeFromParent(draggedNode);
            ((DefaultTreeModel) target.getModel()).insertNodeInto(draggedNode, newParentNode, 0);

            TreePath treePath = new TreePath(draggedNode.getPath());

            target.scrollPathToVisible(treePath);
            target.setSelectionPath(treePath);

            return (true);
        }

        return (false);
    }
}

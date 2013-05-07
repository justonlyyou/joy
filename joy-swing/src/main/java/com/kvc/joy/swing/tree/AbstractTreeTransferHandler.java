package com.kvc.joy.swing.tree;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.tree.*;

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
public abstract class AbstractTreeTransferHandler
        implements DragGestureListener, DragSourceListener, DropTargetListener {

    /** Field description */
    private static BufferedImage image = null;    // buff image

    /** Field description */
    private static DefaultMutableTreeNode draggedNode;

    /** Field description */
    private Rectangle rect2D = new Rectangle();

    /**
     * 放标志
     */
    public boolean dropState = false;

    /**
     * 拖动标志
     */
    public boolean dragState = false;

    /** Field description */
    private DragSource dragSource;    // dragsource

    /** Field description */
    private DefaultMutableTreeNode draggedNodeParent;

    /** Field description */
    private boolean drawImage;

    /** Field description */
    private DropTarget dropTarget;    // droptarget

    /** Field description */
    private DefaultDNDTree tree;

    /**
     * Constructs ...
     *
     *
     * @param tree
     * @param action
     * @param drawIcon
     */
    protected AbstractTreeTransferHandler(DefaultDNDTree tree, int action, boolean drawIcon) {
        this.tree = tree;
        drawImage = drawIcon;
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(tree, action, this);
        dropTarget = new DropTarget(tree, action, this);
    }

    /* Methods for DragSourceListener */

    /**
     * Method description
     *
     *
     * @param dsde
     */
    public void dragDropEnd(DragSourceDropEvent dsde) {}

    /**
     * Method description
     *
     *
     * @param dsde
     */
    public final void dragEnter(DragSourceDragEvent dsde) {
        setDragChangeCursor(dsde);
    }

    /**
     * Method description
     *
     *
     * @param dsde
     */
    public final void dragOver(DragSourceDragEvent dsde) {
        setDragChangeCursor(dsde);
    }

    /**
     * Method description
     *
     *
     * @param dsde
     */
    public final void dropActionChanged(DragSourceDragEvent dsde) {
        setDragChangeCursor(dsde);
    }

    /**
     * Method description
     *
     *
     * @param dse
     */
    public final void dragExit(DragSourceEvent dse) {
        dse.getDragSourceContext().setCursor(DragSource.DefaultMoveNoDrop);
    }

    /**
     * Method description
     *
     *
     * @param dsde
     */
    private void setDragChangeCursor(DragSourceDragEvent dsde) {
        int action = dsde.getDropAction();

        dsde.getDragSourceContext().setCursor((action == DnDConstants.ACTION_COPY) ? DragSource.DefaultCopyDrop : ((action == DnDConstants.ACTION_MOVE) ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop));
    }

    /* Methods for DragGestureListener */

    /**
     * Method description
     *
     *
     * @param dge
     */
    public final void dragGestureRecognized(DragGestureEvent dge) {
        TreePath path = tree.getSelectionPath();

        if (path != null) {
            draggedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
            draggedNodeParent = (DefaultMutableTreeNode) draggedNode.getParent();

            if (drawImage) {
                Rectangle pathBounds = tree.getPathBounds(path);    // getpathbounds of selectionpath
                JComponent lbl = (JComponent) tree.getCellRenderer().getTreeCellRendererComponent(tree, draggedNode, false, tree.isExpanded(path), ((DefaultTreeModel) tree.getModel()).isLeaf(path.getLastPathComponent()), 0, false);    // returning the label

                lbl.setBounds(pathBounds);    // setting bounds to lbl
                image = new BufferedImage(lbl.getWidth(), lbl.getHeight(), java.awt.image.BufferedImage.TYPE_INT_ARGB_PRE);    // buffered image reference passing the label's ht and width

                Graphics2D graphics = image.createGraphics();    // creating the graphics for buffered image

                graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));    // Sets the Composite for the Graphics2D context
                lbl.setOpaque(false);
                lbl.paint(graphics);    // painting the graphics to label
                graphics.dispose();
            }

            if (dragState) {
                dragSource.startDrag(dge, DragSource.DefaultMoveNoDrop, image, new Point(0, 0), new TransferableNode(draggedNode), this);
            }
        }
    }

    /* Methods for DropTargetListener */

    /**
     * Method description
     *
     *
     * @param dtde
     */
    public final void dragEnter(DropTargetDragEvent dtde) {
        Point pt = dtde.getLocation();
        int action = dtde.getDropAction();

        if (drawImage) {
            paintImage(pt);
        }

        if (canPerformAction(tree, draggedNode, action, pt)) {
            dtde.acceptDrag(action);
        } else {
            dtde.rejectDrag();
        }
    }

    /**
     * Method description
     *
     *
     * @param dte
     */
    public final void dragExit(DropTargetEvent dte) {
        if (drawImage) {
            clearImage();
        }
    }

    /**
     * Method description
     *
     *
     * @param dtde
     */
    public final void dragOver(DropTargetDragEvent dtde) {
        Point pt = dtde.getLocation();
        int action = dtde.getDropAction();

        tree.autoscroll(pt);

        if (drawImage) {
            paintImage(pt);
        }

        if (dropState && canPerformAction(tree, draggedNode, action, pt)) {
            dtde.acceptDrag(action);
        } else {
            dtde.rejectDrag();
        }
    }

    /**
     * Method description
     *
     *
     * @param dtde
     */
    public final void dropActionChanged(DropTargetDragEvent dtde) {
        Point pt = dtde.getLocation();
        int action = dtde.getDropAction();

        if (drawImage) {
            paintImage(pt);
        }

        if (canPerformAction(tree, draggedNode, action, pt)) {
            dtde.acceptDrag(action);
        } else {
            dtde.rejectDrag();
        }
    }

    /**
     * Method description
     *
     *
     * @param dtde
     */
    public final void drop(DropTargetDropEvent dtde) {
        try {
            if (drawImage) {
                clearImage();
            }

            int action = dtde.getDropAction();
            Transferable transferable = dtde.getTransferable();
            Point pt = dtde.getLocation();

            if (transferable.isDataFlavorSupported(TransferableNode.NODE_FLAVOR) && getDropState() && canPerformAction(tree, draggedNode, action, pt)) {
                TreePath pathTarget = tree.getPathForLocation(pt.x, pt.y);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) transferable.getTransferData(TransferableNode.NODE_FLAVOR);
                DefaultMutableTreeNode newParentNode = (DefaultMutableTreeNode) pathTarget.getLastPathComponent();

                if (dropState && executeDrop(tree, node, newParentNode, action)) {
                    dtde.acceptDrop(action);
                    dtde.dropComplete(true);

                    return;
                }
            }

            dtde.rejectDrop();
            dtde.dropComplete(false);
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
            dtde.dropComplete(false);
        }
    }

    /**
     * 设置是否可以拖动
     * @param dragState 拖动标志
     */
    public void setDragState(boolean dragState) {
        this.dragState = dragState;
    }

    /**
     * 获得拖动标志
     * @return 返回当前是否可以拖动
     */
    public boolean getDragState() {
        return this.dragState;
    }

    /**
     * 设置是否可放
     * @param dropState 是否可放
     */
    public void setDropState(boolean dropState) {
        this.dropState = dropState;
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
    }

    /**
     * 取得当前是否可放
     * @return 返回当前是否可放
     */
    public boolean getDropState() {
        return this.dropState;
    }

    /**
     * 设置拖放标志
     * @param DNDState 设置是否可以拖放
     */
    public void setDNDState(boolean DNDState) {
        setDragState(DNDState);
        setDropState(DNDState);
    }

    /**
     * Method description
     *
     *
     * @param pt
     */
    private final void paintImage(Point pt) {
        tree.paintImmediately(rect2D.getBounds());
        rect2D.setRect((int) pt.getX(), (int) pt.getY(), image.getWidth(), image.getHeight());
        tree.getGraphics().drawImage(image, (int) pt.getX(), (int) pt.getY(), tree);
    }

    /**
     * Method description
     *
     */
    private final void clearImage() {
        tree.paintImmediately(rect2D.getBounds());
    }

    /**
     * Method description
     *
     *
     * @param isDraw
     */
    public void setDrawImage(boolean isDraw) {
        this.drawImage = isDraw;
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
    public abstract boolean canPerformAction(DefaultDNDTree target, DefaultMutableTreeNode draggedNode, int action, Point location);

    /**
     * Method description
     *
     *
     * @param tree
     * @param draggedNode
     * @param newParentNode
     * @param action
     *
     * @return
     */
    public abstract boolean executeDrop(DefaultDNDTree tree, DefaultMutableTreeNode draggedNode, DefaultMutableTreeNode newParentNode, int action);
}

package org.joy.swing.tree;

//~--- JDK imports ------------------------------------------------------------

import java.awt.datatransfer.*;

import java.util.*;

import javax.swing.tree.*;

/**
 * Class description
 *
 *
 * @version    Enter version here..., 2008-11-12
 * @author     Enter your name here...
 */
public class TransferableNode implements Transferable {

    /** Field description */
    public static final DataFlavor NODE_FLAVOR = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType, "Node");

    /** Field description */
    private DataFlavor[] flavors = { NODE_FLAVOR };

    /** Field description */
    private DefaultMutableTreeNode node;

    /**
     * Constructs ...
     *
     *
     * @param nd
     */
    public TransferableNode(DefaultMutableTreeNode nd) {
        node = nd;
    }

    /**
     * Method description
     *
     *
     * @param flavor
     *
     * @return
     *
     * @throws UnsupportedFlavorException
     */
    public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (flavor == NODE_FLAVOR) {
            return node;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;
    }

    /**
     * Method description
     *
     *
     * @param flavor
     *
     * @return
     */
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return Arrays.asList(flavors).contains(flavor);
    }
}

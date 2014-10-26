package org.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;

/**
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public class DefaultMutableListModel extends DefaultListModel implements MutableListModel {

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public boolean isCellEditable(int index) {
        return true;
    }

    /**
     * Method description
     *
     *
     * @param value
     * @param index
     */
    public void setValueAt(Object value, int index) {
        super.setElementAt(value, index);
    }
}

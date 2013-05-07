package com.kvc.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.ListModel;

/**
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public interface MutableListModel extends ListModel {

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public boolean isCellEditable(int index);

    /**
     * Method description
     *
     *
     * @param value
     * @param index
     */
    public void setValueAt(Object value, int index);
}

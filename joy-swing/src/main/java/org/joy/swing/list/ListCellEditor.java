package org.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public interface ListCellEditor extends CellEditor {

    /**
     * Method description
     *
     *
     * @param list
     * @param value
     * @param isSelected
     * @param index
     *
     * @return
     */
    Component getListCellEditorComponent(JList list, Object value, boolean isSelected, int index);
}

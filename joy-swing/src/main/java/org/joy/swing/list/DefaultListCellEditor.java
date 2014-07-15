package org.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public class DefaultListCellEditor extends DefaultCellEditor implements ListCellEditor {

    /**
     * Constructs ...
     *
     *
     * @param checkBox
     */
    public DefaultListCellEditor(final JCheckBox checkBox) {
        super(checkBox);
    }

    /**
     * Constructs ...
     *
     *
     * @param comboBox
     */
    public DefaultListCellEditor(final JComboBox comboBox) {
        super(comboBox);
    }

    /**
     * Constructs ...
     *
     *
     * @param textField
     */
    public DefaultListCellEditor(final JTextField textField) {
        super(textField);
    }

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
    public Component getListCellEditorComponent(JList list, Object value, boolean isSelected, int index) {
        delegate.setValue(value);

        return editorComponent;
    }
}

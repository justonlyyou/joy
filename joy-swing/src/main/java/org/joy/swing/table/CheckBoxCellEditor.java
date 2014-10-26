package org.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

/**
 * 使表格CheckBox列可编辑
 * @author linju
 */
public class CheckBoxCellEditor extends DefaultCellEditor {

    /**
     * Constructs ...
     *
     *
     * @param checkBox
     */
    public CheckBoxCellEditor(JCheckBox checkBox) {
        super(checkBox);
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Method description
     *
     *
     * @param table
     * @param value
     * @param isSelected
     * @param row
     * @param column
     *
     * @return
     */
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ((JCheckBox) this.editorComponent).setSelected(((Boolean) value).booleanValue());

        return editorComponent;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Object getCellEditorValue() {
        return Boolean.valueOf(((JCheckBox) this.editorComponent).isSelected());
    }
}

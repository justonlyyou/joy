package org.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * 能够生成CheckBox列
 * @author linju
 */
public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    /**
     * Constructs ...
     *
     */
    public CheckBoxRenderer() {
        setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * Method description
     *
     *
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     *
     * @return
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        setSelected(((value != null) && ((Boolean) value).booleanValue()));

        return this;
    }
}

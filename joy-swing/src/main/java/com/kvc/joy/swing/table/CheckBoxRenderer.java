package com.kvc.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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

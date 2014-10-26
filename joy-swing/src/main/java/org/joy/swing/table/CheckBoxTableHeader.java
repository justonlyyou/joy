package org.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * 能为表格头部生成CheckBox显示方式
 */
class CheckBoxTableHeader extends JCheckBox implements TableCellRenderer {

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
        this.setSelected(true);
        this.setEnabled(false);

        JPanel panel = new JPanel();

        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panel.add(this);

        return panel;
    }
}

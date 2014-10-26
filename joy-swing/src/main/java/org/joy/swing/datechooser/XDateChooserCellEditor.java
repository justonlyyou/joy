package org.joy.swing.datechooser;

import com.toedter.calendar.JDateChooser;
import org.joy.swing.datechooser.XDateChooser;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.Date;

/**
 * @author Administrator
 */
public class XDateChooserCellEditor extends AbstractCellEditor implements
        TableCellEditor {

    public XDateChooserCellEditor() {
    }
    private JDateChooser dateChooser = new XDateChooser();

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        Date date = null;
        if (value instanceof Date) {
            date = (Date) value;
        }

        dateChooser.setDate(date);

        return dateChooser;
    }

    public Object getCellEditorValue() {
        return dateChooser.getDate();
    }
}
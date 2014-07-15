package org.joy.swing.datechooser;

import org.joy.swing.datechooser.XDateChooser;
import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

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
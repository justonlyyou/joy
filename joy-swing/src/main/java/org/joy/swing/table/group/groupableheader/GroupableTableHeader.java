/* (swing1.1beta3) */
package org.joy.swing.table.group.groupableheader;

import org.joy.swing.table.XTableHeader;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**  GroupableTableHeader
 * @version 1.0 10/20/98
 * @author Nobuo Tamemasa
 * @mender ZY
 * @see GroupableHeaderExample usage
 * @see JTableHeader
 */
public class GroupableTableHeader extends XTableHeader implements PropertyChangeListener, TableModelListener {

    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void tableChanged(TableModelEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
//
//    protected Vector<ColumnGroup> columnGroups = null;
//    private boolean tableChaned = false;
//
//    public GroupableTableHeader(TableColumnModel model) {
//        super(model);
//        setUI(new GroupableTableHeaderUI(this));
//        initListener();
//        setReorderingAllowed(false);
//    }
//
//    private void initListener() {
//        addPropertyChangeListener("UI", this);
//        addPropertyChangeListener("table", this);
//        getColumnModel().addColumnModelListener(new TableColumnModelListener() {
//
//            @Override
//            public void columnAdded(TableColumnModelEvent e) {
//                if (e.getToIndex() == table.getModel().getColumnCount() - 1 && tableChaned) {
//                    resetGroupColumn();
//                }
//            }
//
//            @Override
//            public void columnRemoved(TableColumnModelEvent e) {
//            }
//
//            @Override
//            public void columnMoved(TableColumnModelEvent e) {
//            }
//
//            @Override
//            public void columnMarginChanged(ChangeEvent e) {
//            }
//
//            @Override
//            public void columnSelectionChanged(ListSelectionEvent e) {
//            }
//        });
//    }
//
//    @Override
//    public void setReorderingAllowed(boolean b) {
//        reorderingAllowed = false;
//    }
//
//    public void addColumnGroup(ColumnGroup g) {
//        if (columnGroups == null) {
//            columnGroups = new Vector<ColumnGroup>();
//        }
//        columnGroups.addElement(g);
//    }
//
//    public void addColumnGroups(ColumnGroup... columnGroups) {
//        for (ColumnGroup columnGroup : columnGroups) {
//            addColumnGroup(columnGroup);
//        }
//    }
//
//    public void clearColumnGroup() {
//        if (columnGroups != null) {
//            columnGroups.clear();
//        }
//    }
//
//    public void removeGroupColumn(ColumnGroup... columnGroups) {
//        if (this.columnGroups != null) {
//            for (ColumnGroup removeColumn : columnGroups) {
//                this.columnGroups.remove(removeColumn);
//            }
//        }
//    }
//
//    public Enumeration getColumnGroups(TableColumn col) {
//        if (columnGroups == null) {
//            return null;
//        }
//        Enumeration enums = columnGroups.elements();
//        while (enums.hasMoreElements()) {
//            ColumnGroup cGroup = (ColumnGroup) enums.nextElement();
//            Vector v_ret = (Vector) cGroup.getColumnGroups(col, new Vector());
//            if (v_ret != null) {
//                return v_ret.elements();
//            }
//        }
//        return null;
//    }
//
//    public void setColumnMargin() {
//        if (columnGroups == null) {
//            return;
//        }
//        int columnMargin = getColumnModel().getColumnMargin();
//        Enumeration enums = columnGroups.elements();
//        while (enums.hasMoreElements()) {
//            ColumnGroup cGroup = (ColumnGroup) enums.nextElement();
//            cGroup.setColumnMargin(columnMargin);
//        }
//    }
//
//    @Override
//    protected void setUI(ComponentUI newUI) {
//        if (this.ui != newUI) {
//            super.setUI(new GroupableTableHeaderUI(this));
//            repaint();
//        }
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//        if (evt.getPropertyName().equals("UI")) {
//            if (!(evt.getNewValue() instanceof GroupableTableHeaderUI)) {
//                setUI(new GroupableTableHeaderUI(this));
//            }
//        } else {
//            JTable oldTable = (JTable) evt.getOldValue();
//            if (oldTable != null) {
//                oldTable.getModel().removeTableModelListener(this);
//            }
//            if (table != null) {
//                table.getModel().addTableModelListener(this);
//            }
//        }
//    }
//
//    private void resetGroupColumn() {
//        if (columnGroups != null) {
//            try {
//                for (ColumnGroup columnGroup : columnGroups) {
//                    resetColumn(columnGroup);
//                }
//            } catch (java.lang.IllegalArgumentException ex) {
//                System.out.println("not found");
//            }
//        }
//        tableChaned = false;
//    }
//
//    public void resetColumn(ColumnGroup columnGroup) {
//        List subColumn = columnGroup.getIncludeCGList();
//        for (int index = 0; index < subColumn.size(); index++) {
//            Object column = subColumn.get(index);
//            if (column instanceof TableColumn) {
//                subColumn.set(index, getTable().getColumn(((TableColumn) column).getIdentifier()));
//            } else if (column instanceof ColumnGroup) {
//                resetColumn((ColumnGroup) column);
//            }
//        }
//    }
//
//    @Override
//    public void tableChanged(TableModelEvent e) {
//        if (e.getColumn() == TableModelEvent.HEADER_ROW) {
//            tableChaned = true;
//        }
//    }
}


/* (swing1.1beta3) */
package com.kvc.joy.swing.table.group.groupableheader;

//~--- non-JDK imports --------------------------------------------------------
//~--- JDK imports ------------------------------------------------------------
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * ColumnGroup.
 * @author Nobuo Tamemasa
 * @version 1.0 10/20/98
 * @mender ZY
 */
public class ColumnGroup {

    /** Field description */
    protected int margin = 0;
    /** Field description */
    protected TableCellRenderer renderer;
    /** Field description */
    protected String text;
    /** Field description */
    protected Vector vector;

    /**
     * Constructs ...
     *
     *
     * @param text
     * @param columns
     */
    public ColumnGroup(String text, TableColumn... columns) {
        this(null, text);

        if (columns != null) {
            addColumn(columns);
        }
    }

    /**
     * Constructs ...
     *
     *
     * @param renderer
     * @param text
     */
    public ColumnGroup(TableCellRenderer renderer, String text) {
        if (renderer == null) {

//          this.renderer = new DefaultTableCellRenderer() {
//
//              public Component getTableCellRendererComponent(JTable table, Object value,
//                      boolean isSelected, boolean hasFocus, int row, int column) {
//                  JTableHeader header = table.getTableHeader();
//                  if (header != null) {
//                      setForeground(header.getForeground());
//                      setBackground(header.getBackground());
//                      setFont(header.getFont());
//                  }
//                  setHorizontalAlignment(JLabel.CENTER);
//                  setText((value == null) ? "" : value.toString());
//                  setBorder(UIManager.getBorder("TableHeader.cellBorder"));
//                  return this;
//              }
//          };
        } else {
            this.renderer = renderer;
        }

        this.text = text;
        vector = new Vector();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public java.util.List getIncludeCGList() {
        return vector;
    }

    /**
     * param obj    TableColumn or ColumnGroup
     *
     * @param obj
     */
    private void add(Object obj) {
        if (obj != null) {
            vector.addElement(obj);
        }
    }

    /**
     * 添加组包含的列头组
     * @param columnGroups 要包含的其他列头组
     */
    public void addColumnGroup(ColumnGroup... columnGroups) {
        for (ColumnGroup columnGroup : columnGroups) {
            add(columnGroup);
        }
    }

    /**
     * 添加要包含的列
     * @param columns 要包含的列
     */
    public void addColumn(TableColumn... columns) {
        for (TableColumn column : columns) {
            add(column);
        }
    }

    /**
     * 删除列
     * @param columns 要包含的列
     */
    public void removeColumn(TableColumn column) {
        boolean ok = vector.remove(column);
        if (!ok) {
            for (Object obj : vector) {
                if (obj instanceof ColumnGroup) {
                    ((ColumnGroup) obj).removeColumn(column);
                }
            }
        }
    }

    /**
     * param c    TableColumn
     * param vector    ColumnGroups
     *
     * @param c
     * @param g
     *
     * @return
     */
    public Vector getColumnGroups(TableColumn c, Vector g) {
        g.addElement(this);

        if (isColumnInVector(c)) {
            return g;
        }

        Enumeration enums = vector.elements();
        while (enums.hasMoreElements()) {
            Object obj = enums.nextElement();
            if (obj instanceof ColumnGroup) {
                Vector groups = (Vector) ((ColumnGroup) obj).getColumnGroups(c, (Vector) g.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    /**
     * Method description
     *
     *
     * @param renderer
     */
    public void setHeaderRenderer(TableCellRenderer renderer) {
        if (renderer != null) {
            this.renderer = renderer;
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Object getHeaderValue() {
        return text;
    }

    /**
     * 获得一个组的最大的宽度
     * @return
     */
    public int getWidth() {
        int width = 0;
        Enumeration enums = vector.elements();
        while (enums.hasMoreElements()) {
            Object obj = enums.nextElement();
            if (obj instanceof TableColumn) {
                TableColumn aColumn = (TableColumn) obj;
                width += aColumn.getWidth();
                width += margin;

            } else {
                width += ((ColumnGroup) obj).getWidth();
            }
        }
        return width;
    }

    /**
     * 这个组是否包含某列
     * @param column
     * @return
     */
    public boolean containColumn(TableColumn column) {
        boolean ok = isColumnInVector(column);
        if (!ok) {
            for (Object obj : vector) {
                if (obj instanceof ColumnGroup) {
                    return ((ColumnGroup) obj).containColumn(column);
                }
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean isColumnInVector(TableColumn column) {
        for (Object object : vector) {
            if (column.equals(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method description
     *
     *
     * @param margin
     */
    public void setColumnMargin(int margin) {
        this.margin = margin;
        Enumeration enums = vector.elements();
        while (enums.hasMoreElements()) {
            Object obj = enums.nextElement();

            if (obj instanceof ColumnGroup) {
                ((ColumnGroup) obj).setColumnMargin(margin);
            }

        }
    }

    /**
     * 清除列组信息
     */
    public void clearCG() {
        if (vector != null) {
            vector.clear();
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String toString() {
        return text;
    }
}

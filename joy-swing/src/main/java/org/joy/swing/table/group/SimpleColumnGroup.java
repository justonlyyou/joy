package org.joy.swing.table.group;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/**
 * 简化了的列组信息封装
 * @mender ckcs
 */
public class SimpleColumnGroup {

    //渲染器
    protected TableCellRenderer renderer;
    //组的名字
    protected String headValue;
    //列组列表
    protected Vector vector;

    /**
     * @param headValue
     * @param columns
     */
    public SimpleColumnGroup(String text, TableColumn... columns) {
        this.headValue = text;
        vector = new Vector();
        if (columns != null) {
            addColumn(columns);
        }
    }

    /**
     * 包含组和列信息
     * @return
     */
    public List getIncludeCGList() {
        return vector;
    }

    /**
     * 添加组包含的列头组
     * @param columnGroups 要包含的其他列头组
     */
    public void addColumnGroup(SimpleColumnGroup... columnGroups) {
        for (SimpleColumnGroup columnGroup : columnGroups) {
            vector.add(columnGroup);
        }
    }

    /**
     * 添加要包含的列,记录列索引
     * 列的模型索引 <==> 组
     * @param columns 要包含的列
     */
    public void addColumn(TableColumn... columns) {
        for (TableColumn column : columns) {
            vector.add(new GroupInfo(column.getModelIndex()));
        }
    }

    /**
     * 获得列所在的直接和间接的组
     * @param column 列
     * @param group 组
     * @return
     */
    public Vector getColumnGroups(TableColumn column, Vector group) {
        group.addElement(this);
        if (isColumnInVector(column.getModelIndex())) {
            return group;
        }
        Enumeration enums = vector.elements();
        while (enums.hasMoreElements()) {
            Object obj = enums.nextElement();
            if (obj instanceof SimpleColumnGroup) {
                Vector groups = (Vector) ((SimpleColumnGroup) obj).getColumnGroups(column, (Vector) group.clone());
                if (groups != null) {
                    return groups;
                }
            }
        }

        return null;
    }

    /**
     * 获得表格渲染器
     * @return
     */
    public TableCellRenderer getHeaderRenderer() {
        return renderer;
    }

    /**
     * 设置表格渲染器
     * @param renderer
     */
    public void setHeaderRenderer(TableCellRenderer renderer) {
        this.renderer = renderer;
        //同时设置子组
        for (Object object : vector) {
            if(object instanceof SimpleColumnGroup){
                ((SimpleColumnGroup)object).setHeaderRenderer(renderer);
            }
        }
    }

    /**
     * 获得组的名字
     * @return 组的名字
     */
    public String getHeaderValue() {
        return headValue;
    }

    /**
     * 获得某组某个组中直接和间接包含的列
     * @param indexList 显示的列的模型索引
     */
    public void getInculdeColumnModelIndex(List<Integer> indexList) {
        Enumeration enums = vector.elements();
        while (enums.hasMoreElements()) {
            Object obj = enums.nextElement();
            if (obj instanceof GroupInfo) {
                GroupInfo aColumn = (GroupInfo) obj;
                indexList.add(aColumn.getModelIndex());

            } else {
                ((SimpleColumnGroup) obj).getInculdeColumnModelIndex(indexList);
            }
        }
    }

    /**
     * 这个组是否包含某列
     * 包括间接包含
     * @param column
     * @return
     */
    public boolean containColumn(TableColumn column) {
        boolean ok = isColumnInVector(column.getModelIndex());
        if (!ok) {
            for (Object obj : vector) {
                if (obj instanceof SimpleColumnGroup) {
                    return ((SimpleColumnGroup) obj).containColumn(column);
                }
            }
            return false;

        } else {
            return true;
        }
    }

    /**
     * 列是否直接包含在本组中
     * @param modelIndex
     * @return
     */
    private boolean isColumnInVector(int modelIndex) {
        for (Object object : vector) {
            if (object instanceof GroupInfo) {
                if (((GroupInfo) object).getModelIndex() == modelIndex) {
                    return true;
                }
            }
        }
        return false;
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
        return headValue;
    }

    /**
     * 组信息
     */
    public class GroupInfo {

        //模型索引
        private int modelIndex;

        public GroupInfo(int modelIndex) {
            this.modelIndex = modelIndex;
        }

        public int getModelIndex() {
            return modelIndex;
        }

        public void setModelIndex(int modelIndex) {
            this.modelIndex = modelIndex;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final GroupInfo other = (GroupInfo) obj;
            if (this.modelIndex != other.modelIndex) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + this.modelIndex;
            return hash;
        }
    }
}

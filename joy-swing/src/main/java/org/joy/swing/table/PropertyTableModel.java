package org.joy.swing.table;

//~--- non-JDK imports --------------------------------------------------------

//~--- JDK imports ------------------------------------------------------------

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.reflect.FieldUtils;

import org.joy.commons.lang.ClassTool;
import org.joy.commons.lang.ReflectionTool;

/**
 * 基于行的表数据模型
 * @param <T> 存放的对象类型
 */
@Deprecated
public class PropertyTableModel<T> extends AbstractTableModel {

//  //显示在ComboBox中的数值在TableModel中的列序号
//  private int selectedValueInTableColumnIndex;
    // 存放所有的行数据；一行就是一个对象。
    /** Field description */
    protected Vector<T> dataVector = new Vector<T>();

    // 需要编辑的列的索引
    /** Field description */
    private int[] editColumnIndex = null;

    // Store all properties that need to be displayed in Table;
    /** Field description */
    private String[] propertiesName = null;

    // 列头现实名称
    /** Field description */
    private String[] columnNames;

    // Keep all mthods that obtain value from each row object.
    /** Field description */
    private Method[] objectPropertyMethod;

    /**
     * Constructs ...
     *
     */
    public PropertyTableModel() {
    }

    /**
     * 构造器
     * @param data 包含有行对象的数据集合.每一个数据对象在模型中就代表一行.允许这个参数为null或者空集合
     * @param propertiesName 表数据模型中每个列要展现的对象属性集合。
     * @param columnNames 要在列头中显示的名字
     */
    public PropertyTableModel(Collection<T> data, String[] propertiesName, String[] columnNames) {
        this.propertiesName = propertiesName;
        this.columnNames = columnNames;

        Class objectClass = null;

        if ((data != null) && !data.isEmpty()) {
            objectClass = data.iterator().next().getClass();
            dataVector.addAll(data);
        }
        //class 为null 的时候调用 会报空指针
        if (objectClass != null) {
            objectPropertyMethod = ReflectionTool.getPropertyMethods(objectClass, propertiesName);
        }
    }

    /**
     * 构造器
     *
     * @param data
     * @param propertiesName 表数据模型中每个列要展现的对象属性集合。
     * @param columnNames 要在列头中显示的名字
     * @param editColumnIndex 表数据模型中需要可编辑的列的索引
     */
    public PropertyTableModel(Collection<T> data, String[] propertiesName, String[] columnNames, int[] editColumnIndex) {
        this(data, propertiesName, columnNames);
        this.editColumnIndex = editColumnIndex;
    }

    /**
     * Get the tableModel' ColumnCount
     * @return the ColumnCount of the tableModel
     */
    @Override
    public int getColumnCount() {
        return getPropertiesName().length;
    }

    /**
     * Get the tableModel' RowCount
     * @return the number of rows in the model
     */
    @Override
    public int getRowCount() {
        return dataVector.size();
    }

    /**
     * Get the tableModel' ColumnNane
     * @return the ColumnNane of the tableModel
     * @param columnIndex 在模型中列的列号
     */
    @Override
    public String getColumnName(int columnIndex) {
        String columnName = "Unnamed";

        if (columnIndex >= getColumnNames().length) {
            System.err.println("BUG : you miss the column name for your RowObjectTableModel.Index =" + columnIndex);
        } else {
            columnName = getColumnNames()[columnIndex];
        }

        return columnName;
    }

    /**
     * Get the value
     *
     * @param rowIndex
     * @param columnIndex
     * @return the value when select
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object bean = this.dataVector.get(rowIndex);
        //初始化不一定会有数据,所以这时候再取
        if (objectPropertyMethod == null) {
            objectPropertyMethod = ReflectionTool.getPropertyMethods(bean.getClass(), propertiesName);
        }
        Method getValueMethod = this.objectPropertyMethod[columnIndex];

        return ReflectionTool.getMethodValue(bean, getValueMethod);
    }

    /**
     * Get the columnIndex
     *
     * @param columnIndex
     * @return the columnIndex of the tableModel
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        Class columnClass = Object.class;

        if (dataVector.size() > 0) {
            Object bean = this.dataVector.get(0);
            columnClass = ReflectionTool.getPropertyType(bean.getClass(), getPropertiesName()[columnIndex]);

            if ((columnClass != null) && columnClass.isPrimitive()) {
                columnClass = convertClass(columnClass);
            }
        }

        return columnClass;
    }

    /**
     * Method description
     *
     *
     * @param returnClass
     *
     * @return
     */
    private Class convertClass(Class returnClass) {
        Class objectClass = null;
        String primitiveClassName = returnClass.getName();

        try {
            if (!primitiveClassName.equals("int")) {
                String upperCaseBegin = String.valueOf(Character.toUpperCase(primitiveClassName.charAt(0)));

                objectClass = Class.forName("java.lang." + upperCaseBegin + primitiveClassName.substring(1));
            } else {
                objectClass = Integer.class;
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return objectClass;
    }

    /**
     * 获取行对象
     * @param rowIndex 列的号数
     * @return 当前的行对象
     */
    public T getRowObject(int rowIndex) {
        return dataVector.get(rowIndex);
    }

    /**
     * Set if the tableModel' cellrow is edit
     * @param    rowIndex        the row whose value to be queried
     * @param    columnIndex     the column whose value to be queried
     * @return   true if the cell is editable
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean editable = false;

        if ((editColumnIndex != null) && (editColumnIndex.length > 0) && (Arrays.binarySearch(editColumnIndex, columnIndex) >= 0)) {
            editable = true;
        }

        return editable;
    }

    /**
     * Return the row count
     *
     * @return
     */
    public int getSize() {
        return getRowCount();
    }

    /**
     * Method description
     *
     *
     * @param object
     */
    public void addRow(T object) {
        int addIndex = dataVector.size();

        dataVector.add(object);
        fireTableRowsInserted(addIndex, addIndex);
    }

    /**
     * 插入一行对象
     * @param rowIndex 插入的行索引
     * @param object 插入的对象
     */
    public void insertRow(int rowIndex, T object) {
        dataVector.insertElementAt(object, rowIndex);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    /**
     * JTable里添加对象的集合
     *
     * @param objectCollection
     */
    public void addRows(Collection<T> objectCollection) {
        int addIndex = dataVector.size();

        if (dataVector.addAll(objectCollection)) {
            fireTableRowsInserted(addIndex, getRowCount() - 1);
        }
    }

    /**
     * removeRow删除一行对象
     *
     * @param object
     */
    public void removeRow(T object) {
        int row = dataVector.indexOf(object);

        if (row > -1) {
            dataVector.remove(object);
            fireTableRowsDeleted(row, row);
        }
    }

    /**
     * removeRow删除一行对象
     * @param rowIndex 要删除的行索引
     */
    public void removeRow(int rowIndex) {
        if (rowIndex > -1) {
            dataVector.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

//  public void removeAll(){
////      int size = dataVector.size();
//      for (Iterator it = dataVector.iterator(); it.hasNext();) {
//          Object elem = (Object) it.next();
//          removeRow(elem);
//      }
////      if(size>0){
////          for(int i=0;i<size;i++){
////              dataVector.remove(i);
////              fireTableRowsDeleted(i,i);
////          }
////
////      }
//
//  }
    /**
     * Method description
     *
     *
     * @param data
     */
    public void setDataVector(Collection<T> data) {
        dataVector.clear();

        if ((data != null) && (data.size() > 0)) {
            dataVector.addAll(data);

            Class objectClass = data.iterator().next().getClass();

            objectPropertyMethod = ReflectionTool.getPropertyMethods(objectClass, propertiesName);
        }

        fireTableDataChanged();
    }

    /**
     * Method description
     *
     */
    public void clearData() {
        dataVector.clear();
        fireTableDataChanged();
    }

    /**
     * @deprecated Use getDataVector
     * @return
     */
    public List getDataList() {
        List data = new ArrayList();

        data.addAll(dataVector);

        return data;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Vector<T> getDataVector() {
        return dataVector;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String[] getColumnNames() {
        return columnNames;
    }

    /**
     * Method description
     *
     *
     * @param columnNames
     */
    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String[] getPropertiesName() {
        return propertiesName;
    }

    /**
     * Method description
     *
     *
     * @param propertiesName
     * @param objectClass
     */
    public void setPropertiesName(String[] propertiesName, Class objectClass) {
        this.propertiesName = propertiesName;

        // Class objectClass=dataVector.firstElement().getClass();
        objectPropertyMethod = ReflectionTool.getPropertyMethods(objectClass, propertiesName);
        fireTableStructureChanged();
    }

    /**
     * Method description
     *
     *
     * @param editColumnIndex
     */
    public void setEditColumnIndex(int[] editColumnIndex) {
        this.editColumnIndex = editColumnIndex;
    }
}

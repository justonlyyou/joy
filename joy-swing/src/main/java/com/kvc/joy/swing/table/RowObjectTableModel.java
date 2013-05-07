package com.kvc.joy.swing.table;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.kvc.joy.commons.lang.string.StringTool;

/**
 * 基于行对象的表格模型 <br>
 */
public class RowObjectTableModel<E>
        extends AbstractTableModel
        implements Iterable<E> {

    /**
     * 列的数据类型名数据
     */
    protected Class[] cloumnClass;
    /**
     * 列名数组
     */
    protected String[] columns;
    /**
     * 行对象Vector
     */
    protected Vector<E> dataVector;
    /**
     * 可以编辑的列数组
     */
    protected int[] editableColumnIndexes;
    /**
     * 列对应的get方法数组
     */
    protected Method[] getMethods;
    /**
     * 列对应的get方法名数组
     */
    protected String[] getterMethodNames;
    /**
     * 列对应的set方法名数组
     */
    protected String[] setterMethodNames;
    /**
     * 列对应的set方法数组
     */
    protected Method[] setMethods;

    /**
     * Constructs ...
     *
     */
    public RowObjectTableModel() {
        this(new String[]{});
    }

    /**
     * 构造函数
     * @param columns 列名
     */
    public RowObjectTableModel(String... columns) {
        int columnCount = (columns == null ? 0 : columns.length);
        dataVector = new Vector<E>(columnCount);
        this.columns = columns;
        cloumnClass = new Class[columns.length];
    }

//    /**
//     * Constructs ...
//     *
//     *
//     * @param columns
//     * @param editableColumnIndexes
//     */
//    public RowObjectTableModel(String[] columns, int... editColumnIndex) {
//        this(columns);
//        this.editableColumnIndexes = editColumnIndex;
//    }
//    /**
//     * 构造函数
//     * @param columns 列名
//     * @param getMethodName 取得属性的方法名
//     */
//    public RowObjectTableModel(String[] columns, String[] getMethodName) {
//        this(columns);
//        this.getterMethodNames = getMethodName;
//    }
    /**
     * 构造函数
     * @param columns  列名
     * @param getterMethodNames get的方法名
     * @param editableColumnIndexes  可编辑的列
     */
    public RowObjectTableModel(String[] columns, String[] getterMethodNames) {
        this(columns, null, null);
    }

    /**
     * 构造方法
     * @param columns 列名
     * @param getterMethodNames get的方法名
     * @param setterMethodNames set的方法名
     * @param editableColumnIndexes  可编辑的列
     */
    public RowObjectTableModel(String[] columns, String[] getterMethodNames, String[] setterMethodNames) {
        this(columns);
        this.getterMethodNames = getterMethodNames;
        this.setterMethodNames = setterMethodNames;
        getEditColumnIndex(setterMethodNames);
    }

    private void getEditColumnIndex(String[] setterMethodNames) {
        if (setterMethodNames != null && setterMethodNames.length != 0) {
            List<Integer> editColumnIndexList = new ArrayList<Integer>();
            for (int i = 0; i < setterMethodNames.length; i++) {
                String setter = setterMethodNames[i];
                if (StringTool.isBlank(setter) == false) {
                    editColumnIndexList.add(i);
                }
            }
            editableColumnIndexes = new int[editColumnIndexList.size()];
            for (int i = 0; i < editableColumnIndexes.length; i++) {
                editableColumnIndexes[i] = editColumnIndexList.get(i);
            }
        }
    }

    /**
     *  设置TableModel的信息
     * @param columns
     * @param getterMethodNames
     * @param setterMethodNames
     * @param editableColumnIndexes
     */
    public void setCustomTableModelInfo(String[] columns, String[] getterMethodNames, String[] setterMethodNames) {
        this.columns = columns;
        this.getterMethodNames = getterMethodNames;
        this.setterMethodNames = setterMethodNames;
        getEditColumnIndex(setterMethodNames);
        setColumnIdentifiers();
    }

    /**
     * 得到列头
     * @return String[]
     */
    public String[] getColumns() {
        return columns;
    }

    /**
     * getRowCount()得到行数
     * @return int
     */
    @Override
    public int getRowCount() {
        return dataVector.size();
    }

    /**
     * getColumnCount()得到列数
     * @return int
     */
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    /**
     * getValueAt  返回 row 和 column 位置的单元格值
     * 利用反射机制得到传入对象的属性值
     *
     * @return Object
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (dataVector.size() != 0) {
            Object value = dataVector.get(rowIndex);

            try {
                Method getMethod = getMethod(value, columnIndex);

                if (getMethod != null) {
                    return getMethod.invoke(value);
                }
            } catch (Exception ex) {

                // //can't find method
                System.err.println("can't find value");

                return null;
            }
        }

        return null;
    }

    /**
     *  重写setValueAt()方法，将value值通过反射，将值设入到表模型中 row 和 column 位置的单元格值
     *
     * @param value
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Object object = (Object) dataVector.get(rowIndex);
        Method columnSetmethod = null;
        if (setMethods == null) {
            setMethods = new Method[getColumnCount()];
        }
        columnSetmethod = setMethods[columnIndex];
        if (columnSetmethod == null) {
            Class rowClass = object.getClass();
            Method[] methods = rowClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(setterMethodNames[columnIndex]) && (method.getParameterTypes() != null) && (method.getParameterTypes().length == 1)) {
                    columnSetmethod = method;
                    setMethods[columnIndex] = columnSetmethod;

                    break;
                }
            }
        }
        if (columnSetmethod != null) {
            try {
                columnSetmethod.invoke(object, value);
                fireTableCellUpdated(rowIndex, columnIndex);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO
//                Logger logger = CommonUtility.getCommonUtil().getLogger(CommonUtility.UTILITY_MODULE_NAME);
//                logger.log(Level.WARNING, "DefaultCustomTableModel setValueAt Error:", e);
            }
        } else {
            //TODO
            System.err.println("Can't find method :" + setterMethodNames[columnIndex] + " for class " + object.getClass().getName());
        }
    }

    private Method getMethod(Object cellValue, int columnIndex) {
        if (getterMethodNames == null) {
            return null;
        }
        Method method = null;
        if (getMethods == null) {
            getMethods = new Method[getterMethodNames.length];
        }
        method = getMethods[columnIndex];
        if (method == null && cellValue.getClass() != null) {
            try {
                method = cellValue.getClass().getMethod(getterMethodNames[columnIndex]);
            } catch (Exception ex) {
                ////can't find method
//                System.out.println("can't find method with column " + columnIndex);
                }
            getMethods[columnIndex] = method;
        }

        return method;
    }

    /**
     * addRow添加一行对象
     * @param object
     */
    public void addRow(E object) {
        int addIndex = dataVector.size();
        dataVector.add(object);
        fireTableRowsInserted(addIndex, addIndex);
    }

    /**
     * removeRow删除一行对象
     * @param object
     */
    public void removeRow(E object) {
        int index = dataVector.indexOf(object);
        if (index >= 0) {
            dataVector.remove(object);
            fireTableRowsDeleted(index, index);
        }
    }

    /**
     * JTable里添加对象的集合
     * @param objectCollection
     */
    public void addRows(Collection<? extends E> objectCollection) {
        dataVector.addAll(objectCollection);
        fireTableDataChanged();
    }

    /**
     * JTable里删除对象的集合
     * @param objectCollection
     */
    public void removeRows(Collection objectCollection) {
        dataVector.removeAll(objectCollection);
        fireTableDataChanged();
    }

    /**
     * 替换模型中的列标识符
     */
    public void setColumnIdentifiers() {
        fireTableStructureChanged();
    }

    /**
     * 可编辑的列
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if ((editableColumnIndexes != null) && (editableColumnIndexes.length >= 0) && (Arrays.binarySearch(editableColumnIndexes, column) >= 0)) {
            return true;
        }

        return false;
    }

    /**
     * 得到指定行的对象
     * @param row
     * @return
     */
    public E getObject(int row) {
        return dataVector.get(row);
    }

    /**
     * 取得指定行对象在模型中的索引
     * @param object 查找的行对象
     * @return 返回索引
     */
    public int rowIndexOf(Object object) {
        return dataVector.indexOf(object);
    }

    /**
     * 显示列头
     *
     * @return String
     * @param col
     */
    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    /**
     *  获得集合
     * @return
     */
    public Vector<E> getAllData() {
        return dataVector;
    }

    /**
     *  以Set的形式获得表中所有的数据
     * @return
     */
    public Set<E> getAllDataSet() {
        return new HashSet<E>(dataVector);
    }

    /**
     *  用新的行 Vector（dataVector）替换当前的 dataVector 实例变量
     * @param objectCollection
     */
    public void setDataVector(Collection<E> objectCollection) {
        if (dataVector.isEmpty() == false) {
            fireTableRowsDeleted(0, dataVector.size() - 1);
            dataVector.clear();
        }
        if (objectCollection != null) {
            dataVector.addAll(objectCollection);
            if (dataVector.isEmpty() == false) {
                fireTableRowsInserted(0, dataVector.size() - 1);
            }
        }
//        XTable table = null;
//        TableModelListener[] tableModelListeners = getTableModelListeners();
//        for (TableModelListener tableModelListener : tableModelListeners) {
//            if (tableModelListener instanceof XTable) {
//                table = (XTable) tableModelListener;
//                break;
//            }
//        }
//        if (table != null) {
//            table.adjustTableColumnWidths();
//        }
    }

    /**
     * 返回 被查询的列的return class
     * @param columnIndex
     * @return 返回该列的类型
     */
    @Override
    public Class getColumnClass(int columnIndex) {
        Class returnClass = cloumnClass[columnIndex];

        if (returnClass != null) {
            return returnClass;
        }

        if (dataVector.size() == 0) {
            return super.getColumnClass(columnIndex);
        }

        Object value = getValueAt(0, columnIndex);

        if (value == null) {
            Method method = getMethod(dataVector.get(0), columnIndex);

            if (method != null) {
                try {
                    returnClass = method.getReturnType();

                    if (returnClass.isPrimitive()) {
                        returnClass = convertClass(returnClass);
                    }
                } catch (Exception ex) {

                    // can't find class
                    System.out.println("Can't find Class of column: " + columnIndex);
                }
            }
        } else {
            returnClass = value.getClass();
        }

        if (returnClass == null) {
            returnClass = super.getColumnClass(columnIndex);
        }

        cloumnClass[columnIndex] = returnClass;

        return returnClass;
    }

    /**
     * Method description
     *
     *
     * @param returnClass
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    private Class convertClass(Class returnClass) throws ClassNotFoundException {
        String primitiveClassName = returnClass.getName();

        if (!primitiveClassName.equals("int")) {
            String upperCaseBegin = String.valueOf(Character.toUpperCase(primitiveClassName.charAt(0)));

            return Class.forName("java.lang." + upperCaseBegin + primitiveClassName.substring(1));
        } else {
            return Integer.class;
        }

//      if (returnClass == int.class) {
//          returnClass = Integer.class;
//      } else if (returnClass == short.class) {
//          returnClass = Short.class;
//      } else if (returnClass == long.class) {
//          returnClass = Long.class;
//      } else if (returnClass == boolean.class) {
//          returnClass = Boolean.class;
//      } else if (returnClass == double.class) {
//          returnClass = Double.class;
//      } else if (returnClass == float.class) {
//          returnClass = Float.class;
//      }
    }

    /**
     * 添加一个对象到指定的行
     *
     * @param object 添加的行对象
     * @param rowIndex 行索引
     */
    public void addObjectToRow(E object, int rowIndex) {
        dataVector.insertElementAt(object, rowIndex);
        fireTableRowsInserted(rowIndex, rowIndex);
    }

    /**
     * Method description
     *
     *
     * @param objectCollection
     * @param rowIndex
     */
    public void insertObjectsAt(Collection<E> objectCollection, int rowIndex) {
        dataVector.addAll(rowIndex, objectCollection);
        fireTableDataChanged();
    }

    /**
     *  清空Model里的数据
     */
    public void clear() {
        dataVector.clear();
        this.fireTableDataChanged();
    }

    /**
     *
     * @param getterMethodNames
     */
    public void setGetterMethodNames(String[] getterMethodNames) {
        this.getterMethodNames = getterMethodNames;
    }

    /**
     *
     * @param setterMethodNames
     */
    public void setSetterMethodNames(String[] setterMethodNames) {
        this.setterMethodNames = setterMethodNames;
    }

    /**
     * 定制显示的列名
     * @param columns 显示的列名
     * @param getMethod 取得属性的方法
     */
    public void setShowingColumnName(String[] columns, String[] getMethod) {
        this.columns = columns;
        this.getterMethodNames = getMethod;
        this.fireTableStructureChanged();
    }

    /**
     * Method description
     *
     *
     * @param columnName
     * @param columnIndex
     */
    public void changeColumnName(String columnName, int columnIndex) {
        this.columns[columnIndex] = columnName;
    }

    /**
     *
     * @param row 删除的行索引
     */
    public void removeRow(int row) {
        this.dataVector.remove(row);
        fireTableRowsDeleted(row, row);
    }

    /**
     * Method description
     *
     *
     * @param rows
     */
    public void removeRows(int[] rows) {
        int rowCount = rows.length;

        for (int row = rowCount - 1; row >= 0; row--) {
            dataVector.remove(rows[row]);
        }

        fireTableRowsDeleted(rows[0], rows[rowCount - 1]);
    }

    /**
     * Method description
     *
     *
     * @param rowIndex
     * @param object
     */
    public void setObject(int rowIndex, E object) {
        this.dataVector.set(rowIndex, object);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return dataVector.iterator();
    }
}

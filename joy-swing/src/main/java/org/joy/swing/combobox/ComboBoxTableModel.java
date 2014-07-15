package org.joy.swing.combobox;

import java.util.*;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.*;
import org.joy.swing.table.PropertyTableModel;

/**
 * 一个ComboBox和Table模型的结合体
 * @author Roland
 */
public class ComboBoxTableModel extends PropertyTableModel implements MutableComboBoxModel {

    /** Field description */
    private Object selectedObject;

    /**
     * 构造函数
     * @param propertiesName 要显示对象类型中的属性名字
     * @param dataList 包含有统一对象类型的List
     * @param columnNames 显示在表头中的文体
     */
    public ComboBoxTableModel(Collection dataList, String[] propertiesName, String[] columnNames) {
        super(dataList, propertiesName, columnNames);
    }

    /**
     * {@link javax.swing.MutableComboBoxModel }
     * @param obj 新增的行对象。这个对象的类型必须是表中对象的类型或者是它的子类。
     */
    @Override
    public void addElement(Object obj) {
        addRow(obj);
        fireChanged(this, dataVector.size() - 1, dataVector.size() - 1);

        // ///fire item state changed if select an item////////////////
//      if ( dataVector.size() == 1 && selectedObject == null && obj != null ) {
//          setSelectedItem( obj );
//      }
        // /////////////////////////////////////////
    }

    /**
     * {@link javax.swing.MutableComboBoxModel }
     *
     * @param obj
     */
    @Override
    public void removeElement(Object obj) {
        int index = dataVector.indexOf(obj);

        if (index > -1) {
            removeRow(obj);
            fireChanged(this, index, index);
        }
    }

    /**
     * {@link javax.swing.MutableComboBoxModel }
     *
     * @param obj
     * @param index
     */
    @Override
    public void insertElementAt(Object obj, int index) {
        super.insertRow(index, obj);
        fireChanged(this, index, index);
    }

    /**
     * {@link javax.swing.MutableComboBoxModel }
     *
     * @param index
     */
    @Override
    public void removeElementAt(int index) {
        super.removeRow(index);
        fireChanged(this, index, index);
    }

    /**
     * {@link javax.swing.DefaultComboBoxModel}
     *
     * @param anObject
     */
    @Override
    public void setSelectedItem(Object anObject) {
        if (((selectedObject != null) &&!selectedObject.equals(anObject)) || ((selectedObject == null) && (anObject != null))) {
            selectedObject = anObject;
            fireChanged(this, -1, -1);
        }
    }

    /**
     * {@link javax.swing.DefaultComboBoxModel}
     *
     * @return
     */
    @Override
    public Object getSelectedItem() {
        return selectedObject;
    }

    /**
     * {@link javax.swing.DefaultComboBoxModel}
     *
     * @param index
     *
     * @return
     */
    @Override
    public Object getElementAt(int index) {
        return super.getRowObject(index);
    }

    /**
     * {@link javax.swing.DefaultComboBoxModel}
     *
     * @param l
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        listenerList.add(ListDataListener.class, l);
    }

    /**
     * {@link javax.swing.DefaultComboBoxModel}
     *
     * @param l
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        listenerList.remove(ListDataListener.class, l);
    }

    /**
     * Method description
     *
     *
     * @param source
     * @param index0
     * @param index1
     */
    protected void fireChanged(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (e == null) {
                    e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }

                ((ListDataListener) listeners[i + 1]).contentsChanged(e);
            }
        }
    }
}

package com.kvc.joy.swing.combobox;


//~--- JDK imports ------------------------------------------------------------

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author zhengpeng
 */
public class CustomComboBoxModel extends AbstractListModel implements MutableComboBoxModel {

    /** Field description */
    private Vector objects;

    /** Field description */
    private Object selectedObject;

    /** Creates a new instance of CustomComboBoxModel */
    public CustomComboBoxModel() {
        objects = new Vector();
    }

    /**
     * Constructs ...
     *
     *
     * @param l
     */
    public CustomComboBoxModel(List<?> l) {
        Vector vector = new Vector(20);

        vector.addAll(l);
        objects = vector;

        if (getSize() > 0) {
            selectedObject = getElementAt(0);
        }
    }

    /**
     * Constructs ...
     *
     *
     * @param v
     */
    public CustomComboBoxModel(Vector<?> v) {
        objects = v;

        if (getSize() > 0) {
            selectedObject = getElementAt(0);
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getSize() {
        return objects.size();
    }

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public Object getElementAt(int index) {
        if ((index >= 0) && (index < objects.size())) {
            return objects.elementAt(index);
        } else {
            return null;
        }
    }

    /**
     * Method description
     *
     *
     * @param anObject
     */
    public void addElement(Object anObject) {
        objects.addElement(anObject);
        fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);

        if ((objects.size() == 1) && (selectedObject == null) && (anObject != null)) {
            setSelectedItem(anObject);
        }
    }

    /**
     * Method description
     *
     *
     * @param anObject
     */
    public void removeElement(Object anObject) {
        int index = objects.indexOf(anObject);

        if (index != -1) {
            removeElementAt(index);
        }
    }

    /**
     * 获得Model里的数据
     *
     * @return
     */
    public Vector getAllData() {
        return objects;
    }

    /**
     * Method description
     *
     *
     * @param anObject
     * @param index
     */
    public void insertElementAt(Object anObject, int index) {
        objects.insertElementAt(anObject, index);
        fireIntervalAdded(this, index, index);
    }

    /**
     * Method description
     *
     *
     * @param index
     */
    public void removeElementAt(int index) {
        if (getElementAt(index) == selectedObject) {
            if (index == 0) {
                setSelectedItem((getSize() == 1) ? null : getElementAt(index + 1));
            } else {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        objects.removeElementAt(index);
        fireIntervalRemoved(this, index, index);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Object getSelectedItem() {
        return selectedObject;
    }

    /**
     * Method description
     *
     *
     * @param anObject
     */
    public void setSelectedItem(Object anObject) {
        if (((selectedObject != null) &&!selectedObject.equals(anObject)) || ((selectedObject == null) && (anObject != null))) {
            selectedObject = anObject;
            fireContentsChanged(this, -1, -1);
        }
    }

    /**
     * Method description
     *
     */
    public void removeAllElements() {
        if (objects.size() > 0) {
            int firstIndex = 0;
            int lastIndex = objects.size() - 1;

            objects.removeAllElements();
            selectedObject = null;
            fireIntervalRemoved(this, firstIndex, lastIndex);
        } else {
            selectedObject = null;
        }
    }

    /**
     * Method description
     *
     *
     * @param collection
     */
    public void addAllElements(Collection collection) {
        if (objects != null) {
            int firstIndex = objects.size();

            objects.addAll(collection);
            selectedObject = null;
            fireIntervalAdded(this, firstIndex, objects.size());
        } else {
            selectedObject = null;
        }
    }

    /**
     * Method description
     *
     *
     * @param collection
     */
    public void setElementsVetor(Collection collection) {
        if (objects != null) {
            int firstIndex = 0;

            removeAllElements();
            addAllElements(collection);

            if (getSize() > 0) {
                selectedObject = getElementAt(0);
            }

            fireIntervalAdded(this, firstIndex, objects.size());
        }
    }
}

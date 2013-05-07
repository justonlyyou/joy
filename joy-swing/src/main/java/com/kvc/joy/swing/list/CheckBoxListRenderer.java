package com.kvc.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * 可以勾选的的JList绘制器
 * @author zy
 */
public class CheckBoxListRenderer extends JCheckBox implements ListCellRenderer, MouseListener, KeyListener, MouseMotionListener {

    /** Field description */
    private boolean press = false;
    /** Field description */
    private boolean defaultCheck = false;
    /** Field description */
    private boolean checkActive = false;
    /** Field description */
    private JList list;
    /** Field description */
    private Map<Object, Boolean> selectionMap;

    /** Creates a new instance of CheckBoxListRenderer */
    public CheckBoxListRenderer() {
        selectionMap = new HashMap<Object, Boolean>();
    }

    /**
     * Constructs ...
     *
     *
     * @param defaultCheck
     */
    public CheckBoxListRenderer(boolean defaultCheck) {
        this();
        setDefaultCheck(defaultCheck);
    }

    /**
     * Method description
     *
     *
     * @param value
     *
     * @return
     */
    public boolean isCheck(Object value) {
        return this.selectionMap.get(value);
    }

    /**
     * Method description
     *
     *
     * @param check
     */
    public void checkAll(boolean check) {
        checkItems(selectionMap.keySet(), check);
    }

    /**
     * Method description
     *
     *
     * @param checkObjects
     * @param isCheck
     */
    public void checkItems(Collection checkObjects, boolean isCheck) {
        for (Object object : checkObjects) {
            this.selectionMap.put(object, isCheck);
        }

        repaintList();
    }

    /**
     * Method description
     *
     */
    public void clearItems() {
        this.selectionMap.clear();
    }

    /**
     * Method description
     *
     *
     * @param checkObjects
     * @param isCheck
     */
    public void checkItem(Object checkObjects, boolean isCheck) {
        this.selectionMap.put(checkObjects, isCheck);
        repaintList();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public List getCheckedItems() {
        List checkItems = new ArrayList();

        for (Object item : selectionMap.keySet()) {
            if (selectionMap.get(item)) {
                checkItems.add(item);
            }
        }

        return checkItems;
    }

    /**
     * Method description
     *
     *
     * @param list
     * @param value
     * @param index
     * @param isSelected
     * @param cellHasFocus
     *
     * @return
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (this.list == null) {
            this.list = list;
            list.addMouseListener(this);
            list.addKeyListener(this);
            list.addMouseMotionListener(this);
        }

        setText((value == null) ? "" : value.toString());

        if (this.selectionMap.get(value) == null) {
            selectionMap.put(value, defaultCheck);
        }

        setSelected(this.selectionMap.get(value));

        if (list.isEnabled()) {
            setEnabled(true);

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            getModel().setRollover(isSelected && checkActive);
            getModel().setArmed(isSelected & press);
            getModel().setPressed(isSelected & press);
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            setEnabled(false);
        }

        return this;
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        if (isCheckActive(e)) {
            setPress(true);
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseReleased(MouseEvent e) {

        if ((list.isSelectedIndex(list.locationToIndex(e.getPoint())) && press) && isCheckActive(e)) {
            changeCheck(e);
        }
        setPress(false);
    }

    /**
     * Method description
     *
     *
     * @param isPress
     */
    private void setPress(boolean isPress) {
        this.press = isPress;
        repaintList();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            setPress(true);
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) {
            setPress(false);
            changeCheck(null);
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Method description
     *
     */
    private void changeCheck(MouseEvent e) {
        if (list.isEnabled()) {
            if (e == null || e.isMetaDown()) {
                for (Object aSelectedItem : list.getSelectedValues()) {
                    selectionMap.put(aSelectedItem, !selectionMap.get(aSelectedItem));
                }
            } else {
                Object selectedItem = null;
                if (e != null) {
                    selectedItem = list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
                } else {
                    selectedItem = list.getSelectedValue();
                }

                boolean select = !selectionMap.get(selectedItem);
                selectionMap.put(selectedItem, select);
                for (Object aSelectedItem : list.getSelectedValues()) {
                    selectionMap.put(aSelectedItem, select);
                }
            }
            fireActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, this.getActionCommand()));
            repaintList();
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     *
     * @return
     */
    private boolean isCheckActive(MouseEvent e) {
        boolean pointActive = true;
        if (list.getSelectedIndex() < 0 || !list.isSelectedIndex(list.locationToIndex(e.getPoint()))) {
            pointActive = false;
        }
        if (pointActive) {
            int y = list.indexToLocation(list.locationToIndex(e.getPoint())).y + getPreferredSize().height / 2;
            pointActive = (e.getX() > 3) && (e.getX() < 18) &&
                    (e.getY() < y + 8) && (e.getY() > y - 8);
        }
        if (checkActive != pointActive) {
            checkActive = pointActive;
            repaintList();
        }
        return checkActive;
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void mouseMoved(MouseEvent e) {
        isCheckActive(e);
    }

    /**
     * Method description
     *
     */
    private void repaintList() {
        if (list != null) {
            list.repaint();
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isDefaultCheck() {
        return defaultCheck;
    }

    /**
     * Method description
     *
     *
     * @param defaultCheck
     */
    public void setDefaultCheck(boolean defaultCheck) {
        this.defaultCheck = defaultCheck;
    }
}

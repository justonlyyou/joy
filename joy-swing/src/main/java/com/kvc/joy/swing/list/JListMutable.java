package com.kvc.joy.swing.list;

//~--- JDK imports ------------------------------------------------------------

import java.applet.Applet;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.EventObject;

import javax.swing.AbstractAction;
import javax.swing.CellEditor;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 * 可编辑的JList
 * 1. List Item can be edited by F2 or mouse click
 * 2. ESC can be used to cancel editing.
 * 3. supports commitOnFocusLost
 *
 * @author Santhosh Kumar T - santhosh@in.fiorano.com
 */
public class JListMutable extends JList implements CellEditorListener {

    /** Field description */
    protected int editingIndex = -1;

    /** Field description */
    protected ListCellEditor editor = null;

    /** Field description */
    protected Component editorComp = null;

    /** Field description */
    private PropertyChangeListener editorRemover = null;

    /**
     * Constructs ...
     *
     *
     * @param dataModel
     */
    public JListMutable(ListModel dataModel) {
        super(dataModel);
        init();
    }

    /**
     * Method description
     *
     */
    private void init() {
        getActionMap().put("startEditing", new StartEditingAction());    // NOI18N
        getActionMap().put("cancel", new CancelEditingAction());    // NOI18N
        addMouseListener(new MouseListener());
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "startEditing");    // NOI18N
        getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancel");    // NOI18N
        putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);    // NOI18N
    }

    /**
     * Method description
     *
     *
     * @param editor
     */
    public void setListCellEditor(ListCellEditor editor) {
        this.editor = editor;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public ListCellEditor getListCellEditor() {
        return editor;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public boolean isEditing() {
        return (editorComp == null) ? false : true;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Component getEditorComponent() {
        return editorComp;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getEditingIndex() {
        return editingIndex;
    }

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public Component prepareEditor(int index) {
        Object value = getModel().getElementAt(index);
        boolean isSelected = isSelectedIndex(index);
        Component comp = editor.getListCellEditorComponent(this, value, isSelected, index);

//      if (comp instanceof JComponent) {
//          JComponent jComp = (JComponent)comp;
//          if (jComp.getNextFocusableComponent() == null) {
//              jComp.setNextFocusableComponent(this);
//          }
//      }
//      new FocusTraversalPolicy
        return comp;
    }

    /**
     * Method description
     *
     */
    public void removeEditor() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener("permanentFocusOwner", editorRemover);    // NOI18N
        editorRemover = null;

        if (editor != null) {
            editor.removeCellEditorListener(this);

            if (editorComp != null) {
                remove(editorComp);
            }

            Rectangle cellRect = getCellBounds(editingIndex, editingIndex);

            editingIndex = -1;
            editorComp = null;

            if (cellRect != null) {
                repaint(cellRect);
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param index
     * @param e
     *
     * @return
     */
    public boolean editCellAt(int index, EventObject e) {
        if ((editor != null) &&!editor.stopCellEditing()) {
            return false;
        }

        if ((index < 0) || (index >= getModel().getSize())) {
            return false;
        }

        if (!isCellEditable(index)) {
            return false;
        }

        if (editorRemover == null) {
            KeyboardFocusManager fm = KeyboardFocusManager.getCurrentKeyboardFocusManager();

            editorRemover = new CellEditorRemover(fm);
            fm.addPropertyChangeListener("permanentFocusOwner", editorRemover);    // NOI18N
        }

        if ((editor != null) && editor.isCellEditable(e)) {
            editorComp = prepareEditor(index);

            if (editorComp == null) {
                removeEditor();

                return false;
            }

            editorComp.setBounds(getCellBounds(index, index));
            add(editorComp);
            editorComp.validate();
            editingIndex = index;
            editor.addCellEditorListener(this);

            return true;
        }

        return false;
    }

    /**
     * Method description
     *
     */
    public void removeNotify() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removePropertyChangeListener("permanentFocusOwner", editorRemover);    // NOI18N
        super.removeNotify();
    }

    /**
     * Method description
     *
     *
     * @param index
     *
     * @return
     */
    public boolean isCellEditable(int index) {
        if (getModel() instanceof MutableListModel) {
            return ((MutableListModel) getModel()).isCellEditable(index);
        }

        return false;
    }

    /**
     * Method description
     *
     *
     * @param value
     * @param index
     */
    public void setValueAt(Object value, int index) {
        ((MutableListModel) getModel()).setValueAt(value, index);
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void editingStopped(ChangeEvent e) {
        if (editor != null) {
            Object value = editor.getCellEditorValue();

            setValueAt(value, editingIndex);
            removeEditor();
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    public void editingCanceled(ChangeEvent e) {
        removeEditor();
    }

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private class CancelEditingAction extends AbstractAction {

        /**
         * Method description
         *
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            JListMutable list = (JListMutable) e.getSource();

            list.removeEditor();
        }

        /**
         * Method description
         *
         *
         * @return
         */
        public boolean isEnabled() {
            return isEditing();
        }
    }


    // This class tracks changes in the keyboard focus state. It is used
    // when the XList is editing to determine when to cancel the edit.
    // If focus switches to a component outside of the XList, but in the
    // same window, this will cancel editing.

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    class CellEditorRemover implements PropertyChangeListener {

        /** Field description */
        KeyboardFocusManager focusManager;

        /**
         * Constructs ...
         *
         *
         * @param fm
         */
        public CellEditorRemover(KeyboardFocusManager fm) {
            this.focusManager = fm;
        }

        /**
         * Method description
         *
         *
         * @param ev
         */
        public void propertyChange(PropertyChangeEvent ev) {
            if (!isEditing() || (getClientProperty("terminateEditOnFocusLost") != Boolean.TRUE)) {    // NOI18N
                return;
            }

            Component c = focusManager.getPermanentFocusOwner();

            while (c != null) {
                if (c == JListMutable.this) {

                    // focus remains inside the table
                    return;
                } else if ((c instanceof Window) || ((c instanceof Applet) && (c.getParent() == null))) {
                    if (c == SwingUtilities.getRoot(JListMutable.this)) {
                        if (!getListCellEditor().stopCellEditing()) {
                            getListCellEditor().cancelCellEditing();
                        }
                    }

                    break;
                }

                c = c.getParent();
            }
        }
    }


    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private class MouseListener extends MouseAdapter {

        /** Field description */
        private Component dispatchComponent;

        /**
         * Method description
         *
         *
         * @param e
         */
        private void setDispatchComponent(MouseEvent e) {
            Component editorComponent = getEditorComponent();
            Point p = e.getPoint();
            Point p2 = SwingUtilities.convertPoint(JListMutable.this, p, editorComponent);

            dispatchComponent = SwingUtilities.getDeepestComponentAt(editorComponent, p2.x, p2.y);
        }

        /**
         * Method description
         *
         *
         * @param e
         *
         * @return
         */
        private boolean repostEvent(MouseEvent e) {

            // Check for isEditing() in case another event has
            // caused the editor to be removed. See bug #4306499.
            if ((dispatchComponent == null) ||!isEditing()) {
                return false;
            }

            MouseEvent e2 = SwingUtilities.convertMouseEvent(JListMutable.this, e, dispatchComponent);

            dispatchComponent.dispatchEvent(e2);

            return true;
        }

        /**
         * Method description
         *
         *
         * @param e
         *
         * @return
         */
        private boolean shouldIgnore(MouseEvent e) {
            return e.isConsumed() || (!(SwingUtilities.isLeftMouseButton(e) && isEnabled()));
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        public void mousePressed(MouseEvent e) {
            if (shouldIgnore(e)) {
                return;
            }

            Point p = e.getPoint();
            int index = locationToIndex(p);

            // The autoscroller can generate drag events outside the Table's range.
            if (index == -1) {
                return;
            }

            if (editCellAt(index, e)) {
                setDispatchComponent(e);
                repostEvent(e);
            } else if (isRequestFocusEnabled()) {
                requestFocus();
            }
        }
    }


    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private static class StartEditingAction extends AbstractAction {

        /**
         * Method description
         *
         *
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
            JListMutable list = (JListMutable) e.getSource();

            if (!list.hasFocus()) {
                CellEditor cellEditor = list.getListCellEditor();

                if ((cellEditor != null) &&!cellEditor.stopCellEditing()) {
                    return;
                }

                list.requestFocus();

                return;
            }

            ListSelectionModel rsm = list.getSelectionModel();
            int anchorRow = rsm.getAnchorSelectionIndex();

            list.editCellAt(anchorRow, null);

            Component editorComp = list.getEditorComponent();

            if (editorComp != null) {
                editorComp.requestFocus();
            }
        }
    }
}

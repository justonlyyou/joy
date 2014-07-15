package org.joy.swing.combobox;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author zhengpeng
 */
public class CustomComboBoxEditor implements ComboBoxEditor, DocumentListener, KeyListener {

    /** Field description */
    private JComboBox currentComboBox = null;

    /** Field description */
    private volatile boolean filtering = false;

    /** Field description */
    Object itemObject = null;

    /** Field description */
    private String oldString = "";

    /** Field description */
    private volatile boolean setting = false;

    /** Field description */
    public JTextField text;

    /**
     * Constructs ...
     *
     *
     * @param currentComboBox
     */
    public CustomComboBoxEditor(JComboBox currentComboBox) {
        this.currentComboBox = currentComboBox;
        text = new JTextField();
        text.getDocument().addDocumentListener(this);
        text.addKeyListener(this);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Component getEditorComponent() {
        return text;
    }

    /**
     * Method description
     *
     *
     * @param item
     */
    @Override
    public void setItem(Object item) {
        if (item != null) {
            this.itemObject = item;
        }

        if (filtering) {
            return;
        }

        setting = true;

        String newText = (item == null) ? "" : item.toString();

        text.setText(newText);
        setting = false;
    }

    /**
     * Method description
     *
     */
    public void cleanTextAndShowAll() {
        this.text.setText("");
        currentComboBox.setPopupVisible(true);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Object getItem() {
        if ((itemObject != null) && text.getText().equals(itemObject.toString())) {
            return this.itemObject;
        } else {
            return text.getText();
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getItemString() {
        return text.getText();
    }

    /**
     * Method description
     *
     */
    @Override
    public void selectAll() {
        text.selectAll();
    }

    /**
     * Method description
     *
     *
     * @param l
     */
    @Override
    public void addActionListener(ActionListener l) {
        text.addActionListener(l);
    }

    /**
     * Method description
     *
     *
     * @param l
     */
    @Override
    public void removeActionListener(ActionListener l) {
        text.removeActionListener(l);
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        handleChange();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        handleChange();
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void changedUpdate(DocumentEvent e) {}

    /**
     * Method description
     *
     */
    protected void handleChange() {

        // 是否启用过滤
        if (setting) {
            return;
        }

        filtering = true;
        ((FilterableComboBoxModel) currentComboBox.getModel()).setFilterMatch(text.getText());
        currentComboBox.setPopupVisible(false);
        currentComboBox.setPopupVisible(true);
        filtering = false;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public JTextField getText() {
        return text;
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        String newString = text.getText();
        boolean found = false;

        for (int i = 0; i < currentComboBox.getItemCount(); i++) {
            if (currentComboBox.getItemAt(i).toString().toLowerCase().startsWith(newString.toLowerCase())) {
                found = true;

                break;
            }
        }

        if (found) {
            oldString = text.getText();
        }

        if (e.getKeyChar() != KeyEvent.VK_ENTER) {
            text.setText(oldString);
        }
    }
}

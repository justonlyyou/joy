/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 *
 * @author Kevice
 */
public class XCheckBox extends JCheckBox {

    /**
     * Creates an initially unselected check box button with no text, no icon.
     */
    public XCheckBox() {
        super();
    }

    /**
     * Creates an initially unselected check box with an icon.
     *
     * @param icon  the Icon image to display
     */
    public XCheckBox(Icon icon) {
        super(icon);
    }

    /**
     * Creates a check box with an icon and specifies whether
     * or not it is initially selected.
     *
     * @param icon  the Icon image to display
     * @param selected a boolean value indicating the initial selection
     *        state. If <code>true</code> the check box is selected
     */
    public XCheckBox(Icon icon, boolean selected) {
        super(icon, selected);
    }

    /**
     * Creates an initially unselected check box with text.
     *
     * @param text the text of the check box.
     */
    public XCheckBox(String text) {
        super(text);
    }

    /**
     * Creates a check box where properties are taken from the
     * Action supplied.
     */
    public XCheckBox(Action a) {
        super(a);
    }

    /**
     * Creates a check box with text and specifies whether
     * or not it is initially selected.
     *
     * @param text the text of the check box.
     * @param selected a boolean value indicating the initial selection
     *        state. If <code>true</code> the check box is selected
     */
    public XCheckBox(String text, boolean selected) {
        super(text, selected);
    }

    /**
     * Creates an initially unselected check box with
     * the specified text and icon.
     *
     * @param text the text of the check box.
     * @param icon  the Icon image to display
     */
    public XCheckBox(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * Creates a check box with text and icon,
     * and specifies whether or not it is initially selected.
     *
     * @param text the text of the check box.
     * @param icon  the Icon image to display
     * @param selected a boolean value indicating the initial selection
     *        state. If <code>true</code> the check box is selected
     */
    public XCheckBox(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
    }
}

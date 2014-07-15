/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.button;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Kevice
 */
public class XButton extends JButton {

    /**
     * Creates a button with no set text or icon.
     */
    public XButton() {
        super();
    }

    /**
     * Creates a button with an icon.
     *
     * @param icon  the Icon image to display on the button
     */
    public XButton(Icon icon) {
        super(icon);
    }

    /**
     * Creates a button with text.
     *
     * @param text  the text of the button
     */
    public XButton(String text) {
        super(text);
    }

    /**
     * Creates a button where properties are taken from the
     * <code>Action</code> supplied.
     *
     * @param a the <code>Action</code> used to specify the new button
     */
    public XButton(Action a) {
        super(a);
    }

    /**
     * Creates a button with initial text and an icon.
     *
     * @param text  the text of the button
     * @param icon  the Icon image to display on the button
     */
    public XButton(String text, Icon icon) {
        super(text, icon);
    }
}

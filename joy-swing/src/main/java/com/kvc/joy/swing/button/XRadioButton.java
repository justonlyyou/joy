/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.button;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JRadioButton;

/**
 *
 * @author Kevice
 */
public class XRadioButton extends JRadioButton {

    /**
     * Creates an initially unselected radio button
     * with no set text.
     */
    public XRadioButton() {
        super();
    }

    /**
     * Creates an initially unselected radio button
     * with the specified image but no text.
     *
     * @param icon  the image that the button should display
     */
    public XRadioButton(Icon icon) {
        super(icon);
    }

    /**
     * Creates a radiobutton where properties are taken from the
     * Action supplied.
     */
    public XRadioButton(Action a) {
        super(a);
    }

    /**
     * Creates a radio button with the specified image
     * and selection state, but no text.
     *
     * @param icon  the image that the button should display
     * @param selected  if true, the button is initially selected;
     *                  otherwise, the button is initially unselected
     */
    public XRadioButton(Icon icon, boolean selected) {
        super(icon, selected);
    }

    /**
     * Creates an unselected radio button with the specified text.
     *
     * @param text  the string displayed on the radio button
     */
    public XRadioButton(String text) {
        super(text);
    }

    /**
     * Creates a radio button with the specified text
     * and selection state.
     *
     * @param text  the string displayed on the radio button
     * @param selected  if true, the button is initially selected;
     *                  otherwise, the button is initially unselected
     */
    public XRadioButton(String text, boolean selected) {
        super(text, selected);
    }

    /**
     * Creates a radio button that has the specified text and image,
     * and that is initially unselected.
     *
     * @param text  the string displayed on the radio button
     * @param icon  the image that the button should display
     */
    public XRadioButton(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * Creates a radio button that has the specified text, image,
     * and selection state.
     *
     * @param text  the string displayed on the radio button
     * @param icon  the image that the button should display
     */
    public XRadioButton(String text, Icon icon, boolean selected) {
        super(text, icon, selected);
    }
}

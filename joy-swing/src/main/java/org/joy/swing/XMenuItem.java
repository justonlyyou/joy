/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import javax.swing.*;

/**
 *
 * @author Kevice
 */
public class XMenuItem extends JMenuItem {

    /**
     * Creates a <code>XMenuItem</code> with no set text or icon.
     */
    public XMenuItem() {
        super();
    }

    /**
     * Creates a <code>XMenuItem</code> with the specified icon.
     *
     * @param icon the icon of the <code>XMenuItem</code>
     */
    public XMenuItem(Icon icon) {
        super(icon);
    }

    /**
     * Creates a <code>XMenuItem</code> with the specified text.
     *
     * @param text the text of the <code>XMenuItem</code>
     */
    public XMenuItem(String text) {
        super(text);
    }

    /**
     * Creates a menu item whose properties are taken from the
     * specified <code>Action</code>.
     *
     * @param a the action of the <code>XMenuItem</code>
     */
    public XMenuItem(Action a) {
        super(a);
    }

    /**
     * Creates a <code>XMenuItem</code> with the specified text and icon.
     *
     * @param text the text of the <code>XMenuItem</code>
     * @param icon the icon of the <code>XMenuItem</code>
     */
    public XMenuItem(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * Creates a <code>XMenuItem</code> with the specified text and
     * keyboard mnemonic.
     *
     * @param text the text of the <code>XMenuItem</code>
     * @param mnemonic the keyboard mnemonic for the <code>XMenuItem</code>
     */
    public XMenuItem(String text, int mnemonic) {
        super(text, mnemonic);
    }
}

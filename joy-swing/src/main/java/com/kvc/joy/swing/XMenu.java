/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing;

import javax.swing.Action;
import javax.swing.JMenu;

/**
 *
 * @author Kevice
 */
public class XMenu extends JMenu {

    /**
     * Constructs a new <code>XMenu</code> with no text.
     */
    public XMenu() {
        super();
    }

    /**
     * Constructs a new <code>XMenu</code> with the supplied string
     * as its text.
     *
     * @param s  the text for the menu label
     */
    public XMenu(String s) {
        super(s);
    }

    /**
     * Constructs a menu whose properties are taken from the
     * <code>Action</code> supplied.
     * @param a an <code>Action</code>
     */
    public XMenu(Action a) {
        super(a);
    }

    /**
     * Constructs a new <code>XMenu</code> with the supplied string as
     * its text and specified as a tear-off menu or not.
     *
     * @param s the text for the menu label
     * @param b can the menu be torn off (not yet implemented)
     */
    public XMenu(String s, boolean b) {
        super(s, b);
    }
}

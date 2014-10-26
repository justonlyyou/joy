/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.pane;

import javax.swing.*;

/**
 *
 * @author Kevice
 */
public class XTabbedPane extends JTabbedPane {

    /**
     * Creates an empty <code>TabbedPane</code> with a default
     * tab placement of <code>XTabbedPane.TOP</code>.
     * @see #addTab
     */
    public XTabbedPane() {
        super();
    }

    /**
     * Creates an empty <code>TabbedPane</code> with the specified tab placement
     * of either: <code>XTabbedPane.TOP</code>, <code>XTabbedPane.BOTTOM</code>,
     * <code>XTabbedPane.LEFT</code>, or <code>XTabbedPane.RIGHT</code>.
     *
     * @param tabPlacement the placement for the tabs relative to the content
     * @see #addTab
     */
    public XTabbedPane(int tabPlacement) {
        super(tabPlacement);
    }

    /**
     * Creates an empty <code>TabbedPane</code> with the specified tab placement
     * and tab layout policy.  Tab placement may be either:
     * <code>XTabbedPane.TOP</code>, <code>XTabbedPane.BOTTOM</code>,
     * <code>XTabbedPane.LEFT</code>, or <code>XTabbedPane.RIGHT</code>.
     * Tab layout policy may be either: <code>XTabbedPane.WRAP_TAB_LAYOUT</code>
     * or <code>XTabbedPane.SCROLL_TAB_LAYOUT</code>.
     *
     * @param tabPlacement the placement for the tabs relative to the content
     * @param tabLayoutPolicy the policy for laying out tabs when all tabs will not fit on one run
     * @exception IllegalArgumentException if tab placement or tab layout policy are not
     *            one of the above supported values
     * @see #addTab
     */
    public XTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }
}

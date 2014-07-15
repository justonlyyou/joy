/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.panel;

import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Kevice
 */
public class XPanel extends JPanel {

    /**
     * Creates a new XPanel with the specified layout manager and buffering
     * strategy.
     *
     * @param layout  the LayoutManager to use
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public XPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Create a new buffered XPanel with the specified layout manager
     *
     * @param layout  the LayoutManager to use
     */
    public XPanel(LayoutManager layout) {
        super(layout);
    }

    /**
     * Creates a new <code>XPanel</code> with <code>FlowLayout</code>
     * and the specified buffering strategy.
     * If <code>isDoubleBuffered</code> is true, the <code>XPanel</code>
     * will use a double buffer.
     *
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public XPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    /**
     * Creates a new <code>XPanel</code> with a double buffer
     * and a flow layout.
     */
    public XPanel() {
        super();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import java.awt.Dimension;
import javax.swing.JSeparator;

/**
 *
 * @author Kevice
 */
public class XSeparator extends JSeparator {

    /** Creates a new horizontal separator. */
    public XSeparator() {
        super();
    }

    /**
     * Creates a new separator with the specified horizontal or
     * vertical orientation.
     *
     * @param orientation an integer specifying
     *		<code>SwingConstants.HORIZONTAL</code> or
     *          <code>SwingConstants.VERTICAL</code>
     * @exception IllegalArgumentException if <code>orientation</code>
     *		is neither <code>SwingConstants.HORIZONTAL</code> nor
     *		<code>SwingConstants.VERTICAL</code>
     */
    public XSeparator(int orientation) {
        super(orientation);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static XSeparator createToolSeparator() {
        XSeparator instance = new XSeparator(JSeparator.VERTICAL);

        instance.setPreferredSize(new Dimension(1, 28));

        return instance;
    }
}

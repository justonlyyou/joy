/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Kevice
 */
public class XFrame extends JFrame {

    /**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public XFrame() throws HeadlessException {
        super();
    }

    /**
     * Creates a <code>Frame</code> in the specified
     * <code>GraphicsConfiguration</code> of
     * a screen device and a blank title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param gc the <code>GraphicsConfiguration</code> that is used
     * 		to construct the new <code>Frame</code>;
     * 		if <code>gc</code> is <code>null</code>, the system
     * 		default <code>GraphicsConfiguration</code> is assumed
     * @exception IllegalArgumentException if <code>gc</code> is not from
     * 		a screen device.  This exception is always thrown when
     *      GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XFrame(GraphicsConfiguration gc) {
        super(gc);
    }

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public XFrame(String title) throws HeadlessException {
        super(title);
    }

    /**
     * Creates a <code>XFrame</code> with the specified title and the
     * specified <code>GraphicsConfiguration</code> of a screen device.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title to be displayed in the
     * 		frame's border. A <code>null</code> value is treated as
     * 		an empty string, "".
     * @param gc the <code>GraphicsConfiguration</code> that is used
     * 		to construct the new <code>XFrame</code> with;
     *		if <code>gc</code> is <code>null</code>, the system
     * 		default <code>GraphicsConfiguration</code> is assumed
     * @exception IllegalArgumentException if <code>gc</code> is not from
     * 		a screen device.  This exception is always thrown when
     *      GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     */
    public XFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }
}

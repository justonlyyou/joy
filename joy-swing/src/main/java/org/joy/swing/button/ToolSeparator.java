package org.joy.swing.button;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Dimension;
import org.joy.swing.XSeparator;

/**
 *
 * @author zy
 */
public class ToolSeparator extends XSeparator {

    /** Field description */
    protected static final Dimension dimension = new Dimension(2, 28);

    /**
     * Constructs ...
     *
     */
    public ToolSeparator() {
        super(VERTICAL);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    protected Dimension getFixSize() {
        return dimension;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Dimension getMaximumSize() {
        return getFixSize();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Dimension getMinimumSize() {
        return getFixSize();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public Dimension getPreferredSize() {
        return getFixSize();
    }
}

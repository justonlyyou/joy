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
public class XProgressBar extends JProgressBar {

    /**
     * Creates a horizontal progress bar
     * that displays a border but no progress string.
     * The initial and minimum values are 0,
     * and the maximum is 100.
     *
     * @see #setOrientation
     * @see #setBorderPainted
     * @see #setStringPainted
     * @see #setString
     * @see #setIndeterminate
     */
    public XProgressBar() {
        super();
    }

    /**
     * Creates a progress bar with the specified orientation,
     * which can be
     * either {@code SwingConstants.VERTICAL} or
     * {@code SwingConstants.HORIZONTAL}.
     * By default, a border is painted but a progress string is not.
     * The initial and minimum values are 0,
     * and the maximum is 100.
     *
     * @param orient  the desired orientation of the progress bar
     * @throws IllegalArgumentException if {@code orient} is an illegal value
     *
     * @see #setOrientation
     * @see #setBorderPainted
     * @see #setStringPainted
     * @see #setString
     * @see #setIndeterminate
     */
    public XProgressBar(int orient) {
        super(orient);
    }

    /**
     * Creates a horizontal progress bar
     * with the specified minimum and maximum.
     * Sets the initial value of the progress bar to the specified minimum.
     * By default, a border is painted but a progress string is not.
     * <p>
     * The <code>BoundedRangeModel</code> that holds the progress bar's data
     * handles any issues that may arise from improperly setting the
     * minimum, initial, and maximum values on the progress bar.
     * See the {@code BoundedRangeModel} documentation for details.
     *
     * @param min  the minimum value of the progress bar
     * @param max  the maximum value of the progress bar
     *
     * @see BoundedRangeModel
     * @see #setOrientation
     * @see #setBorderPainted
     * @see #setStringPainted
     * @see #setString
     * @see #setIndeterminate
     */
    public XProgressBar(int min, int max) {
        super(min, max);
    }

    /**
     * Creates a progress bar using the specified orientation,
     * minimum, and maximum.
     * By default, a border is painted but a progress string is not.
     * Sets the initial value of the progress bar to the specified minimum.
     * <p>
     * The <code>BoundedRangeModel</code> that holds the progress bar's data
     * handles any issues that may arise from improperly setting the
     * minimum, initial, and maximum values on the progress bar.
     * See the {@code BoundedRangeModel} documentation for details.
     *
     * @param orient  the desired orientation of the progress bar
     * @param min  the minimum value of the progress bar
     * @param max  the maximum value of the progress bar
     * @throws IllegalArgumentException if {@code orient} is an illegal value
     *
     * @see BoundedRangeModel
     * @see #setOrientation
     * @see #setBorderPainted
     * @see #setStringPainted
     * @see #setString
     * @see #setIndeterminate
     */
    public XProgressBar(int orient, int min, int max) {
        super(orient, min, max);
    }

    /**
     * Creates a horizontal progress bar
     * that uses the specified model
     * to hold the progress bar's data.
     * By default, a border is painted but a progress string is not.
     *
     * @param newModel  the data model for the progress bar
     *
     * @see #setOrientation
     * @see #setBorderPainted
     * @see #setStringPainted
     * @see #setString
     * @see #setIndeterminate
     */
    public XProgressBar(BoundedRangeModel newModel) {
        super(newModel);
    }
}

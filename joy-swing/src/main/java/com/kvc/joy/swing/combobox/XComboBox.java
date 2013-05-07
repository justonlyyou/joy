/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.combobox;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboPopup;
import com.kvc.joy.swing.PinYinKeySelectionManager;

/**
 * 支持在不可编辑情况下的中英文过滤
 * @author Kevice
 */
public class XComboBox extends JComboBox {

    /** Field description */
    protected int lastWidth = -1;
    /** Field description */
    protected int popupWidth;

    /**
     * Creates a <code>XComboBox</code> with a default data model.
     * The default data model is an empty list of objects.
     * Use <code>addItem</code> to add items.  By default the first item
     * in the data model becomes selected.
     *
     * @see DefaultComboBoxModel
     */
    public XComboBox() {
        super();
        addKeyListener();
        addPopupMenuListener();
    }

    private void addKeyListener() {
        new PinYinKeySelectionManager(this) {

            @Override
            protected int getSelectedIndex() {
                return XComboBox.this.getSelectedIndex();
            }

            @Override
            protected void setSelectedIndex(int index) {
                XComboBox.this.setSelectedIndex(index);
            }
        };
    }

    private void addPopupMenuListener() {
        addPopupMenuListener(new PopuHandler());
    }

    /**
     * Creates a <code>XComboBox</code> that takes its items from an
     * existing <code>ComboBoxModel</code>.  Since the
     * <code>ComboBoxModel</code> is provided, a combo box created using
     * this constructor does not create a default combo box model and
     * may impact how the insert, remove and add methods behave.
     *
     * @param aModel the <code>ComboBoxModel</code> that provides the
     * 		displayed list of items
     * @see DefaultComboBoxModel
     */
    public XComboBox(ComboBoxModel aModel) {
        super(aModel);
        addKeyListener();
        addPopupMenuListener();
    }

    /**
     * Creates a <code>XComboBox</code> that contains the elements
     * in the specified array.  By default the first item in the array
     * (and therefore the data model) becomes selected.
     *
     * @param items  an array of objects to insert into the combo box
     * @see DefaultComboBoxModel
     */
    public XComboBox(final Object[] items) {
        super(items);
        addKeyListener();
        addPopupMenuListener();
    }

    /**
     * Creates a <code>XComboBox</code> that contains the elements
     * in the specified Vector.  By default the first item in the vector
     * (and therefore the data model) becomes selected.
     *
     * @param items  an array of vectors to insert into the combo box
     * @see DefaultComboBoxModel
     */
    public XComboBox(Vector<?> items) {
        super(items);
        addKeyListener();
        addPopupMenuListener();
    }

    /**
     * Method description
     *
     *
     * @param width
     */
    public void setPopupWidth(int width) {
        popupWidth = width;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Dimension getPopupSize() {
        Dimension size = getSize();

        if (popupWidth < 1) {
            popupWidth = size.width;
        }

        return new Dimension(popupWidth, size.height);
    }

    /**
     * This method tries to access the combobox' ComboPopup object.
     * It does so by looking for an instance of
     * @return the combobox' ComboPopup object
     */
    protected javax.swing.plaf.basic.BasicComboPopup getComboPopup() {
        for (int childComponentIndex = 0, n = getUI().getAccessibleChildrenCount(this); childComponentIndex < n; childComponentIndex++) {
            Object component = getUI().getAccessibleChild(this, childComponentIndex);

            if (component instanceof javax.swing.plaf.basic.BasicComboPopup) {
                return (javax.swing.plaf.basic.BasicComboPopup) component;
            }
        }

        return null;
    }

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    private class PopuHandler implements PopupMenuListener {

        /**
         * Method description
         *
         *
         * @param px
         * @param py
         * @param pw
         * @param ph
         *
         * @return
         */
        protected Rectangle computePopupBounds(int px, int py, int pw, int ph) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Rectangle screenBounds;

            // Calculate the desktop dimensions relative to the combo box.
            GraphicsConfiguration gc = getGraphicsConfiguration();
            Point p = new Point();

            SwingUtilities.convertPointFromScreen(p, XComboBox.this);

            if (gc != null) {
                Insets screenInsets = toolkit.getScreenInsets(gc);

                screenBounds = gc.getBounds();
                screenBounds.width -= (screenInsets.left + screenInsets.right);
                screenBounds.height -= (screenInsets.top + screenInsets.bottom);
                screenBounds.x += (p.x + screenInsets.left);
                screenBounds.y += (p.y + screenInsets.top);
            } else {
                screenBounds = new Rectangle(p, toolkit.getScreenSize());
            }

            Rectangle rect = new Rectangle(px, py, pw, ph);

            if ((py + ph > screenBounds.y + screenBounds.height) && (ph < screenBounds.height)) {
                rect.y = -rect.height;
            }

            return rect;
        }

        /**
         * Method description
         *
         *
         * @param maxRowCount
         * @param list
         *
         * @return
         */
        protected int getPopupHeightForRowCount(int maxRowCount, JList list) {

            // Set the cached value of the minimum row count
            int minRowCount = Math.min(maxRowCount, getItemCount());
            int height = 0;
            ListCellRenderer renderer = list.getCellRenderer();
            Object value = null;

            for (int i = 0; i < minRowCount; ++i) {
                value = list.getModel().getElementAt(i);

                Component c = renderer.getListCellRendererComponent(list, value, i, false, false);

                height += c.getPreferredSize().height;
            }

            if (height == 0) {
                height = getHeight();
            }

            JScrollPane scroller = ((JScrollPane) list.getParent().getParent());
            Border border = scroller.getViewportBorder();

            if (border != null) {
                Insets insets = border.getBorderInsets(null);

                height += insets.top + insets.bottom;
            }

            border = scroller.getBorder();

            if (border != null) {
                Insets insets = border.getBorderInsets(null);

                height += insets.top + insets.bottom;
            }

            return height;
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            Dimension popupSize = getPopupSize();
            BasicComboPopup comboBoxPopup = getComboPopup();
            JList listBox = comboBoxPopup.getList();
            int width = Math.max(popupSize.width, listBox.getMaximumSize().width);

            if (getMaximumRowCount() < getModel().getSize()) {
                width = width + UIManager.getInt("ScrollBar.width");
            }

            if (lastWidth != width) {
                popupSize.setSize(width, getPopupHeightForRowCount(getMaximumRowCount(), listBox));

                Rectangle popupBounds = computePopupBounds(0, getBounds().height, popupSize.width, popupSize.height);
                JScrollPane scroller = (JScrollPane) listBox.getParent().getParent();

                scroller.setMaximumSize(popupBounds.getSize());
                scroller.setPreferredSize(popupBounds.getSize());
                scroller.setMinimumSize(popupBounds.getSize());
                listBox.invalidate();

                int selectedIndex = getSelectedIndex();

                if (selectedIndex == -1) {
                    listBox.clearSelection();
                } else {
                    listBox.setSelectedIndex(selectedIndex);
                }

                listBox.ensureIndexIsVisible(listBox.getSelectedIndex());
            }
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void popupMenuCanceled(PopupMenuEvent e) {
        }
    }
}

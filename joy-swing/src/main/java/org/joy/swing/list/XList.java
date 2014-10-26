/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.list;

import org.joy.swing.PinYinKeySelectionManager;

import javax.swing.*;
import java.util.Vector;

/**
 *
 * @author Kevice
 */
public class XList extends JList {

    /**
     * Constructs a {@code XList} that displays elements from the specified,
     * {@code non-null}, model. All {@code XList} constructors delegate to
     * this one.
     * <p>
     * This constructor registers the list with the {@code ToolTipManager},
     * allowing for tooltips to be provided by the cell renderers.
     *
     * @param dataModel the model for the list
     * @exception IllegalArgumentException if the model is {@code null}
     */
    public XList(ListModel dataModel) {
        super(dataModel);
        addKeyListener();
    }

    private void addKeyListener() {
        new PinYinKeySelectionManager(this) {

            @Override
            protected int getSelectedIndex() {
                return XList.this.getSelectedIndex();
            }

            @Override
            protected void setSelectedIndex(int index) {
                XList.this.setSelectedIndex(index);
            }
        };
    }

    /**
     * Constructs a <code>XList</code> that displays the elements in
     * the specified array. This constructor creates a read-only model
     * for the given array, and then delegates to the constructor that
     * takes a {@code ListModel}.
     * <p>
     * Attempts to pass a {@code null} value to this method results in
     * undefined behavior and, most likely, exceptions. The created model
     * references the given array directly. Attempts to modify the array
     * after constructing the list results in undefined behavior.
     *
     * @param  listData  the array of Objects to be loaded into the data model,
     *                   {@code non-null}
     */
    public XList(final Object[] listData) {
        super(listData);
        addKeyListener();
    }

    /**
     * Constructs a <code>XList</code> that displays the elements in
     * the specified <code>Vector</code>. This constructor creates a read-only
     * model for the given {@code Vector}, and then delegates to the constructor
     * that takes a {@code ListModel}.
     * <p>
     * Attempts to pass a {@code null} value to this method results in
     * undefined behavior and, most likely, exceptions. The created model
     * references the given {@code Vector} directly. Attempts to modify the
     * {@code Vector} after constructing the list results in undefined behavior.
     *
     * @param  listData  the <code>Vector</code> to be loaded into the
     *		         data model, {@code non-null}
     */
    public XList(final Vector<?> listData) {
        super(listData);
        addKeyListener();
    }

    /**
     * Constructs a <code>XList</code> with an empty, read-only, model.
     */
    public XList() {
        super();
        addKeyListener();
    }
}

 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

/**
 *
 * @author Kevice
 */
public class XFormattedTextField extends JFormattedTextField {

    /**
     * Creates a <code>XFormattedTextField</code> with no
     * <code>AbstractFormatterFactory</code>. Use <code>setMask</code> or
     * <code>setFormatterFactory</code> to configure the
     * <code>XFormattedTextField</code> to edit a particular type of
     * value.
     */
    public XFormattedTextField() {
        super();
    }

    /**
     * Creates a XFormattedTextField with the specified value. This will
     * create an <code>AbstractFormatterFactory</code> based on the
     * type of <code>value</code>.
     *
     * @param value Initial value for the XFormattedTextField
     */
    public XFormattedTextField(Object value) {
        super(value);
    }

    /**
     * Creates a <code>XFormattedTextField</code>. <code>format</code> is
     * wrapped in an appropriate <code>AbstractFormatter</code> which is
     * then wrapped in an <code>AbstractFormatterFactory</code>.
     *
     * @param format Format used to look up an AbstractFormatter
     */
    public XFormattedTextField(java.text.Format format) {
        super(format);
    }

    /**
     * Creates a <code>XFormattedTextField</code> with the specified
     * <code>AbstractFormatter</code>. The <code>AbstractFormatter</code>
     * is placed in an <code>AbstractFormatterFactory</code>.
     *
     * @param formatter AbstractFormatter to use for formatting.
     */
    public XFormattedTextField(AbstractFormatter formatter) {
        super(formatter);
    }

    /**
     * Creates a <code>XFormattedTextField</code> with the specified
     * <code>AbstractFormatterFactory</code>.
     *
     * @param factory AbstractFormatterFactory used for formatting.
     */
    public XFormattedTextField(AbstractFormatterFactory factory) {
        super(factory);
    }

    /**
     * Creates a <code>XFormattedTextField</code> with the specified
     * <code>AbstractFormatterFactory</code> and initial value.
     *
     * @param factory <code>AbstractFormatterFactory</code> used for
     *        formatting.
     * @param currentValue Initial value to use
     */
    public XFormattedTextField(AbstractFormatterFactory factory, Object currentValue) {
        super(factory, currentValue);
    }

     /**
     * 清除内容
     */
    public void clear() {
        setText("");
        setValue(null);
    }
}

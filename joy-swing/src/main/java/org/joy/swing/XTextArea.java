/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing;

import javax.swing.*;
import javax.swing.text.Document;

/**
 *
 * @author Kevice
 */
public class XTextArea extends JTextArea {

    /**
     * Constructs a new TextArea.  A default model is set, the initial string
     * is null, and rows/columns are set to 0.
     */
    public XTextArea() {
        super();
    }

    /**
     * Constructs a new TextArea with the specified text displayed.
     * A default model is created and rows/columns are set to 0.
     *
     * @param text the text to be displayed, or null
     */
    public XTextArea(String text) {
        super(text);
    }

    /**
     * Constructs a new empty TextArea with the specified number of
     * rows and columns.  A default model is created, and the initial
     * string is null.
     *
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public XTextArea(int rows, int columns) {
        super(rows, columns);
    }

    /**
     * Constructs a new TextArea with the specified text and number
     * of rows and columns.  A default model is created.
     *
     * @param text the text to be displayed, or null
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public XTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
    }

    /**
     * Constructs a new XTextArea with the given document model, and defaults
     * for all of the other arguments (null, 0, 0).
     *
     * @param doc  the model to use
     */
    public XTextArea(Document doc) {
        super(doc);
    }

    /**
     * Constructs a new XTextArea with the specified number of rows
     * and columns, and the given model.  All of the constructors
     * feed through this constructor.
     *
     * @param doc the model to use, or create a default one if null
     * @param text the text to be displayed, null if none
     * @param rows the number of rows >= 0
     * @param columns the number of columns >= 0
     * @exception IllegalArgumentException if the rows or columns
     *  arguments are negative.
     */
    public XTextArea(Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
    }

     /**
     * 清除文本域内容
     */
    public void clear() {
        setText("");
    }
}

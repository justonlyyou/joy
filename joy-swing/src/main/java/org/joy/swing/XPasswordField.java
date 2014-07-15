package org.joy.swing;

import javax.swing.JPasswordField;
import javax.swing.text.Document;

/**
 * @author  <b>Kevice</b>
 */
public class XPasswordField extends JPasswordField {

    public XPasswordField() {
        super();
    }

    public XPasswordField(String text) {
        super(text);
    }

    public XPasswordField(int columns) {
        super(columns);
    }

    public XPasswordField(String text, int columns) {
        super(text, columns);
    }

    public XPasswordField(Document doc, String txt, int columns) {
        super(doc, txt, columns);
    }
}

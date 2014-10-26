package org.joy.swing.regex;

//~--- JDK imports ------------------------------------------------------------

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * 支持正则表达式的文本
 * @author zy
 */
public class RegexDocument extends PlainDocument implements RegexConstants {

    /** Field description */
    private String regex = "";

    /**
     * Constructs ...
     *
     *
     * @param regex
     */
    public RegexDocument(String regex) {
        this.regex = regex;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static RegexDocument getNumberInstance() {
        return new RegexDocument(NUMBER_REGEX);
    }

    /**
     * Method description
     *
     *
     * @param offs
     * @param str
     * @param a
     *
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String sourceText = getText(0, getLength());

        sourceText = sourceText.substring(0, offs) + str + sourceText.substring(offs, getLength());

        if (Pattern.matches(regex, sourceText)) {
            super.insertString(offs, str, a);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}

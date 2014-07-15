package org.joy.swing.regex;

//~--- non-JDK imports --------------------------------------------------------


//~--- JDK imports ------------------------------------------------------------

import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import org.joy.commons.lang.string.I18nTool;

/**
 * 正则表达式的格式化，可以放入JFormattedTextField的构造器
 * @author zy
 */
public class RegexFormatter extends JFormattedTextField.AbstractFormatter implements RegexConstants {

    /** Field description */
    private String regex = null;

    /**
     * Constructs ...
     *
     *
     * @param regex
     */
    public RegexFormatter(String regex) {
        if (regex == null) {
            throw new IllegalArgumentException(" Argument regex can not be null!");
        }

        this.regex = regex;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static RegexFormatter getEmailMatchInstance() {
        return new RegexFormatter(EMAIL_REGEX);
    }

    /**
     * Method description
     *
     *
     * @param text
     *
     * @return
     *
     * @throws ParseException
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        if ((text == null) || text.isEmpty()) {
            if (getFormattedTextField().getValue() == null) {
                return null;
            }

            return text;
        }

        if (Pattern.matches(regex, text)) {
            return text;
        } else {
            if (regex.equals(EMAIL_REGEX)) {
                JOptionPane.showMessageDialog(getFormattedTextField(), I18nTool.getLocalStr("PleaseEnterAValidMailAddress"));
            }

            throw new ParseException("parse fail :", 0);
        }
    }

    /**
     * Method description
     *
     *
     * @param value
     *
     * @return
     *
     * @throws ParseException
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        return (value == null) ? "" : value.toString();
    }
}

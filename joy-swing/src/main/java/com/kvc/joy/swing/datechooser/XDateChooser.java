/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvc.joy.swing.datechooser;

import java.util.Date;

import com.kvc.joy.commons.lang.DateTool;
import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
/**
 * 这个子类提供的作用是在构造JDateChooser同时调用
 * JDateChooser的cleanup()方法以便清除向MenuSelectionManager注册的changeListener，
 * 防止内存泄露。。。
 * @author ckcs
 */
public class XDateChooser extends JDateChooser {

    public XDateChooser(JCalendar jcal, Date date, String dateFormatString, IDateEditor dateEditor) {
        super(jcal, date, dateFormatString == null? DateTool.FMT_HYPHEN_DAY_CLN_SECOND : dateFormatString, dateEditor);
        //清除注册的changeListener监听器
        cleanup();
    }

    public XDateChooser(String datePattern, String maskPattern, char placeholder) {
        this(null, null, datePattern, new JTextFieldDateEditor(datePattern, maskPattern, placeholder));
    }

    public XDateChooser(Date date, String dateFormatString, IDateEditor dateEditor) {
        this(null, date, dateFormatString, dateEditor);
    }

    public XDateChooser(Date date, String dateFormatString) {
        this(date, dateFormatString, null);
    }

    public XDateChooser(Date date) {
        this(date, null);
    }

    public XDateChooser(IDateEditor dateEditor) {
        this(null, null, null, dateEditor);
    }

    public XDateChooser() {
        this(null, null, null, null);
    }
}

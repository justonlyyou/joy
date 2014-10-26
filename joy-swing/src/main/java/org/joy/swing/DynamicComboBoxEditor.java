package org.joy.swing;

import org.joy.commons.lang.ReflectionTool;

import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 * 在JComboBox的编辑框中根据属性的名字显示相应的值
 * @author Roland
 */
public class DynamicComboBoxEditor extends BasicComboBoxEditor {

    // 用于显示对象内容的属性名称
    /**
     * 要显示的属性名称
     */
    private String propertyName = null;

    // 用于保存旧的对象值
    /**
     * 用于保存未被编辑的值
     */
    private Object oldValue;

    /**
     * 构造器
     * @param propertyName 要显示的属性名称
     */
    public DynamicComboBoxEditor(String propertyName) {
        super();
        assert (propertyName != null) || (propertyName.length() > 0) :
                "ERROR: You must indicate the property to display Object in DynamicComboBoxEditor!";
        this.propertyName = propertyName;
    }

    /**
     * 设置显示的对象
     * @param anObject 显示的对象
     */
    @Override
    public void setItem(Object anObject) {
        if (anObject != null) {
            this.oldValue = anObject;

            Object display = null;    // 显示的内容

            display = ReflectionTool.getPropertyValue(anObject, propertyName);

            if (display != null) {
                editor.setText(display.toString());
            } else {
                editor.setText("");
            }
        } else {
            editor.setText("");
        }
    }

    /**
     * 获取被编辑后的对象
     * @return 编辑后的对象
     */
    @Override
    public Object getItem() {
        String newValue = editor.getText();

        if ((oldValue != null) && !(oldValue instanceof String)) {
            if (newValue.equals(oldValue.toString()) || newValue.equals(ReflectionTool.getPropertyValue(oldValue, propertyName).toString())) {
                return oldValue;
            }
        }

        return newValue;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.button;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;
import org.joy.swing.XComponentBeanInfo;

/**
 *
 * @author zy
 */
public class ToolButtonBeanInfo extends XComponentBeanInfo {

    PropertyDescriptor[] properties = null;

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {

        if (properties == null) {
            List<PropertyDescriptor> properList = new ArrayList<PropertyDescriptor>();
            try {
                PropertyDescriptor[] propertyDescriptors = super.getPropertyDescriptors();
                properList.add(getPropertyDescriptor("buttonStyle"));
                for (PropertyDescriptor pd : propertyDescriptors) {
                    properList.add(pd);
                }
                properties = properList.toArray(new PropertyDescriptor[properList.size()]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return properties;
    }

    protected PropertyDescriptor getPropertyDescriptor(String propertyName) {
        PropertyDescriptor propertyDescriptor = null;
        try {
            propertyDescriptor = new PropertyDescriptor(propertyName, ToolButton.class);
            propertyDescriptor.setPropertyEditorClass(ToolButtonPropertyEditor.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return propertyDescriptor;
    }

    public static class ToolButtonPropertyEditor extends PropertyEditorSupport {

        @Override
        public String getJavaInitializationString() {
            return getValue() == null ? "" : "com.bas.util.client.swing.button.ToolButton.Styles." + getValue();
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            for (ToolButton.Styles style : ToolButton.Styles.values()) {
                if (style.toString().equals(text)) {
                    setValue(style);
                    break;
                }
            }
        }

        @Override
        public String getAsText() {
            if (getValue() != null) {
                for (ToolButton.Styles style : ToolButton.Styles.values()) {
                    if (style.toString().equals(((ToolButton.Styles) getValue()).toString())) {
                        return style.toString();
                    }
                }
            }
            return "";
        }

        @Override
        public String[] getTags() {
            String[] styles = new String[ToolButton.Styles.values().length];
            int index = 0;
            for (ToolButton.Styles style : ToolButton.Styles.values()) {
                styles[index] = style.toString();
                index++;
            }
            return styles;
        }
    }
}

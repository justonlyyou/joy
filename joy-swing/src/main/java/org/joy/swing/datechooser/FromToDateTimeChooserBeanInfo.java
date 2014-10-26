/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.datechooser;

import org.joy.swing.XComponentBeanInfo;

import javax.swing.*;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ckcs
 */
public class FromToDateTimeChooserBeanInfo extends XComponentBeanInfo {

    PropertyDescriptor[] properties;

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        if (properties == null) {
            List<PropertyDescriptor> properList = new ArrayList<PropertyDescriptor>();
            try {
                PropertyDescriptor[] pros = super.getPropertyDescriptors();
                properList.add(getPropertyDescriptor("layoutStyle"));
                for (PropertyDescriptor propertyDescriptor : pros) {
                    properList.add(propertyDescriptor);
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
            propertyDescriptor = new PropertyDescriptor(propertyName, FromToDateTimeChooser.class);
            propertyDescriptor.setPropertyEditorClass(FromToDateTimePropertyEditor.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return propertyDescriptor;
    }

    public static class FromToDateTimePropertyEditor extends PropertyEditorSupport {

        @Override
        public String getJavaInitializationString() {
            return getValue() == null ? String.valueOf(BoxLayout.X_AXIS) : getValue().toString();
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            String[] tag = getTags();
            if (tag[0].equals(text)) {
                setValue(BoxLayout.X_AXIS);
            } else {
                setValue(BoxLayout.Y_AXIS);
            }
        }

        @Override
        public String getAsText() {
            if (getValue() != null) {
                if (getValue().equals(BoxLayout.X_AXIS)) {
                    return "X_LAYOUT";
                } else {
                    return "Y_LAYOUT";

                }
            }
            return "Y_AXIS";
        }

        @Override
        public String[] getTags() {
            String[] layout = new String[2];
            layout[0] = "X_LAYOUT";
            layout[1] = "Y_LAYOUT";
            return layout;
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.joy.swing.table.filter;

import org.joy.commons.lang.DateTool;
import org.joy.commons.lang.string.I18nTool;
import org.joy.swing.datechooser.XDateChooser;
import org.joy.swing.regex.RegexDocument;
import org.joy.swing.table.RowStringFilter;

import javax.swing.*;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 过滤器编辑器工厂
 * @author zy
 * @author ckcs
 */
public class FilterEditorFactory {

    private FilterEditorFactory() {
    }

    public static <T> FilterComponent createDefaultRowFilter(int modelIndex, Class<T> columnClass, FilterSetter filterListener) {
        if (Number.class.isAssignableFrom(columnClass)) {
            return createNumberFilter(modelIndex, filterListener);

        } else if (String.class.isAssignableFrom(columnClass)) {
            return createStringFilter(modelIndex, filterListener);

        } else if (Boolean.class.isAssignableFrom(columnClass)) {
            return createBooleanFilter(modelIndex, filterListener);

        } else if (Date.class.isAssignableFrom(columnClass)) {
            return createDateFilter(modelIndex, filterListener);

        } else {
            return createStringFilter(modelIndex, filterListener);
        }
    }

    public static FilterComponent createNumberFilter(int modelIndex, FilterSetter filterListener) {
        return new NumberFilterComponent(modelIndex, filterListener);
    }

    public static FilterComponent createStringFilter(int modelIndex, FilterSetter filterListener) {
        return new StringFilterComponent(modelIndex, filterListener);
    }

    public static FilterComponent createBooleanFilter(int modelIndex, FilterSetter filterListener) {
        return new BooleanFilterComponent(modelIndex, filterListener);
    }

    public static FilterComponent createDateFilter(int modelIndex, FilterSetter filterListener) {
        return new DateFilterComponent(modelIndex, filterListener);
    }

    //时间过滤器
    private static class DateFilterComponent extends FilterComponent<Date> {

        private XDateChooser fristDateChooser;
        private XDateChooser toDateChooser;

        public DateFilterComponent(int modelIndex, FilterSetter filterSetter) {
            super(modelIndex, filterSetter);
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            initComponents();
            initListeners();
        }

        private void initComponents() {
            fristDateChooser = new XDateChooser();

            toDateChooser = new XDateChooser();
            add(fristDateChooser, BorderLayout.WEST);
            add(toDateChooser, BorderLayout.CENTER);
        }

        private void initListeners() {
            ((JTextField) fristDateChooser.getDateEditor().getUiComponent()).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
            ((JTextField) toDateChooser.getDateEditor().getUiComponent()).addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
            fristDateChooser.addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent evt) {
                    filterSetter.rowFilterReady();
                }
            });
            toDateChooser.addPropertyChangeListener(new PropertyChangeListener() {

                public void propertyChange(PropertyChangeEvent evt) {
                    filterSetter.rowFilterReady();
                }
            });
        }

        public DateFilterComponent(int modelIndex) {
            this(modelIndex, null);
        }

        @Override
        public void setFilterValue(Date value) {
            if (value == null) {
                fristDateChooser.setDate(null);
                toDateChooser.setDate(null);
            }
        }

        @Override
        public Date getFilterValue() {
            return null;
        }

        @Override
        public RowFilter getFilter(int modelIndex, Date filterValue) {
            return null;
        }

        @Override
        public RowFilter getFilterOfModelIndex() {
            if (fristDateChooser.getDate() == null && toDateChooser.getDate() == null) {
                return null;

            } else if (fristDateChooser.getDate() == null || toDateChooser.getDate() == null) {
                return createCompRowFilter(fristDateChooser.getDate(), toDateChooser.getDate());

            } else {
            	
                Date firstDate = DateTool.truncate(fristDateChooser.getDate(), Calendar.DATE);
                Date secondDate = DateTool.truncate(toDateChooser.getDate(), Calendar.DATE);
                if (secondDate.compareTo(firstDate) >= 0) {
                    return createCompRowFilter(fristDateChooser.getDate(), toDateChooser.getDate());

                } else {
                    return createCompRowFilter(toDateChooser.getDate(), fristDateChooser.getDate());
                }
            }
        }

        private RowFilter createCompRowFilter(Date fromDate, Date toDate) {
            //包含选择的时间
            List<RowFilter<Object, Object>> filterList = new ArrayList<RowFilter<Object, Object>>();
            //正向
            List<RowFilter<Object, Object>> previewList = null;
            if (fromDate != null) {
                Date tempDate = DateTool.truncate(fromDate, Calendar.DATE);
                previewList = new ArrayList<RowFilter<Object, Object>>();
                previewList.add(RowFilter.dateFilter(ComparisonType.AFTER, tempDate, modelIndex));
                previewList.add(RowFilter.dateFilter(ComparisonType.EQUAL, tempDate, modelIndex));
                filterList.add(RowFilter.orFilter(previewList));
            }
            if (toDate != null) {
                Date tempDate = DateTool.ceiling(toDate, Calendar.DATE);
                previewList = new ArrayList<RowFilter<Object, Object>>();
                previewList.add(RowFilter.dateFilter(ComparisonType.BEFORE, tempDate, modelIndex));
                previewList.add(RowFilter.dateFilter(ComparisonType.EQUAL, tempDate, modelIndex));
                filterList.add(RowFilter.orFilter(previewList));
            }
            return RowFilter.andFilter(filterList);
        }
    }

    //布尔值过滤器
    private static class BooleanFilterComponent extends FilterComponent<Boolean> {

        private JRadioButton yesButton;
        private JRadioButton noButton;
        private JRadioButton noneButton;

        public BooleanFilterComponent(int modelIndex, FilterSetter filterSetter) {
            super(modelIndex, filterSetter);
            setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            initComponents();
            initListeners();
        }

        private void initComponents() {
            ButtonGroup buttonGroup = new ButtonGroup();
            yesButton = new JRadioButton("y");
            yesButton.setFocusable(false);
            buttonGroup.add(yesButton);
            noButton = new JRadioButton("n");
            noButton.setFocusable(false);
            buttonGroup.add(noButton);
            noneButton = new JRadioButton("/");
            noneButton.setFocusable(false);
            noneButton.setSelected(true);
            buttonGroup.add(noneButton);
            add(yesButton);
            add(noButton);
            add(noneButton);
        }

        private void initListeners() {
            yesButton.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    if (yesButton.isSelected()) {
                        filterSetter.rowFilterReady();
                    }
                }
            });
            noButton.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    if (noButton.isSelected()) {
                        filterSetter.rowFilterReady();
                    }
                }
            });
            noneButton.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    if (noneButton.isSelected()) {
                        filterSetter.rowFilterReady();
                    }
                }
            });
        }

        public BooleanFilterComponent(int modelIndex) {
            this(modelIndex, null);
        }

        @Override
        public void setFilterValue(Boolean value) {
            if (value == null) {
                noneButton.setSelected(true);

            } else if (value.booleanValue()) {
                yesButton.setSelected(true);

            } else {
                noButton.setSelected(true);
            }
        }

        @Override
        public Boolean getFilterValue() {
            if (yesButton.isSelected()) {
                return true;

            } else if (noButton.isSelected()) {
                return false;

            } else {
                return null;
            }
        }

        @Override
        public RowFilter getFilter(int modelIndex, Boolean filterValue) {
            if (filterValue == null) {
                return null;
            } else {
                return new SimpleBooleanFilter();
            }
        }

        //简单boolean过滤器
        private class SimpleBooleanFilter extends RowFilter<TableModel, Integer> {

            public SimpleBooleanFilter() {
            }

            @Override
            public boolean include(Entry<? extends TableModel, ? extends Integer> entry) {
                Object value = entry.getValue(getModelIndex());
                if (value == null || noneButton.isSelected()) {
                    return true;

                } else if (Boolean.valueOf(value.toString()) && yesButton.isSelected()) {
                    return true;

                } else if (!Boolean.valueOf(value.toString()) && noButton.isSelected()) {
                    return true;

                } else {
                    return false;
                }
            }
        }
    }

    //字符串过滤
    private static class StringFilterComponent extends FilterComponent<String> {

        private JTextField textField;
        private JComboBox compareComboBox;

        public StringFilterComponent(int modelIndex, FilterSetter filterSetter) {
            super(modelIndex, filterSetter);
            setLayout(new BorderLayout());
            initComponents();
            initListeners();
        }

        public StringFilterComponent(int modelIndex) {
            this(modelIndex, null);
        }

        private void initComponents() {
            compareComboBox = new JComboBox();
            //包含
            compareComboBox.addItem(I18nTool.getLocalStr("inc"));
            //不包含
            compareComboBox.addItem(I18nTool.getLocalStr("exc"));
            textField = new JTextField();
            add(compareComboBox, BorderLayout.WEST);
            add(textField, BorderLayout.CENTER);
        }

        private void initListeners() {
            compareComboBox.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
            textField.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
        }

        @Override
        public void setFilterValue(String value) {
            textField.setText(value);
        }

        @Override
        public String getFilterValue() {
            return textField.getText();
        }

        @Override
        public RowFilter getFilter(int modelIndex, String filterValue) {
            String value = textField.getText();
            if (value == null || value.trim().isEmpty()) {
                return null;

            } else {
                if (getComparisonType().equals("inc")) {
                    return new RowStringFilter(value, modelIndex);

                } else {
                    return RowFilter.notFilter(new RowStringFilter(value, modelIndex));
                }
            }
        }

        protected String getComparisonType() {
            switch (compareComboBox.getSelectedIndex()) {
                case 0:
                    return "inc";
                case 1:
                    return "exc";
                default:
                    return "inc";
            }
        }
    }

    //数字过滤器
    private static class NumberFilterComponent extends FilterComponent<Number> {

        protected JComboBox compareComboBox = new JComboBox();
        protected JTextField numberText = new JTextField();

        public NumberFilterComponent(int modelIndex, FilterSetter filterSetter) {
            super(modelIndex, filterSetter);
            setLayout(new BorderLayout());

            initComponents();
        }

        public NumberFilterComponent(int modelIndex) {
            this(modelIndex, null);
        }

        private void initComponents() {
            add(compareComboBox, BorderLayout.WEST);
            add(numberText, BorderLayout.CENTER);
            compareComboBox.addItem(">");
            compareComboBox.addItem("<");
            compareComboBox.addItem("=");
            numberText.setDocument(RegexDocument.getNumberInstance());
            compareComboBox.addItemListener(new ItemListener() {

                public void itemStateChanged(ItemEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
            numberText.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    filterSetter.rowFilterReady();
                }
            });
        }

        @Override
        public void setFilterValue(Number value) {
            numberText.setText(value == null ? "" : value.toString());
        }

        @Override
        public Number getFilterValue() {
            Number number = null;
            try {
                String numberValue = numberText.getText();
                if (!numberValue.trim().isEmpty()) {
                    number = (Number) NumberFormat.getInstance().parse(numberText.getText());
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            //返回double类型确保不丢失精度
            return number == null ? null : number.doubleValue();
        }

        @Override
        public RowFilter getFilter(int modelIndex, Number filterValue) {
            if (filterValue == null) {
                return null;
            } else {
                return RowFilter.numberFilter(getComparisonType(), filterValue, modelIndex);
            }
        }

        private ComparisonType getComparisonType() {
            switch (compareComboBox.getSelectedIndex()) {
                case 0:
                    return ComparisonType.AFTER;
                case 1:
                    return ComparisonType.BEFORE;
                case 2:
                    return ComparisonType.EQUAL;
            }
            return ComparisonType.EQUAL;
        }
    }
}

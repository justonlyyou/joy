package com.kvc.joy.swing.combobox;

//~--- JDK imports ------------------------------------------------------------
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ComponentUI;

/**
 * 可过滤的ComboBox
 * @author linju
 */
public class FilterableComboBox extends JComboBox {//TODO XComboBox, but with out pinyin supports

    /**
     * 可以输入任意值
     */
    public static final int ANY = 2;
    /**
     * 只能输入存在的项
     */
    public static final int VALID_ONLY = 1;

    /**
     * Constructs ...
     *
     */
    public FilterableComboBox() {
        this(VALID_ONLY);
    }

    public FilterableComboBox(boolean autoSort) {
        this(VALID_ONLY, autoSort);
    }

    /**
     * Constructs ...
     *
     *
     * @param comboBoxModel
     */
    public FilterableComboBox(FilterableComboBoxModel comboBoxModel) {
        this(comboBoxModel, ANY);
    }

    /**
     * Constructs ...
     *
     *
     * @param inputMode
     */
    public FilterableComboBox(int inputMode) {
        this(inputMode, true);
    }

    public FilterableComboBox(int inputMode, boolean autoSort) {
        this(new FilterableComboBoxModel(autoSort), inputMode);
    }

    /**
     *
     * @param data 存放在comboBox中的数据
     */
    public FilterableComboBox(List data) {
        this(data, true);
    }

    public FilterableComboBox(List data, boolean autoSort) {
        this(new FilterableComboBoxModel(data, autoSort), VALID_ONLY);
    }

    /**
     * Constructs ...
     *
     *
     * @param mode
     * @param inputMode
     */
    public FilterableComboBox(FilterableComboBoxModel mode, int inputMode) {
        setModel(mode);
        initListener(inputMode);
        setEditable(true);
        initTextBackground();
    }

    /**
     * Method description
     *
     *
     * @param inputMode
     */
    public void initListener(final int inputMode) {
        setEditor(new FilterComboBoxEditor(inputMode == ANY));
    }

    /**
     * Method description
     *
     *
     * @param aFlag
     */
    @Override
    public void setEditable(boolean aFlag) {
        super.setEditable(true);
    }

    /**
     * Method description
     *
     */
    private void initTextBackground() {
        if (getEditor() != null) {
            getEditor().getEditorComponent().setBackground(Color.WHITE);
        }
    }

    /**
     * Method description
     *
     *
     * @param bg
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);

        if (getEditor() != null) {
            getEditor().getEditorComponent().setBackground(bg);
        }
    }

    @Override
    public FilterableComboBoxModel getModel() {
        return (FilterableComboBoxModel) super.getModel();
    }

    /**
     * Method description
     *
     *
     * @param aModel
     */
    @Override
    public void setModel(ComboBoxModel aModel) {
        if (aModel == null) {
            throw new IllegalArgumentException("Mode can't be null!");
        }

        if (!(aModel instanceof FilterableComboBoxModel)) {
            if (getModel() != null) {
                System.err.print("Warning in setModel: model must be a FilterableComboBoxModel or sub of it!!" + "\n" + "You input model is a :" + aModel.getClass().getName());
            }

            FilterableComboBoxModel autoNewModel = new FilterableComboBoxModel();

            for (int index = 0; index < aModel.getSize(); index++) {
                autoNewModel.addElement(aModel.getElementAt(index));
            }

            aModel = autoNewModel;
        }

        super.setModel(aModel);
    }

    /**
     * Method description
     *
     */
    @Override
    public void updateUI() {
        ComponentUI _ui = ui;

        super.updateUI();

        if ((_ui != null) && !_ui.getClass().equals(ui.getClass())) {
            ((FilterComboBoxEditor) getEditor()).reInstall();
        }
    }

    /**
     *
     * @param anObject
     */
    public void setSelectedItemOfModelItem(Object anObject) {
        if (anObject == null) {
            getModel().setSelectedItem(null);
        } else {
            boolean found = false;
            Object oldSelection = selectedItemReminder;

            if ((oldSelection == null) || !oldSelection.equals(anObject)) {
                for (int i = 0; i < dataModel.getSize(); i++) {
                    Object element = dataModel.getElementAt(i);

                    if (anObject.equals(element)) {
                        getModel().setSelectedItem(element);
                        found = true;

                        break;
                    }
                }

                if (!found) {
                    super.setSelectedItem(anObject);
                }
            }
        }
    }

    /**
     * Method description
     *
     */
    public void clearFilter() {
        getModel().setFilterMatch("");
        setSelectedIndex(-1);
    }

    /**
     * @return the autoSort
     */
    public boolean isAutoSort() {
        return getModel().isAutoSort();
    }

    /**
     * @param autoSort the autoSort to set
     */
    public void setAutoSort(boolean autoSort) {
        getModel().setAutoSort(autoSort);
    }

    /**
     * Class description
     *
     *
     * @version    Enter version here..., 2008-11-12
     * @author     Enter your name here...
     */
    public class FilterComboBoxEditor implements ComboBoxEditor, DocumentListener, KeyListener {

        /** Field description */
        private volatile boolean filtering = false;
        /** Field description */
        Object itemObject = null;
        /** Field description */
        private String oldString = "";
        /** Field description */
        private volatile boolean setting = false;
        /** Field description */
        private boolean canInput = false;
        /** Field description */
        private JComboBox currentComboBox;
        /** Field description */
        public JTextField text;

        /**
         * Constructs ...
         *
         *
         * @param canInput
         */
        public FilterComboBoxEditor(boolean canInput) {
            this.canInput = canInput;
            this.currentComboBox = FilterableComboBox.this;
            text = (JTextField) currentComboBox.getEditor().getEditorComponent();
            init();
        }

        /**
         * Method description
         *
         */
        protected void init() {
            text.getDocument().addDocumentListener(this);

            if (!canInput) {
                text.addKeyListener(this);

                text.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (getItemCount() > 0 && !text.getText().trim().isEmpty()) {
                            setSelectedIndex(0);
                        }
                    }
                });
            }
            text.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent e) {
                    if (!canInput) {
                        if (text.getText().isEmpty()) {
                            setSelectedItem(null);
                        } else {
                            getModel().updateFilteredItems();
                        }
                    }
                }
            });
        }

        /**
         * Method description
         *
         */
        public void reInstall() {
            JComboBox _comboBox = new JComboBox();

            _comboBox.setEditable(true);
            text = (JTextField) _comboBox.getEditor().getEditorComponent();
            currentComboBox.setEditor(_comboBox.getEditor());
            currentComboBox.setEditor(this);
            init();
        }

        /**
         * Method description
         *
         *
         * @return
         */
        @Override
        public Component getEditorComponent() {
            return text;
        }

        /**
         * Method description
         *
         *
         * @param item
         */
        @Override
        public void setItem(Object item) {
            this.itemObject = item;

            if (filtering) {
                return;
            }

            setting = true;

            String newText = (item == null) ? "" : item.toString();

            text.setText(newText);
            setting = false;
        }

        /**
         * Method description
         *
         *
         * @return
         */
        @Override
        public Object getItem() {
            if ((itemObject != null) && text.getText().equals(itemObject.toString())) {
                return this.itemObject;
            } else {
                return text.getText();
            }
        }

        /**
         * Method description
         *
         */
        @Override
        public void selectAll() {
            text.selectAll();
        }

        /**
         * Method description
         *
         *
         * @param l
         */
        @Override
        public void addActionListener(ActionListener l) {
            text.addActionListener(l);
        }

        /**
         * Method description
         *
         *
         * @param l
         */
        @Override
        public void removeActionListener(ActionListener l) {
            text.removeActionListener(l);
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            handleChange();
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleChange();
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void changedUpdate(DocumentEvent e) {
        }

        /**
         * Method description
         *
         */
        protected void handleChange() {
            if (setting) {
                return;
            }

            filtering = true;
            getModel().setFilterMatch(text.getText());

            // A bit nasty but it seems to get the popup validated properly
            setPopupVisible(false);
            setPopupVisible(true);
            filtering = false;
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void keyTyped(KeyEvent e) {
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            if (currentComboBox.getItemCount() > 0) {
                oldString = text.getText();
            }
            if (e.getKeyChar() != KeyEvent.VK_ENTER) {
                text.setText(oldString);
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Object[] items = new Object[]{
            "abc", "aab", "aba", "hpp", "pp", "hlp"
        };
        Vector model = new Vector();

        model.add("Abc");
        model.add("aAb");
        model.add("aac");
        model.add("海尔集团ddddddddddddddddddddddddddddddddddddddddddddddddddd");
        model.add("pp");
        model.add("海盗");

        // JComboBox cmb = new EditableComboBox(model);
        final FilterableComboBox cmb = new FilterableComboBox();

        cmb.setModel(new DefaultComboBoxModel(model));
        frame.getContentPane().add(cmb);

        JButton b = new JButton("clearFilter");

        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                cmb.clearFilter();
            }
        });
        frame.add(b, BorderLayout.NORTH);
        frame.add(new JButton(), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}

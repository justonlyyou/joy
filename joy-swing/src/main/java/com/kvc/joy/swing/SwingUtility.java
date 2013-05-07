package com.kvc.joy.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.EventListenerList;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.table.ColourfulTableHeadCellRenderer;

/**
 * swing工具类
 * @author zy
 */
public class SwingUtility {

    /**
     * 主界面
     */
    private JFrame mainFrame;
    /**
     * swing工具类实例
     */
    private volatile static SwingUtility swingUtil;
    /**
     * 按钮监听器
     */
    private JButtonListener jButtonListener;
    /** Field description */
    private Map<String, String> shortcutkeyToolTipMap;

    /** Creates a new instance of SwingUtility */
    private SwingUtility() {
        jButtonListener = new JButtonListener();
    }

    /**
     * 返回工具类实例
     * @return 返回工具类实例
     */
    public static SwingUtility getInstance() {
        if (swingUtil == null) {
            synchronized (SwingUtility.class) {
                if (swingUtil == null) {
                    swingUtil = new SwingUtility();
                }
            }
        }
        return swingUtil;
    }

    /**
     * 初始化按钮:如果有ICON，且没有TEXT，则将按钮设置为
     * 工具拦按钮形式（无边框的图像按钮）
     * @param initButton 要初始化的按钮
     * @param shortcutkeys 按钮的快捷键，如果没有可以不传此参数，
     * 支持多个快捷键模式
     * DEMO："ctrl S" = Key:ctrl + s
     *       "INSERT" = Key:insert
     *       "A" = Key:a
     */
    public void toolFaceForButton(JButton initButton, String... shortcutkeys) {
        initButton.setHorizontalTextPosition(JButton.CENTER);
        initButton.setBorderPainted(false);
        initButton.setContentAreaFilled(false);
        initButton.removeMouseListener(jButtonListener);
        initButton.addMouseListener(jButtonListener);

        if (shortcutkeys != null) {
            setJButtonShortcutkey(initButton, shortcutkeys);
        }
    }

    /**
     * 设置按钮的快捷键
     * @param toSetButton 要设置快捷键的按钮
     * @param shortcutkeys 按钮的快捷键
     * 支持多个快捷键模式
     * DEMO："ctrl S" = Key:ctrl + s
     *       "INSERT" = Key:insert
     *       "A" = Key:a
     */
    public void setJButtonShortcutkey(JButton toSetButton, String... shortcutkeys) {
        for (String shortcutkey : shortcutkeys) {
            shortcutkey = processKey(shortcutkey);

            if ((toSetButton.getToolTipText() == null) || toSetButton.getToolTipText().isEmpty()) {
                toSetButton.setToolTipText(getShortcutkeyToolTip(shortcutkey));
            }

            toSetButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(shortcutkey), "action" + shortcutkey);
            toSetButton.getActionMap().put("action" + shortcutkey, jButtonListener);
        }
    }

    /**
     * Method description
     *
     *
     * @param shortcutkey
     *
     * @return
     */
    private String getShortcutkeyToolTip(String shortcutkey) {
        if (shortcutkeyToolTipMap == null) {
            shortcutkeyToolTipMap = new HashMap<String, String>();

            shortcutkeyToolTipMap.put("F1", I18nTool.getLocalStr("Help"));
            shortcutkeyToolTipMap.put("ctrl N", I18nTool.getLocalStr("AddNew"));
            shortcutkeyToolTipMap.put("ctrl S", I18nTool.getLocalStr("Save"));
            shortcutkeyToolTipMap.put("DELETE", I18nTool.getLocalStr("Delete"));
            shortcutkeyToolTipMap.put("alt F4", I18nTool.getLocalStr("Exit"));
            shortcutkeyToolTipMap.put("ctrl P", I18nTool.getLocalStr("Print"));
            shortcutkeyToolTipMap.put("F2", I18nTool.getLocalStr("Edit"));
            shortcutkeyToolTipMap.put("F5", I18nTool.getLocalStr("Refresh"));
            shortcutkeyToolTipMap.put("ctrl shift S", I18nTool.getLocalStr("SaveAs"));
        }

        return shortcutkeyToolTipMap.get(shortcutkey);
    }

    /**
     * Method description
     *
     *
     * @param shortcutkey
     *
     * @return
     */
    private String processKey(String shortcutkey) {
        if (shortcutkey == null) {
            throw new IllegalArgumentException("shortcutkey cannot be null!!");
        }

        String processedKey = "";
        int index = 0;
        String[] shorKeys = shortcutkey.split(" ");

        for (String shorKey : shorKeys) {
            if (index == shorKeys.length - 1) {
                shorKey = shorKey.toUpperCase();
            } else {
                shorKey = shorKey.toLowerCase();
            }

            processedKey += ((index > 0) ? " " : "") + shorKey;
            index++;
        }

        return processedKey;
    }

    /**
     * 设置面板内的所有子面板是否可用
     * @param parentCompont 要设置的容器{@link JComponent}
     * @param enable 是否可用{@link boolean }
     */
    public void setChildCompontEnable(JComponent parentCompont, boolean enable) {
        for (int index = 0; index < parentCompont.getComponentCount(); index++) {
            parentCompont.getComponent(index).setEnabled(enable);

            if (parentCompont.getComponent(index) instanceof JComponent) {
                JComponent childComponent = (JComponent) parentCompont.getComponent(index);

                if (childComponent.getComponentCount() > 0) {
                    setChildCompontEnable(childComponent, enable);
                }
            }
        }
    }

    /**
     * 清空指定容器面板内的内容
     * @param parentCompont
     */
    public void cleanChildrenCompontContent(JComponent parentCompont) {
        Vector<JComponent> componentList = new Vector<JComponent>(parentCompont.getComponentCount());

        for (Component childComponent : parentCompont.getComponents()) {
            if (childComponent instanceof JComponent) {
                componentList.add((JComponent) childComponent);
            }
        }

        for (int childIndex = 0; childIndex < componentList.size();) {
            JComponent childComponent = componentList.get(childIndex);

            // 如果是文本类组件就清空文本框
            if (childComponent instanceof JTextComponent) {
                ((JTextComponent) childComponent).setText("");
            } // 如果是下拉列表框，就选到第一项纪录
            else if (childComponent instanceof JComboBox) {
                ((JComboBox) childComponent).setSelectedIndex(-1);
            }

            componentList.remove(childComponent);

            if (childComponent.getComponentCount() > 0) {
                for (Component grandSonComponent : childComponent.getComponents()) {
                    if (grandSonComponent instanceof JComponent) {
                        componentList.add((JComponent) grandSonComponent);
                    }
                }
            }
        }
    }

    /**
     * 创建一个有输入上限的文档编辑器，可以用在文本输入框中
     * @param upperLimitLength 输入上限
     * @return 返回文档编辑器实例
     */
    public static Document createUpperLimitDocument(final int upperLimitLength) {
        return new PlainDocument() {

            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                if (offs <= upperLimitLength) {
                    if (str.length() + getLength() > upperLimitLength) {
                        str = str.substring(0, upperLimitLength - getLength());
                    }

                    super.insertString(offs, str, a);
                }
            }
        };
    }

    /**
     * 释放组件中的所有资源，包括该组件子组件的所有资源
     * @param comp
     */
    public static void freeComponent(Component comp) {
        UIReleaseUtil.freeSwingObject(comp);
    }

    /**
     * 初始化组件（此方法暂时无效）
     * @param component
     */
    public void initComponent(JComponent component) {
        Vector<JComponent> componentList = new Vector<JComponent>(component.getComponentCount());

        for (Component childComponent : component.getComponents()) {
            if (childComponent instanceof JComponent) {
                componentList.add((JComponent) childComponent);
            }
        }

        for (int childIndex = 0; childIndex < componentList.size();) {
            JComponent childComponent = componentList.get(childIndex);

            // 如果是文本类组件就清空文本框
            if (childComponent instanceof JTable) {
                setTableDefautSetting((JTable) childComponent);
            }

            componentList.remove(childComponent);

            if (childComponent.getComponentCount() > 0) {
                for (Component grandSonComponent : childComponent.getComponents()) {
                    if (grandSonComponent instanceof JComponent) {
                        componentList.add((JComponent) grandSonComponent);
                    }
                }
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param table
     */
    private void setTableDefautSetting(JTable table) {    // 编辑自动全选，暂时不要

//      for (int index = 0; index < table.getColumnCount(); index++) {
//          TableCellEditor cellEditor = table.getCellEditor(0, index);
//          if (cellEditor == null) {
//              continue;
//          }
//          Component component = cellEditor.getTableCellEditorComponent(table, null, table.isCellSelected(0, index), 0, index);
////                  table.prepareEditor(table.getCellEditor(0, index), 0, index);
//          if (component instanceof JTextComponent) {
//              component.removeFocusListener(focusListener);
//              component.addFocusListener(focusListener);
//          }
//      }
        ColourfulTableHeadCellRenderer.install(table);
    }

    /**
     * 创建表格菜单，此方法创建一个弹出菜单，默认实现右键点击的时候选中表格。
     * @param table 表格
     * @param menuItems 菜单项
     * @return 返回弹出菜单
     */
    public static JPopupMenu createTablePopuMenu(final JTable table, JMenuItem... menuItems) {
        JPopupMenu menu = new JPopupMenu();
        MouseAdapter mouselis = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent evt) {
                if (evt.isMetaDown()) {
                    ListSelectionModel selectionModel = table.getSelectionModel();
                    int rowOfPoint = table.rowAtPoint(evt.getPoint());

                    if (rowOfPoint >= 0) {
                        if (evt.isShiftDown()) {
                            int selectIndex = selectionModel.getAnchorSelectionIndex();

                            if (selectIndex < 0) {
                                selectIndex = rowOfPoint;
                            }

                            selectionModel.setSelectionInterval(selectIndex, rowOfPoint);
                        } else if (evt.isControlDown()) {
                            if (selectionModel.isSelectedIndex(rowOfPoint)) {
                                selectionModel.removeSelectionInterval(rowOfPoint, rowOfPoint);
                            } else {
                                selectionModel.addSelectionInterval(rowOfPoint, rowOfPoint);
                            }
                        } else {
                            if (!table.isRowSelected(rowOfPoint)) {
                                selectionModel.setSelectionInterval(rowOfPoint, rowOfPoint);
                            }
                        }
                    }

                    ListSelectionModel columnSelectionModel = table.getColumnModel().getSelectionModel();
                    int columnOfPoint = table.columnAtPoint(evt.getPoint());

                    if (columnOfPoint >= 0) {
                        if (!table.isColumnSelected(columnOfPoint)) {
                            columnSelectionModel.setSelectionInterval(columnOfPoint, columnOfPoint);
                        }
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent evt) {
                if (evt.isMetaDown()) {
                    int rowOfPoint = table.rowAtPoint(evt.getPoint());
                    int selectIndex = table.getSelectionModel().getAnchorSelectionIndex();

                    if ((selectIndex >= 0) && (rowOfPoint >= 0)) {
                        if (evt.isControlDown()) {
                            if (table.isRowSelected(selectIndex)) {
                                table.addRowSelectionInterval(selectIndex, rowOfPoint);
                            } else {
                                table.removeRowSelectionInterval(selectIndex, rowOfPoint);
                            }
                        } else {
                            table.setRowSelectionInterval(selectIndex, rowOfPoint);
                        }
                    }

                    ListSelectionModel columnSelectionModel = table.getColumnModel().getSelectionModel();
                    int columnOfPoint = table.columnAtPoint(evt.getPoint());

                    if (columnOfPoint >= 0) {
                        if (!table.isColumnSelected(columnOfPoint)) {
                            columnSelectionModel.setSelectionInterval(columnOfPoint, columnOfPoint);
                        }
                    }
                }
            }
        };

        table.addMouseListener(mouselis);
        table.addMouseMotionListener(mouselis);

        for (JMenuItem menuItem : menuItems) {
            menu.add(menuItem);
        }

        table.setComponentPopupMenu(menu);

        return menu;
    }

    /**
     * 通知组件绘制完毕
     * @param lisList 监听器列表
     */
    public static void fireComponentAftrePaint(EventListenerList lisList) {

        // Guaranteed to return a non-null array
        Object[] listeners = lisList.getListenerList();

        // Process the listeners last to first, notifying
        // those that are interested in this event
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == XComponentListener.class) {

                // Lazily create the event:
                // if (changeEvent == null)
                // changeEvent = new ChangeEvent(this);
                ((XComponentListener) listeners[i + 1]).postPaint(null);
            }
        }
    }

    /**
     * 按钮监听器，用来实现快捷键设置
     */
    private class JButtonListener extends AbstractAction implements MouseListener {

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ((JButton) e.getSource()).doClick();
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            e.isShiftDown();
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void mouseReleased(MouseEvent e) {
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            ((JButton) e.getSource()).setBorderPainted(((JButton) e.getSource()).isEnabled());
            ((JButton) e.getSource()).setContentAreaFilled(true);
        }

        /**
         * Method description
         *
         *
         * @param e
         */
        @Override
        public void mouseExited(MouseEvent e) {
            ((JButton) e.getSource()).setContentAreaFilled(false);
            ((JButton) e.getSource()).setBorderPainted(false);
        }
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}

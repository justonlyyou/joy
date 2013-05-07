package com.kvc.joy.swing.combobox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.kvc.joy.commons.lang.string.I18nTool;
import com.kvc.joy.swing.SwingUtility;
import com.kvc.joy.swing.XSwingUtil;
import com.kvc.joy.swing.tree.CommonTreeNode;

/**
 * 树型表示的下拉框
 * @author zy
 */
public class TreeComboBox
        extends javax.swing.JPanel
        implements KeyListener, ItemSelectable, MouseMotionListener, MouseListener, TreeExpansionListener {

    /**
     * 下拉框中的树
     */
    protected JTree tree;
    /**
     * 选择的路径
     */
    private TreePath selectedPath;
    /**
     * 选择的对象
     */
    protected Object selectedItemReminder;
    /**
     * 弹出的树
     */
    protected JPopupMenu popupTree;
    private JScrollPane scrollPane;
    private int popuHight;
    private int popuWidth;
    private boolean resizable = true;
    public static final int DEFAULT_POPU_MIN_SIZE = 100;
    public static final int DEFAULT_POPU_MAX_SIZE = 450;
    private Insets autoscrollInsets = new Insets(20, 20, 20, 20); // insets
    private Color background = UIManager.getColor("List.background");
    private JList nodeList;
    private String currentText = "";

    /** Creates new form NewJPanel */
    public TreeComboBox() {
        this(new DefaultTreeModel(new DefaultMutableTreeNode(I18nTool.getLocalStr("Loading"))));
    }

    /**
     * 要显示的树模型
     * @param treeModel 树模型
     */
    public TreeComboBox(TreeModel treeModel) {
        initComponents();
        initTree(treeModel);
    }

    /**
     * 通过树的根节点构造一棵树
     * @param root 树的根节点
     */
    public TreeComboBox(TreeNode root) {
        initComponents();
        setRoot(root);
    }

    public void setPopuSize(int width, int hight) {
        setPopuWidth(width);
        setPopuHight(hight);
    }

    public void setPopuWidth(int popuWidth) {
        this.popuWidth = popuWidth;
    }

    public void setPopuHight(int popuHight) {
        this.popuHight = popuHight;
    }

    public void setPopuTreeRootVisible(boolean visible) {
        this.tree.setRootVisible(visible);
        this.tree.setShowsRootHandles(visible);
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;

    }

    protected void setTextIcon(Icon icon) {
        iconLabel.setIcon(icon);
    }

    /**
     * 创建要展现的树
     */
    private void initTreePopup() {
        if (tree == null) {
            initTree(new DefaultTreeModel(new DefaultMutableTreeNode(I18nTool.getLocalStr("Loading"))));
        }
        popupTree = new JPopupMenu() {

            @Override
            public void pack() {
                super.pack();
                setPopuSize(popupTree.getSize().width, popupTree.getSize().height);
            }
        };

        popupTree.add(scrollPane);
        popupTree.setBorder(BorderFactory.createEmptyBorder());
        popupTree.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                setPopuSize(popupTree.getSize().width, popupTree.getSize().height);
                popupTree.setVisible(false);
            }
        });
    }

    private void createInnerScrollPane() {
        scrollPane = new JScrollPane(tree);
        JLabel iconLable = new JLabel(XSwingUtil.getIconInJar("ResizeGripper.gif"));

        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, iconLable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        iconLable.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        iconLable.addMouseMotionListener(this);
    }

    /**
     * 创建树
     * @param treeModel 树模型
     */
    private void initTree(TreeModel treeModel) {
        this.tree = new JTree(treeModel);
        tree.expandRow(0);
        tree.addMouseListener(this);
        tree.addMouseMotionListener(this);
        tree.addKeyListener(this);
        tree.addTreeExpansionListener(this);
        initTreeScroll();
    }

    private void initTreeScroll() {
        if (resizable) {
            createInnerScrollPane();
        } else {
            scrollPane = new JScrollPane(tree);
        }
    }

    protected void showSelectedObjectInfo() {

        setPopuSize(popupTree.getSize().width, popupTree.getSize().height);
        popupTree.setVisible(false);
        chooseResultLabel.requestFocus();
        if (tree.getLastSelectedPathComponent() != null) {
            selectedItemChanged();
        }
    }

    /**
     * 得到下拉窗中的树
     * @return 下拉窗中的树
     */
    public TreeModel getTreeModel() {
        return tree == null ? null : tree.getModel();
    }

    public JTree getTree() {
        return tree;
    }

    /**
     * 设置下拉窗中的树
     * @param treeModel 要显示的树的模型
     */
    public void setTreeModel(TreeModel treeModel) {
        if (tree == null) {
            initTree(treeModel);
        } else {
            this.tree.setModel(treeModel);
            clearSelection();
        }
    }

    public Object getSelectedItem() {
        String text = chooseResultLabel.getText().trim();
        if (!text.isEmpty()) {
            String selectedItemSt = selectedItemReminder == null ? null : selectedItemReminder.toString();
            if (text.equals(selectedItemSt)) {
                return selectedItemReminder;
            }
        }
        return null;
    }

    public TreeNode getSelectedNode() {
        Object selectedItem = getSelectedItem();
        TreePath treePath = findObjectInTree(selectedItem);
        if (treePath == null) {

            return null;
        } else {
            Object lastPathComponent = treePath.getLastPathComponent();
            return (TreeNode) lastPathComponent;
        }
    }

    /**
     * 得到所选取的对象
     * @return 所选取的对象
     */
    public void setSelectedItem(Object object) {
        setSelectedItem(object, true);
    }

    protected void setSelectedItem(Object object, boolean fireItemStateChange) {
        if (object == null) {
            clearSelection();
        } else {
            TreePath findTreePath = findObjectInTree(object);
            if (findTreePath == null) {
                System.err.println("Can not find the '" + object + "' in the tree.");
                clearSelection(object);
            } else {
                tree.setSelectionPath(findTreePath);
                if (fireItemStateChange) {
                    selectedItemChanged();
                } else {
                    changeSelectedItem();
                }
            }
        }
    }

    private TreePath findObjectInTree(Object userObject) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();

        TreePath treePath = null;
        DefaultMutableTreeNode findNode;
        if (root != null) {
            if (userObject.equals(root.getUserObject())) {
                treePath = new TreePath(root.getPath());
            } else {
                Enumeration<DefaultMutableTreeNode> nodeEnum = root.breadthFirstEnumeration();
                for (; nodeEnum.hasMoreElements();) {
                    findNode = nodeEnum.nextElement();
                    if (userObject.equals(findNode.getUserObject())) {
                        treePath = new TreePath(findNode.getPath());
                        break;
                    }
                }
            }
        }
        return treePath;
    }

    public void clearSelection() {
        clearSelection(null);
    }

    private void clearSelection(Object selectedItem) {
        this.tree.clearSelection();
        this.selectedPath = null;
        this.selectedItemReminder = selectedItem;
        String selectInfo = selectedItem == null ? "" : selectedItem.toString();
        this.chooseResultLabel.setText(selectInfo);
        chooseResultLabel.setToolTipText(selectInfo);
    }

    /**
     * 设置树的根节点
     * @param root 根节点
     */
    public void setRoot(TreeNode root) {
        if (tree == null) {
            initTree(new DefaultTreeModel(root));
        } else if (tree.getModel() instanceof DefaultTreeModel) {
            ((DefaultTreeModel) tree.getModel()).setRoot(root);
        }

        if (root instanceof CommonTreeNode) {
            ((CommonTreeNode) root).explore();
            ((DefaultTreeModel) tree.getModel()).nodeStructureChanged(root);
        }

        this.selectedItemReminder = root instanceof DefaultMutableTreeNode ? ((DefaultMutableTreeNode) root).getUserObject() : root;
        tree.setSelectionRow(0);
        this.chooseResultLabel.setText(root.toString());
        selectedItemChanged();
    }

    public void setTreeRootVisible(boolean hideRoot) {
        this.tree.setRootVisible(false);
        this.tree.setShowsRootHandles(true);
    }

    public void setTreeCellRenderer(TreeCellRenderer treeCellRenderer) {
        this.tree.setCellRenderer(treeCellRenderer);
    }

    public TreeCellRenderer getTreeCellRenderer() {
        return this.tree.getCellRenderer();
    }

    /**
     * 初始化面板
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        collapseJButton = new javax.swing.JButton();
        chooseResultLabel = new javax.swing.JTextField();
        iconLabel = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        setLayout(new java.awt.BorderLayout());

        collapseJButton.setText("...");
        collapseJButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        collapseJButton.setMaximumSize(new java.awt.Dimension(20, 20));
        collapseJButton.setMinimumSize(new java.awt.Dimension(20, 20));
        collapseJButton.setPreferredSize(new java.awt.Dimension(20, 20));
        collapseJButton.addActionListener(formListener);
        add(collapseJButton, java.awt.BorderLayout.EAST);

        chooseResultLabel.setBackground(background);
        chooseResultLabel.addKeyListener(formListener);
        add(chooseResultLabel, java.awt.BorderLayout.CENTER);
        add(iconLabel, java.awt.BorderLayout.WEST);
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.KeyListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == collapseJButton) {
                TreeComboBox.this.collapseJButtonActionPerformed(evt);
            }
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == chooseResultLabel) {
                TreeComboBox.this.chooseResultLabelKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 显示下拉框树
     */
    private void showPopuTree(boolean requestFocus) {
        if (this.popupTree == null) {
            initTreePopup();
        }
        int preferredHight = this.popuHight > 0 ? popuHight : (Math.min(Math.max(this.tree.getHeight(), DEFAULT_POPU_MIN_SIZE), DEFAULT_POPU_MAX_SIZE));
        popupTree.setPreferredSize(new Dimension(popuWidth > 0 ? popuWidth : this.getWidth(), preferredHight));
        if (this.selectedPath != null) {
            tree.setSelectionPath(selectedPath);
            tree.scrollPathToVisible(selectedPath);
        }
        popupTree.show(this, 0, this.getSize().height);
        if (requestFocus) {
            scrollPane.setViewportView(tree);
        } else {
            chooseResultLabel.requestFocusInWindow();
        }
    }

    /**
     *搜索事件
     */
    protected void filterTree() {

        String filterKey = chooseResultLabel.getText().trim();
        //如果搜索框文本长度大于0
        filterKey = filterKey.toLowerCase();
        //清除当前树的选择
        tree.clearSelection();
        nodeList.clearSelection();
        ((DefaultListModel) nodeList.getModel()).removeAllElements();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
        //搜索树
        Enumeration<TreeNode> nodeEnum = root.breadthFirstEnumeration();
        for (; nodeEnum.hasMoreElements();) {
            TreeNode treeNode = nodeEnum.nextElement();
            String nodeInfo = treeNode.toString();
            if (nodeInfo != null && nodeInfo.toLowerCase().contains(filterKey)) {
                if (!((DefaultListModel) nodeList.getModel()).contains(treeNode)) {
                    ((DefaultListModel) nodeList.getModel()).addElement(treeNode);
                }
            }
        }
    }

    /**
     * 点击弹出下拉框的树
     * @param evt
     */
    private void collapseJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collapseJButtonActionPerformed
        if (this.isEnabled()) {
            showPopuTree(true);
        }
    }//GEN-LAST:event_collapseJButtonActionPerformed

    private void chooseResultLabelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chooseResultLabelKeyReleased

        if (evt.getKeyChar() != KeyEvent.VK_ENTER) {
            if (nodeList == null) {
                nodeList = new JList(new DefaultListModel());
                nodeList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting() && nodeList.getSelectedValue() != null) {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeList.getSelectedValue();
                            tree.setSelectionPath(new TreePath(node.getPath()));
                            setSelectedItem(node.getUserObject());
                            showSelectedObjectInfo();
                        }
                    }
                });
                nodeList.setCellRenderer(new DefaultListCellRenderer() {

                    @Override
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        TreePath path = new TreePath(((DefaultMutableTreeNode) value).getPath());
                        return tree.getCellRenderer().getTreeCellRendererComponent(tree, value, isSelected, tree.isExpanded(path), tree.getModel().isLeaf(value), tree.getRowForPath(path), cellHasFocus);
                    }
                });
            }
            nodeList.addMouseListener(this);
            JComponent showComponent = null;
            if (chooseResultLabel.getText().isEmpty()) {
                showComponent = tree;
            } else {
                filterTree();
                showComponent = nodeList;
            }
            scrollPane.setViewportView(showComponent);
            showPopuTree(false);
            if (nodeList.getModel().getSize() > 0) {
                currentText = chooseResultLabel.getText().trim();
            } else {
                chooseResultLabel.setText(currentText);
                if (currentText.isEmpty()) {
                    showComponent = tree;
                } else {
                    filterTree();
                    showComponent = nodeList;
                }
                scrollPane.setViewportView(showComponent);
            }
        } else if (nodeList.getModel().getSize() > 0) {
            nodeList.setSelectedIndex(0);
        }
    }//GEN-LAST:event_chooseResultLabelKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chooseResultLabel;
    private javax.swing.JButton collapseJButton;
    private javax.swing.JLabel iconLabel;
    // End of variables declaration//GEN-END:variables

    private void autoScroll(Point cursorLocation) {
        Insets insets = this.autoscrollInsets;
        Rectangle outer = tree.getVisibleRect();
        Rectangle inner = new Rectangle(outer.x + insets.left, outer.y + insets.top, outer.width - (insets.left + insets.right), outer.height - (insets.top + insets.bottom));
        if (!inner.contains(cursorLocation)) {
            Rectangle scrollRect = new Rectangle(cursorLocation.x - insets.left, cursorLocation.y - insets.top, insets.left + insets.right, insets.top + insets.bottom);
            tree.scrollRectToVisible(scrollRect);
        }
    }

    @Override
    public Object[] getSelectedObjects() {
        Object selectedObject = getSelectedItem();
        if (selectedObject == null) {
            return new Object[0];
        } else {
            Object result[] = new Object[1];
            result[0] = selectedObject;
            return result;
        }
    }

    @Override
    public void addItemListener(ItemListener aListener) {
        listenerList.add(ItemListener.class, aListener);
    }

    @Override
    public void removeItemListener(ItemListener aListener) {
        listenerList.remove(ItemListener.class, aListener);
    }

    protected void changeSelectedItem() {
        Object treeNode = tree.getLastSelectedPathComponent();
        if (treeNode instanceof DefaultMutableTreeNode) {
            this.selectedItemReminder = ((DefaultMutableTreeNode) treeNode).getUserObject();
        } else {
            selectedItemReminder = treeNode == null ? null : treeNode.toString();
        }
        String selectedValue = selectedItemReminder == null ? "" : selectedItemReminder.toString();
        chooseResultLabel.setText(selectedValue);
        this.chooseResultLabel.setToolTipText(selectedValue);
        this.selectedPath = tree.getSelectionPath();
    }

    protected void selectedItemChanged() {
        changeSelectedItem();
        if (selectedItemReminder != null) {
            fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED,
                    selectedItemReminder,
                    ItemEvent.DESELECTED));
            fireItemStateChanged(new ItemEvent(this, ItemEvent.ITEM_STATE_CHANGED,
                    selectedItemReminder,
                    ItemEvent.SELECTED));
        }
    }

    protected void fireItemStateChanged(ItemEvent e) {

        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ItemListener.class) {
                ((ItemListener) listeners[i + 1]).itemStateChanged(e);
            }
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        SwingUtility.getInstance().setChildCompontEnable(this, enabled);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getComponent().isEnabled() && tree.getPathForLocation(e.getX(), e.getY()) != null) {
            tree.setSelectionPath(tree.getPathForLocation(e.getX(), e.getY()));
            autoScroll(e.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource() != this.tree) {
            Point endPoint = e.getLocationOnScreen();
            Point startPoint = popupTree.getLocationOnScreen();

            this.popupTree.setPreferredSize(
                    new Dimension(countSize(endPoint.x - startPoint.x + 10),
                    countSize(endPoint.y - startPoint.y + 10)));
            this.popupTree.pack();
        }
    }

    private int countSize(int dragSize) {
        return Math.min(Math.max(dragSize, DEFAULT_POPU_MIN_SIZE), DEFAULT_POPU_MAX_SIZE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getComponent().isEnabled() && tree.getPathForLocation(e.getX(), e.getY()) != null) {
            showSelectedObjectInfo();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (tree.getSelectionRows() != null) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE && tree.getSelectionRows()[0] > 0) {
                if (tree.isExpanded(tree.getSelectionRows()[0])) {
                    tree.collapseRow(tree.getSelectionRows()[0]);
                } else {
                    tree.expandRow(tree.getSelectionRows()[0]);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                showSelectedObjectInfo();
            }
        }
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        Object node = event.getPath().getLastPathComponent();
        if (node instanceof CommonTreeNode) {
            ((CommonTreeNode) node).explore((DefaultTreeModel) tree.getModel());
        }
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
    }

    @Override
    public void setBackground(Color bg) {
        this.background = bg;
        if (chooseResultLabel != null) {
            chooseResultLabel.setBackground(bg);
        }
        super.setBackground(bg);
    }

    public static void main(String[] args) {
        JFrame jFrame = new javax.swing.JFrame();
        final TreeComboBox treeComboBoxPanel = new TreeComboBox();
        treeComboBoxPanel.setBackground(Color.RED);
        jFrame.getContentPane().add(treeComboBoxPanel);
//        treeComboBoxPanel.setTreeModel(new DefaultTreeModel())
        treeComboBoxPanel.setRoot(createTestTree());
        treeComboBoxPanel.setSelectedItem("ssssssss");
        System.out.println("selected item :" + treeComboBoxPanel.getSelectedItem());
//        treeComboBoxPanel.setEnabled(false);
        jFrame.pack();
        java.awt.Dimension dimension = jFrame.getToolkit().getScreenSize();
//        jFrame.setLocation( dimension.width-jFrame.getWidth()/2,dimension.width-jFrame.getHeight()/2);
        jFrame.setBounds(200, 200, 100, 40);
        jFrame.setVisible(true);
        //   jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(
                WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("selected ityem :" + treeComboBoxPanel.getSelectedItem());
                System.exit(0);
            }
        });
    }

    public static DefaultMutableTreeNode createTestTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        for (int index = 0; index < 10; index++) {
            DefaultMutableTreeNode rootChildNode = new DefaultMutableTreeNode("node" + index);
            for (int j = 0; j < 10; j++) {
                rootChildNode.add(new DefaultMutableTreeNode("nodessssssssssssssssssssssssssssssss" + j));
            }
            root.add(rootChildNode);
        }

        return (root);
    }

    public JButton getButton() {
        return collapseJButton;
    }

}

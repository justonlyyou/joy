package org.joy.swing.tree;


import org.joy.swing.button.ToolButton;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;

/**
 * tree������
 * @author  zy
 */
public class DefaultTreeFilter extends javax.swing.JPanel implements ListSelectionListener {

    public DefaultTreeFilter() {
        initComponents();
    }

    public DefaultTreeFilter(JTree demandToFilterTree) {
        this();
        this.setDemandToFilterTree(demandToFilterTree);
    }

    public DefaultTreeFilter(JTree demandToFilterTree, JList resultList) {
        this(demandToFilterTree);
        this.setResultList(resultList);
    }

    public void setDemandToFilterTree(JTree demandToFilterTree) {
        this.demandToFilterTree = demandToFilterTree;
        if (demandToFilterTree.getParent().getParent() instanceof JScrollPane) {
            this.scrollPane = (JScrollPane) demandToFilterTree.getParent().getParent();
        }
        if (filterResultsList == null) {
            setResultList(new JList());
        }
    }

    public void setResultList(JList resultList) {
        this.filterResultsList = resultList;
        resultList.setModel(new DefaultListModel());
        resultList.addListSelectionListener(this);

        resultList.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(
                    JList list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {
                JLabel superRender = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof CommonTreeNode) {
                    superRender.setIcon(((CommonTreeNode) value).getIcon());
                }
                return superRender;
            }
        });
    }

    public JTree getDemandToFilterTree() {
        return this.demandToFilterTree;
    }

    public JList getResultList() {
        return this.filterResultsList;
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputFilterConditionTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        toDoFilterButton = new ToolButton();
        cleanFilterConditionButton = new ToolButton();

        setLayout(new java.awt.BorderLayout());

        inputFilterConditionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFilterConditionTextFieldActionPerformed(evt);
            }
        });
        add(inputFilterConditionTextField, java.awt.BorderLayout.CENTER);

        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new java.awt.BorderLayout());

        toDoFilterButton.setButtonStyle(ToolButton.Styles.FILTER_BUTTON);
        toDoFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toDoFilterButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(toDoFilterButton, java.awt.BorderLayout.CENTER);

        cleanFilterConditionButton.setButtonStyle(ToolButton.Styles.SEARCH_CLEAR_BUTTON);
        cleanFilterConditionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanFilterConditionButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cleanFilterConditionButton, java.awt.BorderLayout.EAST);

        add(buttonPanel, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents
    private void inputFilterConditionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFilterConditionTextFieldActionPerformed

        toDoFilterButton.doClick();
    }//GEN-LAST:event_inputFilterConditionTextFieldActionPerformed

    private void toDoFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toDoFilterButtonActionPerformed
        if (!inputFilterConditionTextField.getText().trim().isEmpty()) {
            demandToFilterTree.clearSelection();

            ((DefaultTreeModel) demandToFilterTree.getModel()).nodeStructureChanged((TreeNode) this.demandToFilterTree.getModel().getRoot());
            ((DefaultListModel) this.filterResultsList.getModel()).clear();
            searchTree();
            this.scrollPane.setViewportView(this.filterResultsList);
            this.demandToFilterTree.scrollPathToVisible(demandToFilterTree.getSelectionPath());
        }
}//GEN-LAST:event_toDoFilterButtonActionPerformed

    private void cleanFilterConditionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanFilterConditionButtonActionPerformed
        inputFilterConditionTextField.setText("");
        this.scrollPane.setViewportView(this.demandToFilterTree);

        ((DefaultListModel) this.filterResultsList.getModel()).clear();
        this.demandToFilterTree.scrollPathToVisible(demandToFilterTree.getSelectionPath());
}//GEN-LAST:event_cleanFilterConditionButtonActionPerformed

    private void searchTree() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) this.demandToFilterTree.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> nodeEnum = root.depthFirstEnumeration();
        DefaultMutableTreeNode subTreeNode = null;
        for (; nodeEnum.hasMoreElements();) {
            subTreeNode = nodeEnum.nextElement();
            String nodeInfo = subTreeNode.getUserObject().toString();
            if (nodeInfo.toLowerCase(Locale.ENGLISH).contains(inputFilterConditionTextField.getText().trim().toLowerCase(Locale.ENGLISH))) {
                ((DefaultListModel) filterResultsList.getModel()).addElement(subTreeNode);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (filterResultsList.getSelectedValue() != null) {
            this.demandToFilterTree.setSelectionPath(new TreePath(((DefaultMutableTreeNode) this.filterResultsList.getSelectedValue()).getPath()));
        }
    }

    public Collection getFilterResult() {
        Collection filterResult = new HashSet();
        if (isListViewMode()) {
            ListModel listModel = filterResultsList.getModel();
            for (int index = 0; index < listModel.getSize(); index++) {
                filterResult.add(listModel.getElementAt(index));
            }
        }
        return filterResult;
    }

    public boolean isListViewMode() {
        return scrollPane.getViewport() != null && scrollPane.getViewport().getView() == filterResultsList;
    }
    JScrollPane scrollPane;
    JList filterResultsList;
    JTree demandToFilterTree;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel buttonPanel;
    ToolButton cleanFilterConditionButton;
    javax.swing.JTextField inputFilterConditionTextField;
    ToolButton toDoFilterButton;
    // End of variables declaration//GEN-END:variables
}

/*
 * TableFilter.java
 */

package org.joy.swing.table;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import org.joy.swing.XSwingUtil;

/**
 *��������
 * @author  zy
 */
public class TableFilter extends javax.swing.JPanel {
    
    private RowStringFilter rowStringFilter = null;
    
    /**
     *������
     */
    public TableFilter() {
        initComponents();
    }
    /**
     * ������
     * @param searchForTable Ҫ���˵ı��
     */
    public TableFilter(JTable searchForTable){
        this();
        setTable(searchForTable);
    }
    /**
     * ������
     * @param searchForTable Ҫ���˵ı��
     * @param searchIndices Ҫ���˵���
     */
    public TableFilter(JTable searchForTable, int... searchIndices){
        this(searchForTable);
        this.setSearchColumns(searchIndices);
        rowStringFilter = new RowStringFilter(searchIndices);
    }
    
    /**
     * ����Ҫ���˵ı��
     * @param searchForTable Ҫ���˵ı��
     */
    public void setTable(JTable searchForTable){
        this.searchForTable=searchForTable;
        setRowSort();
    }
    
    /**
     * ��ù�������ǰ��Ӧ�ı��
     * @return ���ع�������ǰ��Ӧ�ı��
     */
    public JTable getTable(){
        return this.searchForTable;
    }
    
    /**
     * ����Ҫ���˵���
     * @param searchIndices Ҫ���˵���
     */
    public void setSearchColumns(int... searchIndices){
        this.searchIndices=searchIndices;
    }
    
    /**
     * Description: �����ı����ܷ�༭
     * @param boolean
     */
    public void setTextFiledEditable(boolean editable) {
        this.filterTextField.setEditable(editable);
    }
    
    /**
     * Description: �����ı��������
     * @param String �ı��������
     */
    public void setTextFiledText(String text) {
        this.filterTextField.setText(text);
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterTextField = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        cleanButton = new javax.swing.JButton();

        filterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextFieldActionPerformed(evt);
            }
        });

        filterButton.setBackground(new java.awt.Color(255, 255, 255));
        filterButton.setIcon(XSwingUtil.getIconInJar("Filter.gif"));
        filterButton.setBorderPainted(false);
        filterButton.setOpaque(false);
        filterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                filterButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                filterButtonMouseExited(evt);
            }
        });
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        cleanButton.setBackground(new java.awt.Color(255, 255, 255));
        cleanButton.setIcon(XSwingUtil.getIconInJar("Search_clear.gif"));
        cleanButton.setBorderPainted(false);
        cleanButton.setOpaque(false);
        cleanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cleanButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cleanButtonMouseExited(evt);
            }
        });
        cleanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(filterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cleanButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cleanButton, filterButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filterTextField)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(cleanButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addComponent(filterButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cleanButton, filterButton});

    }// </editor-fold>//GEN-END:initComponents
    
    private void cleanButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cleanButtonMouseEntered
        cleanButton.setBackground(this.getBackground());
    }//GEN-LAST:event_cleanButtonMouseEntered
    
    private void cleanButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cleanButtonMouseExited
        cleanButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_cleanButtonMouseExited
    
    private void filterButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterButtonMouseExited
        filterButton.setBackground(Color.WHITE);
    }//GEN-LAST:event_filterButtonMouseExited
    
    private void filterButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filterButtonMouseEntered
        filterButton.setBackground(this.getBackground());
    }//GEN-LAST:event_filterButtonMouseEntered
    
    private void filterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextFieldActionPerformed
        doFilter();
    }//GEN-LAST:event_filterTextFieldActionPerformed
    
    private void cleanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanButtonActionPerformed
        filterTextField.setText("");
        doFilter();
    }//GEN-LAST:event_cleanButtonActionPerformed
    
    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        doFilter();
    }//GEN-LAST:event_filterButtonActionPerformed
    
    private void doFilter(){
        if(tableRowSorter!=null && searchIndices[0]!=-1){
            if(this.rowStringFilter == null){
                rowStringFilter = new RowStringFilter(searchIndices);
            }else{
                rowStringFilter.setIndices(searchIndices);
            }
            rowStringFilter.setSubString(filterTextField.getText());
            if(tableRowSorter.getModel()!=searchForTable.getModel()){
                setRowSort();
            }
            this.tableRowSorter.setRowFilter(rowStringFilter);
        }
    }
    
    private void  setRowSort(){
        if(searchForTable.getRowSorter() instanceof TableRowSorter){
            this.tableRowSorter = (TableRowSorter)searchForTable.getRowSorter();
        }else{
            this.tableRowSorter=new TableRowSorter(searchForTable.getModel());
            searchForTable.setRowSorter(this.tableRowSorter);
        }
    }
    
    
    private int[] searchIndices={-1};
    private TableRowSorter tableRowSorter;
    private javax.swing.JTable searchForTable;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton cleanButton;
    javax.swing.JButton filterButton;
    javax.swing.JTextField filterTextField;
    // End of variables declaration//GEN-END:variables
    
}

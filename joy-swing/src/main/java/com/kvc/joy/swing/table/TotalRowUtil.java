package com.kvc.joy.swing.table;

//~--- JDK imports ------------------------------------------------------------

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 合计行工具类
 * @author linju
 */
public class TotalRowUtil {

    /** Creates a new instance of TotalRowUtil */
    public TotalRowUtil() {}

    /**
     * 整合主表格与合计行表格
     * @param mainTable 主表格
     * @param totalRowTable 合计行表格
     * @param container 存放这两个表格的容器，必须是BorderLayout布局
     * @return 返回主表格的滚动框
     */
    public static JScrollPane integrateTotalTable(final JTable mainTable, final JTable totalRowTable, JComponent container) {
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        totalRowTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        totalRowTable.setColumnModel(mainTable.getColumnModel());

        // 初始化合计行表格
        totalRowTable.setRowHeight(20);
        totalRowTable.setBackground(new Color(235, 235, 235));

        final JScrollPane totalRowTableScroll = new JScrollPane(totalRowTable) {
            public void setColumnHeaderView(Component view) {}
        };
        final JScrollPane mainTableScroll = new JScrollPane(mainTable) {
            @Override
            public void setRowHeaderView(Component view) {
                super.setRowHeaderView(view);
                totalRowTableScroll.revalidate();
            }
        };

        mainTableScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        if (mainTable instanceof XTable) {
            JLabel totallabel = new JLabel() {
                @Override
                public Dimension getPreferredSize() {
                    Dimension dimension = super.getPreferredSize();

                    if (mainTableScroll.getRowHeader() != null) {
                        dimension.width = mainTableScroll.getRowHeader().getWidth();
                    }

                    return dimension;
                }
            };

            totallabel.setBorder(mainTable.getTableHeader().getBorder());
            totallabel.setHorizontalAlignment(JLabel.RIGHT);
            totallabel.setVerticalAlignment(JLabel.TOP);
            totalRowTableScroll.setRowHeaderView(totallabel);
        }

        JScrollBar bar = totalRowTableScroll.getVerticalScrollBar();
        JScrollBar dummyBar = new JScrollBar() {
            public void paint(Graphics g) {}
        };

        dummyBar.setPreferredSize(bar.getPreferredSize());
        totalRowTableScroll.setVerticalScrollBar(dummyBar);

        final JScrollBar bar1 = mainTableScroll.getHorizontalScrollBar();
        JScrollBar bar2 = totalRowTableScroll.getHorizontalScrollBar();

        bar2.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                bar1.setValue(e.getValue());
            }
        });
        mainTableScroll.setPreferredSize(new Dimension(400, 100));
        totalRowTableScroll.setPreferredSize(new Dimension(400, 38));
        container.add(mainTableScroll, BorderLayout.CENTER);
        container.add(totalRowTableScroll, BorderLayout.SOUTH);

        return mainTableScroll;
    }
}

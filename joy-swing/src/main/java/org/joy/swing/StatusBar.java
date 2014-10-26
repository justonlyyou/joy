package org.joy.swing;


import javax.swing.*;
import java.awt.*;

/**
 * 状态栏
 *
 * @author Kevice
 */
public class StatusBar extends JComponent {

    /**
     * 构造器
     */
    public StatusBar() {
        setPreferredSize(new Dimension(200, 20));
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(BorderFactory.createLoweredBevelBorder());
    }

    /**
     * 为状态栏追加一个单元(JLabel)
     * @param width 要追加的单元的宽度
     * @param content 要追加的单元的文本
     * @return 追加的单元
     */
    public JLabel appendCell(int width, String content) {
        JLabel cell = new StatusLabel(width, content);
        add(cell);
        return cell;
    }

    /**
     * 取得状态栏的单元数
     * @return int 状态栏的单元数
     */
    public int getCellCount() {
        return getComponentCount();
    }

    /**
     * 为某个单元设置状态信息
     * @param cellIndex 单元在状态栏里的下标
     * @param text 状态信息
     */
    public void setCellText(int cellIndex, String text) {
        JLabel cell = getCell(cellIndex);
        if (cell != null) {
            cell.setText(text);
        }
    }

    /**
     * 根据下标取得单元
     * @param cellIndex 单元在状态栏里的下标
     * @return 状态栏单元
     */
    private JLabel getCell(int cellIndex) {
        JLabel cell = null;
        if ((cellIndex < getCellCount()) && (cellIndex > -1)) {
            cell = (JLabel) getComponent(cellIndex);
        }
        return cell;
    }

    /**
     * 取得单元的信息
     * @param cellIndex 单元在状态栏里的下标
     * @return 单元的文本
     */
    public String getCellText(int cellIndex) {
        JLabel cell = getCell(cellIndex);
        if (cell != null) {
            return cell.getText();
        }
        return null;
    }

    /**
     * 状态标签
     */
    private class StatusLabel extends JLabel {

        /**
         * 构造器
         * @param width 标签的宽度
         */
        public StatusLabel(int width) {
            super();
            setPreferredSize(new Dimension(width, 20));
        }

        /**
         * 私有构造器
         * @param width 标签的宽度
         * @param content 标签的文本
         */
        private StatusLabel(int width, String content) {
            this(width);
            setText(content);
        }

        /**
         * 在标签后画两条竖线，实现一突起的分隔线的效果
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            int w = getWidth();
            int h = getHeight();

            g.setColor(Color.WHITE);
            g.drawLine(w - 3, 1, w - 3, h - 2);
            g.setColor(new Color(128, 128, 128));
            g.drawLine(w - 1, 1, w - 1, h - 2);
        }

        @Override
        public Insets getInsets() {
            return new Insets(0, 0, 0, 5);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        StatusBar statusBar = new StatusBar();
        statusBar.appendCell(100, "Status:");
        statusBar.appendCell(100, "");
        frame.add(statusBar);
        frame.pack();
        frame.setVisible(true);

    }
    
}

package org.joy.swing;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import org.joy.swing.panel.BASPanel;
import org.joy.swing.panel.BasePanel;

/**
 * 辅助提示标签
 * @author ckcs
 */
public class AuxiliaryNoteLabel extends JLabel {

    //面板状态
    private PanelStatusNote statusNote;
    private JProgressBar progressBar;

    public AuxiliaryNoteLabel() {
        setLayout(new NoteLabelLayoutManager());
        setFocusable(true);
        initComponent();
        initListener();
    }

    private void initComponent() {
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(300, 20));
        progressBar.setIndeterminate(true);
        add(progressBar);
    }

    //初始化监听器
    private void initListener() {
        //截获鼠标事件
        addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                e.consume();
            }

            public void keyPressed(KeyEvent e) {
                e.consume();
            }

            public void keyReleased(KeyEvent e) {
                e.consume();
            }
        });
        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                if (statusNote != null) {
                    if (BASPanel.LOCK_READONLY_STYLE.equals(statusNote.getStyle())) {
                        try {
                            setCursor(Cursor.getSystemCustomCursor("Invalid.32x32"));
                        } catch (Exception ex) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                        }
                    } else {
                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    }
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (statusNote != null) {
            if (BasePanel.LOCK_LOADING_STYLE.equals(statusNote.getStyle())) {
                Rectangle rect = progressBar.getBounds();
                g.drawString(statusNote.getDescribe(), rect.x, rect.y + rect.height + getFontMetrics(getFont()).getHeight());
            }
        }
    }

    /**
     * 更新面板状态的提示
     * @param statusNote
     */
    public void updatePanelNote(PanelStatusNote statusNote) {
        if (statusNote != null && statusNote.needNote()) {
            this.statusNote = statusNote;
            setVisible(true);
        } else {
            this.statusNote = null;
            setVisible(false);
        }
        revalidate();
        repaint();
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        revalidate();
        repaint();
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(r);
        revalidate();
        repaint();
    }

    //layout the note component
    private class NoteLabelLayoutManager implements LayoutManager {

        public void addLayoutComponent(String name, Component comp) {
        }

        public void removeLayoutComponent(Component comp) {
        }

        public Dimension preferredLayoutSize(Container parent) {
            return new Dimension(getWidth(), getHeight());
        }

        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        public void layoutContainer(Container parent) {
            if (statusNote != null) {
                Component[] childs = getComponents();
                int xPosMiddle = getWidth() / 2, yPos = getHeight() / 2;
                Dimension size = null;
                for (Component child : childs) {
                    if (child instanceof JProgressBar && !BASPanel.LOCK_LOADING_STYLE.equals(statusNote.getStyle())) {
                        continue;
                    }
                    size = child.getPreferredSize();
                    child.setBounds(xPosMiddle - size.width / 2, yPos, size.width, size.height);
                }
                requestFocusInWindow();
            }
        }
    }

    public PanelStatusNote getStatusNote() {
        return statusNote;
    }

    //获得索引子主件的总的高度
    private int getTotalComponetHeight() {
        int height = 0;
        Component[] childs = getComponents();
        for (Component component : childs) {
            height += component.getPreferredSize().getHeight();
        }
        return height;
    }
}

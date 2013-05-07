package com.kvc.joy.swing.button;

//~--- JDK imports ------------------------------------------------------------
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 * 有下拉箭头的按钮
 * @author zy
 */
public class MenuButton extends JButton implements ActionListener, SwingConstants {

    /** Field description */
    private int direction = SwingConstants.SOUTH;
    /** Field description */
    private BasicArrowButton painButton = new BasicArrowButton(direction);
    /** Field description */
    private JPopupMenu popupMenu;

    /**
     * Constructs ...
     *
     */
    public MenuButton() {
        super();
        setHorizontalAlignment(LEFT);
    }

    /**
     * Constructs ...
     *
     *
     * @param direction
     */
    public MenuButton(int direction) {
        setDirection(direction);
        setHorizontalAlignment(LEFT);
    }

    /**
     * Method description
     *
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
        repaint();
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Method description
     *
     *
     * @param m
     */
    @Override
    public void setMargin(Insets m) {
        Insets margin = (Insets) m.clone();
        if (margin.right < 20) {
            margin.right = 20;
        }
        super.setMargin(margin);
    }

    /**
     * Method description
     *
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w, h, size;

        w = getMargin().right;
        h = getSize().height;

        // Draw the arrow
        size = Math.min((h - 4) / 3, (w - 4) / 3);
        size = Math.max(size, 2);
        painButton.paintTriangle(g, getWidth() - getMargin().right + getMargin().right / 2, (h - size) / 2, size, direction, this.isEnabled());
    }

    /**
     * Method description
     *
     *
     * @param menuItem
     */
    public void addMenuItem(JMenuItem menuItem) {
        if (popupMenu == null) {
            popupMenu = new JPopupMenu();
            addActionListener(this);
        }

        popupMenu.add(menuItem);
    }

    /**
     * Method description
     *
     *
     * @param menuItems
     */
    public void addMenuItems(JMenuItem... menuItems) {
        for (JMenuItem menuItem : menuItems) {
            addMenuItem(menuItem);
        }
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (popupMenu != null) {
            popupMenu.show(this, 0, getHeight());
        }
    }
}

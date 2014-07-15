package org.joy.swing;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

/**
 * 可以设置组件的边框
 * @author zy
 */
public class ComponentTitledBorder extends AbstractBorder implements SwingConstants {

    /**
     * 距离原点的X距离
     */
    private int offset = 10;

    /**
     * 包含的边框
     */
    private Border border;

    /**
     * 设置的组件
     */
    private Component component;

    /**
     * 设置边框的容器
     */
    private Container container;

    /**
     * 绘制组件的区域
     */
    private Rectangle rect;

    /**
     * 组件边框构造器
     * @param comp 要设置的组件
     */
    public ComponentTitledBorder(Component comp) {
        this(comp, null);
    }

    /**
     * 组件边框构造器
     * @param comp 设置的组件
     * @param border 包含的边框
     */
    public ComponentTitledBorder(Component comp, Border border) {
        this(comp, null, 10);
    }

    /**
     * 组件边框构造器
     * @param comp 设置的组件
     * @param offset 原点的X偏移量
     */
    public ComponentTitledBorder(Component comp, int offset) {
        this(comp, null, offset);
    }

    /**
     * 组件边框构造器
     * @param comp 设置的组件
     * @param border 包含的边框
     * @param offset 距离0点的X偏移量
     */
    public ComponentTitledBorder(Component comp, Border border, int offset) {
        this(comp, null, border, offset);
    }

    /**
     * 组件边框构造器
     * @param comp 要设置的组件
     * @param container 边框所在的容器
     * @param border 包含的边框
     */
    public ComponentTitledBorder(Component comp, Container container, Border border) {
        this(comp, container, border, 10);
    }

    /**
     * 组件边框构造器
     * @param comp 要设置的组件
     * @param container 边框所在的容器
     * @param border 包含的边框
     * @param offset 组件偏移量
     */
    public ComponentTitledBorder(Component comp, Container container, Border border, int offset) {
        this.border = border;
        this.component = comp;
        this.offset = offset;
        setContainer(container);
    }

    /**
     * Method description
     *
     *
     * @param c
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets borderInsets = border.getBorderInsets(c);
        Insets insets = getBorderInsets(c);
        int temp = (insets.top - borderInsets.top) / 2;

        getBorder().paintBorder(c, g, x + 1, y + temp + 1, width - 2, height - temp - 2);

        if ((this.container == null) && (c instanceof Container)) {
            setContainer((Container) c);
        }
    }

    /**
     * Method description
     *
     *
     * @param c
     *
     * @return
     */
    @Override
    public Insets getBorderInsets(Component c) {
        Dimension size = getComp().getPreferredSize();
        Insets insets = getBorder().getBorderInsets(c);

        insets.top = Math.max(insets.top, size.height);

        return insets;
    }

    /**
     * Method description
     *
     *
     * @param container
     */
    protected void setContainer(Container container) {
        if (container != null) {
            this.container = container;

            if (component != null) {
                this.container.add(component);

                Dimension size = component.getPreferredSize();

                rect = new Rectangle(offset, 0, size.width, size.height);

                if (component != null) {
                    if (!component.getBounds().contains(rect)) {
                        component.setBounds(rect);
                    }
                }
            }
        }
    }

    /**
     * Method description
     *
     *
     * @return
     */
    protected Border getBorder() {
        if (border == null) {
            border = UIManager.getBorder("TitledBorder.border");
        }

        return border;
    }

    /**
     * 取得边框组件
     * @return 返回边框上的组件
     */
    public Component getComp() {
        return component;
    }
}

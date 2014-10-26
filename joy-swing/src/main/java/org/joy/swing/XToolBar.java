package org.joy.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 公共的工具栏
 * @author zy
 */
public class XToolBar extends JToolBar {

  /**
     * Creates a new tool bar; orientation defaults to <code>HORIZONTAL</code>.
     */
    public XToolBar() {
        super();
    }

    /**
     * Creates a new tool bar with the specified <code>orientation</code>.
     * The <code>orientation</code> must be either <code>HORIZONTAL</code>
     * or <code>VERTICAL</code>.
     *
     * @param orientation  the orientation desired
     */
    public XToolBar(int orientation) {
        super(orientation);
    }

    /**
     * Creates a new tool bar with the specified <code>name</code>.  The
     * name is used as the title of the undocked tool bar.  The default
     * orientation is <code>HORIZONTAL</code>.
     *
     * @param name the name of the tool bar
     */
    public XToolBar(String name) {
        super(name);
    }

    /**
     * Creates a new tool bar with a specified <code>name</code> and
     * <code>orientation</code>.
     * All other constructors call this constructor.
     * If <code>orientation</code> is an invalid value, an exception will
     * be thrown.
     *
     * @param name  the name of the tool bar
     * @param orientation  the initial orientation -- it must be
     *		either <code>HORIZONTAL</code> or <code>VERTICAL</code>
     * @exception IllegalArgumentException if orientation is neither
     *		<code>HORIZONTAL</code> nor <code>VERTICAL</code>
     */
    public XToolBar(String name, int orientation) {
        super(name, orientation);
    }

    /**
     * 添加按钮
     * @param toolButton
     */
    public void addButton(javax.swing.JButton toolButton) {
        add(toolButton);
    }

    /**
     * 添加默认的分割符
     */
    public void appendSeparator() {
        add(XSeparator.createToolSeparator());
    }

    /**
     * 初始化组件
     */
    protected void initComponent() {
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT, 6, 3));
    }

    /**
     * 插入分割符
     * @param index
     */
    public void insertSeparator(int index) {
        add(XSeparator.createToolSeparator(), index);
    }
}

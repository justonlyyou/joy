package org.joy.swing;

import java.awt.Graphics;
import java.util.EventListener;

/**
 * 扩展组件的监听器
 * @author zy
 */
public interface XComponentListener extends EventListener {

    /**
     * 组件绘制完成后做的一些事情
     * @param g Graphics
     */
    public void postPaint(Graphics g);
}

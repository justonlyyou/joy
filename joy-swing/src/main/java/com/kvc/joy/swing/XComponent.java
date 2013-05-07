package com.kvc.joy.swing;

/**
 * 基本组件要实现的接口
 * @author zy
 */
public interface XComponent {

    /**
     * 添加扩展组件的监听器
     * @param listener 扩展组件的监听器
     */
    public void addXComponentListener(XComponentListener listener);

    /**
     * 移除扩展组件的监听器
     * @param listener 扩展组件的监听器
     */
    public void removeXComponentListener(XComponentListener listener);
}

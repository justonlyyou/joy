package org.joy.swing;

import org.joy.commons.lang.string.I18nTool;
import org.joy.swing.panel.BASPanel;

import java.awt.*;

/**
 * 面板状态提示
 * @author ckcs
 */
public class PanelStatusNote {

    //样式
    private String style;
    //描述
    private String describe;
    //提示使用的自定义提示组件
    private Component nodeCompoent;
    //设置阻隔位置的参照组件
    private Component referComponent;

    public PanelStatusNote() {
        this(BASPanel.LOCK_LOADING_STYLE, I18nTool.getLocalStr("ProcessSoWait"));
    }

    public PanelStatusNote(String describe) {
        this(BASPanel.LOCK_LOADING_STYLE, describe);
    }

    public PanelStatusNote(String style, String describe) {
        this.style = style;
        this.describe = describe;
    }

    public PanelStatusNote(Component compoent) {
        this.nodeCompoent = compoent;
    }

    public Component getCompoent() {
        return nodeCompoent;
    }

    public void setCompoent(Component compoent) {
        this.nodeCompoent = compoent;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Component getNodeCompoent() {
        return nodeCompoent;
    }

    public void setNodeCompoent(Component nodeCompoent) {
        this.nodeCompoent = nodeCompoent;
    }

    public Component getReferComponent() {
        return referComponent;
    }

    public void setReferComponent(Component referComponent) {
        this.referComponent = referComponent;
    }

    /**
     * 是否需要提示
     * @return
     */
    public boolean needNote() {
        return true;
    }
}

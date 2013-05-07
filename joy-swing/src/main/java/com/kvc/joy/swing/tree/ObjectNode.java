package com.kvc.joy.swing.tree;

import java.util.List;

/**
 *要以树方式展现的实体必须实现的接口
 * @author linju
 */
public interface ObjectNode {

    /**
     *获得子节点
     */
    public List getChildren();

    /**
     *在节点上展现的名称
     */
    @Override
    public String toString();

    /**
     *是否为叶子节点
     */
    public boolean isLeaf();
    /**
     *叶节点是否可见
     */
    public boolean isVisible();
}

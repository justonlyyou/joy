package org.joy.plugin.security.erbac.biz;

import org.joy.commons.tree.TreeNode;

import java.util.List;

/**
 * 组服务接口
 *
 * @author Kevice
 * @time 2012-5-3 下午10:21:13
 * @since 1.0.0
 */
public interface IErbacRoleBiz {

    /**
     * 返回所有角色
     *
     * @return List<角色树结点>
     * @author Kevice
     * @time 2012-5-3 下午10:21:13
     * @since 1.0.0
     */
    List<TreeNode> getAllRoles();

}

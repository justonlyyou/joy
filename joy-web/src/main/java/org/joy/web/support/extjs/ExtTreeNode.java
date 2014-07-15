package org.joy.web.support.extjs;

import org.joy.commons.tree.TreeNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Extjs树结点类(Ext.data.NodeInterface)对应的POJO
 * 
 * @author Kevice
 */
public class ExtTreeNode implements Serializable {

	private String id;
	private Boolean allowDrag;
	private Boolean allowDrop;
	private Boolean checked;
	private List<ExtTreeNode> children = new ArrayList<ExtTreeNode>(0);
	private String cls;
	private Integer depth;
	private Boolean expandable;
	private Boolean expanded;
	private String href;
	private String hrefTarget;
	private String icon;
	private String iconCls;
	private Integer index;
	private Boolean isFirst;
	private Boolean isLast;
	private Boolean loaded;
	private Boolean loading;
	private String parentId;
	private String qtip;
	private String qtitle;
	private Boolean root;
	private String text;

	public ExtTreeNode() {
	}

	public static <T> ExtTreeNode create(TreeNode<T> root, IExtTreeNodeAdapter<T> adapter) {
		return adapt(root, adapter);
	}

	private static <T> ExtTreeNode adapt(final TreeNode<T> treeNode, IExtTreeNodeAdapter<T> adapter) {
		ExtTreeNode extTreeNode = new ExtTreeNode();
		T object = treeNode.getObject();
		if (object != null) {
			adapter.adapt(extTreeNode, object);
		}

		List<TreeNode<T>> children = treeNode.getChildren();
		for (TreeNode<T> node : children) {
			extTreeNode.getChildren().add(adapt(node, adapter));
		}
		return extTreeNode;
	}

	public Boolean getAllowDrag() {
		return allowDrag;
	}

	public void setAllowDrag(Boolean allowDrag) {
		this.allowDrag = allowDrag;
	}

	public Boolean getAllowDrop() {
		return allowDrop;
	}

	public void setAllowDrop(Boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public List<ExtTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ExtTreeNode> children) {
		this.children = children;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Boolean getIsLast() {
		return isLast;
	}

	public void setIsLast(Boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	public Boolean getLoading() {
		return loading;
	}

	public void setLoading(Boolean loading) {
		this.loading = loading;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public Boolean getRoot() {
		return root;
	}

	public void setRoot(Boolean root) {
		this.root = root;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

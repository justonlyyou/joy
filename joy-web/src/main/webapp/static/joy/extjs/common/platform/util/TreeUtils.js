Ext.define('com.kvc.joy.extjs.common.platform.util.TreeUtils', {

	/**
	 * 选择父节点时, 自动选中所有的子节点
	 */
	checkAllChildren : function(node, checked) {
		checked ? node.expand() : node.collapse();
		if (node.hasChildNodes()) {
			node.eachChild(function(child) {
				child.set('checked', checked); 
				child.fireEvent('checkchange', child, checked);  
//				this.checkAllChildren(child, checked);
			});
		}
	},

	/**
	 * 选择子节点时，自动选中父节点的父节点
	 */
	checkParent : function(node, checked) {
		if (checked) {
			node.expand();
			var parentNode = node.parentNode;
			if (parentNode != undefined) {
				parentNode.set('checked', checked); 
//				var cb = parentNode.ui.checkbox;
//				if (cb) {
//					cb.checked = checked;
//				}
//				checkParent(parentNode, checked);
			}
		}
	}

});

var TREE_UTILS = Ext.create('com.kvc.joy.extjs.common.platform.util.TreeUtils');
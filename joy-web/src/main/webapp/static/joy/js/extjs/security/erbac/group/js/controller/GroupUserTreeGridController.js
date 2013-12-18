Ext.define('com.kvc.joy.extjs.security.group.js.controller.GroupUserTreeGridController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'groupUserTreeGrid' : {
				checkchange : this.checkchange
			},
			'groupUserTreeGrid button[id=okButton]' : {
				click : this.addGroupUsers
			},
			'groupUserTreeGrid button[id=closeButton]' : {
				click : this.closeDialog
			},
		});
	},
	views : [ 'GroupUserTreeGridView' ],
	stores : [ 'GroupUserTreeGridStore' ],
	models : [],

	checkchange : function(node, checked) {
		TREE_UTILS.checkAllChildren(node, checked);
		TREE_UTILS.checkParent(node, checked);
	},

	addGroupUsers : function(button, e, options) {
		var treeGrid = button.ownerCt.ownerCt;
		var nodes = treeGrid.getChecked();
		var checkedUserIds = [];
		Ext.Array.each(nodes, function(node) {
			var raw = node.raw;
			if (raw && raw.name) {
				checkedUserIds.push(raw.userId);
			}
		});

		if (checkedUserIds.length == 0) {
			Ext.Msg.alert('提示', '请选择要添加的用户。');
		} else {
			var win = treeGrid.ownerCt;
			var groupId = win.params['groupId'];
			Ext.Ajax.request({
				url : JOY.getContextPath() + '/erbac/group.GroupUsers/addGroupUsers.do',
				params : {
					groupId : groupId,
					userIds : checkedUserIds.join(',')
				},
				method : 'POST',
				timeout : 4000,
				success : function(response, opts) {
					button.ownerCt.ownerCt.ownerCt.close();
					var groupUserGrid = win.params['groupUserGrid'];
					groupUserGrid.getStore().load({
						params : {
							groupId : groupId
						}
					});
				}
			});
		}
	},

	closeDialog : function(button, e, options) {
		button.ownerCt.ownerCt.ownerCt.close();
	}
});
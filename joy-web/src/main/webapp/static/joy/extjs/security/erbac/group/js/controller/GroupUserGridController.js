Ext.define('com.kvc.joy.extjs.security.group.js.controller.GroupUserGridController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'groupUserGrid button[id=groupUserGridAdd]' : {
				click : this.addGroupUser
			},
			'groupUserGrid button[id=groupUserGridDel]' : {
				click : this.delGroupUser
			},
			'groupUserGrid button[id=groupUserGridRefresh]' : {
				click : this.refreshGroupUserGrid
			},
			'groupUserGrid button[id=gridToExcel]' : {
				click : this.gridToExcel
			},
			'groupUserGrid' : {
				beforeload : this.beforeload
			},
			'groupUserGrid button[id=userSearchBtn]' : {
				click : this.searchUser
			}
		});
	},
	views : [ 'GroupUserGridView' ],
	stores : [ 'GroupUserGridStore' ],
	models : [],
	
	beforeload : function(store, operation, eOpts ) {
		var groupTree = Ext.getCmp("groupAuthTree");
		var records = groupTree.getSelectionModel().getSelection();
		var groupId = records[0].get('id');
		Ext.apply(store.proxy.extraParams, {
			groupId : groupId
		});
	},

	addGroupUser : function(button, e, options) {
		var groupTree = Ext.getCmp("groupAuthTree");
		var records = groupTree.getSelectionModel().getSelection();
		if (records.length == 0) {
			Ext.Msg.alert('提示', '请先选择一个组。');
		} else {
			var groupId = records[0].get('id');
			var grid = button.ownerCt.ownerCt;
			
			var win = Ext.create('Ext.window.Window', {
			    title: '添加组用户',
			    modal: true,
			    height: 450,
			    width: 600,
			    layout: 'fit',
			    params: {
			    	groupId: groupId,
			    	groupUserGrid: grid
			    },
			    items: {
			        xtype: 'groupUserTreeGrid',
			    }
			});
			
			var groupUserTreeGrid = win.down('groupUserTreeGrid');
			groupUserTreeGrid.store.load({
				params : {
					groupId : groupId
				}
			});
			
			win.show();
		}
	},

	delGroupUser : function(button, e, options) {
		var grid = button.ownerCt.ownerCt;
		var records = grid.getSelectionModel().getSelection();
		if(records.length == 0) {
			Ext.Msg.alert('提示', '您未选择要移除的用户。');
			return;
		}

		var groupAndUsers = Ext.create('Ext.util.MixedCollection');
		Ext.Array.each(records, function(model) {
			var groupName = model.get('groupName');
			var users;
			if (groupAndUsers.containsKey(groupName)) {
				users = groupAndUsers.getByKey(groupName);
			} else {
				users = [];
				groupAndUsers.add(groupName, users);
			}
			users.push(model.get('name'));
		});

		var msg = "确定要移除以下组用户？<br/>";
		groupAndUsers.eachKey(function(key) {
			msg += "<br/>" + key + "：<br/>";
			msg += "&nbsp;&nbsp;&nbsp;&nbsp;" + groupAndUsers.getByKey(key).join(",") + "<br/>";
		});

		Ext.Msg.show({
			title : '确定删除？',
			msg : msg,
			buttons : Ext.Msg.YESNO,
			icon : Ext.Msg.QUESTION,
			fn : function(btnId) {
				if (btnId == 'yes') {
					var data = [];
					Ext.Array.each(records, function(model) {
						data.push(model.get('groupId') + '-' + model.get('userId')); // Ext.JSON.encode()
					});
					if (data.length > 0) {
						Ext.Ajax.request({
							url : grid.getStore().getProxy().api['delGroupUsers'],
							params : {
								groupAndUserIds : data.join(",")
							},
							method : 'POST',
							timeout : 4000,
							success : function(response, opts) {
								Ext.Array.each(records, function(model) {
									grid.getStore().remove(model);
								});
								JOY.showDelayNotice(response.responseText);
							}
						});
					}
				}
			}
		});
	},

	refreshGroupUserGrid : function(button, e, options) {
		var grid = button.ownerCt.ownerCt;
		var groupTree = Ext.getCmp("groupAuthTree");
		var records = groupTree.getSelectionModel().getSelection();
		if (records.length == 1) {
			grid.getStore().load({
				params : {
					groupId : records[0].get('id')
				}
			});
		}
	},
	
	gridToExcel: function(button) {
		var grid = button.ownerCt.ownerCt;
		var groupTree = Ext.getCmp("groupAuthTree");
		var records = groupTree.getSelectionModel().getSelection();
		var groupName = records[0].get('text');
		grid.printTitle = groupName+"组所包含的用户";
		exportToExcel(grid);
	}, 
	
	searchUser : function(button) {
		var usernameTf = Ext.getCmp("usernameTextfield");
		var usernameOptr = usernameTf.logicOperator;
		alert(usernameTf.getName());
		alert(usernameOptr);
		var nameTf = Ext.getCmp("nameTextfield");
		var nameOptr = nameTf.get('logicOperator');
	}

});
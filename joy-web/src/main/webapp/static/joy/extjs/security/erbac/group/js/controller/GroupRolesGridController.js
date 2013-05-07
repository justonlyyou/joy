Ext.define('com.kvc.joy.extjs.security.group.js.controller.GroupRolesGridController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			'groupRolesGrid button[id=groupRolesGridRefresh]' : {
				click : this.refreshGrid
			},
			'groupRolesGrid button[id=groupRolesGridToExcel]' : {
				click : this.gridToExcel
			}
		});
	},
	views : [ 'GroupRolesGridView' ],
	stores : [ 'GroupRolesGridStore' ],
	models : [],

	refreshGrid : function(button, e, options) {
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
		grid.printTitle = groupName+"组所属角色";
		exportToExcel(grid);
	}

});
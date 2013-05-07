Ext.define('com.kvc.joy.extjs.security.group.js.controller.GroupTreeController', {
	extend : 'Ext.app.Controller',
	init : function() {
		this.control({
			"groupTree" : {
				itemclick : this.clickTreeNode,
				load : function(store, records, operation, successful, eOpts) { // api doc里operation和successful两个参数顺序写反了
					Ext.ComponentQuery.query('groupTree')[0].expandAll();
				}
			},
			'#groupTabPanel' : {
				tabchange : function(tabPanel, newCard, oldCard, eOpts) {
					// alert('tabchange');
				}
			}
		});
	},
	views : [ 'GroupTreeView' ],
	stores : [ 'GroupTreeStore' ],
	models : [],

	clickTreeNode : function(tree, record, item, index, e, options) {
		var grid = Ext.getCmp("groupUserGrid");
		grid.getStore().on("beforeload",function(){
			Ext.apply(grid.getStore().proxy.extraParams, {
				groupId : record.get('id'),
			});
		});
		grid.getStore().loadPage(1, {
			params : {
				start : 0,
				limit : Ext.getCmp('userGridPagingToolbar').store.pageSize
			}
		});

		var groupNameLbl = Ext.getCmp("curGroupName");
		groupNameLbl.setText("当前组：" + record.get('text'));
	}

// show: function(tree, record, item, index, e, options) {
// var c = this.application.getController("groupUserGridController");
// c.init();
// console.log("3 -----------------------------");
// //var c = Ext.create("AM.controller.Users2",{application: this.application}).init();
// var view = Ext.widget('useredit');
// view.down('form').loadRecord(record);

// var c2 = this.application.getController("Users3");
// c2.init();
// //var view = Ext.create("AM.view.user.Edit");
// var view2 = Ext.widget('useredit2');
// view2.down('form').loadRecord(record);
// },

});
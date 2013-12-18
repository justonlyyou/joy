Ext.define('com.kvc.joy.sysres.menu.js.controller.menuTreeController', {
	extend: 'Ext.app.Controller',
	init: function() {
		this.control({
			"menuTree":{
				itemclick : function(tree, record, item, index, e, options) {
					var urlStr = record.get('hrefTarget');
					if (!Ext.isEmpty(urlStr)) {
						var tabPanel = Ext.ComponentQuery.query('#tabPanel')[0];
						var tabPage = tabPanel.getComponent(record.get('id'));
						if (!tabPage) {
							tabPage = tabPanel.add({
							    id: record.get('id'),
								title : record.get('text'),
								tabTip : record.get('text'),
								closable : true,
								active:true,
								html: ' <iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + urlStr+ '?t=new Date().getTime()"> </iframe>', //
//								html : 'Hi, I am tab ',
//								autoLoad : {
//									url : urlStr,
////									params : {
////										data : "从客户端传入的参数"
////									},
//									method : 'GET'
//								}
							});
						}
						tabPanel.setActiveTab(tabPage);
					}
				},
				load: function(store, records, operation, successful, eOpts) { //api doc里operation和successful两个参数顺序写反了
					Ext.ComponentQuery.query('menuTree')[0].expandAll();
				} 
			}
		});
	},
	views: ['menuTreeView'],
	stores: ['menuTreeStore'],
	models: []
});
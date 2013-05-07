Ext.Loader.setConfig({
	enabled : true
});
Ext.Loader.setPath('Ext.ux', JOY.getContextPath() + '/joy/extjs/common/third/extjs/ux');
Ext.Loader.setPath('com.kvc.joy.extjs.common.platform', JOY.getContextPath() + '/joy/extjs/common/platform');
Ext.require([ 'Ext.ux.TabScrollerMenu', 'Ext.ux.TabCloseMenu', 'com.kvc.joy.extjs.common.platform.MainTopPanel' ]);
Ext.util.CSS.swapStyleSheet('',JOY.getContextPath() + '/joy/extjs/common/third/extjs/ux/css/TabScrollerMenu.css');

Ext.onReady(function() {
	Ext.QuickTips.init();

	Ext.getDoc().on("contextmenu", function(e) {
		e.stopEvent();
	});

	Ext.application({
		name : 'com.kvc.joy.sysres.menu.js',
		// appFolder : JOY.getContextPath() + '/joy/extjs/sysres/menu/js',
		launch : function() {
			Ext.create('Ext.container.Viewport', {
				closable : true,
				layout : 'border',
				bodyStyle : 'padding: 5px;',
				items : [ {
					region : 'north',
					xtype : 'mainTopPanel'
				}, {
					region : 'west',
					title : '菜单',
					width : 200,
					split : true,
					collapsible : true,
					floatable : false,
					xtype : 'menuTree'
				}, {
					region : 'center',
					xtype : 'tabpanel',
					activeTab : 0,
					itemId : 'tabPanel',
					plugins : [ {
						ptype : 'tabscrollermenu',
						maxText : 15,
						pageSize : 5
					}, Ext.create('Ext.ux.TabCloseMenu', {
						closeTabText : '关闭当前',
						closeOthersTabsText : '关闭其他',
						closeAllTabsText : '关闭所有',
						extraItemsTail : [ '-', {
							text : '可关闭',
							checked : true,
							hideOnClick : true,
							handler : function(item) {
								currentItem.tab.setClosable(item.checked);
							}
						} ],
						listeners : {
							aftermenu : function() {
								currentItem = null;
							},
							beforemenu : function(menu, item) {
								var menuitem = menu.child('*[text="可关闭"]');
								currentItem = item;
								menuitem.setChecked(item.closable);
							}
						}
					}) ]
				} ],
				renderTo : Ext.getBody()
			});
		},
		controllers : [ 'menuTreeController' ]
	});
});
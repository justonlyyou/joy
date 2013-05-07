Ext.onReady(function(){
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
		// 开启Ext.Loader。Ext.Loader是动态加载的核心
		enabled : true
	});
	Ext.application({
		name:'KHW',
		//appFolder : 'erbac/js/role',
		launch:function(){
			Ext.create('Ext.container.Viewport', {
				layout:'border',
				items:[
					{
					title: '角色集',
					region : 'west',
					iconCls: 'roleTree',
					width : 200,
					split : true,
					collapsible : true,
					floatable : false,
					xtype : 'roleTree',
					id: 'roleAuthTree'
				}
				],renderTo:'div_s'
			});
		},
		controllers:['roleTreeController']
	});
});
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
		// 开启Ext.Loader。Ext.Loader是动态加载的核心
		enabled : true
	});
	
	Ext.application({
		name : 'com.kvc.joy.extjs.security.group.js',
//		appFolder : 'js',
		launch : function() {
			Ext.create('Ext.container.Viewport', {
				closable : true,
				defaulsts: {
					bodyStyle : 'padding:1px;',
				},
				layout : 'border',
				items : [ {
					title: '组树形',
					region : 'west',
					iconCls: 'groupTree',
					width : 200,
					split : true,
					collapsible : true,
					floatable : false,
					xtype : 'groupTree',
					id: 'groupAuthTree'
				},  {
					title: '组权限管理',
					region : 'center',
                    xtype: 'panel',
                    iconCls: 'groupTab',
                    id: 'groupPanel',
                    bodyStyle : 'padding-top:5px;',
                    layout: 'border',
	                items: [{
	                	region: 'north',
	                	xtype: 'label',
                		text: '当前组：',
                		id: 'curGroupName',
                		margins: '0 0 7 5',
                    }, {
                    	region: 'center',
                    	xtype : 'tabpanel',
    					activeTab : 0,
    					itemId : 'groupTabPanel',
    					items:[{
    		                title:'包含用户',
    		                layout: 'fit',
    		                items:[{
    		                	xtype: 'groupUserGrid',	
    		                	id: 'groupUserGrid',
    		                	style:'border-botton:0px;' 
    		                }]
    		            },{
    		               title:'所属角色',
    		            },{
    		                title:'组权限'
    		            },{
    		                title:'总权限'
    		            }]
                    }]
				} ],
//				renderTo : 'groupAuth'
			});
		},
		controllers : [ 'GroupTreeController' , 'GroupUserGridController', 'GroupUserTreeGridController', 'GroupRolesGridController']
	});
});
Ext.Loader.setConfig({enabled: true});
Ext.Loader.setPath('Ext.ux', JOY.getContextPath() + '/joy/extjs/common/third/extjs/ux');
Ext.require([ 'Ext.ux.grid.FiltersFeature' ]);

Ext.define('com.kvc.joy.extjs.security.group.js.view.GroupUserGridView', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.groupUserGrid',
	store: 'GroupUserGridStore',
	selModel: {
		selType: 'checkboxmodel'
	},
	viewConfig : {
	    loadMask: true, //??? 为何配置为true4.1版本不行了 ???
	    stripeRows: true, //隔行换色
	},
//	columnLines: true,
	enableKeyNav: true,
	border: 0,
	multiSelect: true,
	frame: true,
	
	 tbar: [
		 {xtype: 'button', text: '添加', id: 'groupUserGridAdd', iconCls: 'groupUserGridAdd'},
		 {xtype: 'button', text: '移除', id: 'groupUserGridDel', iconCls: 'groupUserGridDel'},
		 {xtype: 'button', text: '刷新', id: 'groupUserGridRefresh', iconCls: 'refreshGrid'},
		 {xtype: 'button', text: '导出到Excel', id: 'gridToExcel', iconCls: 'gridToExcel'}
	 ],
	dockedItems: [
	              {
    		xtype : 'toolbar',
    		dock : 'top',
    		items : [ {
    			xtype : 'textfield',
    			name : 'username',
    			fieldLabel: '用户名',
    			labelWidth: '50px',
    			id: 'usernameTextfield',
    			logicOperator: 'ILIKE'
//    			hideLabel : true,
//    			width : 200
    		}, {
    			xtype : 'textfield',
    			name : 'name',
    			fieldLabel: '姓名',
    			labelWidth: '40px',
    			id: 'nameTextfield',
    			logicOperator: 'ILIKE'
//    			hideLabel : true,
//    			width : 200
    		}, {
    			id: 'userSearchBtn',
    			xtype: 'button',
                text: '查询',
//                tooltip: 'Find Previous Row'
            }]},
            
            {
          xtype: 'pagingtoolbar',
          id: 'userGridPagingToolbar',
          store: 'GroupUserGridStore',
          dock: 'bottom',
          displayInfo: true,
          plugins:[Ext.create('com.kvc.joy.extjs.common.platform.util.PageComboResizer')]
	}],
	columns: [
	          {text: '用户名', dataIndex: 'account', width: 100, style :"text-align:center",filterable: true},
	          {text: '姓名', dataIndex: 'name', width: 100, style :"text-align:center"},
	          {text: '组', dataIndex: 'groupName', width: 100, style :"text-align:center"},
	          {text: '性别', dataIndex: 'sex', width: 100, style :"text-align:center"},
	          {text: '最近登录时间', dataIndex: 'lastLoginTime', width: 150, style :"text-align:center"},
	          {text: '登录次数', dataIndex: 'loginCount', width: 100, style :"text-align:center"}
	],
	features: [{
        ftype: 'filters',
        // encode and local configuration options defined previously for easier reuse
        encode: false, // json encode the filter query
        local: true,   // defaults to false (remote filtering)

        // Filters are most naturally placed in the column definition, but can also be
        // added here.
//        filters: [{
//            type: 'boolean',
//            dataIndex: 'visible'
//        }]
    }],
	initComponent: function() {
		this.callParent(arguments);
	}
});
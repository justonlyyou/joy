Ext.define('com.kvc.joy.extjs.security.group.js.view.GroupRolesGridView', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.groupRolesGrid',
	store: 'GroupRolesGridStore',
	selModel: {
		selType: 'checkboxmodel'
	},
	border: 0,
	multiSelect: true,
	frame: true,
	tbar: [
	       {xtype: 'button', text: '刷新', id: 'groupRolesGridRefresh', iconCls: 'refreshGrid'},
	       {xtype: 'button', text: '导出到Excel', id: 'groupRolesGridToExcel', iconCls: 'gridToExcel'}
	],
//	dockedItems: [{
//          xtype: 'pagingtoolbar',
//          store: 'groupUserGridStore',
//          dock: 'bottom',
//          displayInfo: true	              
//	}],
	enableKeyNav: true,
	columnLines: true,
	columns: [
	          {text: '角色名', dataIndex: 'roleName', width: 100, style :"text-align:center"},
	          {text: '创建时间', dataIndex: 'roleCreateTime', width: 100, style :"text-align:center"},
	          {text: '角色描述', dataIndex: 'roleName', width: 200, style :"text-align:center"},
	          {text: '组名', dataIndex: 'groupName', width: 100, style :"text-align:center"},
	],
	initComponent: function() {
		this.callParent(arguments);
	}
});
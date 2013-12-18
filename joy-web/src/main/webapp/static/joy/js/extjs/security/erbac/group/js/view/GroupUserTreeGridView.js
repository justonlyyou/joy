Ext.define('com.kvc.joy.extjs.security.group.js.view.GroupUserTreeGridView', {
	extend : 'Ext.tree.Panel',
	alias : 'widget.groupUserTreeGrid',
	width : 500,
	height : 300,
	collapsible : false,
	useArrows : true,
	rootVisible : false,
	store : 'GroupUserTreeGridStore',
	singleExpand : true,
	multiSelect : true,
	buttons : [ {
		text : '关闭',
		id : 'closeButton'
	}, {
		text : '确定',
		id : 'okButton'
	}, ],
	columns : [ {
		xtype : 'treecolumn',
		text : '用户名',
		flex : 2,
		sortable : true,
		dataIndex : 'account',
		style : "text-align:center"
	}, {
		text : '姓名',
		flex : 1,
		dataIndex : 'name',
		sortable : true,
		style : "text-align:center"
	}, {
		text : '性别',
		flex : 1,
		dataIndex : 'sex',
		sortable : true,
		style : "text-align:center"
	}, {
		text : '最近登录时间',
		flex : 2,
		sortable : true,
		dataIndex : 'lastLoginTime',
		style : "text-align:center"
	}, {
		xtype : 'templatecolumn',
		text : '登录次数',
		flex : 1,
		dataIndex : 'loginCount',
		sortable : true,
		 style : "text-align:center",
		tpl : Ext.create('Ext.XTemplate', '{loginCount:this.formatCount}', {
			formatCount : function(v) {
				if (v < 0) {
					return "";
				}
				return v;
			}
		})
	} ]

});
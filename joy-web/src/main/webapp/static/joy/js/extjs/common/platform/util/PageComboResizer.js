Ext.define('com.kvc.joy.extjs.common.platform.util.PageComboResizer', {
	extend : Object,
	pageSizes : [ 5, 10, 15, 20, 25, 30, 50, 75, 100, 200, 300, 500 ],
	prefixText : '每页显示 ',
	postfixText : '记录',

	constructor : function(config) {
		Ext.apply(this, config);
		this.callParent(arguments);
	},
	init : function(pagingToolbar) {
		var ps = this.pageSizes;
		var combo = Ext.create('Ext.form.ComboBox', {
			typeAhead : true,
			triggerAction : 'all',
			lazyRender : true,
			mode : 'local',
			width : 45,
			store : ps,
			listeners : {
				select : function(c, r, s) {
					pagingToolbar.store.pageSize = r[0].data.field1;
					pagingToolbar.store.loadPage(pagingToolbar.store.currentPage);
				}
			}
		});
		Ext.iterate(this.pageSizes, function(ps) {
			if (ps == pagingToolbar.store.pageSize) {
				combo.setValue(ps);
				return;
			}
		});
		// 将控件放到刷新控件的后面
		var inputIndex = pagingToolbar.items.indexOf(pagingToolbar.items.get('refresh'));
		pagingToolbar.insert(inputIndex, '-');
		pagingToolbar.insert(inputIndex, this.postfixText);
		pagingToolbar.insert(inputIndex, combo);
		pagingToolbar.insert(inputIndex, this.prefixText);
		pagingToolbar.on({
			beforedestroy : function() {
				combo.destroy();
			}
		});

	}
});
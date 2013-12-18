Ext.define("KHW.store.roleTreeStore", {
	extend : 'Ext.data.TreeStore',
	proxy : {
		type : 'ajax',
		url : 'roleTree.do',
		reader : 'json',
		autoLoad : true
	}
});
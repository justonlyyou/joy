Ext.define('com.kvc.joy.sysres.menu.js.store.menuTreeStore', {
	extend : 'Ext.data.TreeStore',
	proxy : {
		type : 'ajax',
		url : JOY.getContextPath() + '/sysres/menu.SysMenu/loadSysMenu.do',
		reader : {
			type : 'json',
		// root : 'result', // 这里不能设置为result(应该是Extjs的bug，而Ext.data.Store是可以的，参考GroupUserGridStore.js)，需要通过以下extractResponseData函数来处理。
		},
		extractResponseData : function(response) {
			return eval('(' + response.responseText + ")").result;
		}
	},
// autoLoad : true
});
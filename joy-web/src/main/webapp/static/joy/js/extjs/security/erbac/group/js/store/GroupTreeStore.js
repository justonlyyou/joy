Ext.define('com.kvc.joy.extjs.security.group.js.store.GroupTreeStore', {
	extend : 'Ext.data.TreeStore',
	proxy : {
		type : 'ajax',
		url : JOY.getContextPath() + '/erbac/group.GroupTree/fetchGroupTree.do',
		reader :  'json',
		extractResponseData : function(response) {
			return eval('(' + response.responseText + ")").result;
		}
	},
});
Ext.define('com.kvc.joy.extjs.security.group.js.store.GroupUserGridStore', {
	extend : 'Ext.data.Store',
	pageSize : 10,
	model : MODEL_FACTORY.getModelByName('com.kvc.joy.rp.extjs.action.security.erbac.support.vo.GroupUserVo'),
	proxy : {
		api : {
			addGroupUsers : JOY.getContextPath() + '/erbac/group.GroupUsers/addGroupUsers.do',
			delGroupUsers : JOY.getContextPath() + '/erbac/group.GroupUsers/delGroupUsers.do'
		},
		type : 'ajax',
		url : JOY.getContextPath() + '/erbac/group.GroupUsers/loadGroupUsers.do',
		reader : {
			type : 'json',
			root : 'result',
			totalProperty : 'paging.totalCount'
		}
	},
	autoLoad : true,
	remoteSort: true
});
Ext.define('com.kvc.joy.extjs.security.group.js.store.GroupRolesGridStore', {
	extend: 'Ext.data.Store',
	model: MODEL_FACTORY.getModelByName('com.kvc.joy.rp.extjs.action.security.erbac.support.vo.GroupRoleVo'),
	proxy: {
		type: 'ajax',
		url: JOY.getContextPath() + '/erbac/group.GroupRoles/loadGroupRoles.do',
		reader: {
			type: 'json',
		},
		writer: {
			type: 'json'
		}
	},
	autoLoad: true

});
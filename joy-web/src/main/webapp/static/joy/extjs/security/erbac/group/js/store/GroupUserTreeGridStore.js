Ext.define('com.kvc.joy.extjs.security.group.js.store.GroupUserTreeGridStore', {
	extend: 'Ext.data.TreeStore',
	 model: MODEL_FACTORY.getModelByName('com.kvc.joy.rp.extjs.action.security.erbac.support.vo.GroupUserVo'),
     proxy: {
         type: 'ajax',
         url: JOY.getContextPath() + '/erbac/group.GroupUsers/loadGroupUserTree.do',
//         extraParams : {
//        	 groupId : '1'
//         },  
         extractResponseData : function(response) {
 			return eval('(' + response.responseText + ")").result;
 		}
     },
     folderSort: true,
});
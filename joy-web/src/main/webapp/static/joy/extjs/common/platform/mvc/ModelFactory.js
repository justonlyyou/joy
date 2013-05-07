Ext.define('com.kvc.joy.extjs.common.platform.mvc.ModelFactory', {
	models : Ext.create('Ext.util.MixedCollection'),
	fields : Ext.create('Ext.util.MixedCollection'),
	getModelByName : function(modelName) {
		if (this.models.containsKey(modelName)) {
			return modelName;
		} else {
			var fields = [];
			if (this.fields.containsKey(modelName)) {
				fields = this.fields.containsKey(modelName);
			} else {
				Ext.Ajax.request({
					url : JOY.getContextPath() + '/extjs/ExtModel/fetchFields.do',
					params : {
						className : modelName
					},
					method : 'POST',
					timeout : 4000,
					async : false,
					success : function(response, opts) {
						fields = eval('(' + response.responseText + ")").result;
					},
					failure : function(response, opts) {
						console.out("失败");
						alert("失败");
					}
				});
			}
			this.fields.add(modelName, fields);

			var newModel = Ext.define(modelName, {
				extend : 'Ext.data.Model',
				fields : fields
			});
			this.models.add(modelName, newModel);
			return modelName;
		}
	}
});

var MODEL_FACTORY = Ext.create('com.kvc.joy.extjs.common.platform.mvc.ModelFactory');
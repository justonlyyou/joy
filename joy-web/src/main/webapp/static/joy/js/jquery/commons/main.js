curl = {
	baseUrl : 'static',
	pluginPath: "3rd/curl/curl/plugin",
	paths : {
//		jquery : '3rd/jquery/jquery/jquery',
		bootstrap : '3rd/bootstrap/bootstrap/js/bootstrap',
		jqValidate : '3rd/jquery/validation/jquery.validate',
		jqAddMethods : '3rd/jquery/validation/additional-methods',
		jqTreeTable : '3rd/jquery/treeTable/jquery.treeTable',
		jqLayer : '3rd/jquery/layer/layer',
		datePicker : '3rd/My97DatePicker/WdatePicker',
	},
	packages : {
		'joy' : {
			location : 'joy/js/jquery',
			main : 'commons/joy',
			config : {}
		},
		'joyCss' : {
			location : 'joy/css/bootstrap',
			main : 'commons/joy',
			config : {}
		}
	},
	preloads: []
};
curl = {
	baseUrl : joy.getWebRootPath()+'/static',
	pluginPath: "3rd/curl/curl/plugin",
	paths : {
		jquery : '3rd/jquery/jquery/jquery',
		bootstrap : '3rd/bootstrap/bootstrap/js/bootstrap',
        jqValidate : {
            location: '3rd/jquery/validation/jquery.validate',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.validate',
                requires: [ 'jquery' ]
            }
        },
        jsrender : {
            location: '3rd/jquery/jsrender/jsrender',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.render',
                requires: [ 'jquery' ]
            }
        },
        layer : {
            location: '3rd/jquery/layer/layer',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.layer',
                requires: [ 'jquery' ]
            }
        },
		jqAddMethods : '3rd/jquery/validation/additional-methods',
		jqTreeTable : '3rd/jquery/treeTable/jquery.treeTable',
		jqLayer : '3rd/jquery/layer/layer',
		datePicker : '3rd/My97DatePicker/WdatePicker'
	},
	packages : {
		'joy' : {
			location : 'joy/js/jquery',
//			main : 'commons/joy',
			config : {}
		},
		'joyCss' : {
			location : 'joy/css/bootstrap',
			main : 'commons/joy',
			config : {}
		}
	},
//	preloads: ['jquery', 'joy']
    preloads: ['jquery']
};
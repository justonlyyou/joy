curl = {
	baseUrl : joy.getWebRootPath()+'/static',
	pluginPath: "3rd/curl/curl/plugin",
	paths : {
		jquery : '3rd/jquery/jquery/jquery.min',
		bootstrap : '3rd/bootstrap/bootstrap/js/bootstrap.min',
        jqValidate : {
            location: '3rd/jquery/validation/jquery.validate.min',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.validate',
                requires: [ 'jquery' ]
            }
        },
        jsrender : {
            location: '3rd/jquery/jsrender/jsrender.min',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.render',
                requires: [ 'jquery' ]
            }
        },
        layer : {
            location: '3rd/jquery/layer/layer.min',
            config: {
                loader: '3rd/curl/curl/loader/legacy',
                exports: '$.fn.layer',
                requires: [ 'jquery' ]
            }
        },
		addMethods : '3rd/jquery/validation/additional-methods.min',
		jqTreeTable : '3rd/jquery/treeTable/jquery.treeTable.min',
		datePicker : '3rd/My97DatePicker/WdatePicker'
	},
	packages : {
		'joy' : {
			location : 'joy/commons/js',
//			main : 'commons/joy',
			config : {}
		},
		
		'joyCss' : {
			location : 'joy/origin/css',
			main : 'commons/joy',
			config : {}
		}
	},
//	preloads: ['jquery', 'joy']
    preloads: ['jquery']
};
Ext.onReady(function() {

	var formPanel = Ext.create("Ext.form.Panel", {
		region:'center',
		title : '登陆',
		bodyStyle : 'padding:5 5 5 5',
		frame : true,
		width : 300,
		height : 200,
		id : 'logForm',
		renderTo : 'loginForm',
		layout : {
			type : 'vbox'
		},
		defaultType : 'textfield',
		defaults : {
			labelSeparator : "： ",
			labelWidth : 50,
			labelAlign : 'right',
			width : 240,
			allowBlank : false,
			msgTarget : 'side',
		},
		items : [ {
			fieldLabel : '用户名',
			name : 'account',
			id : 'userName',
			value: 'kevice',
			grow : false,
			selectOnFocus : true,
//			regex : /^[a-zA-Z]+$/,
//			regexText : 'letter only',
		}, {
			fieldLabel : '密码',
			name : 'password',
			value: 'twl',
			id : 'password',
			inputType : 'password'
		} ],
		buttons : [ {
			text : '登陆',
			handler : login
		}, {
			text : '重置',
			handler : reset
		} ]
	});

	function login() {
		var form = formPanel.getForm();
		if (form.isValid()) {
			form.submit({
//				clientValidation : true,
				url : 'user/Login/user.do',
				method : 'POST',
				success : function(form, action) {
					document.location = "app.do";
				},
				failure : function(form, action) {
					Ext.Msg.alert('错误', action.result.errMsg);
				}
			});
		}
	}
	
	function reset() {
		formPanel.form.reset();
	}

});
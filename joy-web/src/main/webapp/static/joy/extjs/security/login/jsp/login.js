alert("kkkkkkkk");
//
//Ext.onReady(function() {
//
//	var formPanel = Ext.create("Ext.form.Panel", {
//		region:'center',
//		title : '登陆',
//		bodyStyle : 'padding:5 5 5 5',
//		frame : true,
//		width : 300,
//		height : 200,
//		id : 'logForm',
//		renderTo : 'loginForm',
//		layout : {
//			type : 'vbox'
//		},
//		defaultType : 'textfield',
//		defaults : {
//			labelSeparator : "： ",
//			labelWidth : 50,
//			labelAlign : 'right',
//			width : 240,
//			allowBlank : false,
//			msgTarget : 'side',
//		},
//		items : [ {
//			fieldLabel : '用户名',
//			name : 'user.account',
//			id : 'userName',
//			grow : false,
//			selectOnFocus : true,
//			regex : /^[a-zA-Z]+$/,
//			regexText : 'letter only',
//		}, {
//			fieldLabel : '密码',
//			name : 'user.password',
//			id : 'password',
//			inputType : 'password'
//		} ],
//		buttons : [ {
//			text : '登陆',
//			handler : login
//		}, {
//			text : '重置',
//			handler : reset
//		} ]
//	});
//
//	function login() {
//		formPanel.getForm().submit({
//			clientValidation : true,
//			url : 'login.do',
//			method : 'POST',
//			success : function(form, action) {
//				document.location = "app.do";
//				Ext.Msg.alert(action.result.message);
//			},
//			failure : function(form, action) {
//				Ext.Msg.alert('提示', '系统登录失败，原因: ' + action.failureType);
//			}
//		});
//	}
//	
//	function reset() {
//		formPanel.form.reset();
//	}
//
//});
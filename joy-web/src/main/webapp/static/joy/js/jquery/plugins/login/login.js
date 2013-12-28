define(['joy','jqValidate'], function(joy) {
	
	$("#loginForm").validate({
			onfocusout: true,
			rules : {
				captcha : {
					remote : "captcha/validateCode"
				}
			},
			messages : {
				username : {
					required : "请填写用户名."
				},
				password : {
					required : "请填写密码."
				},
				captcha : {
					remote : "验证码不正确.",
					required : "请填写验证码."
				}
			},
			errorContainer : "#messageBox",
			errorLabelContainer : "#loginError",
			wrapper: "ul",
			invalidHandler : function() {
				$("#messageBox").removeClass("hide");
			},
			submitHandler: function(form) {
				$.ajax({
	                cache: true,
	                type: "POST",
	                url: "login",
	                data: $('#loginForm').serialize(),
	                async: false,
	                error: function(request) {
	                    alert("连接不上服务器！");
	                },
	                success: function(data) {
	                	if(data == null || data == "") {
	                		form.commit();
	                	} else {
	                		if(data == "captchaRequire") {
	                			$(".joy-captcha").removeClass("hide");
	                		} else {
	                			alert(data);	
	                		}
	                	}
	                }
	            });
				return true;
			}
		});
		
		// 如果在框架中，则跳转刷新上级页面
		if (self.frameElement && self.frameElement.tagName == "IFRAME") {
			parent.location.reload();
		}
	
});
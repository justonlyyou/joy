<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="登录页面">
<meta name="author" content="Kevice">

<title>${joy:getAppProperty('app.name')}登录</title>

<style type="text/css">
body {
	padding-top: 70px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin-heading {
	text-align: center;
	font-size: 32px;
	margin-bottom: 20px;
	color: #0663a2;
}

.form-signin {
	max-width: 400px;
	padding: 15px;
	margin: 0 auto;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	border-radius: 5px;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: 5px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.copy-right {
	padding: 10px;
	text-align: center;
}

.alert {
	position: relative;
	width: 300px;
	margin: 0 auto;
	padding-bottom: 0px;
}

</style>

<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			onfocusout: true,
			rules : {
				captcha : {
					remote : "${ctx}/captcha/validateCode"
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
	                url: "${ctx}/login",
	                data: $('#loginForm').serialize(),
	                async: false,
	                error: function(request) {
	                    alert("服务器内部发生未预期的错误！");
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
	});
	// 如果在框架中，则跳转刷新上级页面
	if (self.frameElement && self.frameElement.tagName == "IFRAME") {
		parent.location.reload();
	}
</script>

</head>

<body>

	<div class="container">

		<h2 class="form-signin-heading">${joy:getAppProperty('app.name')}</h2>
		<form id="loginForm" class="form-signin" action="${ctx}/login"
			method="get">
			<input type="text" class="form-control required" placeholder="用户名"  name="username" value="kevice" value="${username}" autofocus> 
			<input type="password" class="form-control required" placeholder="密码" name="password" value="theshyboy">
			<tags:captcha name="captcha" />
			<label class="checkbox"> 
				<input type="checkbox" value="remember-me" name="rememberMe" title="下次不需要再登录">
				记住我（公共场所慎用）
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

		<div class="copy-right">Copyright &copy;
			2012-${joy:getAppProperty('app.copyRight.year')} - Powered By
			${joy:getAppProperty('app.copyRight.author')}
			${joy:getAppProperty('app.version')}</div>
			
		<div id="messageBox" class="alert alert-block alert-danger fade in hide">
			<label id="loginError" class="error"></label>
		</div>
		
	</div>

</body>
</html>

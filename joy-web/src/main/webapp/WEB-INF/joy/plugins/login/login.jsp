<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="登录页面">
<meta name="author" content="唐玮琳">

<title>${joyFn:getAppProperty('app.name')}登录</title>

</head>

<body>

	<div class="container">

		<h2 class="form-signin-heading">${joyFn:getAppProperty('app.name')}</h2>
		<form id="loginForm" class="form-signin" action="${ctx}/login"
			method="get">
			<input type="text" class="form-control required" placeholder="用户名"  name="username" value="kevice" value="${username}" autofocus> 
			<input type="password" class="form-control required" placeholder="密码" name="password" value="tangwl">
			<joy:captcha name="captcha" />
			<label class="checkbox"> 
				<input type="checkbox" value="remember-me" name="rememberMe" title="下次不需要再登录">
				记住我（公共场所慎用）
			</label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

		<div class="copy-right">Copyright &copy;
			2012-${joyFn:getAppProperty('app.copyRight.year')} - Powered By
			${joyFn:getAppProperty('app.copyRight.author')}
			${joyFn:getAppProperty('app.version')}</div>
			
		<div id="messageBox" class="alert alert-block alert-danger fade in hide">
			<label id="loginError" class="error"></label>
		</div>
		
	</div>

    <script type="text/javascript">
        curl(['joy/plugins/login/login', 'css!joyCss/plugins/login/login']);
    </script>

</body>
</html>

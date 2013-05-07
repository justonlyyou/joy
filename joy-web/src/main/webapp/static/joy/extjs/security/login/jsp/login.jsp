<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户登陆</title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/joy/extjs/common/platform/include.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/joy/extjs/security/login/js/login.js"></script>
	<style type="text/css">
        html,body {height:100%}
    </style>
</head>
<body>
	<table width="100%" height="100%">
		<tr>
			<td>
				 <center><div id="loginForm"></div></center>
			</td>
		</tr>
	</table>
</body>
</html>
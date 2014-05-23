<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="系统参数详情页面">
<meta name="author" content="唐玮琳">

<title>系统参数详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>用户ID</td>
        		<td>${model.userId}</td>
        		<td>用户帐号</td>
        		<td>${model.userAccount}</td>
        	</tr>
        	<tr>
        		<td>姓名</td>
        		<td>${model.userName}</td>
        		<td>密码(加密)</td>
        		<td>${model.userPassword}</td>
        	</tr>
        	<tr>
        		<td>登陆时间</td>
        		<td><joy:timeFormatter timeStr="${model.loginTime}"/></td>
        		<td>登出时间</td>
        		<td><joy:timeFormatter timeStr="${model.logoutTime}"/></td>
        	</tr>
        	<tr>
        		<td>登陆状态</td>
        		<td>${model.loginState.trans}</td>
        		<td>登陆IP</td>
        		<td>${model.loginIp}</td>
        	</tr>
        	<tr>
        		<td>浏览器类型</td>
        		<td>${model.broswerType}</td>
        		<td>浏览器版本</td>
        		<td>${model.broswerVersion}</td>
        	</tr>
        	<tr>
        		<td>操作系统类型</td>
        		<td>${model.osType}</td>
        		<td>操作系统版本</td>
        		<td>${model.osVersion}</td>
        	</tr>
        	<tr>
        		<td>记住我</td>
        		<td><joy:codeTrans code="${model.rememberMe}" enumClass="bool" /></td>
        	</tr>
		</table>
	</div>
</body>
</html>
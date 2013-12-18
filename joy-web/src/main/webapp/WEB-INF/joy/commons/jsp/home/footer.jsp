<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页底部">
<meta name="author" content="Kevice">

<title>主页底部</title>

<style type="text/css">
.copy-right {
	padding: 10px;
	text-align: center;
	margin-top: 5px;
	margin-bottom: 1px;
	background-color: #f7f7f9;
  	border: 1px solid #e1e1e8;
  	border-radius: 4px;
}
</style>
<script type="text/javascript">
</script>

</head>

<body>
	<div class="copy-right">
			Copyright &copy;
			2012-${joyFn:getAppProperty('app.copyRight.year')} - Powered By
			${joyFn:getAppProperty('app.copyRight.author')}
			${joyFn:getAppProperty('app.version')}
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%--<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>--%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页底部">
<meta name="author" content="唐玮琳">

<title>主页底部</title>
</head>

<body>
	<div class="copy-right">
			Copyright &copy;
			2012-${joyFn:getAppProperty('app.copyRight.year')} - Powered By
			${joyFn:getAppProperty('app.copyRight.author')}
			${joyFn:getAppProperty('app.version')}
	</div>

    <script type="text/javascript">
        curl(['css!joyCss/commons/home/footerNav']);
    </script>
</body>
</html>
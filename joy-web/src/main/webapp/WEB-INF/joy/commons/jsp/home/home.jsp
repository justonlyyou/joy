<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页面">
<meta name="author" content="Kevice">

<title>${joy:getAppProperty('app.name')}</title>

<style type="text/css">
html,body {
	height: 100%;
	width: 100%;
	padding: 0;
	margin: 0;
	border: 0;
}
.mainFrame {
	border: 1px solid #e1e1e8;
	border-radius: 4px;
/* 	background-color: #f7f7f9; */
	padding-left:10px;
	padding-top:10px;
	padding-right:10px;
}

</style>

<script type="text/javascript">
</script>

</head>

<body>
		<div style="padding-right:5px;padding-left:5px;">
			<table width="100%" height="100%">
				<tr>
					<td colspan="2">
						<%@ include file="topNav.jsp"%>
					</td>
				</tr>
				<tr height="100%">
					<td width="20%"  valign="top">
						<%@ include file="leftNav.jsp"%>
					</td>
					<td style="padding-left:5px;">
						<iframe id="mainFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="" name=""></iframe>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<%@ include file="footer.jsp"%>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>
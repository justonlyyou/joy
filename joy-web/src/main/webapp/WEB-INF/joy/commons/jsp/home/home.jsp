<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页面">
<meta name="author" content="唐玮琳">

<title>${joyFn:getAppProperty('app.name')}</title>
   <%-- <script type="text/javascript" src="${thirdCtx}/comet/core.js"></script>
    <script type="text/javascript" src="${thirdCtx}/comet/comet.js"></script>--%>
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

        <script type="text/javascript">
            curl(['css!joyCss/commons/home/home']);
        </script>
</body>
</html>
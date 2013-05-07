<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>500 Oops!</title>
	<script type="text/javascript">
		$(function(){
			$("#center-div").center(true);
		})
	</script>
	<style type="text/css">
	body {
		margin: 0;
		padding: 0;
		background-color: #f5f5f5;
	}
	</style>
</head>
<body>
	<div id="center-div">
		<table style="height: 100%; width: 600px; text-align: center;">
			<tr>
				<td>
				<img width="220" height="393" src="${joyCtx}/commons/images/error.png" style="float: left; padding-right: 20px;" alt="" />
				<h4 style="color: #FF0000; line-height: 25px; font-size: 20px; text-align: left;">500 Oops! Internal Server Error </h4>
				<h4 style="color: #FF0000; line-height: 25px; font-size: 20px; text-align: left;">错误提示:</h4>
				<p style="line-height: 12px; color: #666666; font-family: Tahoma, '宋体'; font-size: 12px; text-align: left;">
					服务器内部错误,请稍候再试!!!<a href="javascript:history.go(-1);">返回</a>!
				</p>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>

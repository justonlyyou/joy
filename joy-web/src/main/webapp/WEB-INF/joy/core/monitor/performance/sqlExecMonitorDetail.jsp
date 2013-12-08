<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="脚本执行详情页面">
<meta name="author" content="Kevice">

<title>脚本执行详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>日志时间</td>
        		<td><tags:timeFormatter timeStr="${model.logTime}" realFmt="yyyyMMddHHmmssSSS" displayFmt="yyyy-MM-dd HH:mm:ss SSS"/></td>
        	</tr>
        	<tr>
        		<td>耗时(毫秒)</td>
        		<td>${model.costTime}</td>
        	</tr>
        	<tr>
        		<td>未参数化sql脚本</td>
        		<td>${model.sqlText}</td>
        	</tr>
        	<tr>
        		<td>参数</td>
        		<td>${model.variables}</td>
        	</tr>
        	<tr>
        		<td>参数化sql脚本</td>
        		<td>${model.fullSql}</td>
        	</tr>
        	<tr>
        		<td>应用</td>
        		<td>${model.appName}</td>
        	</tr>
        	<tr>
        		<td>模块</td>
        		<td>${model.moduleName}</td>
        	</tr>
        	<tr>
        		<td>类名</td>
        		<td>${model.className}</td>
        	</tr>
        	<tr>
        		<td>方法</td>
        		<td>${model.methodName}</td>
        	</tr>
        	<tr>
        		<td>行序</td>
        		<td>${model.lineNumber}</td>
        	</tr>
		</table>
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="脚本安装详情页面">
<meta name="author" content="Kevice">

<title>脚本安装详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>版本域</td>
        		<td>${model.versionDomain}</td>
        		<td>版本号</td>
        		<td>${model.version}</td>
        	</tr>
        	<tr>
        		<td>类型</td>
        		<td>${model.type}</td>
        		<td>脚本</td>
        		<td>${model.script}</td>
        	</tr>
        	<tr>
        		<td>成功</td>
        		<td><joy:codeTrans code="${model.success}" enumClass="bool"/></td>
        		<td>执行时间(毫秒)</td>
        		<td>${model.executionTime}</td>
        	</tr>
        	<tr>
        		<td>安装时间</td>
        		<td><joy:timeFormatter timeStr="${model.installedOn}" origFmt="yyyyMMddHHmmssSSS" displayFmt="yyyy-MM-dd HH:mm:ss SSS"/></td>
        		<td>安装用户</td>
        		<td>${model.installedBy}</td>
        	</tr>
        	<tr>
        		<td>校验和</td>
        		<td>${model.checksum}</td>
        		<td>描述</td>
        		<td>${model.desc}</td>
        	</tr>
		</table>
	</div>
</body>
</html>
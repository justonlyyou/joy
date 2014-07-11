<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="数据源详情页面">
<meta name="author" content="Kevice">

<title>数据源详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
		<div class="joy-title-bar">数据源信息</div>
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>数据源ID</td>
        		<td>${model.id}</td>
        		<td>数据源名称</td>
        		<td>${model.name}</td>
        	</tr>
        	<tr>
        		<td>JNDI名称</td>
        		<td>${model.jndiName}</td>
        		<td>数据库类型</td>
        		<td>${model.dbType}</td>
        	</tr>
        	<tr>
        		<td>数据库名称</td>
        		<td>${model.dbName}</td>
        		<td>数据库别名</td>
        		<td>${model.dbAlias}</td>
        	</tr>
        	<tr>
        		<td>连接串</td>
        		<td colspan="3">${model.dbUrl}</td>
        	</tr>
        	<tr>
        		<td>IP地址</td>
        		<td>${model.ipAddress}</td>
        		<td>端口</td>
        		<td>${model.serverPort}</td>
        	</tr>
        	<tr>
        		<td>用户名</td>
        		<td>${model.username}</td>
        		<td>用户密码</td>
        		<td>${model.password}</td>
        	</tr>
        	<tr>
        		<td>最小连接数</td>
        		<td>${model.minConnCount}</td>
        		<td>最大连接数</td>
        		<td>${model.maxConnCount}</td>
        	</tr>
        	<tr>
        		<td>连接参数</td>
        		<td>${model.parameter}</td>
        		<td>字符集</td>
        		<td>${model.charset}</td>
        	</tr>
        	<tr>
        		<td>启用</td>
        		<td><joy:codeTrans code="${model.active}" enumClass="bool" /></td>
        		<td>系统内置</td>
        		<td><joy:codeTrans code="${model.builtIn}" tableId="bool" /></td>
        	</tr>
        	<tr>
        		<td>描述</td>
        		<td colspan="3">${model.remark}</td>
        	</tr>
		</table>
		
		<joy:crudDetailList/>
		
	</div>
</body>
</html>
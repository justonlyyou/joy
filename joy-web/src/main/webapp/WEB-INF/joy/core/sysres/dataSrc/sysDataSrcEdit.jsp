<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="数据源编辑页面">
<meta name="author" content="Kevice">

<title>数据源编辑</title>

</head>

<body>
	<div class="joy-search-bar">
		<button id="submitBtn" class="btn btn-default">
			<i class="fa fa-search"></i>&nbsp;查询
		</button>
		<button id="newBtn" class="btn btn-default">
			<i class="fa fa-plus"></i>&nbsp;新增
		</button>
	</div>

	<div class="table-responsive">
		<div class="joy-title-bar">数据源信息</div>
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<%--<tr>--%>
        		<%--<td>数据源名称</td>--%>
        		<%--<td><form:input cssClass="form-control input-sm" path="name" /></td>--%>
        		<%--<td>数据库类型</td>--%>
        		<%--<td>--%>
        			<%--<form:select path="dbType" cssClass="form-control  input-sm">--%>
						<%--<joy:codeOptions enumClass="com.kvc.joy.core.persistence.jdbc.support.enums.RdbType" />--%>
					<%--</form:select>--%>
				<%--</td>--%>
        	<%--</tr>--%>
        	<tr>
        		<td>数据库名称</td>
        		<td><input type="text" class="form-control input-sm" name="model.dbName"></td>
        		<td>数据库别名</td>
        		<td><input type="text" class="form-control input-sm" name="model.dbAlias"></td>
        	</tr>
        	<tr>
        		<td>IP地址</td>
        		<td><input type="text" class="form-control input-sm" name="model.ipAddress"></td>
        		<td>端口</td>
        		<td><input type="text" class="form-control input-sm" name="model.serverPort"></td>
        	</tr>
        	<tr>
        		<td>用户名</td>
        		<td><input type="text" class="form-control input-sm" name="model.username"></td>
        		<td>密码</td>
        		<td><input type="password" class="form-control input-sm" name="model.password"></td>
        	</tr>
        	<tr>
        		<td>字符集</td>
        		<td><input type="text" class="form-control input-sm" name="model.charset"></td>
        		<td>其它参数</td>
        		<td><input type="password" class="form-control input-sm" name="model.parameter"></td>
        	</tr>
        	<tr>
        		<td>最小连接数</td>
        		<td><input type="text" class="form-control input-sm" name="model.minConnCount"></td>
        		<td>最大连接数</td>
        		<td><input type="password" class="form-control input-sm" name="model.maxConnCount"></td>
        	</tr>
        	<tr>
        		<td>连接串</td>
        		<td colspan="3"><input type="text" class="form-control input-sm" name="model.dbUrl"></td>
        	</tr>
        	<tr>
        		<td>JNDI</td>
        		<td><input type="text" class="form-control input-sm" name="model.jndi"></td>
        		<td>启用</td>
        		<td>
        			<select class="form-control  input-sm" name="model.active">
						<joy:codeOptions enumClass="bool" showEmptyOption="false"/>
					</select>
        		</td>
        	</tr>
        	<tr>
        		<td>描述</td>
        		<td colspan="3"><input type="text" class="form-control input-sm" name="model.desc"></td>
        	</tr>
		</table>
		
	</div>
</body>
</html>
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

<form:form id="editForm" action="${ctx}/sysDataSrc/edit" method="post">

	<div class="joy-search-bar">
		<button id="saveBtn" class="btn btn-default">
			<i class="fa fa-save"></i>&nbsp;保存
		</button>
		<button id="delBtn" class="btn btn-default">
			<i class="fa fa-times"></i>&nbsp;删除
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
						<%--<joy:codeOptions enumClass="org.joy.core.persistence.jdbc.support.enums.RdbType" />--%>
					<%--</form:select>--%>
				<%--</td>--%>
        	<%--</tr>--%>
        	<tr>
        		<td>数据库名称</td>
        		<td><form:input path="name" cssClass="form-control input-sm" /></td>
        		<td>数据库别名</td>
        		<td><form:input path="dbAlias" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>IP地址</td>
        		<td><form:input path="ipAddress" cssClass="form-control input-sm" /></td>
        		<td>端口</td>
        		<td><form:input path="serverPort" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>用户名</td>
        		<td><form:input path="username" cssClass="form-control input-sm" /></td>
        		<td>密码</td>
        		<td><form:input path="password" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>字符集</td>
        		<td><form:input path="charset" cssClass="form-control input-sm" /></td>
        		<td>其它参数</td>
        		<td><form:input path="parameter" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>最小连接数</td>
        		<td><form:input path="minConnCount" cssClass="form-control input-sm" /></td>
        		<td>最大连接数</td>
        		<td><form:input path="maxConnCount" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>连接串</td>
        		<td colspan="3"><form:input path="dbUrl" cssClass="form-control input-sm" /></td>
        	</tr>
        	<tr>
        		<td>JNDI</td>
        		<td><form:input path="jndiName" cssClass="form-control input-sm" /></td>
        		<td>启用</td>
        		<td>
        			<select class="form-control  input-sm" name="active">
						<joy:codeOptions enumClass="bool" showEmptyOption="false"/>
					</select>
        		</td>
        	</tr>
        	<tr>
        		<td>描述</td>
        		<td colspan="3"><form:input path="remark" cssClass="form-control input-sm" /></td>
        	</tr>
		</table>
		
	</div>

</form:form>

<joy:curl type="edit"/>

</body>
</html>
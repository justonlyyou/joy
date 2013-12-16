<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="关系数据库对象字段详情页面">
<meta name="author" content="Kevice">

<title>关系数据库对象字段详情</title>

</head>

<body>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>数据源ID</td>
        		<td>${dsId}</td>
        		<td>数据源名称</td>
        		<td>${dsName}</td>
        	</tr>
        	<tr>
        		<td>对象名</td>
        		<td>${objName}</td>
        		<td>对象注释</td>
        		<td>${objComment}</td>
        	</tr>
        	<tr>
        		<td>字段名</td>
        		<td>${model.name}</td>
        		<td>注释</td>
        		<td>${model.comment.briefDesc}</td>
        	</tr>
        	<tr>
        		<td>主键</td>
        		<td><tags:codeTrans code="${model.key}" enumClass="bool" /></td>
        		<td>允许为空</td>
        		<td><tags:codeTrans code="${model.nullable}" enumClass="bool" /></td>
        	</tr>
        	<tr>
        		<td>类型</td>
        		<td>${model.type}</td>
        		<td>默认值</td>
        		<td>${model.defaultValue}</td>
        	</tr>
        	<tr>
        		<td>长度</td>
        		<td>${model.length}</td>
        		<td>精度</td>
        		<td>${model.precision}</td>
        	</tr>
        	<tr>
        		<td>代码表ID</td>
        		<td>${model.comment.codeId}</td>
        		<td>描述</td>
        		<td>${model.comment.detailDesc}</td>
        	</tr>
		</table>
	</div>
	
</body>
</html>
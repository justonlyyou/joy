<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="关系数据库对象详情页面">
<meta name="author" content="Kevice">

<title>关系数据库对象详情</title>
</head>

<body>
	<div class="joy-title-bar">对象信息</div>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>数据源ID</td>
        		<td>${model.dsId}</td>
        		<td>数据源名称</td>
        		<td>${dsName}</td>
        	</tr>
        	<tr>
        		<td>对象名</td>
        		<td>${model.name}</td>
        		<td>对象注释</td>
        		<td>${model.comment}</td>
        	</tr>
        	<tr>
        		<td>对象类型</td>
        		<td>${model.type}</td>
        		<td></td>
        		<td></td>
        	</tr>
		</table>
	</div>
	
	<div class="joy-title-bar">字段信息</div>
	<div class="table-responsive">
		<table
			class="table table-condensed table-hover table-striped table-bordered joy-table">
			<thead>
				<tr>
					<th class="joy-table-seq-col" width="30px">#</th>
					<th width="70px">操作</th>
					<th><joy:orderColumn property="model.columns.name" columnName="列名"/></th>
					<th>注释</th>
					<th>主键</th>
					<th>允许为空</th>
					<th>类型</th>
					<th>默认值</th>
					<th>长度</th>
					<th>精度</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${model.columns}" var="p" varStatus="status">
					<tr>
						<td class="joy-table-seq-col">${status.index+1}</td>
						<td><joy:listOperations id="${model.dsId}-${model.name}-${p.name}" showEditOp="false" showDeleteOp="false"/></td>
						<td>${p.name}</td>
						<td>${p.comment.briefDesc}</td>
						<td><joy:codeTrans code="${p.key}" enumClass="bool" /></td>
						<td><joy:codeTrans code="${p.nullable}" enumClass="bool" /></td>
						<td>${p.type}</td>
						<td>${p.defaultValue}</td>
						<td>${p.length}</td>
						<td>${p.precision}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

    <joy:curl res="joy/core/sysres/mdRdbObj/mdRdbObjDetail"/>

</body>
</html>
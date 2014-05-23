<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="系统代码详情页面">
<meta name="author" content="唐玮琳">

<title>系统代码详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
	
		<div class="joy-title-bar">代码信息</div>
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>数据源ID</td>
        		<td>${model.dataSrc.id}</td>
        		<td>数据源名称</td>
        		<td>${model.dataSrc.name}</td>
        	</tr>
        	<tr>
        		<td>表名</td>
        		<td>${model.tableName}</td>
        		<td>表注释</td>
        		<td>${model.tableComment}</td>
        	</tr>
        	<tr>
        		<td>代码字段</td>
        		<td>${model.codeField}</td>
        		<td>译文字段</td>
        		<td>${model.transField}</td>
        	</tr>
        	<tr>
        		<td>父字段</td>
        		<td>${model.parentField}</td>
        		<td>排序字段</td>
        		<td>${model.orderField}</td>
        	</tr>
		</table>
		
		<joy:crudDetailList />
	</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="系统参数详情页面">
<meta name="author" content="Kevice">

<title>系统参数详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
	
		<div class="joy-title-bar">参数信息</div>
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
			<tr>
        		<td>参数名</td>
        		<td>${model.paramName}</td>
        		<td>参数值</td>
        		<td>${model.paramValue}</td>
        	</tr>
        	<tr>
        		<td>参数说明</td>
        		<td>${model.desc}</td>
        		<td>默认值</td>
        		<td>${model.defaultValue}</td>
        	</tr>
        	<tr>
        		<td>是否加密</td>
        		<td><tags:codeTrans code="${model.encrypt}" enumClass="com.kvc.joy.commons.enums.YesNot"/></td>
        		<td></td>
        		<td></td>
        	</tr>
		</table>
		
		<tags:crudDetailList />
	</div>
</body>
</html>
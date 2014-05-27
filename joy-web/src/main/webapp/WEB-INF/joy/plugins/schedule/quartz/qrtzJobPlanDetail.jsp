<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="任务调度计划详情页面">
<meta name="author" content="Kevice">

<title>任务调度计划详情</title>

<script type="text/javascript">
</script>

</head>

<body>
	<div class="table-responsive">
	
		<div class="joy-title-bar">计划信息</div>
		<table
			class="table table-condensed table-hover table-bordered joy-detail-table">
        	<tr>
        		<td>计划ID</td>
        		<td>${model.id}</td>
        		<td>计划名称</td>
        		<td>${model.name}</td>
        	</tr>
        	<tr>
        		<td>CRON表达式</td>
        		<td>${model.cronExp}</td>
        		<td>运行状态</td>
        		<td><joy:codeTrans code="${model.runState}" tableId="job_run_state"/></td>
        	</tr>
        	<tr>
        		<td>上次执行时间</td>
        		<td><joy:timeFormatter timeStr="${model.lastFireTime}" /> </td>
        		<td>下次执行时间</td>
        		<td><joy:timeFormatter timeStr="${model.nextFireTime}" /></td>
        	</tr>
        	<tr>
        		<td>生效时间</td>
        		<td><joy:timeFormatter timeStr="${model.effectTime}" /> </td>
        		<td>到期时间</td>
        		<td><joy:timeFormatter timeStr="${model.expireTime}" /></td>
        	</tr>
        	<tr>
        		<td>启用</td>
        		<td><joy:codeTrans code="${model.active}" enumClass="bool"/></td>
        		<td>系统内置</td>
        		<td><joy:codeTrans code="${model.builtIn}" enumClass="bool"/></td>
        	</tr>
        	<tr>
        		<td>描述</td>
        		<td colspan="3">${model.desc}</td>
        	</tr>
		</table>
		
		<joy:crudDetailList />
	</div>
</body>
</html>
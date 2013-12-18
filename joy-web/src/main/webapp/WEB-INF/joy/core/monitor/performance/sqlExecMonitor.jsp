<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SQL脚本安装日志管理页面">
<meta name="author" content="Kevice">

<title>SQL脚本安装日志管理页面</title>

<script type="text/javascript">
	function showDetail(id) {
		$.layer({
		    type : 2,
		    title : 'SQL脚本安装日志详情',
		    iframe : {src : '${ctx}/sqlExecMonitor/get?id='+id},
		    area : ['750px' , '466px'],
		    offset : ['50px','']
		});
	}
</script>

</head>

<body>

	<form:form action="${ctx}/sqlExecMonitor/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<table>
						<tr>
							<td>
								执行时间：
							</td>
							<joy:timeRangePicker property="logTime" realFmt="yyyyMMddHHmmssSSS"/>
							<td>
								&nbsp;耗时大于：
							</td>
							<td>
								<form:input class="form-control" path="costTime" placeholder="请输入耗时毫秒数" data-joy-props="operator:'>'"/>
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<form:button id="submitBtn" class="btn btn-default">
									<i class="fa fa-search"></i>&nbsp;查询
								</form:button>
							</td>
							
						</tr>
					</table>
				</div>

				<div class="table-responsive">
					<table
						class="table table-condensed table-hover table-striped table-bordered joy-table">
						<thead>
							<tr>
								<th class="joy-table-seq-col" width="30px">#</th>
								<th width="70px">操作</th>
								<th>
								<joy:orderColumn property="costTime" columnName="耗时(毫秒)" defaultOrder="DESC" /></th>
								<th><joy:orderColumn property="logTime" columnName="执行时间" /></th>
								<th>应用</th>
								<th>类</th>
								<th>方法</th>
								<th>行序</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="stauts">
								<tr>
									<td class="joy-table-seq-col">${stauts.index+1}</td>
									<td><joy:listOperations id="${p.id}" showEditOp="false" showDeleteOp="false"/></td>
									<td>${p.costTime}</td>
									<td><joy:timeFormatter timeStr="${p.logTime}" realFmt="yyyyMMddHHmmssSSS" displayFmt="yyyy-MM-dd HH:mm:ss SSS"/></td>
									<td>${p.appName}</td>
									<td>${fn:split(p.className, ".")[fn:length(fn:split(p.className, ".")) - 1]}</td>
									<td>${p.methodName}</td>
									<td>${p.lineNumber}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<joy:pagination />

			</div>
		</div>
	</form:form>

</body>
</html>
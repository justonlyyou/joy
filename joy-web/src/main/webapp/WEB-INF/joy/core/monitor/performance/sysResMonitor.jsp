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
</head>

<body>

	<form:form action="${ctx}/sqlScriptInstallLog/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal" role="form" style="height: 36px">
						<div class="form-group">
							<label for="paramName" class="col-sm-1 control-label">参数名</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="paramName" placeholder="请输入参数名" data-joy-props="operator:'ilike'"/>
							</div>
							<label for="paramValue" class="col-sm-1 control-label">参数值</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="paramValue" placeholder="请输入参数值" data-joy-props="operator:'ilike'"/>
							</div>
							<div class="col-sm-6">
								<form:button id="submitBtn" class="btn btn-default">
									<i class="fa fa-search"></i>&nbsp;查询
								</form:button>
							</div>
						</div>
					</div>
				</div>

				<div class="table-responsive">
					<table
						class="table table-condensed table-hover table-striped table-bordered joy-table">
						<thead>
							<tr>
								<th class="joy-table-seq-col" width="30px">#</th>
								<th width="70px">操作</th>
								<th>
								<joy:orderColumn property="paramName" columnName="参数名" defaultOrder="ASC" /></th>
								<th><joy:orderColumn property="paramValue" columnName="参数值" /></th>
								<th>说明</th>
								<th>是否加密</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="status">
								<tr>
									<td class="joy-table-seq-col">${status.index+1}</td>
									<td><joy:listOperations id="${p.id}" showEditOp="false" showDeleteOp="false" pageObj="mgmtPage"/></td>
									<td>${p.paramName}</td>
									<td>${p.paramValue}</td>
									<td>${p.desc}</td>
									<td><joy:codeTrans code="${p.encrypt}" enumClass="bool" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

                <joy:pagination pageObj="mgmtPage"/>

			</div>
		</div>
	</form:form>

</body>
</html>
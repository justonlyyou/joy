<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="系统数据源管理页面">
<meta name="author" content="Kevice">

<title>系统数据源管理页面</title>

</head>

<body>
	<form:form action="${ctx}/sysDataSrc/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal">
						<label for="id" class="col-sm-1 control-label">数据源</label>
						<div class="col-sm-2">
							<form:select path="id" class="form-control" data-joy-props="operator:'='">
								<form:option value="">-- 请选择 --</form:option>
								<c:forEach items="${joyFn:getAllDataSrc()}" var="entry">
									<form:option value="${entry.id}" class="joy-select-option">${entry.name}</form:option>
								</c:forEach>
							</form:select>
						</div>
						<div class="col-sm-1">
							<form:button id="submitBtn" class="btn btn-default" style="margin-left: 5px">
								<i class="fa fa-search"></i>&nbsp;查询
							</form:button>
						</div>
						<div class="col-sm-8">
							<form:button id="newBtn" class="btn btn-default" style="margin-left: 3px">
								<i class="fa fa-plus"></i>&nbsp;新增
							</form:button>
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
								<joy:orderColumn property="id" columnName="数据源ID" defaultOrder="ASC" /></th>
								<th><joy:orderColumn property="name" columnName="数据源名称" /></th>
								<th>JNDI</th>
								<th>URL</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="status">
								<tr>
									<td class="joy-table-seq-col">${status.index+1}</td>
									<td><joy:listOperations id="${p.id}"/></td>
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td>${p.jndiName}</td>
									<td>${p.dbUrl}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<joy:pagination />

			</div>
		</div>
	</form:form>
	
	<script type="text/javascript">
        curl(['joy/core/sysres/sysDataSrc']);
	</script>

</body>
</html>
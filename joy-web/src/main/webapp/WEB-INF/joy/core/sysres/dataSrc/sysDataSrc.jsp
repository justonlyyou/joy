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

<script type="text/javascript">
	function showDetail(id) {
		$.layer({
		    type : 2,
		    title : '系统数据源详情',
		    iframe : {src : '${ctx}/sysDataSrc/get?id='+id},
		    area : ['750px' , '500px'],
		    offset : ['50px','']
		});
	}
</script>

</head>

<body>

	<form action="${ctx}/sysDataSrc/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<tags:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal" role="form" style="height: 36px">
						<div class="form-group">
							<label for="id" class="col-sm-1 control-label">数据源</label>
							<div class="col-sm-2">
								<select class="form-control" name="id" value="${id}" data-joy-props="operator:'='">
									<option value="">-- 请选择 --</option>
									<c:forEach items="${joy:getAllDataSrc()}" var="entry">
										<option value="${entry.id}" class="joy-select-option">${entry.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-9">
								<button id="submitBtn" class="btn btn-default">
									<i class="fa fa-search"></i>&nbsp;查询
								</button>
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
								<tags:orderColumn property="id" columnName="数据源ID" defaultOrder="ASC" /></th>
								<th><tags:orderColumn property="name" columnName="数据源名称" /></th>
								<th>JNDI</th>
								<th>URL</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="stauts">
								<tr>
									<td class="joy-table-seq-col">${stauts.index+1}</td>
									<td><tags:listOperations id="${p.id}"/></td>
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td>${p.jndiName}</td>
									<td>${p.dbUrl}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<tags:pagination />

			</div>
		</div>
	</form>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="系统代码管理页面">
<meta name="author" content="Kevice">

<title>系统代码管理页面</title>

<script type="text/javascript">
	function showDetail(id) {
		$.layer({
		    type : 2,
		    title : '系统代码详情',
		    iframe : {src : '${ctx}/sysCodeTable/get?id='+id},
		    area : ['750px' , '466px'],
		    offset : ['50px','']
		});
	}
</script>

</head>

<body>

	<form action="${ctx}/sysCodeTable/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal" role="form" style="height: 36px">
						<div class="form-group">
							<label for="tableName" class="col-sm-1 control-label">表名</label>
							<div class="col-sm-2">
								<input class="form-control" value="${tableName}"
									name="tableName" placeholder="请输入表名" data-joy-props="operator:'ilike'">
							</div>
							<label for="tableComment" class="col-sm-1 control-label">表中文名</label>
							<div class="col-sm-2">
								<input class="form-control" value="${tableComment}"
									name="tableComment" placeholder="请输入表中文名" data-joy-props="operator:'ilike'">
							</div>
							<div class="col-sm-6">
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
								<joy:orderColumn property="tableName" columnName="表名" defaultOrder="ASC" /></th>
								<th><joy:orderColumn property="tableComment" columnName="表中文名" /></th>
								<th>代码字段名</th>
								<th>译文字段名</th>
								<th>排序字段名</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="stauts">
								<tr>
									<td class="joy-table-seq-col">${stauts.index+1}</td>
									<td><joy:listOperations id="${p.id}"/></td>
									<td>${p.tableName}</td>
									<td>${p.tableComment}</td>
									<td>${p.codeField}</td>
									<td>${p.transField}</td>
									<td>${p.orderField}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<joy:pagination />

			</div>
		</div>
	</form>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="关系数据库对象管理页面">
<meta name="author" content="唐玮琳">

<title>关系数据库对象管理页面</title>
</head>

<body>

	<form:form action="${ctx}/mdRdbObj/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal" role="form" style="height: 36px">
						<div class="form-group">
							<label for="dsId" class="col-sm-1 control-label">数据源</label>
							<div class="col-sm-2">
								<form:select class="form-control" path="dsId" data-joy-props="operator:'='"> 
									<c:forEach items="${joyFn:getAllDataSrc()}" var="entry">
										<form:option value="${entry.id}" class="joy-select-option">${entry.name}</form:option>
									</c:forEach>
								</form:select>
							</div>
							<label for="name" class="col-sm-1 control-label">对象名</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="name" placeholder="请输入对象名" data-joy-props="operator:'ilike'" />
							</div>
							<label for="comment" class="col-sm-1 control-label">对象注释</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="comment" placeholder="请输入对象注释" data-joy-props="operator:'ilike'" />
							</div>
							<div class="col-sm-3">
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
								<th><joy:orderColumn property="name" columnName="对象名" defaultOrder="ASC" /></th>
								<th>对象注释</th>
								<th>对象类型</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="status">
								<tr>
									<td class="joy-table-seq-col">${status.index+1}</td>
									<td><joy:listOperations id="${p.dsId}-${p.name}" showEditOp="false" showDeleteOp="false" pageObj="mgmtPage"/></td>
									<td>${p.name}</td>
									<td>${p.comment}</td>
									<td>${p.type}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<joy:pagination pageObj="mgmtPage"/>

			</div>
		</div>
	</form:form>

    <script type="text/javascript">
        curl(['joy/core/sysres/mdRdbObj/mdRdbObj'], function(MgmtPage) {
            mgmtPage = new MgmtPage();
        });
    </script>

</body>
</html>
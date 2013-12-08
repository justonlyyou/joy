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
			title : '脚本安装日志详情',
			iframe : {
				src : '${ctx}/sqlScriptInstallLog/get?id=' + id
			},
			area : [ '750px', '466px' ],
			offset : [ '50px', '' ]
		});
	}
</script>

</head>

<body>

	<form action="${ctx}/sqlScriptInstallLog/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<tags:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<table>
						<tr>
							<td>
								安装时间：
							</td>
							<tags:timeRangePicker property="installedOn" realFmt="yyyyMMddHHmmssSSS"/>
							<td>
								&nbsp;版本域：
							</td>
							<td>
								<input class="form-control" value="${versionDomain}"
									name="versionDomain" placeholder="请输入版本域" data-joy-props="operator:'ilike'">
							</td>
							<td>
								&nbsp;版本：
							</td>
							<td>
								<input class="form-control" value="${version}" name="version"
									placeholder="请输入版本" data-joy-props="operator:'ilike'">
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<button id="submitBtn" class="btn btn-default">
									<i class="fa fa-search"></i>&nbsp;查询
								</button>
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
								<th>版本域</th>
								<th>版本</th>
								<th><tags:orderColumn property="installedOn"
										columnName="安装时间" defaultOrder="DESC" /></th>
								<th>脚本</th>
								<th>成功</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="stauts">
								<tr>
									<td class="joy-table-seq-col">${stauts.index+1}</td>
									<td><tags:listOperations id="${p.id}" showEditOp="false" showDeleteOp="false" /></td>
									<td>${p.versionDomain}</td>
									<td>${p.version}</td>
									<td><tags:timeFormatter timeStr="${p.installedOn}" realFmt="yyyyMMddHHmmssSSS" /></td> 
									<td>${p.script}</td>
									<td><tags:codeTrans code="${p.success}" enumClass="com.kvc.joy.commons.enums.YesNot" /></td>
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
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="任务调度计划管理页面">
<meta name="author" content="Kevice">

<title>任务调度计划管理页面</title>
</head>

<body>

	<form:form action="${ctx}/qrtzJobPlan/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<div class="form-horizontal" role="form" style="height: 36px">
						<div class="form-group">
							<label for="id" class="col-sm-1 control-label">计划ID</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="id" placeholder="请输入计划ID" data-joy-props="operator:'ilike'"/>
							</div>
							<label for="name" class="col-sm-1 control-label">计划名称</label>
							<div class="col-sm-2">
								<form:input class="form-control" path="name" placeholder="请输入计划名称" data-joy-props="operator:'ilike'"/>
							</div>
							<label for="runState" class="col-sm-1 control-label">运行状态</label>
							<div class="col-sm-2">
								<joy:codeSelect cssClass="form-control" path="runState" emptyOptionText="请选择" tableId="run_state" />
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
								<th width="90px">操作</th>
								<th>计划ID</th>
								<th>计划名称</th>
								<th>运行状态</th>
								<th>生效时间</th>
								<th>到期时间</th>
								<th>上次执行时间</th>
								<th>下次执行时间</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="status">
								<tr>
									<td class="joy-table-seq-col">${status.index+1}</td>
									<td><joy:listOperations id="${p.id}" pageObj="mgmtPage"/></td>
									<td>${p.id}</td>
									<td>${p.name}</td>
									<td><joy:codeTrans code="${p.runState}" 
										enumClass="com.kvc.joy.plugin.schedule.quartz.support.enums.JobRunState" /></td>
									<td><joy:timeFormatter timeStr="${p.effectTime}" /></td>
									<td><joy:timeFormatter timeStr="${p.expireTime}" /></td>
									<td><joy:timeFormatter timeStr="${p.lastFireTime}" /></td>
									<td><joy:timeFormatter timeStr="${p.nextFireTime}" /></td>
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
        curl(['joy/commons/BaseMgmtPage'], function(MgmtPage) {
            mgmtPage = new MgmtPage();
        });
    </script>

</body>
</html>
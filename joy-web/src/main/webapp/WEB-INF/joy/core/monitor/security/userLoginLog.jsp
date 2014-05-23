<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="SQL脚本安装日志管理页面">
<meta name="author" content="唐玮琳">

<title>SQL脚本安装日志管理页面</title>
</head>

<body>

	<form:form action="${ctx}/userLoginLog/list" method="POST">
		<div class="panel panel-default">
			<div class="panel-heading">
				<joy:pageNavTitle />
			</div>
			<div class="panel-body">
				<div class="joy-search-bar">
					<table>
						<tr>
							<joy:timeRangePicker property="loginTime" startPlaceholder="登陆时间始" endPlaceholder="登陆时间止"/>
							<td>&nbsp;&nbsp;</td>
							<td>
								<form:input class="form-control" path="userAccount" title="登陆帐号" placeholder="登陆帐号" data-joy-props="operator:'ilike'"/>
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<form:input class="form-control" path="loginIp" title="登陆IP" placeholder="登陆IP" data-joy-props="operator:'like'"/>
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<joy:codeSelect cssClass="form-control" path="loginStateCode" tableId="login_state"/>
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
								<th>帐号</th>
								<th><joy:orderColumn property="loginTime" columnName="登陆时间" defaultOrder="DESC" /></th>
								<th>登出时间</th>
								<th>登陆IP</th>
								<th>登陆状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="status">
								<tr>
									<td class="joy-table-seq-col">${status.index+1}</td>
									<td><joy:listOperations id="${p.id}" showEditOp="false" showDeleteOp="false" pageObj="mgmtPage"/></td>
									<td>${p.userAccount}</td>
									<td><joy:timeFormatter timeStr="${p.loginTime}"/></td>
									<td><joy:timeFormatter timeStr="${p.logoutTime}"/></td>
									<td>${p.loginIp}</td>
									<td>${p.loginState.trans}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

                <joy:pagination pageObj="mgmtPage"/>

			</div>
		</div>
	</form:form>

    <%--//TODO--%>
    <script src="${thirdCtx}/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        curl(['joy/core/monitor/security/userLoginLog'], function(MgmtPage) {
            mgmtPage = new MgmtPage();
        });
    </script>

</body>
</html>
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
		    iframe : {src : '${ctx}/userLoginLog/get?id='+id},
		    area : ['750px' , '466px'],
		    offset : ['50px','']
		});
	}
</script>

</head>

<body>

	<form action="${ctx}/userLoginLog/list" method="POST">
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
								<input class="form-control" value="${userAccount}" name="userAccount" 
									title="登陆帐号" placeholder="登陆帐号" data-joy-props="operator:'ilike'">
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<input class="form-control" value="${loginIp}" name="loginIp"
									title="登陆IP" placeholder="登陆IP" data-joy-props="operator:'like'">
							</td>
							<td>&nbsp;&nbsp;</td>
							<td>
								<select class="form-control" name="loginStateCode" value="${loginStateCode}" title="登陆状态" data-joy-props="operator:'='">
									<option value="">-- 登陆状态 --</option>
									<joy:codeOptions enumClass="com.kvc.joy.plugin.security.login.support.enums.LoginState" showEmptyOption="false"/>
								</select>
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
								<th>帐号</th>
								<th><joy:orderColumn property="loginTime" columnName="登陆时间" defaultOrder="DESC" /></th>
								<th>登出时间</th>
								<th>登陆IP</th>
								<th>登陆状态</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageStore.result}" var="p" varStatus="stauts">
								<tr>
									<td class="joy-table-seq-col">${stauts.index+1}</td>
									<td><joy:listOperations id="${p.id}" showEditOp="false" showDeleteOp="false"/></td>
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

				<joy:pagination />

			</div>
		</div>
	</form>

</body>
</html>
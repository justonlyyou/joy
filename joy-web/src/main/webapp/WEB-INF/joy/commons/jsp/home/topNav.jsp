<%@ page contentType="text/html;charset=UTF-8"%>
<%--<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>--%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页顶部水平导航栏">
<meta name="author" content="Kevice">

<title>主页顶部水平导航栏</title>

</head>

<body>
	<nav class="navbar navbar-default" role="navigation" style="margin-bottom:5px;">
		<div class="navbar-header navbar-brand" style="padding:0 10px 0 10px;">
			<h5 title="${joyFn:getAppProperty('app.name')}">
				<b><font size="5">J</font></b>
<%-- 				<i class="${joyFn:getAppProperty('app.icon.class')}"></i> --%>
    <i class="fa fa-smile-o fa-2x"></i>
				<b><font size="5">Y</font></b>
			</h5>
		</div>
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul id="topMenu" class="nav navbar-nav"></ul>
			<ul class="nav navbar-nav navbar-right" >
				<li class="dropdown">
					<a href="javascript:exit()" title="退出"><i class="fa fa-power-off"></i></a>
				</li>
			</ul>
		</div>
	</nav>

    <script type="text/javascript">
        curl(['joy/commons/home/topNav', 'css!joyCss/commons/home/topNav']);
    </script>

    <script id="topMenuTmpl" type="text/x-jsrender">
        {{for m}}
            <li id="menuItem{{:object.id}}">
                <a href='javascript:toggleMenuItem({{:object.id}});fetchLeftMenus({{:object.id}})'>{{:object.text}}</a>
            </li>
        {{/for}}
    </script>

    <script type="text/javascript">
        function toggleMenuItem(itemId) {
            $("#topMenu li").removeClass("active");
            $("#menuItem"+itemId).addClass("active");
        }

        function fetchLeftMenus(parentId) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "sysMenu/fetchSubMenus",
                async: false,
                data: "parentId="+parentId,
                error: function(request) {
                    alert("发生未预期的错误！");
                },
                success: function(data) {
                    var json = eval(data);
                    var html = $("#accordionTmpl").render({m:json});
                    $("#accordion").html(html);
                }
            });
        }
    </script>

</body>
</html>
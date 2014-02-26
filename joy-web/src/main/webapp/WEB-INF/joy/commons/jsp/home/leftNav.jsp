<%@ page contentType="text/html;charset=UTF-8"%>
<%--<%@ include file="/WEB-INF/joy/commons/include/inc-all.jsp"%>--%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="主页左侧垂直导航栏">
<meta name="author" content="Kevice">

<title>主页左侧垂直导航栏</title>

</head>

<body>
	<div class="panel-group" id="accordion"></div>

    <script type="text/javascript">
        curl(['joy/commons/home/leftNav', 'css!joyCss/commons/home/leftNav']);
        function openPage(url, itemId, text) { //TODO
            $("li[id^='leafItem']").removeClass("active");
            $("#leafItem"+itemId).addClass("active");
            if(url) {
                if(url.indexOf("?") == -1) {
                    url += "?";
                } else {
                    url += "&";
                }
                url += "_joy_key__cur_menu_id=" + itemId;
                url = "${ctx}" + url;
                $("#mainFrame").attr("src", url);
                $("#mainFrame").attr("name", "{menuId:'"+itemId+"',menuText:'"+text+"'}");
            }
        }
    </script>

    <script id="accordionTmpl" type="text/x-jsrender">
        {{for m}}
        <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-toggle="collapse"
                            data-parent="#accordion" href="#collapse{{:object.id}}"> {{:object.text}} </a>
                    </h4>
                </div>
                <div id="collapse{{:object.id}}" class="panel-collapse collapse {{if object.active == 1}}in{{/if}}">
                    <div class="panel-body">
                        <ul class="nav nav-pills nav-stacked">
                            {{for children}}
                                <li id="leafItem{{:object.id}}" class="{{if object.active == 1}}active{{/if}}">
                                    <a href="javascript:openPage('{{:object.url}}',{{:object.id}},'{{:object.text}}')">{{:object.text}}</a>
                                </li>
                            {{/for}}
                        </ul>
                    </div>
                </div>
        </div>
        {{/for}}
    </script>
</body>
</html>
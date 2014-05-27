<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="主页左侧垂直导航栏">
    <meta name="author" content="Kevice">

    <title>主页左侧垂直导航栏</title>
    <script src="${thirdCtx}/jquery/jquery/jquery.js"></script>
    <script src="${thirdCtx}/bootstrap/bootstrap/js/bootstrap.js"></script>
</head>

<body>
    <div class="panel-group" id="accordion"></div>

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
                                        <a href="javascript:leftNav.openPage('{{:object.url}}','{{:object.id}}','{{:object.text}}')">{{:object.text}}</a>
                                    </li>
                                {{/for}}
                            </ul>
                        </div>
                    </div>
            </div>
            {{/for}}
    </script>

    <script type="text/javascript">
        curl(['joy/commons/home/leftNav', 'css!joyCss/commons/home/leftNav'], function(LeftNav) {
            leftNav = new LeftNav();
        });
    </script>
</body>
</html>
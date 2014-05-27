define(['jsrender', 'joy/commons/Comet', 'joy/commons/ClassTool', 'layer', 'css!3rd/jquery/layer/skin/layer'], function (jsrender, Comet, ClassTool, layer) {

    return ClassTool.extend({

        init: function () {
            var _this = this;
            $.ajax({
                cache: true,
                type: "POST",
                url: "sysMenu/fetchRootMenus",
                async: false,
                error: function (request) {
                    alert("发生未预期的错误！");
                },
                success: function (data) {
                    var json = eval(data);
                    var html = $("#topMenuTmpl").render({m: json});
                    $("#topMenu").html(html);
                    $.each(json, function (i, item) {
                        var obj = item.object;
                        if (obj.active) {
                            $("#menuItem" + obj.id).addClass("active");
                            _this.fetchLeftMenus(obj.id);
                            return false;
                        }
                    });
                }
            });

//            var comet = new Comet({
//                url: 'test.comet',
//                accept: function (data) {
//                    console.info(data);
//                }
//            });
//            comet.connection({
//                userId: 'kevice' //TODO
//            });
        },

        toggleMenuItem: function (itemId) {
            $("#topMenu li").removeClass("active");
            $("#menuItem" + itemId).addClass("active");
        },

        fetchLeftMenus: function (parentId) {
            $.ajax({
                cache: true,
                type: "POST",
                url: "sysMenu/fetchSubMenus",
                async: false,
                data: "parentId=" + parentId,
                error: function (request) {
                    alert("发生未预期的错误！");
                },
                success: function (data) {
                    var json = eval(data);
                    var html = $("#accordionTmpl").render({m: json});
                    $("#accordion").html(html);
                }
            });
        },

        exit: function () {
            $.layer({
                shade: [0], //不显示遮罩
                area: ['250px', 'auto'],
                dialog: {
                    msg: '您确定要退出系统？',
                    btns: 2,
                    type: -1,
                    btn: ['确定', '取消'],
                    yes: function () {
                        $.ajax({
                            url: joy.getWebRootPath()+"/logout?_joy_key__logout_method_code=11",
                            error: function (request) {
                                alert("发生未预期的错误！");
                            },
                            success: function (data) {
                                window.location.reload();
                            }
                        });
                    }
                }
            });
        }

    });

});
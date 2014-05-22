curl(['jsrender', 'joy/commons/Comet'], function(jsrender, Comet) {

    $.ajax({
        cache: true,
        type: "POST",
        url: "sysMenu/fetchRootMenus",
        async: false,
        error: function(request) {
            alert("发生未预期的错误！");
        },
        success: function(data) {
            var json = eval(data);
            var html = $("#topMenuTmpl").render({m:json});
            $("#topMenu").html(html);
            $.each(json, function(i, item) {
                var obj = item.object;
                if(obj.active) {
                    $("#menuItem"+obj.id).addClass("active");
                    fetchLeftMenus(obj.id);
                    return false;
                }
            });
        }
    });

    var comet = new Comet({
        url : 'test.comet',
        accept : function(data) {
            console.info(data);
        }
    });
    comet.connection({
        userId : 'kevice' //TODO
    });

    function openPage(url, itemId, text) {
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
	
});
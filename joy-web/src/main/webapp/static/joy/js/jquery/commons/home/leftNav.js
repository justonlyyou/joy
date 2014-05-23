define(['joy/commons/ClassTool'], function(ClassTool) {

    return ClassTool.extend({
        openPage : function(url, itemId, text) {
            $("li[id^='leafItem']").removeClass("active");
            $("#leafItem" + itemId).addClass("active");
            if (url) {
                if (url.indexOf("?") == -1) {
                    url += "?";
                } else {
                    url += "&";
                }
                url += "_joy_key__cur_menu_id=" + itemId;
                url = joy.getWebRootPath() + "/" + url;
                $("#mainFrame").attr("src", url);
                $("#mainFrame").attr("name", "{menuId:'" + itemId + "',menuText:'" + text + "'}");
            }
        }
    });

});
define(['joy/commons/ClassTool'], function (ClassTool) {

    return ClassTool.extend({

        init : function() {

        },

        gotoPage: function (n, s) {
            if (!isNaN(n)) {
                $("#_joy_id__paging_pageNumber").val(n);
            }
            if (!isNaN(s)) {
                $("#_joy_id__paging_pageSize").val(s);
            }
            var action = $("form").attr("action");
            if (action.indexOf("?") == -1) {
                action += "?";
            } else {
                action += "&";
            }
            action += "_joy_key__paging_repaging=false";
            $("form").attr("action", action);
            $("form").submit();
            return false;
        },

        _processPaginationTag: function () {
            var _this = this;
            if ($(".joy-pagination").length != 0) {
                $("#submitBtn").bind("click", function (e) {
                    _this.gotoPage(1);
                });
            }
        },

        _processOrderColumnTag: function () {
            var _this = this;
            if ($('label[id^=_joy_id__order_column_]').length != 0) {
                var $orderHiddens = $('input:hidden[id^=_joy_id__order_value_]');
                $orderHiddens.each(function (i, item) {
                    var $orderHidden = $(item);
                    var property = $orderHidden.attr("id").replace('_joy_id__order_value_', '');
                    var order = $orderHidden.val();
                    if (!order) {
                        order = $("#_joy_id__order_default_" + property).val();
                    }
                    if (order) {
                        order = order.toUpperCase() == "DESC" ? "fa fa-sort-down" : "fa fa-sort-up";
                    }
                    var $orderLbl = $("#_joy_id__order_column_" + property);
                    $orderLbl.html($orderLbl.html() + ('<i class="' + order + '"></i>' || ""));

                    var $th = $orderLbl.parent("th");
                    $th.css("cursor", "hand");

                    $th.hover(function () {
                        $th.removeClass("joy-table-th-sort-th-hout");
                        $th.addClass("joy-table-th-sort-th-hover");
                    }, function () {
                        $th.removeClass("joy-table-th-sort-th-hover");
                        $th.addClass("joy-table-th-sort-th-hout");
                    });

                    $th.click(function () {
                        order = $orderHidden.val();
                        if (order == "DESC") {
                            $orderHidden.val("");
                        } else if (order == "ASC") {
                            $orderHidden.val("DESC");
                        } else {
                            $orderHidden.val("ASC");
                        }
                        _this.gotoPage();
                    });
                });
            }
        },

        currentMenu: function () {
            var win = window.parent;
            var $node;
            while(!($node = $(win.document).find("iframe[id=mainFrame]")).length) {
                win = win.parent;
            }
            var json = $node.attr("name");
            return eval("(" + json + ")");
        },

        currentMenuId: function () {
            return this.currentMenu().menuId;
        },

        currentMenuText: function () {
            return this.currentMenu().menuText;
        }

    });

});
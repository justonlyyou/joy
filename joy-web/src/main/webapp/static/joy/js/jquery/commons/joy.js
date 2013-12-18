$(function() {
	processOperator();
	processPaginationTag();
	processOrderColumnTag();
});

function processOperator() {
	var $form = $("form");
	var inputs = $form.find("input");
	var selects = $form.find("select");
	var inputables = $.merge(inputs, selects);
    $.each(inputables, function(i, item) {
		var propsStr = item.getAttribute("data-joy-props");
		if(propsStr) {
			var attrName = item.getAttribute("name");
			var opParamName = "_joy_key__operator_" + attrName;
			var props = eval("({"+ propsStr + "})");
			var hidden = $form.find("input[name=" + opParamName + "]");
			if(hidden.length == 0) {
				hidden = $("<input type='hidden' name='" + opParamName + "' value='" + props.operator + "'/>");
				$form.append(hidden);
			}
		}
	});
}

function processPaginationTag() {
	if($(".joy-pagination").length != 0) {
		$("#submitBtn").bind("click", function(e) {
			gotoPage(1);
		});	
	}
}

function gotoPage(n,s){
	if(!isNaN(n)) {
		$("#_joy_id__paging_pageNumber").val(n);	
	}
	if(!isNaN(s)) {
		$("#_joy_id__paging_pageSize").val(s);	
	}
	var action = $("form").attr("action");
	if(action.indexOf("?") == -1) {
		action += "?";
	} else {
		action += "&";
	}
	action += "_joy_key__paging_repaging=false";
	$("form").attr("action", action);
	$("form").submit();
	return false;
}

function processOrderColumnTag() {
	if($('label[id^=_joy_id__order_column_]').length != 0) {
		var $orderHiddens = $('input:hidden[id^=_joy_id__order_value_]');
		$orderHiddens.each(function(i, item) {
			var $orderHidden = $(item);
			var property = $orderHidden.attr("id").replace('_joy_id__order_value_', '');
			var order = $orderHidden.val();
			if(!order) {
				order = $("#_joy_id__order_default_"+property).val();
			}
			if(order) {
				order = order.toUpperCase()=="DESC" ? "fa fa-sort-down" : "fa fa-sort-up";
			}
			var $orderLbl = $("#_joy_id__order_column_"+property);
			$orderLbl.html($orderLbl.html() + ('<i class="' + order + '"></i>' || ""));
			
			var $th = $orderLbl.parent("th");
			$th.css("cursor", "hand");
			
			$th.hover(function() {
				$th.removeClass("joy-table-th-sort-th-hout");
				$th.addClass("joy-table-th-sort-th-hover");
			}, function() {
				$th.removeClass("joy-table-th-sort-th-hover");
				$th.addClass("joy-table-th-sort-th-hout");
			});
			
			$th.click(function(){
				order = $orderHidden.val();
				if(order == "DESC") {
					$orderHidden.val("");
				} else if(order == "ASC") {
					$orderHidden.val("DESC");
				} else {
					$orderHidden.val("ASC");
				}
				gotoPage();
			});
		});
	}
}

function currentMenu() {
	var json = $(window.parent.document).find("iframe[id=mainFrame]").attr("name");
	return eval("("+ json + ")");
}

function currentMenuId() {
	return currentMenu().menuId;
}

function currentMenuText() {
	return currentMenu().menuText;
}
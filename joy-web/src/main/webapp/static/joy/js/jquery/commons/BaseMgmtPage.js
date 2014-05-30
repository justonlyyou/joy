define(['joy/commons/BasePage', 'layer'], function(BasePage, layer) {
	
	return BasePage.extend({

        title : null,
        $newBtn : null,
        $form : null,

        init : function(title) {
            this._super();
            if(title) {
                this.title = title;
            } else {
                this.title = this.currentMenuText();
            }
            this.$newBtn = $("#newBtn");
            this.$form = $("form");

            this.onPageLoad();
            this.bindEvent();
        },

        bindEvent : function() {
            var _this = this;
            if(this.$newBtn.length) {
                this.$newBtn.bind("click", function(e) {
                    e.preventDefault();
                    _this.addRecord();
                });
            }
        },

        /**
         * 执行查询
         */
        query : function(event) {

        },

        onPageLoad: function () {
            this._processOperator();
            this._processPaginationTag();
            this._processOrderColumnTag();
        },

        _processOperator: function () {
            var inputs = this.$form.find("input");
            var selects = this.$form.find("select");
            var inputables = $.merge(inputs, selects);
            var _this = this;
            $.each(inputables, function (i, item) {
                var propsStr = item.getAttribute("data-joy-props");
                if (propsStr) {
                    var attrName = item.getAttribute("name");
                    var opParamName = "_joy_key__operator_" + attrName;
                    var props = eval("({" + propsStr + "})");
                    var hidden = _this.$form.find("input[name=" + opParamName + "]");
                    if (hidden.length == 0) {
                        hidden = $("<input type='hidden' name='" + opParamName + "' value='" + props.operator + "'/>");
                        _this.$form.append(hidden);
                    }
                }
            });
        },

        addRecord : function(area, offset) {
            if(!area) {
                area =  ['750px' , '500px'];
            }
            if(!offset) {
                offset = ['30px',''];
            }
            $.layer({
                type : 2,
                title : this.title + "新增",
                iframe : {src : 'add'},
                area : area,
                offset : offset
            });
        },

        editRecord : function(id, area, offset) {
            if(!area) {
                area =  ['750px' , '500px'];
            }
            if(!offset) {
                offset = ['30px',''];
            }
            $.layer({
                type : 2,
                title : this.title + "编辑",
                iframe : {src : 'edit?id=' + id},
                area : area,
                offset : offset
            });
        },

        showDetail : function(id, area, offset) {
            if(!area) {
                area =  ['750px' , '500px'];
            }
            if(!offset) {
                offset = ['30px',''];
            }
            $.layer({
                type : 2,
                title : this.title + "详情",
                iframe : {src : 'get?id=' + id},
                area : area,
                offset : offset
            });
        },

        deleteRecord : function(id) {
            if(confirm("确定要删除该记录？")) {
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: 'delete?id=' + id,
                    async: false,
                    error: function(request, state, msg) {
                        if(state == 'error') {
                            alert(msg);
                        } else {
                            alert("删除失败！");
                        }
                    },
                    success: function(data) {
                        if (data) {
                            alert(data);
                        } else {
                            alert("删除成功！");
                            this.$form.submit();
                        }
                    }
                });
            }
        }

	});

});
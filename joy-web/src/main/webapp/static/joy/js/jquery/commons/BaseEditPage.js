define(['joy/commons/BasePage'], function(BasePage) {

    return BasePage.extend({

        recordId : null,
        $saveBtn : null,
        $delBtn : null,
        $editForm : null,

        init : function() {
            this.recordId = joy.getUrlParam("id");

            this.$saveBtn = $('#saveBtn');
            this.$delBtn = $('#delBtn');
            this.$editForm = $('#editForm');
            this.$delBtn.length && !this.recordId && this.$delBtn.attr("disabled", true);

            this.bindEvent();
        },

        bindEvent : function() {
            var _this = this;
            if(this.$saveBtn.length) {
                this.$saveBtn.bind("click", function(e) {
                    e.preventDefault();
                    _this.persist();
                });
            }
            if(this.$delBtn.length) {
                this.$delBtn.bind("click", function(e) {
                    e.preventDefault();
                    _this.delete();
                });
            }
        },

        persist : function() {
            if(this.recordId) {
                var hidden = $("<input type='hidden' name='id' value='" + this.recordId + "'/>");
                this.$editForm.append(hidden);
            }
            $.ajax({
                type: "POST",
                url: "persist",
                data: this.$editForm.serialize(),
                async: false,
                error: function(request) {
                    alert("连接不上服务器或服务器内部错误！");
                },
                success: function(data) {
                    alert("保存成功！");
                }
            });
        },

        delete : function(id) {
            if(confirm("确定要删除该记录？")) {
                $.ajax({
                    cache: true,
                    type: "POST",
                    url: 'delete?id=' + id,
                    async: false,
                    error: function(request) {
                        alert("删除失败！");
                    },
                    success: function(data) {
                        alert("删除成功！");
                    }
                });
            }
        }

    });

});
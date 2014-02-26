curl(['joy/commons/BaseManagerPage'], function(BaseManagerPage) {

    var SysDataSrcListPage = BaseManagerPage.extend({

        init : function() {
            var _this = this;
            $("#newBtn").bind("click", function(e) {
                e.preventDefault();
                _this.addRecord();
            });
        },

        addRecord : function() {
            $.layer({
                type : 2,
                title : '系统数据源新增',
                iframe : {src : getWebRootPath()+'/sysDataSrc/add'},
                area : ['750px' , '500px'],
                offset : ['50px','']
            });
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '系统数据源详情',
                iframe : {src : '${ctx}/sysDataSrc/get?id='+id},
                area : ['750px' , '500px'],
                offset : ['50px','']
            });
        },

        editRecord : function(id) {

        }

    });

    new SysDataSrcListPage();

});
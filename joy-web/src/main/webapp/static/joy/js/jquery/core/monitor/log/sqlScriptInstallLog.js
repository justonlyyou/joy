curl(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    var mgmtPage = BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '脚本安装日志详情',
                iframe : {src : joy.getWebRootPath()+'/sqlScriptInstallLog/get?id='+id},
                area : ['750px' , '466px'],
                offset : ['30px','']
            });
        },

        editRecord : function(id) {
            alert("//TODO")
        },

        deleteRecord : function(id) {
            alert("//TODO")
        }

    });

    joy.mgmtPage = new mgmtPage();

});
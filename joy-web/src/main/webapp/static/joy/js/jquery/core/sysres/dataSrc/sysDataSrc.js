curl(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    var mgmtPage = BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        addRecord : function() {
            $.layer({
                type : 2,
                title : '系统数据源新增',
                iframe : {src : joy.getWebRootPath()+'/sysDataSrc/add'},
                area : ['750px' , '500px'],
                offset : ['50px','']
            });
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '系统数据源详情',
                iframe : {src : joy.getWebRootPath()+'/sysDataSrc/get?id='+id},
                area : ['750px' , '500px'],
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
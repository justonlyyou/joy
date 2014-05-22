curl(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    var mgmtPage = BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '系统代码详情',
                iframe : {src : joy.getWebRootPath()+'/sysCodeTable/get?id='+id},
                area : ['850px' , '500px'],
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
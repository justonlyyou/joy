define(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    return BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '关系数据库对象详情',
                iframe : {src : joy.getWebRootPath()+'/mdRdbObj/get?id='+id},
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

});
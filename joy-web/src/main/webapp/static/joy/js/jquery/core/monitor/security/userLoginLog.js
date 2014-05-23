define(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    return BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : 'SQL脚本安装日志详情',
                iframe : {src : joy.getWebRootPath()+'/userLoginLog/get?id='+id},
                area : ['750px' , '466px'],
                offset : ['30px','']
            });
        }

    });

});
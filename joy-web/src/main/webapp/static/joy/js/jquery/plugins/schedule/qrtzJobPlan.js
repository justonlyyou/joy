curl(['joy/commons/BaseMgmtPage'], function(BaseMgmtPage) {

    var mgmtPage = BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '任务调度计划详情',
                iframe : {src : joy.getWebRootPath()+'/qrtzJobPlan/get?id='+id},
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
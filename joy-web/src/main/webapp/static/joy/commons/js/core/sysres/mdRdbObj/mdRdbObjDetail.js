define(['joy/base/BaseMgmtPage'], function(BaseMgmtPage) {

    return BaseMgmtPage.extend({

        init : function() {
            this._super();
        },

        showDetail : function(id) {
            $.layer({
                type : 2,
                title : '关系数据库对象字段详情',
                iframe : {src : 'get?id='+id},
                area : ['650px' , '400px'],
                offset : ['30px','']
            });
        }

    });

});
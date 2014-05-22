//define(['joy/commons/ClassTool' ], function(ClassTool) {
//    return
joy = {
        mgmtPage : null, //TODO 临时方案
        editPage : null, //TODO 临时方案
        detaiPage : null, //TODO 临时方案

        getWebCtx: function () {
            //TODO
        },

        getWebRootPath: function () {
            var webroot = document.location.href;
            webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
            webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
            webroot = webroot.substring(0, webroot.indexOf('/'));
            var rootpath = "/" + webroot;
            return rootpath;
        },

        /**
         * 创建类
         * @param props
         * @returns {*|void}
         */
    //TODO
//		createClass : function(props) {
//			return ClassTool.extend(props);
//		},

        /**
         * 生成32位UUID
         * @returns {string}
         */
        genUuid: function () {
            return 'xxxxxxxxxxxx4xxxyxxxxxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
                return v.toString(16);
            });
        }

    }
    ;

//});
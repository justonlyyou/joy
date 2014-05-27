joy = {

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

        getUrlParam : function(url) {
            var reg = new RegExp("(^|&)" + url + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        },

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

    };

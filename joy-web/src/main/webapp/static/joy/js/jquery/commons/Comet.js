/**
 * 原版的实现者为auto-comet的作者XiaohangHu
 */
define(['joy/commons/ClassTool'], function(ClassTool) {

    return ClassTool.extend({

        /** 请求参数名：同步 */
        SYNCHRONIZE_KEY : "_S_COMET",
        /** 同步值：创建连接 */
        CONNECTION_VALUE : "C",
        /** 同步值：断开连接 */
        DISCONNECT_VALUE : "D",
        /** 返回参数名：连接ID */
        CONNECTIONID_KEY : "_C_COMET",

        url : null,
        cid : null,
        accept : function() {},
        async : true,
        /** 是否在连接状态 */
        isConnect : false,

        /**
         * 构造器
         * @param props 参数对象
         */
        init : function(props) {
            this.url = props.url;
            (props.async != undefined) && (this.async = props.async);
            (props.accept != undefined) &&  (this.accept = props.accept);
        },

        /**
         * 开始链接
         *
         * @param userParam 连接时传递给服务器端的参数
         * @param success 连接成功处理方法
         * @param failure 连接失败处理方法
         * @param caller 调用者
         */
        connection : function(userParam, success, failure, caller) {
            if (!userParam) {
                userParam = {};
            }
            if(userParam._joy_channel_id == undefined) {
                userParam['_joy_channel_id'] = joy.genUuid();
            }
            userParam[this.SYNCHRONIZE_KEY] = this.CONNECTION_VALUE;
            var _this = this;
            $.ajax({
                type : 'POST',
                url : _this.url,
                data : userParam,
                success : function(result) {
                    var data = eval("(" + result + ")");
                    var cid = data[_this.CONNECTIONID_KEY];
                    caller = caller ? caller : null;
                    if (null == cid) { // 拒接连接
                        if (failure) {
                            failure.call(caller);
                        }
                    } else { // 连接成功
                        if (success) {
                            success.call(caller);
                        }
                        _this.cid = cid;
                        _this.isConnect = true, _this.polling(cid);
                    }
                }
            });
        },

        /** 轮询 */
        polling : function(cid) {
            if (!this.isConnect) {
                return;
            }
            var param = {};
            param[this.CONNECTIONID_KEY] = cid;
            var _this = this;
            $.ajax({
                type : 'GET',
                url : _this.url,
                data : param,
                success : function(result) {
                    var datas = eval("(" + result + ")");
                    _this.acceptDatas(datas);
                }
            });
        },

        acceptDatas : function(datas) {
            // 接受的最后一个消息
            var lastData = datas[datas.length - 1];
            // 如果是断开连接
            var disconnect = this.isDisconnectObj(lastData);
            var len = datas.length;
            if (disconnect) {
                len--;
            }
            if (!disconnect) {// 如果不是断开连接，继续轮询
                if (this.async) {// 如果是异步处理
                    this.polling(this.cid);
                    this.acceptDatasByLength(datas, len);
                } else {
                    this.acceptDatasByLength(datas, len);
                    this.polling(this.cid);
                }
            } else {
                this.acceptDatasByLength(datas, len);
            }
        },

        isDisconnectObj : function(o) {
            return o[this.SYNCHRONIZE_KEY] == this.DISCONNECT_VALUE;
        },

        /**
         * 处理数组中指定长度的数据
         */
        acceptDatasByLength : function(datas, len, disconnect) {
            for ( var i = 0; i < len; i++) {
                var data = datas[i];
                this.accept(data);
            }
        },

        /** 断开连接 */
        disconnect : function(userParam, callback, caller) {
            var param = userParam ? userParam : {};
            param[this.SYNCHRONIZE_KEY] = this.DISCONNECT_VALUE;
            param[this.CONNECTIONID_KEY] = this.cid;
            this.isConnect = false;
            var _this = this;
            $.ajax({
                type : 'GET',
                url : _this.url,
                data : param,
                success : function(result) {
                    caller = caller ? caller : null;
                    if (callback) {
                        callback.call(caller);
                    }
                }
            });
        }

	});

});
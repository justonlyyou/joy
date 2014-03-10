if (typeof Auto == "undefined")
	Auto = {};
Auto.NAME = "Auto";
Auto.emptyFunciton = function() {
};
/**
 * print..
 */
Auto.toString = function(obj) {
	var str = "";
	for ( var o in obj) {
		str += o + "[" + typeof obj[o] + "]:" + obj[o] + "\r\n";
	}
	return str;
};
/**
 * 贪婪的拷贝
 * 
 * @param source目标
 * @param target来源
 */
Auto.copy = function(source, target) {
	for ( var f in target) {
		source[f] = target[f];
	}
};
/**
 * 仅拷贝某种类型
 * 
 * @param source目标
 * @param target来源
 * @param type[string]("string","function",...)类型
 * 
 */
Auto.copyByType = function(source, target, type) {
	for ( var f in target) {
		var fo = target[f];
		if (typeof fo == type)
			source[f] = fo;
	}
};
/**
 * 
 * 迭代深度拷贝，不迭代数组类型。
 * 
 */
Auto.deepCopy = function(source, target) {
	for ( var f in target) {
		var fo = target[f];
		if (typeof fo == "object" && fo != null && !(fo instanceof Array)) {
			if (source[f] == undefined)
				source[f] = {};
			this.deepCopy(source[f], fo);
		} else {
			source[f] = fo;
		}
	}
};
/**
 * 知足的拷贝
 * 
 * @param source目标
 * @param target来源
 */
Auto.contentCopy = function(source, target) {
	for ( var f in target) {
		if (source[f] !== undefined) {
			source[f] = target[f];
		}
	}
};
/**
 * @method Auto.newClass
 * @see http://hi.baidu.com/huxiaohang/blog/item/2962a8c2254718110ff4773e.html
 * 
 * @param o.extend[class]父类
 * @param o.public[object]共有属性，方法
 * @param o.constructor[function]构造器
 * @param o.static[object]类属性，方法
 */
Auto.newClass = function(classAccessory) {

	var newClass = function() {
		this.construct.apply(this, arguments);
	};

	var construct = classAccessory.constructor;
	if (!construct) {
		construct = function() {
		};
	}

	var parent = classAccessory.extend;
	if (parent) {

		// 继承类方法
		Auto.copyByType(newClass, parent, "function");

		var c = function() {
		};
		c.prototype = parent.prototype;
		newClass.prototype = new c();

		var constructPar = parent.prototype.construct;
		if (constructPar) {
			newClass.prototype.construct = function() {
				constructPar.apply(this, arguments);
				construct.apply(this, arguments);
			};
		}

	}

	if (!newClass.prototype.construct) {
		newClass.prototype.construct = function() {
			construct.apply(this, arguments);
		};
	}

	Auto.copy(newClass, classAccessory.static);

	Auto.deepCopy(newClass.prototype, classAccessory.public);
	classAccessory = null;
	return newClass;
};

Auto.Object = Auto.newClass({
	public : {
		userData : null,
		toString : function() {
			return Auto.toString(this);
		},
		print : function() {
			alert(this.toString());
		}
	},
	constructor : function() {
		var o = arguments[arguments.length - 1];
		Auto.contentCopy(this, o);
	},
	static : {
		$ : function(o) {
			if (o instanceof this)
				return o;
			else
				return new this(o);
		}
	}
});

Auto.Observable = Auto.newClass({
	extend : Auto.Object,
	public : {
		customListeners : null,
		addCustomListener : function(eventType, fun, caller) {
			if (!fun)
				return false;
			var ls = this.customListeners[eventType + "s"];
			if (!ls) {
				ls = [];
				this.customListeners[eventType + "s"] = ls;
			}
			fun.c = caller || this;
			ls.push(fun);
			return true;
		},
		removeCustomListener : function(eventType, fun) {
			var ls = this.customListeners[eventType + "s"];
			if (ls) {
				ls.remove(fun);
				return true;
			}
			return false;
		},
		doCustomEvent : function(eventType) {
			var ls = this.customListeners[eventType + "s"];
			if (ls) {
				var event = {
					type : eventType
				};
				var rs = [];
				for ( var i = 0, len = ls.length; i < len; i++) {
					var fun = ls[i];
					var r = fun.call(fun.c, event);
					rs.push(r);
				}
				return rs;
			}
		}
	},
	constructor : function() {
		this.customListeners = {};
	}
});
/** Ajax ******************************************************************** */
Auto.AjaxUtils = {

	/**
	 * @param param[object]
	 *            值为null的属性不会拼接
	 */
	parseParam : function(o) {
		var paramStr = "";
		for ( var p in o) {
			var value = o[p];
			if (value) {
				value = value.replace(/\+/g, "%2B");
				value = value.replace(/\&/g, "%26");
				paramStr += p + "=" + value + "&";
			}
		}
		return (paramStr == "") ? null : paramStr;
	},
	isSuccess : function(status) {
		try {
			return !status && location.protocol == "file:"
					|| (status >= 200 && status < 300) || status == 304
					|| status == 1223;
		} catch (e) {
		}
		return false;
	},
	createRequest : function() {
		try {
			return new XMLHttpRequest();
		} catch (trymicrosoft) {
			try {
				return new ActiveXObject("Msxm12.XMLHTTP");
			} catch (othermicrosoft) {
				try {
					return new ActiveXObject("Microsoft.XMLHTTP");
				} catch (failed) {
					throw new Error("Can't create XMLHttpRequest!");
				}
			}
		}
	},
	/**
	 * @param o.url[string]
	 * @param o.method[string]("GET","get",other)
	 * @param o.paramStr[string]
	 * @param o.caller[object]
	 * @param o.callback[function]
	 * @param o.header[object]
	 * @param o.async[boolean]异步请求？默认true
	 */
	sendRequest : function(o) {
		if (o.async == null) {
			o.async = true;
		}
		switch (o.method) {
		case "get":
		case "GET":
			if (o.paramStr) {
				o.url += "?" + o.paramStr;
				o.paramStr = null;
			}
			break;
		default:
			o.method = "POST";
		}
		/**
		 * 单例的XMLHttpRequest对象，会有“请求覆盖”的问题，所以这里每次都创建新的对象。
		 */
		var req = this.createRequest();
		if (o.async != false)
			req.onreadystatechange = back;
		req.open(o.method, o.url, o.async);
		o.header = o.header || {
			"Content-Type" : "application/x-www-form-urlencoded;",
			"X-Requested-With" : "XMLHttpRequest",
			"If-Modified-Since" : "Thu, 01 Jan 1970 00:00:00 GMT"
		};

		for ( var i in o.header) {
			req.setRequestHeader(i, o.header[i]);
		}
		req.send(o.paramStr);
		function back() {
			if (o.callback)
				o.callback.call(o.caller, req);
		}
		if (o.async == false)
			back();
	},
	/**
	 * 无参数时自动调用GET方式
	 * 
	 * @method Un.AjaxUtils.request
	 * @param o.url[string]
	 * @param o.method[string]("GET","get","POST","post"..)
	 * @param o.param[object]
	 * @param o.caller[object]
	 * @param o.success[function]
	 * @param o.failure[function]
	 * @param o.header[object]
	 * @param o.async[boolean]异步请求？默认true
	 */
	request : function(o) {
		o.paramStr = this.parseParam(o.param);

		if (!o.method)
			o.method = o.paramStr ? "POST" : "GET";

		function back(req) {
			if (req.readyState == 4) {
				var s = req.status;
				if (Un.AjaxUtils.isSuccess(s)) {
					if (o.success)
						o.success.call(this, req.responseText);
				} else {
					if (o.failure) {
						o.failure.call(this, req.responseText, s);
					} else {
						throw new Error("ajax http status [ " + s + " : "
								+ req.statusText + " ].\r\n" + "url [ " + o.url
								+ " ] " + req.responseText);
					}
				}
			}
		}

		o.callback = back;
		this.sendRequest(o);
	}

};
/** ********************************************************************** Ajax */
/**
 * @param url[String]
 *            连接地址
 * @param accept[Function]
 *            接受数据处理方法
 * @param o.async[boolean]异步接受数据？默认false
 */
Auto.Comet = {
	extend : Auto.Observable,
	static : {
		/** 请求参数名：同步 */
		SYNCHRONIZE_KEY : "_S_COMET",
		/** 同步值：创建连接 */
		CONNECTION_VALUE : "C",
		/** 同步值：断开连接 */
		DISCONNECT_VALUE : "D",
		/** 返回参数名：连接ID */
		CONNECTIONID_KEY : "_C_COMET"
	},
	public : {
		url : null,
		cid : null,
		accept : Auto.emptyFunciton,
		async : false,
		/** 是否在连接状态 */
		isConnect : false,
		/**
		 * 开始链接
		 * 
		 * @param userParam
		 *            连接时传递给服务器端的参数
		 * @param success
		 *            连接成功处理方法
		 * @param failure
		 *            连接失败处理方法
		 * 
		 */
		connection : function(userParam, success, failure, caller) {
			if (!userParam) {
				userParam = {};
			}
			userParam[Auto.Comet.SYNCHRONIZE_KEY] = Auto.Comet.CONNECTION_VALUE;
			var req = {
				url : this.url,
				param : userParam,
				caller : this,
				success : function(result) {
					var data = eval("(" + result + ")");
					cid = data[Auto.Comet.CONNECTIONID_KEY];
					caller = caller ? caller : null;
					if (null == cid) {// 拒接连接
						if (failure) {
							failure.call(caller);
						}
					} else {// 连接成功
						if (success) {
							success.call(caller);
						}
						this.cid = cid;
						this.isConnect = true, this.polling(cid);
					}
				}
			};
			Un.AjaxUtils.request(req);
		},
		isDisconnectObj : function(o) {
			return o[Auto.Comet.SYNCHRONIZE_KEY] == Auto.Comet.DISCONNECT_VALUE;
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
					this.polling(cid);
					this.acceptDatasByLength(datas, len);
				} else {
					this.acceptDatasByLength(datas, len);
					this.polling(cid);
				}
			} else {
				this.acceptDatasByLength(datas, len);
			}
		},
		/** 轮询 */
		polling : function(cid) {
			if (!this.isConnect) {
				return;
			}
			var param = {};
			param[Auto.Comet.CONNECTIONID_KEY] = cid;
			var req = {
				url : this.url,
				method : "GET",
				param : param,
				caller : this,
				success : function(result) {
					var datas = eval("(" + result + ")");
					this.acceptDatas(datas);
				}
			};
			Un.AjaxUtils.request(req);
		},
		/** 断开连接 */
		disconnect : function(userParam, callback, caller) {
			var param = userParam ? userParam : {};
			param[Auto.Comet.SYNCHRONIZE_KEY] = Auto.Comet.DISCONNECT_VALUE;
			param[Auto.Comet.CONNECTIONID_KEY] = this.cid;
			var req = {
				url : this.url,
				method : "GET",
				param : param,
				caller : this,
				success : function(result) {
					caller = caller ? caller : null;
					if (callback) {
						callback.call(caller);
					}
				}
			};
			this.isConnect = false;
			Un.AjaxUtils.request(req);
		}
	},
	constructor : function() {
	}
};
Auto.Comet = Auto.newClass(Auto.Comet);
/**
 * <p>
 * Description: Un in Thunisoft
 * </p>
 * <p>
 * Email: <a href="mailto:huhang1986@126.com">huhang1986@126.com</a>
 * </p>
 *
 * @author huxh
 * @date 2010-1
 */

/**
 * Un
 */
if (typeof Un == "undefined")
	Un = {};
Un.NAME = "un";

Un.BLANK_IMAGE_URL = Un.ROOT + "style/blank.gif";

/** *浏览器的类型及版本 */
Un.AGENT = {
	userAgent : navigator.userAgent.toLowerCase(),
	getAgent : function() {
		this.VERSION = (this.userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [
				0, "0" ])[1];
		if (window.ActiveXObject) {
			this.NAME = "ie";
		} else if (this.userAgent.indexOf("firefox") >= 1) {
			this.NAME = "firefox";
		} else if (window.MessageEvent && !document.getBoxObjectFor) {
			this.NAME = "chrome";
		} else if (/opera/.test(C)) {
			this.NAME = "opera";
		} else if (/webkit/.test(C)) {
			this.NAME = "safari";
		}
	},
	is : function(n, v) {
		if (this.NAME == n) {
			if (v && this.VERSION != v)
				return false;
			return true;
		}
		return false;
	}
};
Un.AGENT.getAgent();
if (Un.AGENT.is("ie", "6.0")) {
	document.execCommand("BackgroundImageCache", false, true);
}

/**
 * 贪婪的拷贝
 * 
 * @param source目标
 * @param target来源
 */
Un.copy = function(source, target) {
	for ( var f in target) {
		source[f] = target[f];
	}
};
/**
 * 知足的拷贝
 * 
 * @param source目标
 * @param target来源
 */
Un.contentCopy = function(source, target) {
	for ( var f in target) {
		if (source[f] !== undefined) {
			source[f] = target[f];
		}
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
Un.copyByType = function(source, target, type) {
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
Un.deepCopy = function(source, target) {
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
 * 获得id
 */
Un.getId = function() {
	var id = 1000;
	return function() {
		id++;
		return Un.NAME + id;
	};
}();

/**
 * document.getElementById.. fireFox不支持这种写法：Un.getById =document.getElementById;
 */
Un.getById = function(id) {
	return document.getElementById(id);
};
/**
 * print..
 */
Un.toString = function(obj) {
	var str = "";
	for ( var o in obj) {
		str += o + "[" + typeof obj[o] + "]:" + obj[o] + "\r\n";
	}
	return str;
};
Un.print = function(obj) {
	alert(Un.toString(obj));
};
Un.emptyFunciton = function() {
};

/** Util ******************************************************************** */
Un.Util = {};
/**
 * 只有当数组中有一个或者一个以上的值为false时，返回false。
 */
Un.Util.getBoolean = function(array) {
	if (array) {
		for ( var i = 0, len = array.length; i < len; i++) {
			if (array[i] == false)
				return false;
		}
	}
	return true;
};

Un.StringUtils = {
	trim : function(str) {
		if (null == str)
			return null;
		return str.replace(/^\s+|\s+$/g, "");
	},
	startsWith : function(str1, str2) {
		if (str1.substr(0, str2.length) == str2)
			return true;
		return false;
	},
	isBlank : function(str) {
		if (typeof str == "undefined" || null == str)
			return true;
		var s = this.trim(str);
		if ("" == s)
			return true;
		return false;
	},
	isNotBlank : function(str) {
		return !StringUtils.isBlank(str);
	}
};

Un.ArrayUtils = {
	indexOf : function(arr, o) {
		for ( var i = 0, len = arr.length; i < len; i++) {
			if (arr[i] == o)
				return i;
		}
		return -1;
	},
	remove : function(arr, o) {
		var i = ArrayUtils.indexOf(arr, o);
		if (i >= 0) {
			arr.splice(i, 1);
			return true;
		}
		return false;
	}
};

Un.WindowUtils = {
	/** 判断一个窗口是否有父窗口 */
	hasParent : function(win) {
		return win.parent == win;
	},
	/** 获取一个窗口的父窗口，顶层窗口没有父窗口 */
	getParent : function(win) {
		if (this.hasParent(win)) {
			return win.parent;
		}
		return null;
	},
	/** 获取一个窗口的Opener */
	getOpener : function(win) {
		return win.opener;
	},
	/** 迭代所有父窗口 */
	iteratorParent : function(win, fun, cal) {
		var p = this.getParent(win);
		if (p) {
			fun.call(cal, p);
			this.iteratorParent(p);
		}
	},
	/** 迭代所有opener */
	iteratorOpener : function(win, fun, cal) {
		var op = this.getOpener(win);
		if (op) {
			fun.call(cal, op);
			this.iteratorOpener(op);
		}
	}
};
/** ******************************************************************** Util */
/**
 * @method Un.newClass
 * @see http://hi.baidu.com/huxiaohang/blog/item/2962a8c2254718110ff4773e.html
 * 
 * @param o.extend[class]父类
 * @param o.public[object]共有属性，方法
 * @param o.constructor[function]构造器
 * @param o.static[object]类属性，方法
 */
Un.newClass = function(classAccessory) {

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
		Un.copyByType(newClass, parent, "function");

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

	Un.copy(newClass, classAccessory.static);

	Un.deepCopy(newClass.prototype, classAccessory.public);
	classAccessory = null;
	return newClass;
};

Un.Object = Un.newClass({
	public : {
		userData : null,
		toString : function() {
			return Un.toString(this);
		},
		print : function() {
			alert(this.toString());
		}
	},
	constructor : function() {
		var o = arguments[arguments.length - 1];
		Un.contentCopy(this, o);
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

Un.Observable = Un.newClass({
	extend : Un.Object,
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

/** Element *************************************************************** */
/**
 * @param o.e[HTMLElement]
 * @param o.tagName[string]("div","span"...)
 * @param o.$[object]
 * @param o.style[object]
 * @param o.myData[?]
 * @method setStyle
 * @method hasClass
 * @method addClass
 * @method removeClass
 * @method addEventListener
 * @method removeEventListener
 * @method addListener
 * @method removeListener
 * @method appendChild
 * @method appendChildren
 * @method appendInto
 * @method insertBefore
 * @method insertAfter
 * @method destroy
 * @method show
 * @method hide
 * @method setInnerHtml
 */
Un.Element = {
	extend : Un.Observable,
	public : {
		e : null,
		tagName : "div",
		className : "",
		$ : null,
		getId : function() {
			return this.e.id;
		},
		setId : function(id) {
			this.e.id = id;
		},
		setStyle : function(s, v) {
			this.e.style[s] = v;
		},
		getStyle : function(s) {
			return this.e.style[s];
		},
		setAttribute : function(p, v) {
			this.e[p] = v;
		},
		getAttribute : function(p) {
			return this.e[p];
		},
		hasClass : function(cls) {
			var reg = new RegExp("(^|\\s)" + cls + "(\\s|$)");
			return reg.test(this.e.className);
		},
		addClass : function(cls) {
			if (!this.hasClass(cls))
				this.e.className += (this.e.className ? " " : "") + cls;
		},
		removeClass : function(cls) {
			if (this.hasClass(cls)) {
				var repReg = new RegExp("(\\s+|^)" + cls + "(\\s+|$)");
				cls = this.e.className.replace(repReg, " ");
				cls = Un.StringUtils.trim(cls);
				this.e.className = cls;
			}
		},
		addEventListener : function(eventType, fun, caller, bub, cap) {
			caller = caller || this;
			fun.proxy = Un.Element.getListenerProxy(fun, caller, bub);
			if (this.e.attachEvent) {
				this.e.attachEvent("on" + eventType, fun.proxy);
			} else if (this.e.addEventListener) {
				cap = cap || false;
				this.e.addEventListener(eventType, fun.proxy, cap);
			}
		},
		removeEventListener : function(eventType, fun, cap) {
			try {
				if (this.e.detachEvent) {
					this.e.detachEvent("on" + eventType, fun.proxy);
				} else if (this.e.removeEventListener) {
					cap = cap || false;
					this.e.removeEventListener(eventType, fun.proxy, cap);
				}
			} catch (e) {
			}
		},
		addListener : function(eventType, fun, caller, bub, cap) {
			this.addEventListener(eventType, fun, caller, bub, cap);
		},
		removeListener : function(eventType, fun, cap) {
			this.removeEventListener(eventType, fun, cap);
		},
		appendChild : function(ele) {
			this.e.appendChild(Un.Element.getHtmlElement(ele));
		},
		removeChild : function(ele) {
			this.e.removeChild(Un.Element.getHtmlElement(ele));
		},
		removeAllChild : function() {
			while (this.e.childNodes.length !== 0) {
				this.e.removeChild(this.e.childNodes[0]);
			}
		},
		getChildren : function() {
			return this.e.childNodes;
		},
		appendChildren : function(eles) {
			if (eles)
				for ( var i = 0, le = eles.length; i < le; i++) {
					this.appendChild(eles[i]);
				}
		},
		appendInto : function(ele) {
			ele.appendChild(this.e);
		},
		insertBefore : function(ele) {
			Un.Element.insertBefore(ele, this);
		},
		insertAfter : function(ele) {
			Un.Element.insertAfter(ele, this);
		},
		destroy : function() {
			this.e.parentNode.removeChild(this.e);
			for ( var i in this) {
				this[i] = null;
			}
		},
		isShow : function() {
			var d = this.getStyle("display");
			return d != "none";
		},
		show : function() {
			this.setStyle("display", "");
		},
		hide : function() {
			this.setStyle("display", "none");
		},
		setInnerHtml : function(text) {
			if (typeof text != "object")
				this.e.innerHTML = text;
			else {
				this.appendChild(text);
			}
		},
		getInnerHtml : function() {
			return this.e.innerHTML;
		},
		margin : function(t, r, b, l) {
			var str = "";
			var index = (arguments.length > 3) ? 4 : arguments.length;
			for ( var i = 0; i < index; i++) {
				var v = arguments[i];
				if (typeof v == "number") {
					if (v != 0)
						str += v + "px ";
					else
						str += v + " ";
				} else {
					str += "auto ";
				}
			}
			this.setStyle("margin", str.substring(0, str.length - 1));
		},
		/**
		 * 设置透明度
		 * 
		 * @param f(0-100)
		 */
		setOpacity : function(f) {
			this.e.style.filter = "alpha(opacity=" + f + ")";
		},
		setWidth : function(w) {
			this.setStyle("width", parseInt(w) + "px");
		},
		setHeight : function(h) {
			this.setStyle("height", parseInt(h) + "px");
		},
		getX : function() {
			return this.e.offsetLeft;
		},
		getY : function() {
			return this.e.offsetTop;
		},
		getOffsetWidth : function() {
			return this.e.offsetWidth;
		},
		getOffsetHeight : function() {
			return this.e.offsetHeight;
		},
		getClientWidth : function() {
			return this.e.clientWidth;
		},
		getClientHeight : function() {
			return this.e.clientHeight;
		},
		getScrollWidth : function() {
			return Math.max(this.e.scrollWidth, this.e.clientHeight);
		},
		getScrollHeight : function() {
			return Math.max(this.e.scrollHeight, this.e.clientHeight);
		},
		setScrollTop : function(s) {
			this.e.scrollTop = s;
		},
		getScrollTop : function() {
			return this.e.scrollTop;
		},
		getScrollLeft : function() {
			return this.e.scrollLeft;
		},
		slideUp : function(t, c) {
			this.setStyle("overflow", "hidden");
			var h = this.getClientHeight();
			this.slideUp.initHeight = h;
			var a = new Un.Animate({
				setter : this.setHeight,
				caller : this,
				formula : Un.Animate.getFormula(h, 0, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setStyle("overflow", "");
					this.hide();
					if (c)
						c.call(this);
				}
			});
			a.start();
		},
		slideDown : function(t, c) {
			this.show();
			this.setStyle("overflow", "hidden");
			var h = this.slideUp.initHeight || this.getClientHeight();
			var a = new Un.Animate({
				setter : this.setHeight,
				caller : this,
				formula : Un.Animate.getFormula(0, h, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setStyle("height", "");
					this.setStyle("overflow", "");
					if (c)
						c.call(this);
				}
			});
			a.start();
		},
		/**
		 * 淡出,隐
		 */
		fadeOut : function(t, c) {
			var a = new Un.Animate({
				setter : this.setOpacity,
				caller : this,
				formula : Un.Animate.getFormula(100, 0, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setOpacity(0);
					if (c)
						c.call(this);
				}
			});
			a.start();
		},
		/**
		 * 淡入,显
		 */
		fadeIn : function(t, c) {
			var a = new Un.Animate({
				setter : this.setOpacity,
				caller : this,
				formula : Un.Animate.getFormula(0, 100, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setOpacity(100);
					if (c)
						c.call(this);
				}
			});
			a.start();
		},
		hideAnimate : function(t) {
			var t = 1000;
			this.slideUp(t);
			this.fadeOut(t);

			this.show();
			this.setStyle("overflow", "hidden");
			var w = this.getClientWidth();
			this.hideAnimate.initWidth = w;
			var a = new Un.Animate({
				setter : this.setWidth,
				caller : this,
				formula : Un.Animate.getFormula(w, 0, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setStyle("overflow", "");
					this.hide();
					// if (c)
					// c.call(this);
				}
			});
			a.start();
		},
		showAnimate : function(t) {
			var t = 1000;
			this.show();
			this.slideDown(t);
			this.fadeIn(t);

			this.show();
			this.setStyle("overflow", "hidden");
			var w = this.hideAnimate.initWidth || this.getClientWidth();
			var a = new Un.Animate({
				setter : this.setWidth,
				caller : this,
				formula : Un.Animate.getFormula(0, w, t,
						Un.Animate.easing.swing),
				callback : function() {
					this.setStyle("width", "");
					this.setStyle("overflow", "");
					// if (c)
					// c.call(this);
				}
			});
			a.start();
		},
		equals : function(e) {
			return (e.e && e.e == this.e);
		}
	},
	constructor : function(tagName, className, text_o) {
		if (typeof tagName == "string") {
			this.tagName = tagName;
			if (className)
				this.className = className;
		}
		if (!this.e) {
			this.e = document.createElement(this.tagName);
			this.e.className = this.className;
			Un.copy(this.e, this.$);
		}
		if (text_o) {
			var node = document.createTextNode(text_o);
			this.e.appendChild(node);
		}
		delete this.tagName;
	},
	static : {
		get : function(p) {
			switch (typeof p) {
			case "string":
				var p = Un.getById(p);
				if (!p)
					return p;
			case "object":
				if (p instanceof this)
					return p;
				else {
					p = Un.Element.getHtmlElement(p);
					if (p == null)
						return null;
					return new this({
						e : p
					});
				}
			}
		},
		getHtmlElement : function(ele) {
			if (ele instanceof Un.Element) {
				return ele.e;
			} else {
				return ele;
			}
		},
		getListenerProxy : function(fun, caller, bub) {
			return function() {
				if (window.event) {
					var event = window.event;
					if (bub == false)
						event.cancelBubble = true;
				} else {
					var event = arguments[0];
					if (bub == false)
						event.stopPropagation();
				}
				fun.call(caller, event);
			};
		},
		insertBefore : function(newEle, targetEle) {
			newEle = this.getHtmlElement(newEle);
			targetEle = this.getHtmlElement(targetEle);
			targetEle.parentNode.insertBefore(newEle, targetEle);
		},
		insertAfter : function(newEle, targetEle) {
			newEle = this.getHtmlElement(newEle);
			targetEle = this.getHtmlElement(targetEle);
			var parent = targetEle.parentNode;
			if (parent.lastChild == targetEle) {
				parent.appendChild(newEle);
			} else {
				parent.insertBefore(newEle, targetEle.nextSibling);
			}
		}
	}
};
Un.Element = Un.newClass(Un.Element);

if (!Un.AGENT.is("ie")) {
	Un.Element.prototype.setOpacity = function(f) {
		this.setStyle("opacity", f / 100);
	};
}
/** *************************************************************** Element */

Un.document = Un.Element.get(document);
Un.window = Un.Element.get(window);

/** ready *************************************************************** */
Un.readyList = [];
Un.ready = function(f) {
	if (this.isReady) {
		f().call(document);
	} else {
		this.readyList.push(f);
	}
};
Un.isReady = false;
Un.allReady = function() {
	this.isReady = true;
	if (this.readyList) {
		for ( var i = 0, len = this.readyList.length; i < len; i++) {
			this.readyList[i].call(document);
		}
	}
	this.readyList = null;
};
Un.doReady = function() {
	if (document.addEventListener) {
		document.addEventListener("DOMContentLoaded", function() {
			document.removeEventListener("DOMContentLoaded", arguments.callee,
					false);
			Un.allReady();
		}, false);
	} else {
		if (document.attachEvent) {
			document.attachEvent("onreadystatechange",
					function() {
						if (document.readyState === "complete") {
							document.detachEvent("onreadystatechange",
									arguments.callee);
							Un.allReady();
						}
					});
			if (document.documentElement.doScroll && window == window.top) {
				(function() {
					if (!Un.isReady) {
						try {
							document.documentElement.doScroll("left");
						} catch (E) {
							setTimeout(arguments.callee, 0);
							return;
						}
						Un.allReady();
					}
				})();
			}
		}
	}
	Un.window.addListener("load", Un.allReady, Un);
};
Un.doReady();

Un.ready(function() {
	Un.body = new Un.Component({
		e : document.body
	});
	Un.body.getScrollWidth = function() {
		return document.documentElement.scrollWidth;
	};
	Un.body.getScrollHeight = function() {
		return document.documentElement.scrollHeight;
	};
	Un.body.getClientWidth = function() {
		return document.documentElement.clientWidth;
	};
	Un.body.getClientHeight = function() {
		return document.documentElement.clientHeight;
	};
	Un.body.getScrollLeft = function() {
		return document.documentElement.scrollLeft;
	};
	Un.body.getScrollTop = function() {
		return document.documentElement.scrollTop;
	};
});
/** *************************************************************** ready */

/** Component *************************************************************** */
Un.Component = Un.newClass({
	extend : Un.Element,
	public : {
		mask : null,
		renderTo : null,
		applyTo : null,
		addMask : function() {
			this.mask = new Un.Box("div", "u_mask");
			if (Un.AGENT.is("ie", "6.0")) {
				var m = new Un.Box("iframe", "u_mask_iframe_ie6");
				this.mask.appendChild(m);
			}
			this.appendChild(this.mask);
		},
		setMaskSize : function() {
			this.mask.hide();
			this.mask.setSize(this.getScrollWidth(), this.getScrollHeight());
			this.mask.show();
		},
		showMask : function(o) {
			if (!this.mask)
				this.addMask();
			this.setMaskSize();
			this.mask.show();
			this.addListener("resize", this.setMaskSize, this);
		},
		hideMask : function() {
			this.mask.hide();
			this.removeListener("resize", this.setMaskSize);
		},
		render : function(obj) {
			this.renderTo = obj || this.renderTo;
			this.renderTo = Un.Component.get(this.renderTo);
			this.renderTo = this.renderTo || Un.body;
			this.appendInto(this.renderTo);
		}
	},
	constructor : function() {
	}
});
/** *************************************************************** Component */

/** Button *************************************************************** */
Un.Button = Un.newClass({
	extend : Un.Element,
	public : {
		className : "u_btn",
		up : function() {
			this.removeClass(this.className + "_over");
			this.removeClass(this.className + "_down");
		},
		over : function() {
			this.removeClass(this.className + "_down");
			this.addClass(this.className + "_over");
		},
		down : function() {
			this.removeClass(this.className + "_over");
			this.addClass(this.className + "_down");
		}
	},
	constructor : function() {
		this.addListener("mousedown", this.down, null, false);
		this.addListener("mouseup", this.over, null, false);
		this.addListener("mouseover", this.over, null, false);
		this.addListener("mouseout", this.up, null, false);
		this.addClass("u_btn");
	}
});
/** *************************************************************** Button */

/** Box *************************************************************** */
Un.Box = Un.newClass({
	extend : Un.Component,
	public : {
		width : 180,
		height : 110,
		getWidth : function() {
			return parseInt(this.e.style.width);
		},
		getHeight : function() {
			return parseInt(this.e.style.height);
		},
		setSize : function(w, h) {
			this.setWidth(w);
			this.setHeight(h);
		},
		top : function(t) {
			this.e.style.top = t + "px";
		},
		right : function(r) {
			this.e.style.right = r + "px";
		},
		bottom : function(b) {
			this.e.style.bottom = b + "px";
		},
		left : function(l) {
			this.e.style.left = l + "px";
		},
		setPosition : function(t, r, b, l) {
			this.top(t);
			this.right(r);
			this.bottom(b);
			this.left(l);
		},
		moveTo : function(x, y) {
			this.top(y);
			this.left(x);
		},
		addMask_ie6 : function() {
			this.appendChild(new Un.Box("iframe", "u_mask_iframe_ie6"));
		},
		setCursor : function(c) {
			this.setStyle("cursor", c);
		},
		apply : function(obj) {
			this.applyTo = obj || this.applyTo;
			this.applyTo = Un.Component.get(this.applyTo);
			this.applyTo.insertAfter(this);
			var height = this.applyTo.getHeight();
			if (isNaN(height)) {
				height = this.applyTo.getOffsetHeight();
			}
			this.moveTo(this.applyTo.getX(), this.applyTo.getY() + height);
		}
	}
});
/** *************************************************************** Box */

Un.Date = {
	getTime : function() {
		return (new Date()).getTime();
	}
};

/** Timer ******************************************************************** */
Un.Timer = Un.newClass({
	extend : Un.Observable,
	public : {
		start : function(fun, i, c) {
			if (this.intervalId)
				return false;
			var t = this;
			this.startTime = Un.Date.getTime();
			var s = this.startTime;
			var proxy = function() {
				var nowTime = Un.Date.getTime();
				var b = fun.call(c, nowTime - s);
				if (b === false)
					t.stop();
			};
			this.intervalId = window.setInterval(proxy, i);
			return true;
		},
		stop : function() {
			var rs = this.doCustomEvent("beforeStop");
			if (!Un.Util.getBoolean(rs))
				return false;
			if (!this.intervalId)
				return false;
			window.clearInterval(this.intervalId);
			this.intervalId = null;
			this.doCustomEvent("stop");
			return true;
		}
	}
});
/** ******************************************************************** Timer */

/** ******************************************************************** Animate */
/**
 * @param o.setter[function]设置器
 * @param o.caller[function]
 * @param o.formula[function]包含动画算法的函数
 * @param o.callback[function]回调函数
 */
Un.Animate = Un.newClass({
	extend : Un.Observable,
	public : {
		interval : 10,
		setter : null,
		formula : null,
		caller : null,
		callback : null,
		start : function(fun, c) {
			var caller = this.caller;
			var prox = function(time) {
				var n = this.formula(time);
				if (n === false)
					return false;
				this.setter.call(caller, n);
			};
			this.timer.start(prox, this.interval, this);
			this.timer.addCustomListener("stop", this.callback, caller);
		},
		stop : function() {
			this.timer.stop;
		}
	},
	constructor : function() {
		this.timer = new Un.Timer();
	},
	static : {
		/**
		 * 获得动画算法
		 * 
		 * @param sa起始值
		 * @param so最终值
		 * @param time时间
		 */
		getFormula : function(sa, so, time, fun) {
			var max = Math.max(sa, so);
			var min = Math.min(sa, so);
			var formula = function(t) {
				var n = fun(t / time, null, sa, so - sa);
				if (t >= time || n < min || n > max)
					return false;
				return n;
			};
			return formula;
		},
		easing : {
			linear : function(sa, so, time) {
				return firstNum + diff * p;
			},
			swing : function(p, n, firstNum, diff) {
				return ((-Math.cos(p * Math.PI) / 2) + 0.5) * diff + firstNum;
			}
		}
	}
});
Un.AnimateGroup = Un.newClass({
	extend : Un.Observable,
	public : {
		animates : null,
		addAnimate : function(a) {
			this.animates.push(a);
		},
		start : function() {
			var arr = this.animates;
			for ( var i = 0, len = arr.length; i < len; i++) {
				arr[i].start();
			}
		},
		stop : function() {
			var arr = this.animates;
			for ( var i = 0, len = arr.length; i < len; i++) {
				arr[i].stop();
			}
		}
	},
	constructor : function() {
		this.animates = this.animates || [];
	}
});
/** Animate ******************************************************************** */

/** Ajax ******************************************************************** */
Un.AjaxUtils = {

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

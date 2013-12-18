Ext.Loader.setConfig({
	enabled : true
});
Ext.Loader.setPath('Ext.ux.window', _ctxPath() + '/joy/extjs/common/platform/util');
Ext.require([ 'Ext.ux.window.Notification']);



Ext.define("com.kvc.joy.extjs.common.platform.util.Joy", {
	
	getContextPath : function() {
	    return _ctxPath();
	},
	
	showDelayNotice : function(msg, type, delay, title, position, spacing) {
		this.showDelayNotification(msg, type, delay, title, position, spacing);
	},
	
	showDelayNotification : function(msg, type, delay, title, position, spacing) {
		var iconCls;
		if (type == null) {
			type = "info";
			iconCls = 'ux-notification-icon-information';
		} else {
			if(type == "error") {
				iconCls = "ux-notification-icon-error";
			} else {
				iconCls = 'ux-notification-icon-information';
			}
		}
		if(delay == null) {
			delay = 2500;
		}
		if(title == null) {
			if(type == "error") {
				title = "警告";
			} else {
				title = "提示";
			}
		}
		if(position == null) {
			position = "br";
		}
		if(spacing == null) {
			spacing = 60;
		}
		
		Ext.create('widget.uxNotification', {
			title: title,
			position: position,
			iconCls: iconCls,
			autoHideDelay: delay,
			spacing: 60,
			html: msg
		}).show();
	}
	
});

var JOY = Ext.create('com.kvc.joy.extjs.common.platform.util.Joy');
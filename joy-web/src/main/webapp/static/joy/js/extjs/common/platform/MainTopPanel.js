   //递归实现无限级iframe
changeiframe=function(win, css)
    {
      var arr = win.Ext.select('iframe');
      arr.each(function (item) {   
      var winsub = win.Ext.isIE ? win.frames[item.dom.id] : item.dom.contentWindow;
        if(winsub.Ext!=null)
        {
           //我的页面是二级目录下，所以使用’http://www.cnblogs.com/’
            winsub.Ext.util.CSS.swapStyleSheet("theme", css);
            changeiframe(winsub,css);        
        }
       })
}

Ext.define('com.kvc.joy.extjs.common.platform.MainTopPanel', {
	extend : 'Ext.panel.Panel',
	alias : 'widget.mainTopPanel',
	layout : {
		type : 'hbox',
		pack : 'end'
	},
	defaults:{margins:'5 5 5 0'},
	items : [ {
		region : 'east',
		xtype : 'combobox',
		itemId : 'themeComboBox',
		fieldLabel : '主题',
		labelAlign : 'right',
		forceSelection : true,
		editable : false,
		store : Ext.create("Ext.data.Store", {
            fields: ["abbr", "name"],
            data: [
                    { "abbr": "ext-all-scoped", "name": "Classic" },
                    { "abbr": "ext-all-gray", "name": "Gray" },
                    { "abbr": "ext-all-access", "name": "Accessibility" }
                  ],
            sorters: ["abbr"]
        }),
		queryMode : "local",
		displayField : "name",
		valueField : "abbr",
		listeners : {
			scope : this,
			'select' : function(comboBox, record, index) {
				var themeCss = comboBox.getValue();
				var css = JOY.getContextPath() + '/joy/extjs/common/third/extjs/resources/css/'+themeCss+'.css';
				
				 //设置cookies
                var date=new Date();
                date.setTime(date.getTime()+30*24*3066*1000);
                document.cookie="themeCss="+themeCss+";expires="+date.toGMTString();
				
				Ext.util.CSS.swapStyleSheet('theme', css);
				changeiframe(window, css);
			},
			'afterrender': function(t,o ) {
                var cookieArr = window.document.cookie.split(";");   
                var themeCss = 'ext-all-scoped';
                for(var i=0;i<cookieArr.length;i++) {
                	var arr = cookieArr[i].split("=");
                	if(Ext.String.trim(arr[0])=='themeCss') {
                		themeCss = arr[1];
                		break;
                    }
                };
                
                t.setValue(themeCss);
//                t.fireEvent('select', t);
            }
		}

	} ]
});
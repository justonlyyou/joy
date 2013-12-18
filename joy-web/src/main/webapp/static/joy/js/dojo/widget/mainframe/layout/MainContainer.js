define([
	"dojo/_base/declare",
	"joy/layout/JBorderContainer",
	"dojo/window",
	"joy/layout/JToggleSplitter"
    ], 
    
    function(declare, JBorderContainer, winUtils, JToggleSplitter) {
		return declare("joy.mainframe.layout.MainContainer", JBorderContainer, {
	
	        _splitterClass : JToggleSplitter,
	
			postCreate : function() {
				this.inherited(arguments);
				this._setHeight();
			},
			
			/**
			 * 窗口调整大小时，重新计算容器高度
			 * @override 
			 */
			resize : function() {
	            this.inherited(arguments);
	            this._setHeight();
	        } ,
			
			_setHeight : function() {
				this.domNode.style.height = winUtils.getBox().h + "px";
			}
		});
	}

);
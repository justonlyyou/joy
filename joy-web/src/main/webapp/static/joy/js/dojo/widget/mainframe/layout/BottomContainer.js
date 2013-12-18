define([	
	"dojo/_base/declare",
	"dijit/layout/ContentPane"
	], 
	
	function(declare, LayoutContainer){
		return declare( "joy.mainframe.layout.BottomContainer", LayoutContainer, {
			baseClass : "dijitHomeContentPane",
			
		});
	}

);
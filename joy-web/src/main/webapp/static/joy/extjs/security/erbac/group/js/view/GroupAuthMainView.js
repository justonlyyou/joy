Ext.define("com.kvc.joy.extjs.security.group.js.view.GroupAuthMainView", {
	extend : 'Ext.Viewport',
	alias : 'widget.groupAuthMain',
	layout: 'fit',
	
	initComponent: function() {
        var me = this;
        
        Ext.apply(me, {
            items: [
                {
                    xtype: 'panel',
                    border: false,
                    id    : 'viewport',
                    layout: {
                        type: 'vbox',
                        align: 'stretch'
                    },
                    
	                items: [
                    {
                        xtype: 'textfield',
                        name : 'name',
                        fieldLabel: 'Name'
                    },
                    {
                        xtype: 'textfield',
                        name : 'email',
                        fieldLabel: 'Email'
                    }
                ]
                }
            ]
        });
                
        me.callParent(arguments);
    }
	
//	 initComponent: function() {
//	        this.items = [
//	            {
//	                xtype: 'form',
//	                padding: '5 5 0 5',
//	                border: false,
//	                style: 'background-color: #fff;',
//
//	                items: [
//	                    {
//	                        xtype: 'textfield',
//	                        name : 'name',
//	                        fieldLabel: 'Name'
//	                    },
//	                    {
//	                        xtype: 'textfield',
//	                        name : 'email',
//	                        fieldLabel: 'Email'
//	                    }
//	                ]
//	            }
//	        ];

//	        this.buttons = [
//	            {
//	                text: 'Save',
//	                action: 'save'
//	            },
//	            {
//	                text: 'Cancel',
//	                scope: this,
//	                handler: this.close
//	            }
//	        ];

//	        this.callParent(arguments);
//	    }
});
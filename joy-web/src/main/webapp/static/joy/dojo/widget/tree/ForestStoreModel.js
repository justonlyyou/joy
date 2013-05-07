define([
        "dojo/_base/declare", 
        "dijit/tree/ForestStoreModel",
        "dojo/_base/lang"
        ], 
        
        function(declare, ForestStoreModel, lang) {
			return declare("joy.tree.ForestStoreModel", ForestStoreModel, {
				
		        /**
		         * Dojo对于叶子／非叶子节点的图标处理时会做自动判断。在懒加载时由于有些节点没有加载子节点，
		         * dojo在处理这些节点的图标的时候会显示默认的叶子图标，所以这里需要重
		         * 
		         * @param item 对应节点的数据项
		         * @return true表示该节点为非叶子节点
		         * @rewrite
		         */
				mayHaveChildren: function(/*dojo.data.Item*/ item) {
				    if(item.root)  {
				        return true;  
				    }  else {
				        // 这里认为初始化树的时候对于每一个父节点的数据项中都有children属性，且该属性的值长度不为0
				    	var children = this.store.getValue(item, 'children');
				        return children && children.length != 0;
				    }
				}
			
			});
		}

);

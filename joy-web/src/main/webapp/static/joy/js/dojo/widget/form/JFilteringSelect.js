define([
	"dojo/_base/declare",
	"dijit/form/FilteringSelect"
	], 
	
	function(declare, FilteringSelect) {
		return declare("joy.form.JFilteringSelect", FilteringSelect, {
			
			// 新增一个自定义属性，用于指定FilteringSelect的textbox中最终显示内容的属性字段
			 displayValueAttr : null,
			 
            /**
             * 使FilteringSelect支持displayValueAttr指定textbox最终显示内容，而不是默认显示searchAttr指定的字段内容
             * @rewrite dijit.form._AutoCompleterMixin
             */
			 _announceOption: function(/*Node*/ node){
                if(!node) {
                    return;
                }
                // pull the text value from the item attached to the DOM node
                var newValue;
                if(node == this.dropDown.nextButton ||
                    node == this.dropDown.previousButton) {
                    newValue = node.innerHTML;
                    this.item = undefined;
                    this.value = '';
                } else {
                    var item = this.dropDown.items[node.getAttribute("item")];
                    var displayAttr = this.displayValueAttr!=null?this.displayValueAttr:this.searchAttr;//此处判断是否配置了自定义属性displayValueAttr
                     
                    newValue = (this.store._oldAPI ?    // remove getValue() for 2.0 (old dojo.data API)
                        this.store.getValue(item, displayAttr) : item[displayAttr]).toString();//将this.searchAttr替换为displayAttr
 
                    this.set('item', item, false, newValue);
                }
                // get the text that the user manually entered (cut off autocompleted text)
                this.focusNode.value = this.focusNode.value.substring(0, this._lastInput.length);
                // set up ARIA activedescendant
                this.focusNode.setAttribute("aria-activedescendant", domAttr.get(node, "id"));
                // autocomplete the rest of the option to announce change
                this._autoCompleteText(newValue);
            }
		
		});
	}

);
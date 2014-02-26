define(["jquery",
        "joy"
    ], function($, joy) {
	
	

	return duceap.declare("commons.BaseManager", null, {
	
	    dataLoader : null,
	    queryBtn : null,
	    toggleSeniorQueryBtn : null,
	    seniorQueryBtn : null,
	    resetSeniorQueryBtn : null,
	    addNewBtn : null,
	    deleteBtn : null,
	    queryConditionForm : null,
	    seniorQueryConditionPane : null,
	    seniorQueryConditionForm : null,
	    resultGrid : null,
	    listPage : null,
	    editPage : null,
	    detailPage : null,
	
	    // 构造函数 获取相应组件引用
	    constructor : function(dataLoader) {
	        this.dataLoader = dataLoader;
	        this.queryBtn = dataLoader.getCmpByName("queryBtn");
	        this.toggleSeniorQueryBtn = dataLoader.getCmpByName("toggleSeniorQueryBtn");
	        this.seniorQueryBtn = dataLoader.getCmpByName("seniorQueryBtn");
	        this.resetSeniorQueryBtn = dataLoader.getCmpByName("resetSeniorQueryBtn");
	        this.addNewBtn = dataLoader.getCmpByName("addNewBtn");
	        this.deleteBtn = dataLoader.getCmpByName("deleteBtn");
	        this.queryConditionForm = dataLoader.getCmpByName("queryConditionForm");
	        this.seniorQueryConditionPane = dataLoader.getCmpByName("seniorQueryConditionPane");
	        this.seniorQueryConditionForm = dataLoader.getCmpByName("seniorQueryConditionForm");
	        if(this.seniorQueryConditionForm && this.resultGrid) {
	            var ds = this.resultGrid.getDataStore();
	            this.seniorQueryConditionForm.set("store", ds ? ds.dataStoreName : "");
	        }
	        this.resultGrid = dataLoader.getCmpByName("resultGrid");
	        this.listPage = dataLoader.getCmpByName("listPage");
	        this.editPage = dataLoader.getCmpByName("editPage");
	        this.detailPage = dataLoader.getCmpByName("detailPage");
	        if(this.resultGrid) {
	            this.resultGrid.controler = this;
	        }
	
	        this.eventConnect();
	    },
	
	    eventConnect : function() {
	        duceap.connect(this.queryBtn, "onClick", duceap.hitch(this, "query", this.queryConditionForm));
	        duceap.connect(this.seniorQueryBtn, "onClick", duceap.hitch(this, "query", this.seniorQueryConditionForm));
	        duceap.connect(this.resetSeniorQueryBtn, "onClick", this, "resetCondition");
	        duceap.connect(this.toggleSeniorQueryBtn, "onClick", this, "toggleSeniorQuery");
	        duceap.connect(this.addNewBtn, "onClick", duceap.hitch(this, "showEditPage", null));
	        duceap.connect(this.deleteBtn, "onClick", this, "deleteRows");
	        duceap.connect(this.editPage, "onLoad", this, "createEditPage");
	        duceap.connect(this.detailPage, "onLoad", this, "createDetailPage");
	    },
	
	    /**
	     * 执行查询
	      */
	    query : function(queryForm) {
	        // 校验表单
	        if (queryForm.validateForm()) {
	            if(queryForm === this.seniorQueryConditionForm) {
	                  this.toggleSeniorQuery();
	            }
	            // 获取查询条件
	            var condition = queryForm.getQueryCondition();
	            // 设置查询条件
	            this.resultGrid.setQueryCondition(condition);
	            // 重新加载表格数据
	            this.resultGrid.load();
	        }
	    },
	
	    /**
	     * 打开/关闭高级查询面板
	     */
	    toggleSeniorQuery : function() {
	        var visible = this.seniorQueryConditionPane.get("visible");
	        if(visible){
	            this.toggleSeniorQueryBtn.set("label", "开启高级搜索");
	        }else{
	            this.toggleSeniorQueryBtn.set("label", "关闭高级搜索");
	        }
	        this.seniorQueryConditionPane.set("visible", !visible);
	    },
	
	    /**
	     * 重置高级查询条件
	     */
	    resetCondition : function() {
	        this.seniorQueryConditionForm.reset();
	    },
	
	    /**
	     * 删除表格选择的记录
	     */
	    deleteRows : function() {
	    	var selectedRows;
	    	if(this.resultGrid.declaredClass.indexOf("gridx") != -1) {
	    		selectedRows = this.resultGrid.model.getMarkedRows();
	    	} else { // 兼容旧表格
	    		selectedRows = this.resultGrid.selection.getSelected();
	    	}
	        
	        if(selectedRows.length == 0) {
	            this.dataLoader.alert({message : "您未选择要删除的记录！"});
	        } else {
	            this.dataLoader.confirm({
	                icon : 'WARN',
	                message : '是否删除?',
	                title : '删除提示',
	                scope : this,
	                onComplete : function(result) {
	                    if ("OK" == result) {
	                    	var idAttr;
	                    	if(this.resultGrid.declaredClass.indexOf("gridx") != -1) {
	                    		idAttr = this.resultGrid.getRowKeyAttr();
	            	    	} else { // 兼容旧表格
	            	    		idAttr = this.resultGrid.getDataStore().getRowSet().getIdentifierAttribute();
	            	    	}
	                        var deleteIds = "";
	                        var forbid  = duceap.some(selectedRows, function(item) {
	                            return item["isSystem"] == "是";
	                        });
	                        if(forbid) {
	                            this.dataLoader.alert({message : "所选有记录包含有系统内置对象，不能删除！"});
	                        } else {
	                            duceap.forEach(selectedRows, function(item){
	                                var id = item[idAttr];
	                                if(duceap.isArray(id)) {
	                                	id = id[0];
	                                }
	                                deleteIds += id + ",";
	                            }, this);
	                            this.deleteRow(deleteIds);
	                        }
	                    }
	                }});
	        }
	    },
	
	    deleteRow : function (id) {
	        var url = this.getDeleteActionUrl();
	        var shadeBox = duceap.shadeBox({message : "正在删除,请稍等..."});
	        duceap.Action.post({
	            url:url,
	            scope:this,
	            param:{
	                id:id
	            },
	            load:function (code, message) {
	
	                this.dataLoader.alert({
	                    title:message.title,
	                    message:message.detail
	                });
	                // 重新加载表格数据
	                this.resultGrid.load();
	                if(this.resultGrid.selection) {
	                	this.resultGrid.selection.clear();
	                }
	                shadeBox.hide();
	            },
	            error:function (code, message) {
	
	                this.dataLoader.alert({
	                    title:message.title,
	                    message:message.detail
	                });
	                // 重新加载表格数据
	                this.resultGrid.load();
	                if(this.resultGrid.selection) {
	                	this.resultGrid.selection.clear();
	                }
	                shadeBox.hide();
	            }
	        });
	    },
	
	    /**
	     * 表格中删除一条记录
	     */
	    deleteRowFromGrid : function(id, index) {
	        this.dataLoader.confirm({
	            icon : 'WARN',
	            message : '是否删除?',
	            title : '删除提示',
	            scope : this,
	            onComplete : function(result) {
	                if ("OK" == result) {
	                   this.deleteRow(id);
	                }
	            }
	        });
	    },
	
	    /**
	     * 创建编辑/详情页面
	     * @param page 编辑/详情TabPage
	     */
	    createDetailPage : function() {
	        var page = this.newDetailPage();
	        this.detailPage.controller.create({
	            value : page
	        });
	        page.show();
	    },
	
	    newDetailPage : function() {
	        return new BaseDetailPage(this.detailPage.controller, this);
	    },
	
	    /**
	     * 创建编辑页面
	     */
	    createEditPage : function() {
	        var page = this.newEditPage();
	        this.editPage.controller.create({
	            value : page
	        });
	        page.show();
	    },
	
	    newEditPage : function() {
	        return new BaseEditPage(this.editPage.controller, this);
	    },
	
	    /**
	     * 展现编辑页面
	     * @param id 记录主键
	     */
	    showEditPage : function(id) {
	        var url = duceap.ContextPath + this.getEditPageUrl() + "?id=" + id + "&fromList=true";
        	this.editPage.set("visible", true);
	        this.editPage.refreshPage(url);
	        this.listPage.set("visible", false);
	        if(this.detailPage) {
	            this.detailPage.set("visible", false);
	        }
	    },
	
	    /**
	     * 展现详情页面
	     * @param id 记录主键
	     */
	    showDetailPage : function(id) {
	        var url = duceap.ContextPath + this.getDetailPageUrl() + "?id=" + id + "&fromList=true";
	        	this.detailPage.set("visible", true);
        	this.detailPage.refreshPage(url);
	        this.listPage.set("visible", false);
	        if(this.editPage) {
	            this.editPage.set("visible", false);
	        }
	    },
	
	    /**
	     * 取得编辑页面的URL，如果没有编辑页面，子类可以不用重写
	     */
	    getEditPageUrl : function() {
	        throw Error("未重写getEditPageUrl()，该方法用于取得编辑页面的URL！");
	    },
	
	    /**
	     * 取得详情页面的URL，如果没有详情页面，子类可以不用重写
	     */
	    getDetailPageUrl : function() {
	        throw Error("未重写getDetailPageUrl()，该方法用于取得详情页面的URL！");
	    },
	
	    /**
	     * 取得删除数据的Action URL，如果不需要删除操作，子类可以不用重写
	     */
	    getDeleteActionUrl : function() {
	        throw Error("未重写getDeleteActionUrl()，该方法用于取得删除数据的Action的URL！");
	    }
	
	});

});
/**
 * Created with JetBrains WebStorm.
 * User: Kevice
 * Date: 12-12-7
 * Time: 下午9:43
 * To change this template use File | Settings | File Templates.
 */
define([], function(){

	return duceap.declare("commons.BaseEditPage", null, {

	    dataLoader : null,
	    mgr : null,
	    args : null,
	    fromList : null, // 是否从列表中打开的详情
	    backBtn : null,
	    addNewBtn : null,
	    saveBtn : null,
	    deleteBtn : null,
	    editForm : null,
	    isEdit : null,
	    saveActionUrl : null,
	    editActionUrl : null,
	    deleteActionUrl : null,
	
	    constructor : function(dataLoader, mgr, urlArgs) {
	        this.dataLoader = dataLoader;
	        this.mgr = mgr;
	        this.args = dataLoader.getParameter();
	        this.fromList = this.args.fromList;
	        if(this.fromList) {
	            this.backBtn = dataLoader.getCmpByName("backBtn");
	            this.backBtn.set("visible", true);
	        }
	        this.editForm = dataLoader.getCmpByName("editForm");
	        this.addNewBtn = dataLoader.getCmpByName("addNewBtn");
	        this.saveBtn = dataLoader.getCmpByName("saveBtn");
	        this.deleteBtn = dataLoader.getCmpByName("deleteBtn");
	
	        if(urlArgs) {
	            this.saveActionUrl = urlArgs.saveActionUrl;
	            this.editActionUrl = urlArgs.editActionUrl;
	            this.deleteActionUrl = urlArgs.deleteActionUrl;
	        }
	
	        this.eventConnect();
	    },
	
	    show: function(){
	        if(this.args.id && this.args.id != "null"){
	            this.isEdit = true;
	            this.editRecord(this.args.id);
	        } else {
	            this.isEdit = false;
	            this.newRecord();
	        }
	    },
	
	    eventConnect : function() {
	        if(this.fromList) {
	            duceap.connect(this.backBtn, "onClick", this, "backToList");
	        }
	        duceap.connect(this.addNewBtn, "onClick", this, "newRecord");
	        duceap.connect(this.saveBtn, "onClick", this, "save");
	        duceap.connect(this.deleteBtn, "onClick", this, "deleteRecord");
	    },
	
	    backToList : function() {
	        this.mgr.editPage.set("visible", false);
	        this.mgr.listPage.set("visible", true);
	        // 重新加载表格数据
	        this.mgr.resultGrid.load();
	        this.mgr.resultGrid.selection.clear();
	    },
	
	    // 保存表单修改结果
	    save : function() {
	        var url = this.getSaveActionUrl();
	        if (this.editForm.validateForm()) {
	            var shadeBox = duceap.shadeBox({message : "正在保存,请稍等..."});
	            this.editForm.submit({
	                url : url,
	                scope : this,
	                load : function(code, message) {
	                    this.successfullySave(code, message);
	                    shadeBox.hide();
	                },
	                error : function(code, message) {
	                    this.faillySave(code, message);
	                    shadeBox.hide();
	                }
	            });
	        }
	    },
	    
	    successfullySave : function(code, message) {
	    	this.dataLoader.alert({
	            title : message.title,
	            message : message.detail
	        });
	        this.isEdit = true;
	        this.deleteBtn.set("visible", true);
	    },
	    
	    faillySave : function(code, message) {
	    	this.dataLoader.alert({
	            title : message.title,
	            message : message.detail
	        });
	    },
	
	    // 新增一条记录
	    newRecord : function() {
	        this.isEdit = false;
	        this.editForm.newRow();
	//        this.editForm.reset();
	        this.deleteBtn.set("visible", false);
	    },
	
	    // 编辑一行记录
	    editRecord : function(id) {
	        var shadeBox = duceap.shadeBox({message : "正在加载,请稍等..."});
	        var url = this.getEditActionUrl(id) + '?id=' + id;
	        this.editForm.fetch({
	            url : url,
	            scope : this,
	            load : function(code, message) {
	                this.successfullyLoad(code, message);
	                shadeBox.hide();
	            },
	            error : function(code, message) {
	                this.faillyLoad(code, message);
	                shadeBox.hide();
	            }
	        });
	    },
	    
	    successfullyLoad : function(code, message) {
	    	this.deleteBtn.set("visible", true);
	    },
	    
	    faillyLoad : function(code, message) {
	    	this.dataLoader.alert({
	            title : message.title,
	            message : message.detail
	        });
	    },
	
	    // 删除表单中的记录
	    deleteRecord : function() {
	        this.dataLoader.confirm({
	            icon : 'WARN',
	            message : '是否删除?',
	            title : '删除提示',
	            scope : this,
	            onComplete : function(result) {
	                if ("OK" == result) {
	                    var shadeBox = duceap.shadeBox({message : "正在删除,请稍等..."});
	                    // 获取该条记录对应的主键值
	                    var key = this.editForm.get("key");
	                    var url = this.getDeleteActionUrl(key.value);
	                    duceap.Action.post({
	                        url : url,
	                        scope : this,
	                        param : {
	                            id : key.value
	                        },
	                        load : function(code, message) {
	                        	this.successfullyDelete(code, message);
	                            shadeBox.hide();
	                        },
	                        error : function(code, message) {
	                            this.faillyDelete(code, message);
	                            shadeBox.hide();
	                        }
	                    });
	                }
	            }
	        });
	    },
	    
	    successfullyDelete : function(code, message) {
	    	this.dataLoader.alert({
	            title : message.title,
	            message : message.detail
	        });
	        this.newRecord();
	    },
	    
	    faillyDelete : function(code, message) {
	    	this.dataLoader.alert({
	            title : message.title,
	            message : message.detail
	        });
	    },
	
	    getSaveActionUrl : function() {
	        if(!this.saveActionUrl) {
	            throw Error("saveActionUrl为空，该值可以通过构造方法传入、重写getSaveActionUrl方法或调用setSaveActionUrl方法三种方式提供！");
	        }
	        return this.saveActionUrl;
	    },
	
	    setSaveActionUrl : function(saveActionUrl) {
	        this.saveActionUrl = saveActionUrl;
	    },
	
	    getEditActionUrl : function() {
	        if(!this.editActionUrl) {
	            throw Error("editActionUrl为空，该值可以通过构造方法传入、重写getEditActionUrl方法或调用setEditActionUrl方法三种方式提供！");
	        }
	        return this.editActionUrl;
	    },
	
	    setEditActionUrl : function(editActionUrl) {
	        this.editActionUrl = editActionUrl;
	    },
	
	    getDeleteActionUrl : function() {
	        if(!this.deleteActionUrl) {
	            throw Error("deleteActionUrl为空，该值可以通过构造方法传入、重写getDeleteActionUrl方法或调用setDeleteActionUrl方法三种方式提供！");
	        }
	        return this.deleteActionUrl;
	    },
	
	    setDeleteActionUrl : function(deleteActionUrl) {
	        this.deleteActionUrl  = deleteActionUrl;
	    }
	
	});
	
});

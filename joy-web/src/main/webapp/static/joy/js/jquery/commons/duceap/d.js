/**
 * Created with JetBrains WebStorm.
 * User: Kevice
 * Date: 12-12-7
 * Time: 下午20:43
 * To change this template use File | Settings | File Templates.
 */
define([], function(){

	return duceap.declare("commons.BaseDetailPage", null, {

	    dataLoader : null,
	    args : null,
	    fromList : null, // 是否从列表中打开的详情
	    backBtn : null,
	    mgr : null,
	    detailForm : null,
	    detailActionUrl : null,
	
	
		// 构造函数 获取相应组件引用
		constructor : function(dataLoader, mgr, detailActionUrl) {
			this.dataLoader = dataLoader;
	        this.mgr = mgr;
	        this.detailActionUrl = detailActionUrl;
			this.detailForm = this.dataLoader.getCmpByName("detailForm");
	        this.args = this.dataLoader.getParameter();
	        this.fromList = this.args.fromList;
	        if(this.fromList) {
	            this.backBtn = this.dataLoader.getCmpByName("backBtn");
	            this.backBtn.set("visible", true);
	        }
	        this.eventConnect();
	    },
	
	    show: function(){
	        if(this.args.id){
	            this.showRow(this.args.id);
	        }
	    },
	
	    eventConnect : function() {
	        if(this.fromList) {
	            duceap.connect(this.backBtn, "onClick", this, "backToList");
	        }
	    },
		
		showRow : function(id) {
			var shadeBox = this.dataLoader.shadeBox({message : "正在加载,请稍等..."});
			// 指定编辑的记录
			var url = this.getDetailActionUrl() + '?id=' + id;
	
			this.detailForm.fetch({
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
		},
		
		faillyLoad : function(code, message) {
			this.dataLoader.alert({
				title : message.title,
				message : message.detail
			});
		},
	
	    backToList : function() {
	        this.mgr.detailPage.set("visible", false);
	        this.mgr.listPage.set("visible", true);
	        // 重新加载表格数据
	        this.mgr.resultGrid.load();
	        this.mgr.resultGrid.selection.clear();
	    },
	
	    getDetailActionUrl : function() {
	        if(!this.detailActionUrl) {
	            throw Error("detailActionUrl为空，该值可以通过构造方法传入、" +
	            		"重写getDetailActionUrl方法或调用setDetailActionUrl方法三种方式提供！");
	        }
	        return this.detailActionUrl;
	    },
	
	    setDetailActionUrl : function(detailActionUrl) {
	        this.detailActionUrl = detailActionUrl;
	    }
	
	});
	
});


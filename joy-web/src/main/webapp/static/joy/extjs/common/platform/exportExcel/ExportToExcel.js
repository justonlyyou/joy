function exportToExcel(grid) {
	var vExportContent = grid.getExcelXml();
	if (Ext.isIE6 || Ext.isIE7 || Ext.isSafari || Ext.isSafari2 || Ext.isSafari3) {
		var fd = Ext.get('frmDummy');
		if (!fd) {
			fd = Ext.DomHelper.append(Ext.getBody(), {
				tag : 'form',
				method : 'post',
				id : 'frmDummy',
				action : 'exportexcel.jsp',
				target : '_blank',
				name : 'frmDummy',
				cls : 'x-hidden',
				cn : [ {
					tag : 'input',
					name : 'exportContent',
					id : 'exportContent',
					type : 'hidden'
				} ]
			}, true);
		}
		fd.child('#exportContent').set({
			value : vExportContent
		});
		fd.dom.submit();
	} else {
		document.location = 'data:application/vnd.ms-excel;base64,' + Base64.encode(vExportContent);
	}
}
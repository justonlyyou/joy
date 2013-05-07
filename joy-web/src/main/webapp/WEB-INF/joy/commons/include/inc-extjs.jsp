<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="/WEB-INF/jsp/include/include-base.jsp" %>

<script type="text/javascript" >

	function _ctxPath() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0, index + 1);
		return result;
	}
	
	(function() {
	
		// theme
		var cookieArr = window.document.cookie.split(";");
		var themeCss = 'ext-all-scoped';
		for ( var i = 0; i < cookieArr.length; i++) {
			var arr = cookieArr[i].split("=");
			if (arr[0].replace(" ", "") == 'themeCss') {
				themeCss = arr[1];
				break;
			}
		}
		document.write('<link rel="stylesheet" type="text/css" href="' + _ctxPath()
				+ '/joy/extjs/common/third/extjs/resources/css/' + themeCss + '.css"/>');
		
	 	
		document.write('<script type="text/javascript" charset="UTF-8" src="' + _ctxPath() 
				+ '/joy/extjs/common/third/extjs/bootstrap.js"></script>');
		document.write('<script type="text/javascript" charset="UTF-8" src="' + _ctxPath() 
				+ '/joy/extjs/common/platform/util/Joy.js"></script>');
		
	
	})();

</script>
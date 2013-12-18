<%@ include file="/WEB-INF/joy/commons/include/inc-jquery.jsp"%>

<link href="${thirdCtx}/bootstrap/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<script src="${thirdCtx}/bootstrap/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('[data-toggle=tooltip]').tooltip({
			container : "body"
		});

		$('[data-toggle=popover]').popover({
			container : "body"
		});
		$('.dropdown-toggle').dropdown();
	});
</script>

<link href="${thirdCtx}/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">

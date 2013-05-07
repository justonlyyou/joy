<%@ include file="/WEB-INF/joy/commons/include/inc-jquery.jsp"%>

<link href="${joyCtx}/third/bootstrap/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<script src="${joyCtx}/third/bootstrap/bootstrap/js/bootstrap.js"></script>
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

<link href="${joyCtx}/third/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">

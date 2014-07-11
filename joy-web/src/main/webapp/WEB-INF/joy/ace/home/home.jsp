<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>控制台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<%@ include file="/WEB-INF/joy/ace/include/inc-style.jsp"%>
		
		<script src="${thirdCtx}/ace/js/ace-extra.min.js"></script>
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="${thirdCtx}/ace/js/html5shiv.js"></script>
		<script src="${thirdCtx}/ace/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
		<!-- Top -->
		<%@ include file="topNav.jsp"%>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			 <div class="main-container-inner">
			 	<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<!-- Left -->
				<%@ include file="leftNav.jsp"%>
				
				<div class="main-content" style="height: 650px;">
					<iframe id="mainFrame" scrolling="auto" frameborder="0" width="100%" height="100%" src="" name=""></iframe>
				</div>
				
			 </div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<script src="${thirdCtx}/ace/js/jquery-1.10.2.min.js"></script>
		<script src="${thirdCtx}/ace/js/bootstrap.min.js"></script>
		<script src="${thirdCtx}/ace/js/typeahead-bs2.min.js"></script>
		<!-- page specific plugin scripts -->
		<!--[if lte IE 8]>
		  <script src="${thirdCtx}/ace/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="${thirdCtx}/ace/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${thirdCtx}/ace/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${thirdCtx}/ace/js/jquery.slimscroll.min.js"></script>
		<script src="${thirdCtx}/ace/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${thirdCtx}/ace/js/jquery.sparkline.min.js"></script>
		<script src="${thirdCtx}/ace/js/flot/jquery.flot.min.js"></script>
		<script src="${thirdCtx}/ace/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${thirdCtx}/ace/js/flot/jquery.flot.resize.min.js"></script>
		
		<%@ include file="/WEB-INF/joy/commons/include/inc-js.jsp"%>
		<script type="text/javascript">
	        curl(['joy/home/topNav'], function(TopNav) {
	            topNav = new TopNav();
	        });
	        curl(['joy/home/leftNav'], function(LeftNav) {
	            leftNav = new LeftNav();
	        });
    	</script>
    	
		<!-- ace scripts -->
		<script src="${thirdCtx}/ace/js/ace-elements.min.js"></script>
		<script src="${thirdCtx}/ace/js/ace.min.js"></script>
		
</body>
</html>


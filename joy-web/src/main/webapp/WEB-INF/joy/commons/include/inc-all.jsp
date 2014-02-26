<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<script>
    function getWebRootPath() {
        var webroot=document.location.href;
        webroot=webroot.substring(webroot.indexOf('//')+2,webroot.length);
        webroot=webroot.substring(webroot.indexOf('/')+1,webroot.length);
        webroot=webroot.substring(0,webroot.indexOf('/'));
        var rootpath="/"+webroot;
        return rootpath;
    }
</script>

<script src="${joyCtx}/js/jquery/commons/main.js"></script>
<script data-curl-run="" src="${thirdCtx}/curl/curl.js"></script>

<link href="${thirdCtx}/bootstrap/bootstrap/css/bootstrap.css" rel="stylesheet" media="screen">
<link href="${thirdCtx}/bootstrap/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="${cssCtx}/commons/joy.css" rel="stylesheet">
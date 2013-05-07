<%@ page contentType="text/html;charset=UTF-8" %>

<%@ include file="include-base.jsp" %>

<script>
    dojoConfig = {
        has: {
            "dojo-firebug": false,
            "dojo-debug-messages": true
        },
        // Don't attempt to parse the page for widgets
        parseOnLoad: true,
        isDebug: true,
        async: true,
        isDebug : true,
        packages: [
            {
                name: "joy",
                location: "joy"
            }
        ],
        paths: {
			 "joy": "<%=path%>/<%=resPath%>/dojo/widget",
			 "dojo" : "<%=path%>/<%=resPath%>/third/dojo/dojo",
			 "dijit" : "<%=path%>/<%=resPath%>/third/dojo/dijit",
			 "dojox" : "<%=path%>/<%=resPath%>/third/dojo/dojox",
			 "joyDojo": "<%=path%>/<%=resPath%>/dojo"
		},
        // Timeout after 10 seconds
        waitSeconds: 10,
        aliases: [
            // Instead of having to type "dojo/domReady!", we just want "ready!" instead
            ["ready", "dojo/domReady"]
        ],
        // Get "fresh" resources
        cacheBust: true
    };
</script>

<script type="text/javascript" src="<%=path%>/<%=resPath%>/third/dojo/dojo/dojo.js"></script>

<script type="text/javascript">
	joy = {};
	joy.require = require;
	joy.ContextPath = "<%=path%>";
	joy.resPath = "<%=resPath%>";
	joy.BasePath = joy.ContextPath  + "/" + joy.resPath;
	
	require(["joyDojo/initializer"], function() {
		dojo.doc.body.setAttribute("class", "<%= JoyPropeties.DOJO_THEME%>");
	});
</script>

<style type="text/css">
	@import "<%=path%>/<%=resPath%>/<%=JoyPropeties.DOJO_THEME_PATH%>";
	@import "<%=path%>/<%=resPath%>/third/dojo/dijit/themes/claro/document.css";
<%-- 	@import "<%=request.getContextPath()%>/css/icon.css"; --%>
</style>


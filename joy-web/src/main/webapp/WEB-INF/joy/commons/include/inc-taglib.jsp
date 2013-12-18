<%@ taglib prefix="shiro" uri="/WEB-INF/joy/commons/tld/shiro.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="joyFn" uri="/WEB-INF/joy/commons/tld/joyFn.tld" %>
<%@ taglib prefix="joy" tagdir="/WEB-INF/tags" %>

<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
<c:set var="staticCtx" value="${ctx}/static"/>
<c:set var="joyCtx" value="${ctx}/static/joy"/>
<c:set var="thirdCtx" value="${ctx}/static/3rd"/>
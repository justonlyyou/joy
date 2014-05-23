<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="timeStr" type="java.lang.String" required="true" description="时间值"%>
<%@ attribute name="origFmt" type="java.lang.String" required="false" description="原始的时间格式，默认为yyyyMMddHHmmss"%>
<%@ attribute name="displayFmt" type="java.lang.String" required="false" description="展现的时间格式，默认为yyyy-MM-dd HH:mm:ss"%>

<c:if test='${timeStr!=null && timeStr!=""}'>
	<c:set var="origFmt" value='${origFmt==null ? "yyyyMMddHHmmss" : origFmt}'/>
	<c:set var="displayFmt" value='${displayFmt==null ? "yyyy-MM-dd HH:mm:ss" : displayFmt}'/>
	
	${joyFn:formatDate(timeStr, origFmt, displayFmt)}
</c:if>
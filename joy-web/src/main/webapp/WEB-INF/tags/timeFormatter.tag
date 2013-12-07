<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="timeStr" type="java.lang.String" required="true" description="时间值"%>
<%@ attribute name="realFmt" type="java.lang.String" required="false" description="实际的时间格式，默认为yyyyMMddHHmmss"%>
<%@ attribute name="displayFmt" type="java.lang.String" required="false" description="展现的时间格式，默认为yyyy-MM-dd HH:mm:ss"%>

<c:if test='${timeStr!=null && timeStr!=""}'>
	<c:set var="realFmt" value='${realFmt==null ? "yyyyMMddHHmmss" : realFmt}'/>
	<c:set var="displayFmt" value='${displayFmt==null ? "yyyy-MM-dd HH:mm:ss" : displayFmt}'/>
	
	${joy:formatDate(timeStr, realFmt, displayFmt)}
</c:if>
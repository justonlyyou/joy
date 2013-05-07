<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="dateStr" type="java.lang.String" required="true" description="日期值"%>
<%@ attribute name="inFmt" type="java.lang.String" required="false" description="输入的日期值，默认为yyyyMMddHHmmss"%>
<%@ attribute name="outFmt" type="java.lang.String" required="false" description="输出的日期值，默认为yyyy-MM-dd HH:mm:ss"%>

<c:if test='${dateStr!=null && dateStr!=""}'>
	<c:set var="dateStr" value='${dateStr}'/>
	<c:set var="inFmt" value='${inFmt==null ? "yyyyMMddHHmmss" : inFmt}'/>
	<c:set var="outFmt" value='${outFmt==null ? "yyyy-MM-dd HH:mm:ss" : outFmt}'/>
	
	${joy:formatDate(dateStr, inFmt, outFmt)}
</c:if>
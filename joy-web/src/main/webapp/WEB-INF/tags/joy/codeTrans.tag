<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="code" type="java.lang.String" required="true" description="要翻译的代码"%>
<%@ attribute name="tableId" type="java.lang.String" required="false" description="存放代码的数据库表的ID"%>
<%@ attribute name="enumClass" type="java.lang.String" required="false" description="存放代码的枚举类"%>

<c:set var="code" value="${code}"/>
<c:set var="tableId" value="${tableId}"/>
<c:set var="enumClass" value="${enumClass}"/>

${joyFn:translateCode(code,tableId,enumClass).trans}
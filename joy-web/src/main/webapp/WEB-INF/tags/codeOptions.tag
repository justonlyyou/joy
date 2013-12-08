<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="selectedValue" type="java.lang.String" required="false" description="选中的项的值"%>
<%@ attribute name="showEmptyOption" type="java.lang.String" required="false" description="是否显示空选项"%>
<%@ attribute name="tableId" type="java.lang.String" required="false" description="存放代码的数据库表的ID"%>
<%@ attribute name="enumClass" type="java.lang.String" required="false" description="存放代码的枚举类"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="选项样式"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="选项样式类"%>

<c:set var="tableId" value="${tableId}"/>
<c:set var="enumClass" value="${enumClass}"/>

<c:if test="${showEmptyOption == null || showEmptyOption}">
	<option value="" class="joy-select-empty-option">
			--&nbsp;请选择&nbsp;--
	</option>
</c:if>
<c:forEach items="${joy:getAllCodeAndTrans(tableId,enumClass)}" var="entry">
	<option value="${entry.key}" class="joy-select-option ${cssClass}" style="${cssStyle}" 
		<c:if test="${selectedValue == entry.key}"> selected</c:if>>${entry.value.trans}</option>
</c:forEach>

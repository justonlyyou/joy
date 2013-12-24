<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="path" type="java.lang.String" required="false" description="用于回填的属性名"%>
<%@ attribute name="emptyOptionText" type="java.lang.String" required="false" description="空选项文本"%>
<%@ attribute name="showEmptyOption" type="java.lang.Boolean" required="false" description="是否显示空选项"%>
<%@ attribute name="tableId" type="java.lang.String" required="false" description="存放代码的数据库表的ID"%>
<%@ attribute name="enumClass" type="java.lang.String" required="false" description="存放代码的枚举类"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="样式"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="样式类"%>
<%@ attribute name="optionCssStyle" type="java.lang.String" required="false" description="选项样式"%>
<%@ attribute name="optionCssClass" type="java.lang.String" required="false" description="选项样式类"%>
<%@ attribute name="joyProps" type="java.lang.String" required="false" description="joy扩展属性json串"%>

<c:set var="tableId" value="${tableId}"/>
<c:set var="enumClass" value="${enumClass}"/>

<form:select path="${path}" cssClass="${cssClass}" cssStyle="${cssStyle}" data-joy-props="operator:'=',${joyProps}">
	<c:if test="${emptyOptionText != null}">
		<form:option value="" class="joy-select-empty-option">
				--&nbsp;${emptyOptionText}&nbsp;--
		</form:option>
	</c:if>
	<c:if test="${emptyOptionText == null}">
		<c:if test="${showEmptyOption == null || showEmptyOption}">
			<form:option value="" class="joy-select-empty-option">
				--&nbsp;${joyFn:getCodeCategory(tableId)}&nbsp;--
			</form:option>
		</c:if>
	</c:if>
	<c:forEach items="${joyFn:getAllCodeAndTrans(tableId,enumClass)}" var="entry">
		<form:option value="${entry.key}" cssClass="joy-select-option ${optionCssClass}" cssStyle="${optionCssStyle}">
			${entry.value.trans}
		</form:option>
	</c:forEach>
</form:select>
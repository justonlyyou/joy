<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="property" type="java.lang.String" required="true" description="时间属性"%>
<%@ attribute name="displayFmt" type="java.lang.String" required="false" description="时间展现格式，默认为yyyy-MM-dd HH:mm:ss"%>
<%@ attribute name="origFmt" type="java.lang.String" required="false" description="时间实际格式，默认为yyyyMMddHHmmss"%>
<%@ attribute name="firstDayOfWeek" type="java.lang.Integer" required="false" description="每周第一天，默认为1(周一)"%>
<%@ attribute name="autoUpdateOnChanged" type="java.lang.String" required="false" description="值改变时自动更新，默认为true"%>
<%@ attribute name="placeholder" type="java.lang.String" required="false" description="提示占位串"%>

<c:set var="time" value='${fn:replace(requestScope[property], "SSS", "000")}'></c:set>
<c:set var="displayFmt" value='${displayFmt==null ? "yyyy-MM-dd HH:mm:ss" : displayFmt}'></c:set>
<c:set var="origFmt" value='${origFmt==null ? "yyyyMMddHHmmss" : origFmt}'></c:set>
<c:set var="firstDayOfWeek" value='${firstDayOfWeek==null ? 1 : firstDayOfWeek}'></c:set>
<c:set var="autoUpdateOnChanged" value='${autoUpdateOnChanged==null ? "true" : autoUpdateOnChanged}'></c:set>

<input type="hidden" id="_joy_id__time_${property}" name="${property}" value="${time}">
<input class="Wdate form-control joy-date-picker" value='${joyFn:formatDate(timeStart, origFmt, displayFmt)}' placeholder="${placeholder}" title="${placeholder}"
	onFocus="WdatePicker({dateFmt:'${displayFmt}',realFullFmt:'${fn:replace(origFmt, 'SSS', '000')}',firstDayOfWeek:${firstDayOfWeek},autoUpdateOnChanged:${autoUpdateOnChanged},vel:'_joy_id__time_${timeStartProperty}'})" />

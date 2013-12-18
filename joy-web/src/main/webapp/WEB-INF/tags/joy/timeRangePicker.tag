<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="property" type="java.lang.String" required="true" description="时间属性"%>
<%@ attribute name="displayFmt" type="java.lang.String" required="false" description="时间展现格式，默认为yyyy-MM-dd HH:mm:ss"%>
<%@ attribute name="realFmt" type="java.lang.String" required="false" description="时间实际格式，默认为yyyyMMddHHmmss"%>
<%@ attribute name="firstDayOfWeek" type="java.lang.Integer" required="false" description="每周第一天，默认为1(周一)"%>
<%@ attribute name="autoUpdateOnChanged" type="java.lang.String" required="false" description="值改变时自动更新，默认为true"%>
<%@ attribute name="startPlaceholder" type="java.lang.String" required="false" description="起始值提示占位串"%>
<%@ attribute name="endPlaceholder" type="java.lang.String" required="false" description="结束值提示占位串"%>



<c:set var="timeStartProperty" value="time_start__${property}"></c:set>
<c:set var="timeStart" value='${fn:replace(requestScope[timeStartProperty], "SSS", "000")}'></c:set>
<c:set var="timeEndProperty" value="time_end__${property}"></c:set>
<c:set var="timeEnd" value='${fn:replace(requestScope[timeEndProperty], "SSS", "000")}'></c:set>
<c:set var="displayFmt" value='${displayFmt==null ? "yyyy-MM-dd HH:mm:ss" : displayFmt}'></c:set>
<c:set var="realFmt" value='${realFmt==null ? "yyyyMMddHHmmss" : realFmt}'></c:set>
<c:set var="firstDayOfWeek" value='${firstDayOfWeek==null ? 1 : firstDayOfWeek}'></c:set>
<c:set var="autoUpdateOnChanged" value='${autoUpdateOnChanged==null ? "true" : autoUpdateOnChanged}'></c:set>

<input type="hidden" id="_joy_id__${timeStartProperty}" name="${timeStartProperty}" value='${timeStart}'>
<input type="hidden" id="_joy_id__${timeEndProperty}" name="${timeEndProperty}"  value="${timeEnd}">

<td>
	<input class="Wdate form-control joy-date-picker" value='${joyFn:formatDate(timeStart, realFmt, displayFmt)}' placeholder="${startPlaceholder}" title="${startPlaceholder}"
		onFocus="WdatePicker({dateFmt:'${displayFmt}',realFullFmt:'${fn:replace(realFmt, 'SSS', '000')}',firstDayOfWeek:${firstDayOfWeek},autoUpdateOnChanged:${autoUpdateOnChanged},vel:'_joy_id__${timeStartProperty}'})" />
</td>
<td>
&nbsp;&nbsp;-&nbsp;&nbsp;
</td>
<td>
	<input class="Wdate form-control joy-date-picker" value='${joyFn:formatDate(timeEnd, realFmt, displayFmt)}' placeholder="${endPlaceholder}"  title="${endPlaceholder}"
		onFocus="WdatePicker({dateFmt:'${displayFmt}',realFullFmt:'${fn:replace(realFmt, 'SSS', '000')}',firstDayOfWeek:${firstDayOfWeek},autoUpdateOnChanged:${autoUpdateOnChanged},vel:'_joy_id__${timeEndProperty}'})" />
</td>

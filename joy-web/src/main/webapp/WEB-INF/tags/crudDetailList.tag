<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="showDeleteInfo" type="java.lang.String" required="false" description="是否显示删除信息"%>
<%@ attribute name="modelProperty" type="java.lang.String" required="false" description="模型属性名"%>

<c:set var="m" value='${modelProperty==null ? requestScope["model"] : requestScope[modelProperty]}'/>

<div class="joy-title-bar">管理信息</div>
<table
	class="table table-condensed table-hover table-bordered joy-detail-table">
	<tr>
   		<td>创建用户帐号</td>
   		<td></td>
   		<td>创建用户姓名</td>
   		<td></td>
   	</tr>
   	<tr>
   		<td>创建用户单位</td>
   		<td></td>
   		<td>创建时间</td>
   		<td><tags:timeFormatter timeStr="${m.createTime}" /></td>
   	</tr>
   	<tr>
   		<td>最近修改用户帐号</td>
   		<td></td>
   		<td>最近修改用户姓名</td>
   		<td></td>
   	</tr>
   	<tr>
   		<td>最近修改用户单位</td>
   		<td></td>
   		<td>最近修改时间</td>
   		<td><tags:timeFormatter timeStr="${m.updateTime}" /></td>
   	</tr>
   	<c:if test="${showDeleteInfo}">
	   	<tr>
	   		<td>删除用户帐号</td>
	   		<td></td>
	   		<td>删除用户姓名</td>
	   		<td></td>
	   	</tr>
	   	<tr>
	   		<td>删除用户单位</td>
	   		<td></td>
	   		<td>删除时间</td>
	   		<td><tags:timeFormatter timeStr="${m.deleteTime}" /></td>
	   	</tr>
   	</c:if>
</table>
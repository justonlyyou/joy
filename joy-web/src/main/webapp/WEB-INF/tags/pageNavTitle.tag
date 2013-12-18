<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="cssClass" type="java.lang.String" required="false" description="样式类"%>

<c:set var="menuId" value="${_joy_key__cur_menu_id}"></c:set>

<input id="_joy_id__cur_menu_id" name="_joy_key__cur_menu_id" type="hidden" value="${menuId}" />

<ol class="breadcrumb joy-page-nav-title ${cssClass}">
	<c:forEach items="${joyFn:getMenuPath(menuId)}" var="menu">
		<li <c:if test="${menu.id == menuId}">class="active"</c:if>>
			<b>${menu.text}</b>
		</li>
	</c:forEach>
</ol>
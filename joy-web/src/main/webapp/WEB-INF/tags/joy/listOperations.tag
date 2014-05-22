<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="true" description="行记录的id(响应事件的参数)"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="样式类"%>

<%@ attribute name="detailFunc" type="java.lang.String" required="false" description="详细操作响应事件名称(默认为showDetail)"%>
<%@ attribute name="showDetailOp" type="java.lang.String" required="false" description="显示详情操作(默认显示)"%>
<%@ attribute name="editFunc" type="java.lang.String" required="false" description="编辑操作响应事件名称(默认为editRecord)"%>
<%@ attribute name="showEditOp" type="java.lang.String" required="false" description="显示编辑操作(默认显示)"%>
<%@ attribute name="deleteFunc" type="java.lang.String" required="false" description="删除操作响应事件名称(默认为deleteRecord)"%>
<%@ attribute name="showDeleteOp" type="java.lang.String" required="false" description="显示删除操作(默认显示)"%>

<%@ attribute name="exOp1CssClass" type="java.lang.String" required="false" description="预留操作1样式类(主要用于传图标样式，未指定不会出现该操作)"%>
<%@ attribute name="exOp1Title" type="java.lang.String" required="false" description="预留操作1提示文本"%>
<%@ attribute name="exOp1Func" type="java.lang.String" required="false" description="预留操作1响应事件名称"%>

<%@ attribute name="exOp2CssClass" type="java.lang.String" required="false" description="预留操作2样式类(主要用于传图标样式，未指定不会出现该操作)"%>
<%@ attribute name="exOp2Title" type="java.lang.String" required="false" description="预留操作2提示文本"%>
<%@ attribute name="exOp2Func" type="java.lang.String" required="false" description="预留操作2响应事件名称"%>

<c:set var="detailFunc" value='${detailFunc == null ? "showDetail" : detailFunc}' />
<c:set var="editFunc" value='${editFunc == null ? "editRecord" : editFunc}' />
<c:set var="deleteFunc" value='${deleteFunc == null ? "deleteRecord" : deleteFunc}' />

<div class="joy-list-row-operations ${cssClass}">
	<c:if test="${showDetailOp == null || showDetailOp}">
		<i class="fa fa-list-alt" title="详情" onclick="joy.mgmtPage.${detailFunc}('${id}')" ></i>&nbsp;
	</c:if>
	<c:if test="${showEditOp == null || showEditOp}">
		<i class="fa fa-edit" title="编辑" onclick="joy.mgmtPage.${editFunc}('${id}')"></i>&nbsp;
	</c:if>
	
	<c:if test="${showDeleteOp == null || showDeleteOp}">
		<i class="fa fa-times" title="删除" onclick="joy.mgmtPage.${deleteFunc}('${id}')"></i>&nbsp;
	</c:if>
	<c:if test="${exOp1CssClass != null}">
		<i class="${exOp1CssClass}" title="${exOp1Title}" onclick="joy.mgmtPage.${exOp1Func}('${id}')"></i>&nbsp;
	</c:if>
	<c:if test="${exOp2CssClass != null}">
		<i class="${exOp2CssClass}" title="${exOp2Title}" onclick="joy.mgmtPage.${exOp2Func}('${id}')"></i>&nbsp;
	</c:if>
</div>


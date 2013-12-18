<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="property" type="java.lang.String" required="true" description="列属性名"%>
<%@ attribute name="defaultOrder" type="java.lang.String" required="false" description="默认排序"%>
<%@ attribute name="columnName" type="java.lang.String" required="true" description="列名"%>
<%@ attribute name="columnNameCssStyle" type="java.lang.String" required="false" description="列名样式"%>
<%@ attribute name="columnNameCssClass" type="java.lang.String" required="false" description="列名样式类"%>

<input id="_joy_id__order_by_${property}" name="_joy_key__order_by_${property}" type="hidden" value="${property}" />
<input id="_joy_id__order_value_${property}" name="_joy_key__order_value_${property}" type="hidden" value="${pageStore.queryLogics.orderMap[property]}" />
<input id="_joy_id__order_default_${property}" name="_joy_key__order_default_${property}" type="hidden" value="${defaultOrder}" />

<span class="joy-table-th-text ${columnNameCssClass}" style="${columnNameCssStyle}">${columnName}</span>
&nbsp;
<label id="_joy_id__order_column_${property}" class="joy-table-th-sort"></label>

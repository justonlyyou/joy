<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>

<%@ attribute name="id" type="java.lang.String" required="false" description="分页组件ID"%>
<%@ attribute name="simpleMode" type="java.lang.String" required="false" description="简单模式，只显示页码"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="样式类"%>

<input id="_joy_id__paging_pageNumber" name="_joy_key__paging_pageNumber" type="hidden" value="${pageStore.paging.pageNumber}"/>
<input id="_joy_id__paging_pageSize" name="_joy_key__paging_pageSize" type="hidden" value="${pageStore.paging.pageSize}"/>

<div class="joy-pagination ${cssClass}">
	<ul class="pagination">
		<c:if test="${pageStore.paging.pageNumber == pageStore.paging.first}">
			<li class="disabled"><a href="#">« 上一页</a></li>
		</c:if>
		<c:if test="${pageStore.paging.firstPage == false}">
			<li><a href="javascript:gotoPage(${pageStore.paging.prePage}, ${pageStore.paging.pageSize});">« 上一页</a></li>
		</c:if>
		
		<c:if test="${pageStore.paging.midBeginPage > pageStore.paging.first}">
			<c:set var="end" value="${pageStore.paging.midBeginPage}"/>
			<c:if test="${pageStore.paging.first + pageStore.paging.slider < pageStore.paging.midBeginPage}">
				<c:set var="end" value="${pageStore.paging.first + pageStore.paging.slider}"/>
			</c:if>
			<c:set var="index" />
			<c:forEach var="i" begin="${pageStore.paging.first}" end="${end-1}" step="1">
				<li><a href="javascript:gotoPage(${i}, ${pageStore.paging.pageSize});">${i+1-pageStore.paging.first}</a></li>
				<c:set var="index" value="${i}"/>
			</c:forEach>
			<c:if test="${index < pageStore.paging.midBeginPage}">
				<li class="disabled"><a href="#">...</a></li>
			</c:if>
		</c:if>
		
		<c:if test="${pageStore.paging.totalCount == null}">
			<li class="disabled"><a href="#">0</a></li>
		</c:if>
		<c:forEach var="i" begin="${pageStore.paging.midBeginPage}" end="${pageStore.paging.midEndPage}" step="1">
			<c:if test="${i == pageStore.paging.pageNumber}">
				<li class="active"><a href="#">${i+1-pageStore.paging.first}</a></li>
			</c:if>
			<c:if test="${i != pageStore.paging.pageNumber && pageStore.paging.totalCount > 0}">
				<li><a href="javascript:gotoPage(${i}, ${pageStore.paging.pageSize});">${i+1-pageStore.paging.first}</a></li>
			</c:if>
		</c:forEach>

		<c:set var="endPageIndex" value="${pageStore.paging.midEndPage}"/>
		<c:if test="${pageStore.paging.last - pageStore.paging.midEndPage > pageStore.paging.slider}">
			<li class="disabled"><a href="#">...</a></li>
			<c:set var="endPageIndex" value="${pageStore.paging.last - pageStore.paging.slider}"/>
		</c:if>

		<c:forEach var="i" begin="${endPageIndex+1}" end="${pageStore.paging.last}" step="1">
			<li><a href="javascript:gotoPage(${i}, ${pageStore.paging.pageSize});">${i+1-pageStore.paging.first}</a></li>
		</c:forEach>
		
		<c:if test="${pageStore.paging.pageNumber == pageStore.paging.last}">
			<li class="disabled"><a href="#">下一页 »</a></li>
		</c:if>
		<c:if test="${pageStore.paging.pageNumber != pageStore.paging.last}">
			<li><a href="javascript:gotoPage(${pageStore.paging.nextPage}, ${pageStore.paging.pageSize});">下一页 »</a></li>
		</c:if>
	</ul>
	
	<c:if test="${simpleMode == null || simpleMode == false}">
		<ul class="pagination">
			<li class="disabled">
				    <a href="#" style="border:0;">
					    当前第
						<input <c:if test="${pageStore.paging.totalCount == 0 || pageStore.paging.totalCount == null}">disabled</c:if> 
							type="text" value="${pageStore.paging.pageNumber}" 
							style="width:21px;height:20px;padding:2px;display:inline;text-align:center" class="form-control"
							onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13) gotoPage(this.value,${pageStore.paging.pageSize});"
							onclick="this.select();">
						页，
						每页显示
						<input <c:if test="${pageStore.paging.totalCount == 0 || pageStore.paging.totalCount == null}">disabled</c:if>
							type="text" value="${pageStore.paging.pageSize}" 
							style="width:21px;height:20px;padding:2px;display:inline;text-align:center" class='form-control' 
							onkeypress="var e=window.event||this;var c=e.keyCode||e.which;if(c==13) gotoPage(${pageStore.paging.pageNumber},this.value);"
							onclick="this.select();">
						条，共 ${pageStore.paging.totalCount} 条
					</a>
			</li>
		</ul>
	</c:if>
	
</div>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="type" type="java.lang.String" required="false" description="页面类型，取值mgmt、edit、detail"%>
<%@ attribute name="res" type="java.lang.String" required="false" description="页面资源"%>

<c:choose>
    <c:when test="${res == null}">
        <c:choose>
            <c:when test="${type == 'mgmt'}">
                <script type="text/javascript">
                    curl(['joy/commons/BaseMgmtPage'], function(Page) {
                        page = new Page();
                    });
                </script>
            </c:when>
            <c:when test="${type == 'edit'}">
                <script type="text/javascript">
                    curl(['joy/commons/BaseEditPage'], function(Page) {
                        page = new Page();
                    });
                </script>
            </c:when>
            <c:when test="${type == 'detail'}">
                <script type="text/javascript">
                    curl(['joy/commons/BaseDetailPage'], function(Page) {
                        page = new Page();
                    });
                </script>
            </c:when>
            <c:otherwise>
                错误：res属性的值为空时，必须指定type属性的值！
            </c:otherwise>
        </c:choose>
    </c:when>
    <c:otherwise>
        <script type="text/javascript">
            curl(['${res}'], function(Page) {
                page = new Page();
            });
        </script>
    </c:otherwise>
</c:choose>
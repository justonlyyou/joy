<%@ include file="/WEB-INF/joy/commons/include/inc-taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="${param.author}">
    <title></title>
    <jsp:include page='/WEB-INF/joy/${joyFn:getJoyProperty("page.theme")}/include/inc-style.jsp'></jsp:include>
</head>

<body>
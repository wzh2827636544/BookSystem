<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" width="230px" height="80px">
    <span class="wel_word">订单详情</span>
    <div>
            <span>欢迎<span class="um_span">${user.username}</span>光临书城</span>
            <a href="OrderServlet?action=listOrder">我的订单</a>
        <a href="index.jsp">返回</a>
    </div>
</div>

<div id="main">

    <table style="width: 800px;height: 300px">
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
            </tr>
        <c:forEach items="${orderItems}" var="detail">
            <tr>
                <td>${detail.name}</td>
                <td>${detail.count}</td>
                <td>${detail.price}</td>
                <td>${detail.totalPrice}</td>
            </tr>
        </c:forEach>

    </table>
</div>
<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>
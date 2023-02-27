<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
%>
<jsp:include page="header.jsp">
    <jsp:param name="msg" value="图书管理系统"/>
</jsp:include>
<div id="main" style="height: 800px;">
    <table>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_add.jsp">添加图书</a></td>
        </tr>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td>封面</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><img src="${book.imgPath}" height="100px"></td>
                <td><a href="BookServlet?action=queryById&id=${book.id}&pageNo=${page.pageNo}">修改</a></td>
                <td><a href="javascript:void(0)" onclick="mydelete('${book.id}',this)">删除</a></td>
            </tr>
        </c:forEach>
    </table>

    <jsp:include page="/pages/common/page.jsp"></jsp:include>
<%@include file="/pages/common/bottom.jsp" %>
</body>
</html>
<script>
    function mydelete(id, aobj) {
        var name = aobj.parentElement.parentElement.cells[0].innerHTML;
        if (window.confirm("你真的不要<" + name + ">了吗？真的吗？真的吗？")) {
            window.location.href = "BookServlet?action=delete&id=" + id+"&pageNo=${page.pageNo}";
        }
    }
</script>
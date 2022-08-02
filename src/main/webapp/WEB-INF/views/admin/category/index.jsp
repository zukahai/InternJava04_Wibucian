<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Trang chủ</title>
</head>
<body>
tablelist
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>action</td>
    </tr>
    <c:forEach var="item" items="${categories}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/category/edit/${item.id}">Edit</a>
                <a href="${pageContext.request.contextPath}/admin/category/delete/${item.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
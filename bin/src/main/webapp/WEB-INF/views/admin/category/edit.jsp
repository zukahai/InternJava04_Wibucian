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
<form method="post" action="${pageContext.request.contextPath}/admin/category/update">
    <input type="text" name="id" readonly placeholder="id" value="<c:out value="${category.id}" > </c:out> ">
    <input type="text" name="name" placeholder="name" value="<c:out value="${category.name}" > </c:out> ">
    <input type="submit" value="Cập nhật">
</form>
</table>
</body>
</html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "java.util.Date" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>Add Employee</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
          crossorigin="anonymous" />
</head>
<body>
<%
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  String date = sdf.format(new Date());
%>
<div class="container">

    <h3>Update Employee</h3>
    <hr/>

    <form action="/staff/update" method="POST">

        <input readonly type="text" name = "idEmployee" value="${employee.idEmployee}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "name" value="${employee.name}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "email" value="${employee.email}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "address" value="${employee.address}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "phoneNumber" value="${employee.phoneNumber}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "gender" value="${employee.gender}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "srcEmployee" value="${employee.srcEmployee}" class="form-control col-4 mb-4" placeholder="" />

        <input type="text" name = "maritalStatus" value="${employee.maritalStatus}" class="form-control col-4 mb-4" placeholder="" />

        <input type="number" name = "coefficientsSalary" value="${employee.coefficientsSalary}" class="form-control col-4 mb-4" placeholder="" />

        <input type="date" name = "birthDay" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${employee.birthDay}" />" class="form-control col-4 mb-4" placeholder="" />

        <button type="submit" class="btn btn-primary col-2">Save</button>
        <input type="hidden"/>
    </form>
    <hr/>
    <a th:href="@{/list}">Back to list</a>
</div>

</body>
</html>
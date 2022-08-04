<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8"/>
    <title>Create New Product</title>
</head>
<body>
<div align="center">
    <h1>Create New Product</h1>
    <br/>
    <form action="/admin/typeTable"
          method="post">

        <table border="0" cellpadding="10">
            <tr>
                <td>Tên sản phẩm:</td>
                <td><input type="text" name="typeName"/></td>
            </tr>
            <tr>
                <td>Thể loại:</td>
                <td><input type="text" name="price"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">Save</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
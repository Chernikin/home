<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Department Page</title>
</head>
<body>
<form action="create-department-action" method="post">
    <table>
        <tr>
            <td>Department name</td>
            <td><input type="text" name="departmentName"></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td><input type="text" name="comments"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="submit" value="Create">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

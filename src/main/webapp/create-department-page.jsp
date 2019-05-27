<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <td><input value="${incorrectDepartmentData.departmentName}" type="text" name="departmentName"></td>
            <td>
                <c:out value="${errors.departmentName}"> </c:out></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td><input value="${incorrectDepartmentData.comments}" type="text" name="comments"></td>
            <td>
                <c:out value="${errors.comments}"> </c:out></td>
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

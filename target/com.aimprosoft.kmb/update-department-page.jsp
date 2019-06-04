<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Department Page</title>
</head>
<body>
<h3>Updating department with id: ${department.id}</h3>
<form action="update-department" method="get">
    <table>
        <tr>
            <td>Department name</td>
            <td><input type="text" name="departmentName" value="${department.departmentName}"></td>
            <td><c:out value="${errors.departmentName}"> </c:out></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td><input type="text" name="comments" value="${department.comments}"></td>
            <td><c:out value="${errors.comments}"> </c:out></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Update</button>
                <input type="hidden" name="departmentId" value="${department.id}">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@page errorPage="error.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Department Page</title>
</head>
<body>
<form action="add-department" method="post">
    <table>
        <tr>
            <td>Department name</td>
            <td><input value="${errors.incorrectDepartmentData.departmentName}" type="text" name="departmentName"></td>
            <td><c:out value="${errors.departmentName}"> </c:out></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td><input value="${errors.incorrectDepartmentData.comments}" type="text" name="comments"></td>
            <td><c:out value="${errors.comments}"> </c:out></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="submit" value="Create">
            </td>
        </tr>
        <tr>
            <td>
                <button type="button" name="back" onclick="history.back()"><b>back</b></button>
            </td>
        </tr>
    </table>
</form>
<button><a href="/">Main page</a></button>
</body>
</html>

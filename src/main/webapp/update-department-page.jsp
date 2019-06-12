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
<form action="update-department" method="post">
    <table>
        <tr>
            <td>Department name</td>
            <td><input type="text" name="departmentName"
                       value="<c:choose><c:when test="${department != null}">${department.departmentName}</c:when>
<c:otherwise>${errors.incorrectDepartmentData.departmentName}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.departmentName}"> </c:out></td>
        </tr>
        <tr>
            <td>Comments</td>
            <td><input type="text" name="comments"
                       value="<c:choose><c:when test="${department != null}">${department.comments}</c:when>
<c:otherwise>${errors.incorrectDepartmentData.comments}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.comments}"> </c:out></td>
        </tr>
        <tr>
            <td>
                <button type="submit">Update</button>
                <input type="hidden" name="departmentId" value="${departmentId}">
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

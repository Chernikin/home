<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee Page</title>
</head>
<body>
<form action="create-employee-action" method="post">
    <table>
        <tr>
            <td>First name</td>
            <td><input value="${incorrectEmployeeData.firstName}" type="text" name="firstName"></td>
            <td><c:out value="${errors.firstName}"> </c:out></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input value="${incorrectEmployeeData.lastName}" type="text" name="lastName"></td>
            <td><c:out value="${errors.lastName}"> </c:out></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input value="${incorrectEmployeeData.email}" type="text" name="email"></td>
            <td><c:out value="${errors.email}"> </c:out></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input value="${incorrectEmployeeData.age}" type="text" name="age"></td>
            <td><c:out value="${errors.age}"> </c:out></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input value="${incorrectEmployeeData.phoneNumber}" type="text" name="phoneNumber"></td>
            <td><c:out value="${errors.phoneNumber}"> </c:out></td>
        </tr>
        <tr>
            <td>Employment date</td>
            <td><input value="${incorrectEmployeeData.employmentDate}" type="date" name="employmentDate"></td>
            <td><c:out value="${errors.employmentDate}"> </c:out></td>
        </tr>
        <tr>
            <td>Department id</td>
            <td>
                <input disabled type="text" name="departmentId" value="${departmentId}">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Create</button>
                <input type="hidden" value="${departmentId}" name="departmentId">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee Page</title>
</head>
<body>
<h3>Updating employee with id: ${employee.id}</h3>
<form action="update-employee-action" method="get">
    <table>
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName" value="${employee.firstName}"></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName" value="${employee.lastName}"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${employee.email}"></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age" value="${employee.age}"></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input type="text" name="phoneNumber" value="${employee.phoneNumber}"></td>
        </tr>
        <tr>
            <td>Employment date</td>
            <td><input type="date" name="employmentDate" value="${employee.employmentDate}"></td>
        </tr>
        <tr>
            <td>Department id</td>
            <td>
                <select name="departmentId">
                    <c:forEach items="${allDepartments}" var="department">
                        <option value="${department.id}" selected>${department.departmentName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Update</button>
                <input type="hidden" name="employeeId" value="${employee.id}">
            </td>
        </tr>
    </table>
</form>
</body>
</html>

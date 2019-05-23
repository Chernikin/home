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
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age"></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input type="text" name="phoneNumber"></td>
        </tr>
        <tr>
            <td>Employment date</td>
            <td><input type="date" name="employmentDate"></td>
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

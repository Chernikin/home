<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 23.05.19
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page errorPage="error.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Departments Page</title>
</head>
<body>
<h3>${successMessage}</h3>
<h3>${errorMessage}</h3>
<div>
    <table cellpadding="5" cellspacing="5" border="1">
        <tr>
            <td colspan="6" align="center"><b>All Departments</b></td>
        </tr>
        <tr>
            <td colspan="6">
                <button><a href="create-department-page.jsp"><b>Create Department</b></a></button>
            </td>
        </tr>
        <tr>
            <td><b>Id</b></td>
            <td><b>Department name</b></td>
            <td><b>Comments</b></td>
            <td><b>Update</b></td>
            <td><b>Delete</b></td>
            <td><b>List of employees</b></td>
        </tr>
        <c:forEach items="${allDepartments}" var="department">
            <tr>
                <td>${department.id}</td>
                <td>${department.departmentName}</td>
                <td>${department.comments}</td>
                <td>
                    <form action="link-to-update-department" method="post">
                        <button type="submit">update department</button>
                        <input type="hidden" value="${department.id}" name="departmentId">
                    </form>
                </td>
                <td>
                    <form action="delete-department" method="get">
                        <button type="submit">delete department</button>
                        <input type="hidden" value="${department.id}" name="departmentId">
                    </form>
                </td>
                <td>
                    <form action="manage-employees?departmentId=${departmentId}" method="get">
                        <button type="submit">list employees</button>
                        <input type="hidden" value="${department.id}" name="departmentId">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<form action="update-employee" method="post">
    <table>
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName"
                       value="<c:choose><c:when test="${employee != null}">${employee.firstName}</c:when>
<c:otherwise>${errors.incorrectEmployeeData.firstName}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.firstName}"> </c:out></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName"
                       value="<c:choose><c:when test="${employee != null}">${employee.lastName}</c:when>
<c:otherwise>${errors.incorrectEmployeeData.lastName}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.lastName}"> </c:out></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"
                       value="<c:choose><c:when test="${employee != null}">${employee.email}</c:when>
<c:otherwise>${errors.incorrectEmployeeData.email}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.email}"> </c:out></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="text" name="age"
                       value="<c:choose><c:when test="${employee != null}">${employee.age}</c:when>
<c:otherwise>${errors.incorrectEmployeeData.age}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.age}"> </c:out></td>
        </tr>
        <tr>
            <td>Phone number</td>
            <td><input type="text" name="phoneNumber"
                       value="<c:choose><c:when test="${employee != null}">${employee.phoneNumber}</c:when>
<c:otherwise>${errors.incorrectEmployeeData.phoneNumber}</c:otherwise></c:choose>"></td>
            <td><c:out value="${errors.phoneNumber}"> </c:out></td>
        </tr>
        <tr>
            <td>Employment date</td>
            <td><input type="date" name="employmentDate"
            <c:choose>
                       <c:when test="${employee != null}">value="<fmt:formatDate value="${employee.employmentDate}" pattern="yyyy-MM-dd"/>"
            </c:when>
                       <c:otherwise>value="<fmt:formatDate value="${errors.incorrectEmployeeData.employmentDate}" pattern="yyyy-MM-dd"/>"
            </c:otherwise>
            </c:choose>>
            </td>
            <td><c:out value="${errors.employmentDate}"> </c:out></td>
        </tr>
        <tr>
            <td>Department id</td>
            <td>
                <select name="newDepartmentId">
                    <c:forEach items="${allDepartments}" var="department">
                        <option value="${department.id}"
                                <c:if test="${department.id eq employee.department.id or department.id eq errors.incorrectEmployeeData.department.id}">
                                    selected="selected"</c:if>>${department.departmentName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Update</button>
                <input type="hidden" name="employeeId" value="${employeeId}">
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

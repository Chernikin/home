<%@ page import="com.aimprosoft.kmb.exceptions.ServiceException" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.05.19
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

<h3>Sorry, an error is happened!</h3>
Error:
<div><%=exception.getMessage()%>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.05.19
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>

<h3>Sorry, an error is happened!</h3>
<div>
    Error: ${exception.getMessage()} Reason: ${exception.getCause()}
</div>

<button type="button" name="back" onclick="history.back()"><b>back</b></button>
<button><a href="/">Main page</a></button>
</body>
</html>

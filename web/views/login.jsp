<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <p><strong>Project By: </strong> Tom Grozev</p>

    <h2>Login</h2>

    <div>
        <c:if test="${hasErrors}">
            <p>There were some errors: </p>
            <c:forEach var="error" items="${errors}">
                <p>${error.getMessage()}</p>
            </c:forEach>
        </c:if>

        <c:if test="${param.error}">
            <p>${param.error}</p>
        </c:if>
    </div>

    <c:if test="${param.registered == 'true'}">
        <p>You have registered successfully, please login with your new details.</p>
    </c:if>

    <c:if test="${param.expired == 'true'}">
        <p>Login expired, please login again.</p>
    </c:if>

    <form action="login" method="post">
        Username: <input type="text" name="username">
        <br>
        Password: <input type="password" name="password">
        <br><br>
        <input type="submit" value="Login">
    </form>
    <br>
    <p>-- OR --</p>
    <a href="register">Register</a>
</body>
</html>

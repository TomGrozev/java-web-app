<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Logout</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <p><strong>Project By: </strong> Tom Grozev</p>

    <h2>Are you sure you want to logout?</h2>

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

    <form action="logout" method="post">
        <input type="submit" value="Yes">
        <a href="topics">No, go back</a>
    </form>
</body>
</html>

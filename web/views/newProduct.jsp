<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>New Product</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <p><strong>Project By: </strong> Tom Grozev</p>

        <h2>The Python Discussion Forum</h2>

        <c:choose>
            <c:when test="${login_session != null}">
                <a href="logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="login">Login</a>
            </c:otherwise>
        </c:choose>

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

        <h3>New product</h3>
        <a href="products">All product for sale</a>

        <form action="newProduct" method="POST">
            <div>
                <label for="title">Title</label>
                <input type="text" id="title" name="title" />
            </div>

            <div>
                <label for="description">Description</label>
                <textarea cols="60" rows="10" id="description" name="description"></textarea>
            </div>

            <div>
                <label for="price">Price</label>
                <input type="number" id="price" min="0" step="any" name="price" />
            </div>
            <br>
            <input type="submit" value="Sell Product">
        </form>
    </body>
</html>

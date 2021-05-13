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
        <title>Products</title>
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
        <a href="topics">Go to Topics</a>
        <a href="cart">My Cart</a>

        <h4>Products for Sale</h4>

        <a href="newProduct">Sell a Product</a>
        <table>
            <thead>
                <tr>
                    <th>Product</th>
                    <th>Seller</th>
                    <th>Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td><a href="products?id=${product.id}">${product.title}</a></td>
                    <td>${product.getUser().getUsername()}</td>
                    <td>${product.getFormattedPrice()}</td>
                    <td>
                        <form action="cart" method="post">
                            <input type="hidden" name="id" value="${product.id}">
                            <input type="submit" value="Add to Cart">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </body>
</html>

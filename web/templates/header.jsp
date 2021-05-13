<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/14/21
  Time: 3:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
<a href="products">Products</a>
<a href="topics">Topics</a>
<a href="cart">My Cart</a>
<a href="profile">Profile</a>

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

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/14/21
  Time: 3:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="banner"><strong>Project By: </strong> Tom Grozev</div>

<div class="header container">
    <h2>The Python Discussion Forum</h2>

    <div class="nav">
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
    </div>

    <div>
        <c:if test="${hasErrors}">
            <br><br>
            <c:forEach var="error" items="${errors}">
                <div class="toast toast--yellow">
                    <div class="toast__icon">
                        <svg version="1.1" class="toast__svg" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 301.691 301.691" style="enable-background:new 0 0 301.691 301.691;" xml:space="preserve">
<g>
    <polygon points="119.151,0 129.6,218.406 172.06,218.406 182.54,0  "></polygon>
    <rect x="130.563" y="261.168" width="40.525" height="40.523"></rect>
</g>
    </svg>
                    </div>
                    <div class="toast__content">
                        <p class="toast__type">Error</p>
                        <p class="toast__message">${error.getMessage()}</p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>

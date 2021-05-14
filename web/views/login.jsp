<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>Login</h2>

<c:if test="${param.registered == 'true'}">
    <p>You have registered successfully, please login with your new details.</p>
</c:if>

<form action="login" method="post">
    <div class="input-group">
        Username: <input type="text" name="username">
    </div>
    <div class="input-group">
        Password: <input type="password" name="password">
    </div>
    <br><br>
    <c:if test="${redirect_after != null}">
        <input type="hidden" name="redirect_after" value="${redirect_after}">
    </c:if>
    <input type="submit" value="Login" class="btn">
</form>
<h4>-- OR --</h4>
<a class="btn" href="register">Register</a>

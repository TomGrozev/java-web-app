<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h2>Register</h2>

<form action="register" method="post">
    <div class="input-group">
        Username: <input type="text" name="username">
    </div>
    <div class="input-group">
        Password: <input type="password" name="password">
    </div>
    <br><br>
    <input type="submit" value="Register" class="btn">
</form>
<br>
<h4>-- OR --</h4>
<a class="btn" href="login">Login</a>

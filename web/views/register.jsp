<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="register" method="post">
    Username: <input type="text" name="username">
    <br>
    Password: <input type="password" name="password">
    <br><br>
    <input type="submit" value="Register">
</form>
<br>
<p>-- OR --</p>
<a href="login">Login</a>

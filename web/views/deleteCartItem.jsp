<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Remove <strong>${product.title}</strong> from cart?</h3>
<a class="btn" href="cart">Cart</a>

<form action="deleteCartItem" method="POST">
    <input type="hidden" name="id" value="${product.id}">
    <p>Are you sure you want to remove this product from your cart?</p>
    <input class="btn" type="submit" value="Yes, Remove It">
    <a class="btn" href="cart">No, take me back!</a>
</form>

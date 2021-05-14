<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>${product.getTitle()}</h3>
<br>
<a class="btn" href="products">All products</a>
<br><br><br>
<c:if test="${product.getUser().getUsername() == login_session.getUsername()}">
    <a class="btn" href="editProduct?id=${product.getId()}">Edit Product</a>
    <a class="btn" href="deleteProduct?id=${product.getId()}">Delete Product</a>
</c:if>

<p>Author: ${product.getUser().getUsername()}</p>
<p>Price: ${product.getFormattedPrice()}</p>
<p>Description: ${product.getDescription()}</p>

<br><br>
<form action="cart" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <input class="btn" type="submit" value="Add to Cart">
</form>

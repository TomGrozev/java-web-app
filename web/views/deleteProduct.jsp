<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Delete <strong>${product.title}</strong></h3>
<a href="products">All products</a>

<form action="deleteProduct" method="POST">
    <input type="hidden" name="id" value="${product.id}">
    <p>Are you sure you want to delete this product?</p>
    <input type="submit" value="Yes, Delete It">
    <a href="products">No, take me back!</a>
</form>

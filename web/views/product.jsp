<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>${product.getTitle()}</h3>
<a href="products">All products</a>

<c:if test="${product.getUser().getUsername() == login_session.getUsername()}">
    <a href="editProduct?id=${product.getId()}">Edit Product</a>
    <a href="deleteProduct?id=${product.getId()}">Delete Product</a>
</c:if>

<p>Author: ${product.getUser().getUsername()}</p>
<p>Price: ${product.getFormattedPrice()}</p>
<p>Description: ${product.getDescription()}</p>

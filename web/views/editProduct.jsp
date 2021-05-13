<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Edit product</h3>
<a href="products">All product for sale</a>

<form action="editProduct" method="POST">
    <input type="hidden" name="id" value="${product.getId()}">
    <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="${product.getTitle()}" />
    </div>

    <div>
        <label for="description">Description</label>
        <textarea cols="60" rows="10" id="description" name="description">${product.getDescription()}</textarea>
    </div>

    <div>
        <label for="price">Price</label>
        <input type="number" id="price" name="price" min="0" step="any" value="${product.getPrice()}" />
    </div>
    <br>
    <input type="submit" value="Edit Product">
</form>

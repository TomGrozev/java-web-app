<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>New product</h3>
<a href="products">All product for sale</a>

<form action="newProduct" method="POST">
    <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" />
    </div>

    <div>
        <label for="description">Description</label>
        <textarea cols="60" rows="10" id="description" name="description"></textarea>
    </div>

    <div>
        <label for="price">Price</label>
        <input type="number" id="price" min="0" step="any" name="price" />
    </div>
    <br>
    <input type="submit" value="Sell Product">
</form>

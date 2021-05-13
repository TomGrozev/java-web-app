<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>Products for Sale</h4>

<a href="newProduct">Sell a Product</a>
<table>
    <thead>
        <tr>
            <th>Product</th>
            <th>Seller</th>
            <th>Price</th>
            <th></th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><a href="products?id=${product.id}">${product.title}</a></td>
            <td>${product.getUser().getUsername()}</td>
            <td>${product.getFormattedPrice()}</td>
            <td>
                <form action="cart" method="post">
                    <input type="hidden" name="id" value="${product.id}">
                    <input type="submit" value="Add to Cart">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

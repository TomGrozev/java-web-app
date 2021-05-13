<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table>
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${cart.getProducts()}">
        <tr>
            <td>${product.getTitle()}</td>
            <td>${product.getFormattedPrice()}</td>
            <td>
                <form action="deleteCartItem" method="post">
                    <input type="hidden" name="id" value="${product.getId()}">
                    <input type="submit" value="Remove">
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Hi, ${user.getUsername()}.</h3>

<h4>My Products</h4>
<table>
    <thead>
    <tr>
        <th>Product</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td><a href="products?id=${product.getId()}">${product.getTitle()}</a></td>
            <td>${product.getFormattedPrice()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h4>Engaged Topics</h4>
<table>
    <thead>
    <tr>
        <th>Title</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="topic" items="${topics}">
        <tr>
            <td><a href="topics?id=${topic.getId()}">${topic.getTitle()}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

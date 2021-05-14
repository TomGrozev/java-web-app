<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h4>Active Topics</h4>

<a class="btn" href="newTopic">New Topic</a>
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>User</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="topic" items="${topics}">
        <tr>
            <td><a href="topics?id=${topic.id}">${topic.title}</a></td>
            <td>${topic.user.getUsername()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

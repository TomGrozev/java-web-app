<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Delete <strong>${topic.title}</strong></h3>
<a href="topics">All topics</a>

<form action="deleteTopic" method="POST">
    <input type="hidden" name="id" value="${topic.id}">
    <p>Are you sure you want to delete this topic?</p>
    <input type="submit" value="Yes, Delete It">
    <a href="topics">No, take me back!</a>
</form>

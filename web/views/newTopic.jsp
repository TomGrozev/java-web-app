<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>New topic</h3>
<a href="topics">All topics</a>

<form action="newTopic" method="POST">
    <div>
        <label for="title">Title</label>
        <input type="text" id="title" name="title" />
    </div>

    <div>
        <label for="content">Content</label>
        <textarea cols="60" rows="10" id="content" name="content"></textarea>
    </div>
    <br>
    <input type="submit" value="Post Topic">
</form>

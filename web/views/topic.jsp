<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>${topic.title}</h3>
<a href="topics">All topics</a>

<c:if test="${topic.getUser().getUsername() == login_session.getUsername()}">
    <a href="editTopic?id=${topic.getId()}">Edit Topic</a>
    <a href="deleteTopic?id=${topic.getId()}">Delete Topic</a>
</c:if>

<p>${topic.getUser().getUsername()}: ${topic.getContent()}</p>

<c:if test="${topic.isEdited()}">
    <span>Edited</span>
</c:if>

<h4>Feedback:</h4>

<c:forEach var="feedback" items="${topic.getFeedback()}">
    <p>${feedback.user.getUsername()}: ${feedback.getContent()}</p>
</c:forEach>

<h5>Leave feedback: </h5>

<form action="feedback" method="POST">
    <input type="hidden" name="topic_id" value="${topic.getId()}">
    <textarea name="feedback" id="newFeedback" cols="30" rows="10"></textarea>
    <br>
    <input type="submit" value="Post">
</form>

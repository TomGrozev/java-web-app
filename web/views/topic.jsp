<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 3/27/21
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>${topic.title} | Topic</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <p><strong>Project By: </strong> Tom Grozev</p>

        <h2>The Python Discussion Forum</h2>

        <c:choose>
            <c:when test="${login_session != null}">
                <a href="logout">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="login">Login</a>
            </c:otherwise>
        </c:choose>

        <div>
            <c:if test="${hasErrors}">
                <p>There were some errors: </p>
                <c:forEach var="error" items="${errors}">
                    <p>${error.getMessage()}</p>
                </c:forEach>
            </c:if>

            <c:if test="${param.error}">
                <p>${param.error}</p>
            </c:if>
        </div>

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
    </body>
</html>

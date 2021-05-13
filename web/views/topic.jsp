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

        <c:if test="${topic.user.getUsername() == login_session.getUsername()}">
            <a href="deleteTopic?id=${topic.id}">Delete Topic</a>
        </c:if>

        <p>${topic.user.getUsername()}: ${topic.content}</p>

        <h4>Comments:</h4>

        <c:forEach var="comment" items="${topic.comments}">
            <p>${comment.user.getUsername()}: ${comment.content}</p>
        </c:forEach>

        <h5>Write a comment: </h5>

        <form action="comments" method="POST">
            <input type="hidden" name="topic_id" value="${topic.id}">
            <textarea name="comment" id="newComment" cols="30" rows="10"></textarea>
            <br>
            <input type="submit" value="Post">
        </form>
    </body>
</html>

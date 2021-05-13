<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 5/14/21
  Time: 3:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
    <jsp:include page="/templates/header.jsp" />

    <jsp:include page="/views/${pagePath}.jsp" />

    <jsp:include page="/templates/footer.jsp" />
</body>
</html>

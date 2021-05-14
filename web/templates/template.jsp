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
    <link rel="stylesheet" href="https://pagecdn.io/lib/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <jsp:include page="/templates/header.jsp" />

    <div class="body container">
        <jsp:include page="/views/${pagePath}.jsp" />
    </div>

    <jsp:include page="/templates/footer.jsp" />
</body>
</html>

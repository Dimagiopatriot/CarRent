<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 18.09.2017
  Time: 0:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>

<p class="server-error"><fmt:message key="error.on.server"/></p>

<%@include file="footer.jsp" %>
</body>
</html>

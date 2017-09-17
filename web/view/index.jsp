<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 17.09.2017
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
  <head>
    <%@include file="head.jsp" %>
  </head>
  <body>
  <%@include file="header.jsp"%>
  <div class="row ">
    <p class="welcome-title"><fmt:message key="greetings"/></p>
  </div>

  <%@include file="footer.jsp" %>
  </body>
</html>

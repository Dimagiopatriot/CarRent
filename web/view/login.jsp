<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 18.09.2017
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<form class="form-horizontal">
    <fieldset class="mycont">
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="email"><fmt:message key="email"/></label>
            <div class="col-md-4">
                <input id="email" name="email" type="email" placeholder="example@email.com"
                       class="form-control input-md" required="required">

            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="userpassword"><fmt:message key="password"/></label>
            <div class="col-md-4">
                <input id="userpassword" name="userpassword" type="password" placeholder="<fmt:message key="password"/>"
                       class="form-control input-md" required="required">

            </div>
        </div>

        <!-- Button -->
        <label class="col-md-4 control-label" for="loginbutton"></label>
        <div class="col-md-8">
            <button id="loginbutton" name="loginbutton" class="btn btn-success"><fmt:message key="sign.in"/></button>
        </div>

    </fieldset>
</form>

<%@include file="footer.jsp" %>
</body>
</html>

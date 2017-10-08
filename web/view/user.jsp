<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 27.09.2017
  Time: 16:47
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
<form method="post" action="/user/clientUpdate" class="form-horizontal">
    <fieldset class="mycont">

        <div class="form-group">
            <h3 class="col-sm-6 control-label"><fmt:message key="user.name"/>
                <small><font color="black">${user.name}</font></small>
            </h3>
        </div>

        <div class="form-group">
            <h3 class="col-sm-6 control-label"><fmt:message key="user.surname"/>
                <small><font color="black"> ${user.surname}</font></small>
            </h3>
        </div>

        <div class="form-group">
            <h3 class="col-sm-6 control-label"><fmt:message key="email"/>
                <small><font color="black"> ${user.userAuth.email}</font></small>
            </h3>
        </div>

        <c:if test="${user != null && user.userAuth.role.toString() == 'client'}">
            <div class="form-group">
                <label class="col-md-4 control-label" for="userphone"><fmt:message key="user.phone"/></label>
                <div class="col-md-4">
                    <input id="userphone" name="userphone" placeholder="+380_ _ _ _ _ _ _ _"
                           value="${user.phone}"
                           class="form-control input-md">

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="usercount"><fmt:message key="user.count"/></label>
                <div class="col-md-4">
                    <input id="usercount" name="usercount" min="0" value="${user.count}" type="number"
                           placeholder="in UAH"
                           class="form-control input-md">

                </div>
            </div>

            <div>
                <button class="btn btn-success"><fmt:message key="user.update"/></button>
            </div>
            <a href="/user/clientOrders"><fmt:message key="transition.to.orders"/> </a>
            <a href="/user/makeOrder"><fmt:message key="transition.to.makeorder"/> </a>
        </c:if>
        <c:if test="${user != null && user.userAuth.role.toString() == 'admin'}">
            <a href="/user/adminOrders"><fmt:message key="transition.to.orders"/> </a>
        </c:if>
    </fieldset>
    <c:forEach items="${errors}" var="item">
        <p class="text-danger"><fmt:message key="${item}"/></p>
        <br>
    </c:forEach>
    <p class="text-success"><fmt:message key="${success}"/></p>
</form>

<%@include file="footer.jsp" %>
</body>
</html>

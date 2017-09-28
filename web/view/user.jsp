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
<form class="form-horizontal">
    <fieldset class="mycont">

        <div class="form-group">
            <h3 class="col-md-4"><fmt:message key="user.name"/>
                <small><font color="black">${user.name}</font></small>
            </h3>
        </div>

        <div class="form-group">
            <h3 class="col-md-4"><fmt:message key="user.surname"/>
                <small><font color="black"> ${user.surname}</font></small>
            </h3>
        </div>

        <div class="form-group">
            <h3 class="col-md-4"><fmt:message key="email"/>
                <small><font color="black"> ${user.email}</font></small>
            </h3>
        </div>

        <c:if test="${user != null && user.userAuth.role.toString() == 'client'}">
            <div class="form-group">
                <h3 class="col-md-4"><fmt:message key="user.phone"/> <input id="userphone" name="userphone"
                                                                            placeholder="0000000000"
                                                                            class="form-control input-md"
                                                                            required="required"
                                                                            value="${user.phone}"></h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4"><fmt:message key="user.count"/> <input id="usercount" name="usercount"
                                                                            placeholder="in UAH"
                                                                            class="form-control input-md"
                                                                            required="required"
                                                                            value="${user.count}"></h3>
            </div>

            <div>
                <button class="btn btn-success"><fmt:message key="user.update"/></button>
            </div>
        </c:if>

    </fieldset>
</form>

<%@include file="footer.jsp" %>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 05.10.2017
  Time: 17:07
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
<form class="form-horizontal" method="post" action="/user/addDamage/create">
    <fieldset class="mycont">
        <h3 align="middle"><u><fmt:message key="order.damage"/></u></h3>
        <div class="form-group">
            <label class="col-md-4 control-label" for="damageDescription"><fmt:message
                    key="damage.description"/></label>
            <div class="col-md-4">
                <input id="damageDescription" name="damageDescription"
                       placeholder="<fmt:message key="damage.description"/>"
                       class="form-control input-md" required="required">

            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label" for="damageRepairBill"><fmt:message
                    key="damage.repairBill"/></label>
            <div class="col-md-4">
                <input id="damageRepairBill" name="damageRepairBill" type="number" min="0"
                       placeholder="<fmt:message key="damage.repairBill"/>"
                       class="form-control input-md" required="required">

            </div>
        </div>
        <div align="middle">
            <button class="btn btn-success"><fmt:message key="damage.addDamage"/></button>
        </div>
        <c:if test="${success != null}">
            <p class="text-success"><fmt:message key="${success}"/></p>
        </c:if>
        <c:if test="${errors != null}">
            <p class="text-danger"><fmt:message key="${errors}"/></p>
        </c:if>
        <div align="bottom|left">
            <a href="/user/adminOrders"><fmt:message key="transition.to.orders"/> </a>
        </div>
        <div align="bottom|left">
            <a href="/user"><fmt:message key="transition.to.user"/> </a>
        </div>
    </fieldset>
</form>
<%@include file="footer.jsp" %>
</body>
</html>

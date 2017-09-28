<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 27.09.2017
  Time: 20:07
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
        <c:forEach items="${orders}" var="order">
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.car"/>
                    <small><font color="black">${order.car.toString()}</font></small>
                </h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.period"/>
                    <small><font color="black">${order.dateStartRent.toString()}
                        - ${order.dateEndRent.toString()}</font>
                    </small>
                </h3>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="carSelect"><fmt:message key="order.status"/></label>
                <div class="col-md-4">
                    <select id="carSelect" name="carSelect" class="form-control" required="required">
                        <option value="GET_FOR_CONFIRMATION">Get for confirmation</option>
                        <option value="ACCEPTED">Accepted</option>
                        <option value="DENIED">Denied</option>
                        <option value="CLOSED">Closed</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="adminComment"><fmt:message key="order.comment"/></label>
                <div class="col-md-4">
                    <input id="adminComment" name="adminComment" placeholder="<fmt:message key="order.comment"/>"
                           class="form-control input-md">

                </div>
            </div>
            <h3 align="middle"><u><fmt:message key="order.damage"/></u></h3>
            <div class="form-group">
                <label class="col-md-4 control-label" for="damageDescription"><fmt:message
                        key="damage.description"/></label>
                <div class="col-md-4">
                    <input id="damageDescription" name="damageDescription"
                           placeholder="<fmt:message key="damage.description"/>"
                           class="form-control input-md">

                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="damageRepairBill"><fmt:message
                        key="damage.repairBill"/></label>
                <div class="col-md-4">
                    <input id="damageRepairBill" name="damageRepairBill" type="number" min="0"
                           placeholder="<fmt:message key="damage.repairBill"/>"
                           class="form-control input-md">

                </div>
            </div>
            <div align="middle">
                <button class="btn btn-success"><fmt:message key="damage.addDamage"/></button>
            </div>
            <div align="bottom|left">
                <button class="btn btn-success"><fmt:message key="order.confirmChanges"/></button>
            </div>
        </c:forEach>
        <div align="bottom|left">
            <a href="/user"><fmt:message key="transition.to.user"/> </a>
        </div>
    </fieldset>
</form>
<%@include file="footer.jsp" %>
</body>
</html>

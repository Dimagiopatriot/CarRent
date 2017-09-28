<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 27.09.2017
  Time: 19:36
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
            <label class="col-md-4 control-label" for="carSelect"><fmt:message key="order.car"/></label>
            <div class="col-md-4">
                <select id="carSelect" name="carSelect" class="form-control" required="required">
                    <option value="LADA">Lada</option>
                    <option value="BMW">BMW</option>
                    <option value="MERCEDES">Mercedes</option>
                    <option value="HYNDAI">Hyundai</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="date"><fmt:message key="email"/></label>
            <div class="col-md-4" id="date">
                <input id="dateFrom" name="dateFrom" type="date" required="required">
                <input id="dateTo" name="dateTo" type="date" required="required">
            </div>
        </div>
        <h3><fmt:message key="order.price"/>
            <small><font color="black">${order.car.getRentPricePerHour()}</font></small>
        </h3>

        <div class="col-md-8">
            <button id="orderButton" name="orderButton" class="btn btn-success"><fmt:message key="order.makeOrder"/></button>
        </div>
    </fieldset>
</form>

<%@include file="footer.jsp" %>
</body>
</html>

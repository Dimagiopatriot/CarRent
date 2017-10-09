<%@ page import="util.constant.Parameters" %><%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 27.09.2017
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" uri="formatPrice.tld" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<%@include file="header.jsp" %>
<form class="form-horizontal" method="post" action="/user/makeOrder/create">
    <fieldset class="mycont">
        <div class="form-group">
            <label class="col-md-4 control-label" for="carSelect"><fmt:message key="order.car"/></label>
            <div class="col-md-4">
                <select id="carSelect" name="carSelect" class="form-control" required="required">
                    <option value="LADA" selected>Lada</option>
                    <option value="BMW">BMW</option>
                    <option value="MERCEDES">Mercedes</option>
                    <option value="HYNDAI">Hyundai</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="date"><fmt:message key="order.period"/></label>
            <div class="col-md-4" id="date">
                <input id="dateFrom" name="dateFrom" type="date" required="required">
                <input id="dateTo" name="dateTo" type="date" required="required">
            </div>
        </div>
        <div class="form-group">
            <h3 class="col-md-4 control-label"><fmt:message key="order.price"/>
                <small><font color="black" id="orderPriceResult"></font></small>
                <small><font color="black"><my:formatPrice
                        language="${pageContext.session.getAttribute('language')}"/></font></small>
            </h3>
            <input type="hidden" name="orderPriceResultInput" id="orderPriceResultInput">
            <button id="getPriceButton" name="getPriceButton" class="btn btn-success"
                    onclick="calculate_order_price(); return false;">
                <fmt:message key="order.getPrice"/>
            </button>
        </div>


        <div>
            <button id="orderButton" name="orderButton" class="btn btn-success"><fmt:message
                    key="order.makeOrder"/></button>
        </div>

        </br>
        <div align="bottom|left">
            <a href="/user"><fmt:message key="transition.to.user"/></a>
        </div>
        </br>
        <div align="bottom|left">
            <a href="/user/clientOrders"><fmt:message key="transition.to.orders"/></a>
        </div>
        <c:forEach items="${errors}" var="item">
            <p class="text-danger"><fmt:message key="${item}"/></p>
            <br>
        </c:forEach>
        <p class="text-success"><fmt:message key="${success}"/></p>
    </fieldset>
</form>
<%@include file="footer.jsp" %>

<script>
    function calculate_order_price() {
        var carType = document.getElementById("carSelect").value;
        var dateFrom = new Date(document.getElementById("dateFrom").value);
        var dateTo = new Date(document.getElementById("dateTo").value);

        var timeDiff = Math.abs(dateTo.getTime() - dateFrom.getTime());
        var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));
        var result = 0;
        switch (carType) {
            case 'LADA':
                result = diffDays * 13.5;
                break;
            case 'BMW':
                result = diffDays * 20;
                break;
            case 'MERCEDES':
                result = diffDays * 21;
                break;
            case 'HYNDAI':
                result = diffDays * 16;
                break;
            default:
                result = 0;
        }
        if (!isNaN(result)) {
            document.getElementById("orderPriceResult").innerHTML = result;
            document.getElementById("orderPriceResultInput").value = result;
        } else {
            document.getElementById("orderPriceResult").innerHTML = 0;
            document.getElementById("orderPriceResultInput").value = result;
        }
    }
</script>
</body>
</html>

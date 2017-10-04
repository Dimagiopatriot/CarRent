<%--
  Created by IntelliJ IDEA.
  User: troll
  Date: 27.09.2017
  Time: 19:57
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

<form class="form-horizontal" method="post" action="/user/clientOrders">
    <fieldset class="mycont">

        <c:forEach items="${orders}" var="item">
            <hr>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.car"/>
                    <small><font color="black">${item.car.toString()}</font></small>
                </h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.period"/>
                    <small><font color="black">${item.dateStartRent.toString()} - ${item.dateEndRent.toString()}</font>
                    </small>
                </h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.status"/>
                    <small><font color="black">${item.status.toString()}</font></small>
                </h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><fmt:message key="order.comment"/>
                    <small><font color="black">${item.comment}</font></small>
                </h3>
            </div>
            <div class="form-group">
                <h3 class="col-md-4 control-label"><u><fmt:message key="order.damage"/></u></h3>
            </div>

            <div class="form-group">
                <h4 class="col-md-4 control-label" id="damageDescription"><fmt:message key="damage.description"/>
                    <small><font color="black">${item.damage.damageDescription}</font></small>
                </h4>
            </div>
            <div class="form-group">
                <h4 class="col-md-4 control-label" id="damageRepairBill"><fmt:message key="damage.repairBill"/>
                    <small><font color="black">-- ${item.damage.repairBill}</font></small>
                </h4>
            </div>
            <hr>
        </c:forEach>
        <div align="bottom|left">
            <a href="/user"><fmt:message key="transition.to.user"/> </a>
        </div>
        <div align="bottom|left">
            <a href="/user/makeOrder"><fmt:message key="transition.to.makeorder"/></a>
        </div>
    </fieldset>
</form>


<%@include file="footer.jsp" %>
</body>
</html>

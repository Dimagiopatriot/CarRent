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
<div class="form-horizontal">
    <fieldset class="mycont">
        <c:forEach items="${orders}" var="item" varStatus="itemRaw">
            <form method="post" action="/user/updateOrder">
                <hr style="border-top: 1px solid #000000 !important;">
                <input type="hidden" name="id" value="${item.id}">
                <input type="hidden" name="userId" value="${item.userId}">
                <div class="form-group">
                    <h3 class="col-md-4 control-label"><fmt:message key="order.car"/>
                        <input type="hidden" name="carSelect" value="${item.car.toString()}">
                        <small><font color="black">${item.car.toString()}</font></small>
                    </h3>
                </div>
                <div class="form-group">
                    <h3 class="col-md-4 control-label"><fmt:message key="order.period"/>
                        <input type="hidden" name="dateFrom" value="${item.dateStartRent.toString()}">
                        <input type="hidden" name="dateTo" value="${item.dateEndRent.toString()}">
                        <small><font color="black">${item.dateStartRent.toString()}
                            - ${item.dateEndRent.toString()}</font>
                        </small>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="statusSelect"><fmt:message key="order.status"/></label>
                    <div class="col-md-4">
                        <select id="statusSelect" name="statusSelect" class="form-control" required="required">
                            <option value="GET_FOR_CONFIRMATION" ${item.status.toString().equals("get_for_confirmation")?"selected":""}>
                                Get for confirmation
                            </option>
                            <option value="ACCEPTED"${item.status.toString().equals("accepted")?"selected":""}>Accepted
                            </option>
                            <option value="DENIED"${item.status.toString().equals("denied")?"selected":""}>Denied
                            </option>
                            <option value="CLOSED"${item.status.toString().equals("closed")?"selected":""}>Closed
                            </option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-4 control-label" for="adminComment"><fmt:message key="order.comment"/></label>
                    <div class="col-md-4">
                        <input id="adminComment" name="adminComment" placeholder="<fmt:message key="order.comment"/>"
                               value="${item.comment}"
                               class="form-control input-md">

                    </div>
                </div>
                <p class="text-success"><fmt:message key="${updateErrorOrder}"/></p>
                <p class="text-danger"><fmt:message key="${updateSuccessOrder}"/></p>

                <div align="bottom|left">
                    <button class="btn btn-success"><fmt:message key="order.confirmChanges"/></button>
                </div>
            </form>

            <form method="post" action="/user/addDamage">
                <input type="hidden" name="id" value="${item.id}">
                <div align="bottom|left">
                    <button class="btn btn-success"><fmt:message key="transition.to.damage"/></button>
                </div>
            </form>

            <hr style="border-top: 1px solid #000000 !important;">
        </c:forEach>
        </br>
        <div align="bottom|left">
            <a href="/user"><fmt:message key="transition.to.user"/> </a>
        </div>
    </fieldset>
</div>
<%@include file="footer.jsp" %>
</body>
</html>

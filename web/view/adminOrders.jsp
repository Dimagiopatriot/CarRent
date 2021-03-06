<%@ page import="util.constant.Parameters" %><%--
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
        <form method="post" action="/user/adminOrders/sort">
            <div class="form-group">
                <label class="col-md-4 control-label" for="sort"><fmt:message key="order.status"/></label>
                <div class="col-md-4">
                    <select id="sort" name="sort" class="form-control">
                        <option value="GET_FOR_CONFIRMATION" ${sort.toString().equals("get_for_confirmation")?"selected":""}>
                            Get for confirmation
                        </option>
                        <option value="ACCEPTED"${sort.toString().equals("accepted")?"selected":""}>Accepted
                        </option>
                        <option value="DENIED"${sort.toString().equals("denied")?"selected":""}>Denied
                        </option>
                        <option value="CLOSED"${sort.toString().equals("closed")?"selected":""}>Closed
                        </option>
                    </select>
                </div>
            </div>

            <label class="col-md-4 control-label" for="sortbutton"></label>
            <div class="col-md-8">
                <button id="sortbutton" name="sortbutton" class="btn btn-success">
                    <fmt:message key="order.sort"/></button>
            </div>
            <div class="col-md-4">
                <c:forEach items="${pages}" var="page">
                    ${page}
                </c:forEach>
            </div>
            <div class="col-md-4">
                <c:if test="${currentPage != 0}">
                    <fmt:message key="pagination.yourPage"/> ${currentPage}
                </c:if>
            </div>
        </form>
        <div class="col-md-4">
            <form method="post" action="/user/adminOrders/pagination">
                <input type="hidden" value="<%=request.getAttribute(Parameters.SORT)%>" name="status">
                <fmt:message key="pagination.enterPage"/> <input type="number" name="pageToGo" min="0" step="1">
                <button class="btn btn-success"><fmt:message key="pagination.goToPage"/> </button>
            </form>
        </div>
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
                <c:if test="${updateErrorOrder != null}">
                    <p class="text-danger"><fmt:message key="${updateErrorOrder}"/></p>
                </c:if>
                <c:if test="${updateSuccessOrder != null}">
                    <p class="text-success"><fmt:message key="${updateSuccessOrder}"/></p>
                </c:if>

                <div align="bottom|left">
                    <button class="btn btn-success"><fmt:message key="order.confirmChanges"/></button>
                </div>
            </form>

            <c:if test="${item.damage.id == 0}">
                <form method="post" action="/user/addDamage">
                    <input type="hidden" name="id" value="${item.id}">
                    <div align="bottom|left">
                        <button class="btn btn-success"><fmt:message key="transition.to.damage"/></button>
                    </div>
                </form>
            </c:if>
            <c:if test="${item.damage.id != 0}">
                <fmt:message key="damage.alreadyAdded"/>
            </c:if>

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

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
<fieldset class="mycont">

    <c:forEach items="${orders}" var="item">
        <h3><fmt:message key="order.car"/>
            <small><font color="black">${item.car.toString()}</font></small>
        </h3>

        <h3><fmt:message key="order.period"/>
            <small><font color="black">${item.dateStartRent.toString()} - ${item.dateEndRent.toString()}</font></small>
        </h3>

        <h3><fmt:message key="order.status"/>
            <small><font color="black">${item.status.toString()}</font></small>
        </h3>

        <h3><fmt:message key="order.comment"/>
            <small><font color="black">${item.comment}</font></small>
        </h3>

        <h3><u><fmt:message key="order.damage"/></u></h3>
        <c:forEach items="${item.getDamages}" var="damageItem">

            <h4><fmt:message key="damage.description"/>
                <small><font color="black">${damageItem.damageDescription}</font></small>
            </h4>
            <h4><fmt:message key="damage.repairBill"/>
                <small><font color="black">-- ${damageItem.repairBill}</font></small>
            </h4>
            </br>
        </c:forEach>
    </c:forEach>
</fieldset>

<%@include file="footer.jsp" %>
</body>
</html>

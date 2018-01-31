<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 26.01.2018
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="my_orders"/></title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css"/>
</head>

<body>
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column middle" style="background-color:#bbb;">

        <c:choose>
            <c:when test="${not empty payments}">

                <h2 class="header"><fmt:message key="your_payments"/></h2>
                <table class="exhibitionsTable">
                    <thead>
                    <tr>
                        <th><fmt:message key="payment_id"/>:</th>
                        <th><fmt:message key="exposition_theme"/>:</th>
                        <th><fmt:message key="ticket_id"/>:</th>
                        <th><fmt:message key="ticket_number"/>:</th>
                        <th><fmt:message key="payment_date"/>:</th>
                        <th><fmt:message key="paid"/>:</th>
                        <th><fmt:message key="date_start"/>:</th>
                        <th><fmt:message key="date_end"/>:</th>
                    </tr>
                    </thead>

                    <c:forEach items="${payments}" var="payment">
                        <tbody>
                        <tr>
                            <td><c:out value="${payment.paymentId}"/></td>
                            <td><c:out value="${payment.ticket.exposition.theme}"/></td>
                            <td><c:out value="${payment.ticket.ticketId}"/></td>
                            <td><c:out value="${payment.ticket.number}"/></td>
                            <td><c:out value="${payment.date}"/></td>
                            <td><c:out value="${payment.amount}"/></td>
                            <td><c:out value="${payment.ticket.exposition.dateStart}"/></td>
                            <td><c:out value="${payment.ticket.exposition.dateStart}"/></td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <fmt:message key="noExpositionsFound"/>
            </c:otherwise>
        </c:choose>
        <form>
            <select id="language" name="language" onchange="submit()">
                <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
                <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
            </select>
        </form>
    </div>
</div>
</body>
</html>

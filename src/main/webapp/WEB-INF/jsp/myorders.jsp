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
    <title><fmt:message key="my_orders" /></title>
</head>

<body>
${language}
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<c:choose>
    <c:when test="${not empty payments}">
        <h2><fmt:message key="your_payments" /></h2>
        <table>
            <c:forEach items="${payments}" var="payment">
                <tr>
                    <th><fmt:message key="payment_id" />:</th>
                    <td>${payment.paymentId}</td>

                    <th><fmt:message key="exposition_theme" />:</th>
                    <td>${payment.ticket.exposition.theme}</td>

                    <th><fmt:message key="ticket_id"/>:</th>
                    <td>${payment.ticket.ticketId}</td>

                    <th><fmt:message key="ticket_number" />:</th>
                    <td>${payment.ticket.number}</td>

                    <th><fmt:message key="payment_date" />:</th>
                    <td>${payment.date}</td>

                    <th><fmt:message key="paid" />:</th>
                    <td>${payment.amount}</td>

                    <th><fmt:message key="date_start" />:</th>
                    <td>${payment.ticket.exposition.dateStart}</td>

                    <th><fmt:message key="date_end" />:</th>
                    <td>${payment.ticket.exposition.dateStart}</td>
                </tr>

            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <br>
        <fmt:message key="noExpositionsFound" />
    </c:otherwise>
</c:choose>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
</body>
</html>

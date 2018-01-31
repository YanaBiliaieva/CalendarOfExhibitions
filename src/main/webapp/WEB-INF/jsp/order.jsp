<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 23.01.2018
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="order_ticket"/></title>
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column next">
        <c:choose>
            <c:when test="${ exposition.ticketsAvailable gt 0}">
                <h2 class="header"><fmt:message key="purchaseTicket"/></h2>
                <table class="exhibitionsTable">
                    <tr>
                        <th><fmt:message key="exposition"/>:</th>
                        <td> ${exposition.theme}</td>
                    </tr>

                    <tr>
                        <th><fmt:message key="date_start"/>:</th>
                        <td> ${exposition.dateStart}</td>
                    </tr>

                    <tr>
                        <th><fmt:message key="date_end"/>:</th>
                        <td> ${exposition.dateEnd}</td>
                    </tr>

                    <tr>
                        <th><fmt:message key="price"/>:</th>
                        <td> ${exposition.price}</td>
                    </tr>
                    <tr>
                        <th><fmt:message key="balance"/>:</th>
                        <td> ${sessionScope.user.balance}</td>
                    </tr>
                    <form action="order" method="post">
                        <tr>
                            <th><fmt:message key="tickets"/>:</th>
                            <td>
                                <select name="quantity">

                                    <c:forEach var="i" begin="1" end="${exposition.ticketsAvailable}">
                                        <option><c:out value="${i}"/></option>
                                    </c:forEach>
                                </select>

                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td><input type="hidden" name="quantity" value="${quantity}">
                                <input type="hidden" name="expositionId" value="${exposition.id}">
                                <button type="submit"><fmt:message key="buy"/></button>
                            </td>
                        </tr>

                    </form>
                    <fmt:message key="dear"/> ${sessionScope.user.firstname}.
                    <fmt:message key="code_purchase"/>

                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div>
                    <fmt:message key="noTicketsFound"/>
                </div>
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

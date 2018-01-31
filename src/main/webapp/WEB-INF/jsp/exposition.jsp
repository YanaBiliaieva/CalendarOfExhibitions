<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 23.01.2018
  Time: 19:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"  />

<html>
<head><title><fmt:message key="exhibitions" /></title>
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body >
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column next">
    <c:choose>
        <c:when test="${not empty exposition}">
            <h1 class="header">${exposition.theme}</h1>
            <table class="exhibitionsTable">
                <tr>
                    <th><fmt:message key="name" />:</th>
                    <td>${exposition.theme}</td>
                </tr>

                <tr>
                     <th><fmt:message key="description" />:</th>
                    <td>${exposition.description}</td>
                </tr>
                <tr>
                    <th><fmt:message key="date_start" />:</th>
                    <td>${exposition.dateStart}</td>
                </tr>
                <tr>
                    <th><fmt:message key="date_end" />:</th>
                    <td>${exposition.dateEnd}</td>
                </tr>
                <tr>
                    <th><fmt:message key="price" />:</th>
                    <td>${exposition.price}</td>
                </tr>
                <tr>
                    <th><fmt:message key="hallName" />:</th>
                    <td>${exposition.hallName}</td>
                </tr>
                    <th><fmt:message key="hallCity" />:</th>
                    <td>${exposition.hallCity}</td>
                </tr>
                <tr>
                    <th><fmt:message key="hallAddress" />:</th>
                    <td>${exposition.hallAddress}</td>
                </tr>
                <tr>
                    <th><fmt:message key="tickets" />:</th>
                    <td>${exposition.ticketsAvailable}</td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <br>
                <fmt:message key="noExpositionsFound" />
        </c:otherwise>
    </c:choose>
        <div id="message">
            <c:if test="${exposition.ticketsAvailable eq 0}">
                <fmt:message key="no_tickets_available" />
            </c:if>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                        <form action="order" method="get">
                            <input type="hidden" name="expositionId" value="${exposition.id}">
                            <button type="submit">
                                <fmt:message key="buy" />
                            </button>
                        </form>
                    </c:when>
                    <c:otherwise>
                    <fmt:message key="not_logged" />
                </c:otherwise>
            </c:choose>
        </div>
        <hr/>
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
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="m" tagdir="/WEB-INF/tags/taglib.tld" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"  />

<html>
<head>
    <title><fmt:message key="exhibitions" /></title>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css"/>
</head>

<body>

<c:import url="/WEB-INF/jsp/header.jsp"/><br/>


<c:set var="totalCount" scope="session" value="${lst.size()}"/>
<c:set var="perPage" scope="session" value="${5}"/>
<c:set var="pageStart" value="${param.start}"/>
<c:if test="${empty pageStart or pageStart < 0}">
    <c:set var="pageStart" value="0"/>
</c:if>
<c:if test="${totalCount < pageStart}">
    <c:set var="pageStart" value="${pageStart - perPage}"/>
</c:if>


<div class="row">
    <div class="column side"></div>
    <div class="column middle" style="background-color:#bbb;">
        <h1 class="header"><fmt:message key="exhibitions" /></h1>
<table class="exhibitionsTable">
    <thead>
    <tr>
        <th><fmt:message key="number" /></th>
        <th><fmt:message key="theme" /></th>
        <th><fmt:message key="date_start" /></th>
        <th><fmt:message key="date_end" /></th>
        <th><fmt:message key="price" /></th>
        <th><fmt:message key="hallCity" /></th>
        <th><fmt:message key="tickets" /></th>
        <th><fmt:message key="tr_more" /></th>
    </tr>
    </thead>

    <c:set var="count" value="0" scope="page"/>
    <c:forEach var="exhibition" items="${exhibitions}" begin="${pageStart}" end="${pageStart + perPage - 1}">

    <tbody>
        <tr>
            <td>
                <c:out value="${count+1}"/>
                <c:set var="count" value="${count}" scope="page"/>
            </td>
            <td><c:out value="${exhibition.theme}"/></td>
            <td><c:out value="${exhibition.dateStart}"/></td>
            <td><c:out value="${exhibition.dateEnd}"/></td>
            <td><c:out value="${exhibition.price}"/></td>
            <td><c:out value="${exhibition.hallCity}"/></td>
            <td><c:out value="${exhibition.ticketsAvailable}"/></td>
            <td>
                <form action="exposition" method="post">
                    <input type="hidden" name="expositionId" value="${exhibition.id}">
                    <button class="myButton" type="submit">
                        <fmt:message key="more" />
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    <tfoot>
    <a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </tfoot></tbody>
</table>

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


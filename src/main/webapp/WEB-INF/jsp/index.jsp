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
${language}
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

<h1><fmt:message key="exhibitions" /></h1>
<table class="exhibitionsTable">
    <thead>
    <tr>
        <th>head1</th>
        <th>head2</th>
        <th>head3</th>
        <th>head4</th>
        <th>head5</th>
        <th>head6</th>
        <th>head7</th>
        <th>head8</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <td colspan="8">
            <div class="links"><a href="#">&laquo;</a> <a class="active" href="#">1</a> <a href="#">2</a> <a href="#">3</a> <a href="#">4</a> <a href="#">&raquo;</a></div>
        </td>
    </tr>
    </tfoot>
    <tbody>
    <tr>
        <td>cell1_1</td>
        <td>cell2_1</td>
        <td>cell3_1</td>
        <td>cell4_1</td>
        <td>cell5_1</td>
        <td>cell6_1</td>
        <td>cell7_1</td>
        <td>cell8_1</td>
    </tr>
    <tr>
        <td>cell1_2</td>
        <td>cell2_2</td>
        <td>cell3_2</td>
        <td>cell4_2</td>
        <td>cell5_2</td>
        <td>cell6_2</td>
        <td>cell7_2</td>
        <td>cell8_2</td>
    </tr>
    <tr>
        <td>cell1_3</td>
        <td>cell2_3</td>
        <td>cell3_3</td>
        <td>cell4_3</td>
        <td>cell5_3</td>
        <td>cell6_3</td>
        <td>cell7_3</td>
        <td>cell8_3</td>
    </tr>
    <tr>
        <td>cell1_4</td>
        <td>cell2_4</td>
        <td>cell3_4</td>
        <td>cell4_4</td>
        <td>cell5_4</td>
        <td>cell6_4</td>
        <td>cell7_4</td>
        <td>cell8_4</td>
    </tr>
    <tr>
        <td>cell1_5</td>
        <td>cell2_5</td>
        <td>cell3_5</td>
        <td>cell4_5</td>
        <td>cell5_5</td>
        <td>cell6_5</td>
        <td>cell7_5</td>
        <td>cell8_5</td>
    </tr>
    </tbody>
</table>
<table class="exhibitionsTable">
    <thead>
    <tr>
        <th><fmt:message key="number" /></th>
        <th><fmt:message key="theme" /></th>
        <th><fmt:message key="date_start" /></th>
        <th><fmt:message key="date_end" /></th>
        <th><fmt:message key="description" /></th>
        <th><fmt:message key="price" /></th>
        <th><fmt:message key="hallName" /></th>
        <th><fmt:message key="hallCity" /></th>
        <th><fmt:message key="hallAddress" /></th>
        <th><fmt:message key="tickets" /></th>
    </tr>
    </thead>
    <tfoot>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach var="exhibition" items="${exhibitions}" begin="${pageStart}" end="${pageStart + perPage - 1}">
    <a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage}
    <a href="?start=${pageStart + perPage}">>></a>
    </tfoot>
    <tbody>

        <tr>
            <td><c:set var="count" value="${count + 1}" scope="page"></c:set>
                <c:out value="${count}"/>
            </td>
            <td><c:out value="${exhibition.theme}"/></td>
            <td><c:out value="${exhibition.dateStart}"/></td>
            <td><c:out value="${exhibition.dateEnd}"/></td>
            <td><c:out value="${exhibition.description}"/></td>
            <td><c:out value="${exhibition.price}"/></td>
            <td><c:out value="${exhibition.hallName}"/></td>
            <td><c:out value="${exhibition.hallCity}"/></td>
            <td><c:out value="${exhibition.hallAddress}"/></td>
            <td><c:out value="${exhibition.ticketsAvailable}"/></td>
            <td> exhibition.id=<c:out value="${exhibition.id}"/></td>
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
    </tbody>
</table>

<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
</body>
</html>


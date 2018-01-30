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
</head>
<body>
${language}
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="container">
    <c:choose>
        <c:when test="${ exposition.ticketsAvailable gt 0}">
            <h2><fmt:message key="purchaseTicket"/></h2>


            <div><fmt:message key="exposition"/>:</div>
            <div> ${exposition.theme}</div>


            <div><fmt:message key="date_start"/>:</div>
            <div>${exposition.dateStart}</div>


            <div><fmt:message key="date_end"/>:</div>
            <div>${exposition.dateEnd}</div>


            <div><fmt:message key="price"/>:</div>
            <tdivd>${exposition.price}</tdivd>
            <div><fmt:message key="balance"/></div>
            <div>${sessionScope.user.balance}
            </div>
            <form action="order" method="post">
                <div><select name="quantity">
                    <fmt:message key="tickets"/>
                    <c:forEach var="i" begin="1" end="${exposition.ticketsAvailable}">
                        <option><c:out value="${i}"/></option>
                    </c:forEach>
                  </select></div>
                <input type="hidden" name="quantity" value="${quantity}">
                <input type="hidden" name="expositionId" value="${exposition.id}">
                <button type="submit"><fmt:message key="buy"/></button>
            </form>
            <fmt:message key="dear"/> ${sessionScope.user.firstname}.
            <fmt:message key="code_purchase"/>
        </c:when>
        <c:otherwise>
            <br>
            <div>
                <fmt:message key="noTicketsFound"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
</body>
</html>

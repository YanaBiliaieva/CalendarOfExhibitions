<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 29.01.2018
  Time: 5:58
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
    <title><fmt:message key="create_exhibition"/></title>
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column next">
<h1 class="header"><fmt:message key="create_exhibition"/></h1>
<form action="createex" method="post">
    <table>
        <tr>
            <th><fmt:message key="name"/>:</th>
            <td><input type="text" name="theme" required="" maxlength="254" autofocus=""></td>
        </tr>

        <tr>
            <th><fmt:message key="description"/>:</th>
            <td><input type="text" name="description" required="" maxlength="254" autofocus=""></td>
        </tr>
        <tr>
            <th><fmt:message key="date_start"/>:</th>
            <td><input type="date" name="dateStart" required="" autofocus=""></td>
        </tr>
        <tr>
            <th><fmt:message key="date_end"/>:</th>
            <td><input type="date" name="dateEnd" required="" autofocus=""></td>
        </tr>
        <tr>
            <th><fmt:message key="price"/>:</th>
            <td><input type="number" name="price" required="" maxlength="10" autofocus=""></td>
        </tr>
        <tr>
            <th><fmt:message key="tickets"/>:</th>
            <td><input type="number" name="ticketsAvailable" required="" maxlength="11" autofocus=""></td>
        </tr>

    <tr>
        <th><fmt:message key="hallName"/>:</th>
        <td><select name="hallid">
            <c:forEach items="${halls}" var="hall">
                <option value="${hall.id}">
                    <c:out value="${hall.name}"/>
                </option>
            </c:forEach>
        </select>
        </td>
    </tr>
    <tr>
        <th></th>
        <td>
            <button type="submit"><fmt:message key="create_exhibition"/></button>
        </td>
    </tr>
    </table>
</form>
${error}
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

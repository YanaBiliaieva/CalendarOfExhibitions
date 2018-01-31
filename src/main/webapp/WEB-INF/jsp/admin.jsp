<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 27.01.2018
  Time: 22:12
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
    <title><fmt:message key="admin_panel"/></title>
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body>

<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column next">
        <h1 class="header"><fmt:message key="admin_panel"/> - <fmt:message key="choose_action"/></h1>
        <table>
            <tr>
                <form action="createex" method="get">
                    <button type="submit">
                        <fmt:message key="new_exhibition"/>
                    </button>
                </form>
            </tr>
            <tr>
                <form action="createhall" method="get">
                    <button type="submit">
                        <fmt:message key="create_hall"/>
                    </button>
                </form>
            </tr>
        </table>

        ${info}
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

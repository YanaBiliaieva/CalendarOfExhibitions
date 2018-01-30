<%--
  Created by IntelliJ IDEA.
  User: Яна
  Date: 29.01.2018
  Time: 18:17
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
    <title><fmt:message key="create_hall"/></title>
</head>
<body>
${language}
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<fmt:message key="halls"/>
<form action="createhall" method="post">
    <fmt:message key="hallName"/>:
    <input type="text" name="name" required="" maxlength="254" autofocus="">
    <fmt:message key="hallAddress"/>:
    <input type="text" name="address" required="" maxlength="50" autofocus="">

    <select name="cityname">
    <c:forEach items="${cities}" var="city">
        <option value="${city.name}" > ${city.name}
        </option>
    </c:forEach>
    </select>

    <button type="submit"><fmt:message key="create_hall"/></button>

</form>
${error}
<fmt:message key="city"/>
<form action="addcity" method="post">
    <fmt:message key="city_name"/>:
    <input type="text" name="city" required="" maxlength="100" autofocus="">
    <button type="submit"><fmt:message key="create_city"/></button>
</form>
${message}
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>
</body>
</html>

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
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class="row">
    <div class="column side"></div>
    <div class="column next">

        <h1 class="header"><fmt:message key="halls"/></h1>
            <form action="createhall" method="post">
                <table class="exhibitionsTable">
                <tr>
                    <th><fmt:message key="hallName"/>:</th>
                    <td><input type="text" name="name" required="" maxlength="254" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="hallAddress"/>:</th>
                    <td><input type="text" name="address" required="" maxlength="50" autofocus=""></td>
                </tr>

                <tr>
                    <th><fmt:message key="city_name"/>:</th>
                    <td>
                        <select name="cityname">
                            <c:forEach items="${cities}" var="city">
                                <option value="${city.name}"> ${city.name}
                                </option>
                            </c:forEach>
                        </select></td>
                </tr>
        </table>
                <div class="column next2 ">
                    <button type="submit"><fmt:message key="create_hall"/></button>
                </div>

        </form>
        <div class="column next3"> ${error}</div>
        <div class="column next3"><h1 class="header"><fmt:message key="city"/></h1></div>
        <form action="addcity" method="post">
        <table class="exhibitionsTable">

                <tr>
                    <th><fmt:message key="city_name"/>:</th>
                    <td><input type="text" name="city" required="" maxlength="100" autofocus=""></td>
                </tr>

        </table>
          <div class="column next2 ">
              <button type="submit"><fmt:message key="create_city"/></button>
          </div>


        </form>
        <div class="column next3">${message}
            <br><hr/></div>

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

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="interface"/>
<html>
<head>
    <title><fmt:message key="login"/></title>
    <link href="<c:url value='/css/exhibition.css'/>" rel="stylesheet" type="text/css">

</head>
<body>
<div class="row">
    <div class="column side"></div>
    <div class="column next">
        <table>
            <form action="exhibitions" method="get">
                <button type="submit">
                    <fmt:message key="home_page"/>
                </button>
            </form>
            <form action="register" method="get">
                <button type="submit">
                    <fmt:message key="registration"/>
                </button>
            </form>
        </table>


        <h3 class="header"><fmt:message key="login"/></h3>
        <table class="exhibitionsTable">
            <form name="LoginForm" method="post" action="login">
                <input type="hidden" name="command" value="login"/>
                <tr>
                    <th><fmt:message key="login_login"/>:</th>
                    <td><input type="text" name="login" value="" required></td>
                </tr>
                <tr>
                    <th><fmt:message key="password"/>:</th>
                    <td><input type="password" name="password" value="" required></td>
                </tr>
                <tr><th></th>

                   <td><input type="submit" value=<fmt:message key="login"/> /></td>
                </tr>
            </form>
        </table>
            <c:if test="${login_incorrect}">
                <fmt:message key="wrong_login"/>
            </c:if>
            <c:if test="${registered}">
                <fmt:message key="registered"/>
            </c:if>
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
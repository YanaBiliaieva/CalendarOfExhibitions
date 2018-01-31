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
    <title><fmt:message key="registration"/></title>
    <link rel="stylesheet" href="<c:url value="/css/exhibition.css"/>" type="text/css"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>
<div class="row">
    <div class="column side"></div>
    <div class="column next">

        <form action="register" method="post">

            <h1 class="header"><fmt:message key="registration"/></h1>
            <table class="exhibitionsTable">
                <tr>
                    <th><fmt:message key="first_name"/></th>
                    <td><input type="text" name="firstname" id="01" required="" maxlength="50" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="last_name"/></th>
                    <td><input type="text" name="lastname" id="02" required="" maxlength="50" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="login_login"/></th>
                    <td><input type="text" name="login" id="03" required="" maxlength="50" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="password"/></th>
                    <td><input type="password" name="password" id="04" required="" maxlength="50" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="password_confirm"/></th>
                    <td><input type="password" name="password2" id="05" required="" maxlength="50" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="email"/></th>
                    <td><input type="text" name="email" id="06" required="" maxlength="254" autofocus=""></td>
                </tr>
                <tr>
                    <th><fmt:message key="phone"/></th>
                    <td>+<input type="number" name="phone" id="07" required="" maxlength="12" autofocus=""></td>
                </tr>
                <tr>
                    <th/>
                    <td>
                        <button type="submit"><fmt:message key="registration"/></button>
                    </td>
                </tr>
            </table>
            ${errorMessage}

        </form>
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
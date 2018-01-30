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
</head>
<body>
${language}
<c:import url="/WEB-INF/jsp/header.jsp"/><br/>


<form action="register" method="post">
    <tr>
        <td><fmt:message key="registration"/></td>
    </tr>
    <tr>
        <td><fmt:message key="first_name"/></td>
        <td><input type="text" name="firstname" id="01" required="" maxlength="50" autofocus=""></td>
    </tr>
    <tr>
        <td><fmt:message key="last_name"/></td>
        <td><input type="text" name="lastname" id="02" required="" maxlength="50" autofocus=""></td>
    </tr>
    <tr>
        <td>
        <td><fmt:message key="login_login"/></td>
        </td>
        <td><input type="text" name="login" id="03" required="" maxlength="50" autofocus=""></td>
    </tr>
    <tr>
        <td><fmt:message key="password"/></td>
        <td><input type="password" name="password" id="04" required="" maxlength="50" autofocus=""></td>
    </tr>
    <tr>
        <td><fmt:message key="password_confirm"/></td>
        <td><input type="password" name="password2" id="05" required="" maxlength="50" autofocus=""></td>
    </tr>
    <tr>
        <td><fmt:message key="email"/></td>
        <td><input type="text" name="email" id="06" required="" maxlength="254" autofocus=""></td>
    </tr>
    <tr>
        <td><fmt:message key="phone"/></td>
        <td>+<input type="number" name="phone" id="07" required="" maxlength="12" autofocus=""></td>
    </tr>
    <tr>
        <td/>
        <td>
            <button type="submit"><fmt:message key="registration"/></button>
        </td>
    </tr>

        ${errorMessage}

</form>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en_EN" ${language == 'en_EN' ? 'selected' : ''}>English</option>
        <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
    </select>
</form>


</body>
</html>
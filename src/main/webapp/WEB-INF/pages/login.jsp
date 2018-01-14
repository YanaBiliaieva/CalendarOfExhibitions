<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Login</h3>

<form action="login" method="post">
    <input type="hidden" name="command" value="login"/>
    <input id="input_login" type="text" class="validate" name="login" value="" required>
    <label for="input_login">Login: </label>
    <input id="input_password" type="hidden" name="password" value="" required>
    <label for="input_password">Password: </label>

    <button type="submit" name="action">Login</button>
</form>

</body>
</html>
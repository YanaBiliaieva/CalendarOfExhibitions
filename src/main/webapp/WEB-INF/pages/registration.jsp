<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a class="button" href="/index">Home </a>

<form action="/register" method="post">
    <tr>
        <td>Registration</td>
    </tr>
    <tr>
        <td>First name</td>
        <td><input type="text" name="firstname" id="01"></td>
    </tr>
    <tr>
        <td>Last name</td>
        <td><input type="text" name="lastname" id="02"></td>
    </tr>
    <tr>
        <td>Login</td>
        <td><input type="text" name="login" id="03"></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="password" name="password" id="04"></td>
    </tr>
    <tr>
        <td>Password confirm</td>
        <td><input type="password" name="password2" id="05"></td>
    </tr>
    <tr>
        <td>Email</td>
        <td><input type="text" name="email" id="06"></td>
    </tr>
    <tr>
        <td>Phone</td>
        <td><input type="text" name="email" id="07"></td>
    </tr>
    <tr>
        <td/>
        <td>
            <button type="submit">Registration</button>
        </td>
    </tr>
    <tr>
        <td>
            ${RegistrationError}
        </td>
    </tr>
</form>


</body>
</html>
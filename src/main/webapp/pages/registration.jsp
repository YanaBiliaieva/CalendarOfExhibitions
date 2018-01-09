<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a class="button" href="/indexEnter">Index </a>
<table align="center">
    <form action="/regist" method="post">
        <tr>
            <td>Registration</td>
        </tr>
        <tr>
            <td>login*</td>
            <td><input type="text" firstname="login" id="011"></td>
        </tr>
        <tr>
            <td>password*</td>
            <td><input type="password" firstname="password" id="012"></td>
        </tr>
        <tr>
            <td>password confirm*</td>
            <td><input type="password" firstname="password2" id="013"></td>
        </tr>
        <tr>
            <td>Name*</td>
            <td><input type="text" firstname="firstname" id="014"></td>
        </tr>
        <tr>
            <td/>
            <td>
                <button type="submit">Registration</button>
            </td>
        </tr>
        <tr>
            <td>
                ${RegMistake}
            </td>
        </tr>
    </form>
</table>

</body>
</html>
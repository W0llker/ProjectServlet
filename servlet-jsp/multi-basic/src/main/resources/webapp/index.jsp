<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
</head>
<body>
<h1>
    <center>Авторизация!</center>
</h1>
<form   name="Form"
        method="get"
        action="http://localhost:8080/AuthenticationServlet">
    <center>
        <p><input type="text" name="login" placeholder="Логин" required></p>
        <p><input type="text" name="password" placeholder="Пароль" required></p>
        <input type=submit value="Войти">
        <h6> если вы не зарегистрированы, то пройдите <a href="/authentication/registration.jsp">Регистрацию</a></h6>
    </center>
</form>
</body>
</html>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .register-container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 350px;
            max-width: 90%;
        }

        h2 {
            text-align: center;
            color: #333333;
            margin-bottom: 30px;
        }

        label {
            display: block;
            font-weight: bold;
            color: #333333;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 16px;
        }

        .register-btn {
            background-color: #4CAF50;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
            width: 100%;
        }

        .register-btn:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            font-size: 14px;
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
        }

        .register-link a {
            color: #333333;
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        .register-link a:hover {
            color: #4CAF50;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h2>Регистрация пользователя</h2>
        <form name="Form" method="post" action="http://localhost:8080/Registration">
            <label for="firstname">Имя:</label>
            <input type="text" id="name" name="name">

            <label for="lastname">Фамилия:</label>
            <input type="text" id="surName" name="surName">

            <label for="username">Логин:</label>
            <input type="text" id="login" name="login">

            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password">

            <input type="submit" value="Зарегистрироваться" class="register-btn">
        </form>
        <div class="register-link">
            <h5 style="color: red">${error}</h5>
            <p>Уже есть аккаунт? <a href="/index.jsp">Войти</a></p>
        </div>
    </div>
</body>
</html>


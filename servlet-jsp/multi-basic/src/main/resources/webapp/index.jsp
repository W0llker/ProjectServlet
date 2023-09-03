<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<head>
    <meta charset="UTF-8">
  <title>Форма входа</title>
  <style>
   <style>
      h2 {
        color: #333;
      }

      img {
        width: 200px;
        border-radius: 50%;
        margin-bottom: 20px;
      }
      input[type="text"],
      input[type="password"] {
        display: block;
        margin: 10px auto;
        padding: 10px;
        width: 200px;
        border: 1px solid #ccc;
        border-radius: 5px;
      }
      input[type="submit"] {
        background: #4CAF50;
        color: #fff;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        cursor: pointer;
      }

      input[type="submit"]:hover {
        background: #45a049;
      }
    body {
      background: #f1f1f1;
      font-family: Arial, sans-serif;
      display: flex;
      align-items: center;
      justify-content: center;
      min-height: 100vh;
    }

    .container {
      background: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
      text-align: center;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Форма входа</h2>
    <img src="image/EazFDI9WoAEHsQ1.jpg" alt="Лиса">
    <form     name="Form"
              method="post"
              action="http://localhost:8080/login">
      <input type="text" id="login" name="login" placeholder="Логин">
      <input type="password" id="password" name="password" placeholder="Пароль">
      <input type="submit" value="Войти">
    </form>
    <h6 style="color: red">${error}</h6>
    <p>Нет аккаунта? <a href="/registration">Зарегистрируйтесь</a></p>
  </div>
</body>
</html>
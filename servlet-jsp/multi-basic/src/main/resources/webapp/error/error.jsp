<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Страница ошибок</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f8f8f8;
      margin: 0;
      padding: 0;
    }

    .container {
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
    }

    .error-box {
      text-align: center;
      padding: 40px;
      background-color: #fff;
      border-radius: 8px;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
    }

    h1 {
      margin-bottom: 20px;
      color: #333;
      font-size: 24px;
    }

    p {
      color: #777;
      font-size: 16px;
      line-height: 1.5;
    }

    .login-link {
      margin-top: 30px;
    }

    .login-link a {
      color: #007bff;
      text-decoration: none;
      font-size: 16px;
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="error-box">
          <h1>Ошибка</h1>
          <p><h2 style="color: red">${error}</h2></p>
          <div class="login-link">
            <a href="index.jsp">Войти</a>
          </div>
      </div>
    </div>
  </div>
</body>
</html>
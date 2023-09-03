<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Мой магазин</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 20px;
            text-align: center;
        }

        main {
            width: 80%;
            margin: 20px auto;
        }

        h1 {
            color: #fff;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .product {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
        }

        .product img {
            width: 150px;
            margin-right: 20px;
        }

        .product .info {
            flex-grow: 1;
        }

        .product h3 {
            margin: 0;
            font-size: 20px;
            color: #333;
        }

        .product p {
            margin: 0;
            color: #666;
        }

        .buy-form {
            display: flex;
            align-items: center;
            justify-content: flex-end;
        }

        .buy-input {
            width: 50px;
            height: 20px;
        }

        .buy-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<header>
   <h1>Добро пожаловать в магазин!</h1>
  <form class="buy-form" method="POST" action="/actionClient">
        <input type="hidden" name="basket" value="basket">
        <button type="submit"><img src="image/basket.png" alt="Корзина"></button>
  </form>
</header>
<main>
    <c:forEach var="product" items="${products}">
            <div class="product">
                <img src="image/${product.typeGood}.jpg" alt="Товар 1">
                <div class="info">
                    <h3>${product.nameProduct}</h3>
                    <p>${product.typeGood}</p>
                    <p>${product.price}</p>
                    <form class="buy-form" method="POST" action="/actionClient">
                        <input type="hidden" name="id" value="${product.id}">
                        <input type="number" name="count" class="buy-input" min="1" value="1">
                        <input type="submit" class="buy-button" value="Купить">
                    </form>
                </div>
            </div>
        </c:forEach>
</main>
</body>
</html>
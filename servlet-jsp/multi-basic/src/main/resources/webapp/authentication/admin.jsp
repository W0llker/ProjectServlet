<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html lang="ru">
<html>
<head>
    <title>Админ</title>
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
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-gap: 20px;
        }

        .product {
            display: flex;
            align-items: center;
            margin-bottom: 20px;
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
        }

        .product p {
            margin: 0;
            color: #666;
        }

        form {
            margin-top: 20px;
        }

        .form-row {
            display: flex;
            margin-bottom: 10px;
        }

        .form-row label {
            display: block;
            font-weight: bold;
            margin-right: 5px;
        }

        .form-row select,
        .form-row input {
            margin-right: 10px;
        }

        .form-row input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .product-list {
            height: 300px;
            overflow-y: scroll;
            margin-top: 20px;
        }

        .product-list button {
            margin-top: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .button-container {
            display: flex;
            justify-content: flex-end;
            margin-top: 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Администрация!</h1>
</header>

<main>
    <!-- Форма для добавления товара -->
    <form action="http://localhost:8080/Product" method="post">
        <input type="hidden" name="addProduct" value="add">
        <div class="form-row">
            <label for="type">Тип товара:</label>
            <select name="type" id="type">
                <option value="COMPUTER">Компьютеры</option>
                <option value="MOBILE_PHONE">Мобильный телефон</option>
                <option value="ACCESSORIES">Комплектующие</option>
                <!-- Добавьте здесь другие варианты типов товаров -->
            </select>
        </div>

        <div class="form-row">
            <label for="name">Наименование:</label>
            <input type="text" name="name" id="name">
        </div>

        <div class="form-row">
            <label for="code">Код:</label>
            <input type="number" name="code" id="code">
        </div>

        <div class="form-row">
            <label for="price">Цена:</label>
            <input type="number" name="price" id="price">
        </div>

        <input type="submit" value="Добавить товар">
    </form>

    <!-- Форма для изменения товара -->
    <form action="http://localhost:8080/Product" method="post">
        <input type="hidden" name="editProduct" value="edit">
        <div class="form-row">
            <label for="edit_id">ID товара:</label>
            <select name="edit_id" id="edit_id">
             <c:forEach var="product" items="${products}">
             <c:out value="${product}" />
             <option value="${product.id}">"${product.id}"</option>
             </c:forEach>
             </select>
        </div>

        <div class="form-row">
            <label for="edit_type">Тип товара:</label>
            <select name="edit_type" id="edit_type">
                <option value="COMPUTER">Компьютеры</option>
                <option value="MOBILE_PHONE">Мобильный телефон</option>
                <option value="ACCESSORIES">Комплектующие</option>
                <!-- Добавьте здесь другие варианты типов товаров -->
            </select>
        </div>

        <div class="form-row">
            <label for="edit_name">Наименование:</label>
            <input type="text" name="edit_name" id="edit_name">
        </div>

        <div class="form-row">
            <label for="edit_code">Код:</label>
            <input type="text" name="edit_code" id="edit_code">
        </div>

        <div class="form-row">
            <label for="edit_price">Цена:</label>
            <input type="text" name="edit_price" id="edit_price">
        </div>

        <input type="submit" value="Изменить товар">
    </form>

    <!-- Форма для удаления товара -->
    <form action="http://localhost:8080/Product" method="post">
    <input type="hidden" name="deleteProduct" value="delete">
        <div class="form-row">
            <label for="delete_id">ID товара:</label>
            <input type="text" name="delete_id" id="delete_id">
        </div>
        <input type="submit" value="Удалить товар">
    </form>

    <!-- Форма для просмотра списка товаров -->
    <form action="http://localhost:8080/Product" method="post">
    <input type="hidden" name="showProduct" value="show">
        <div class="product-list">
                <c:forEach var="product" items="${products}">
                     <h5><c:out value="${product}" />
                     <br></h5>
                </c:forEach>
            <!-- Здесь будет генерация списка товаров по клику на кнопку "Обновить" -->
        </div>
        <div class="button-container">
            <button type="submit">Обновить товары</button>
        </div>
    </form>
</main>
</body>
</html>
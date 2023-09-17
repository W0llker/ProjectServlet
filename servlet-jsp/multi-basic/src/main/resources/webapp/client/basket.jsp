<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
  <title>Корзина</title>
  <style>

    body {
      font-family: Arial, sans-serif;
      background-color: #f1f1f1;
      margin: 0;
      padding: 20px;
    }

    h2 {
      color: #333;
    }

    ul {
      list-style-type: none;
      padding: 0;
      margin: 0;
    }

    li {
      margin-bottom: 10px;
      padding: 10px;
      background-color: #fff;
      border-radius: 5px;
      box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
    }

    .cart-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 20px;
    }

    .cart-header h2 {
      margin: 0;
    }

    .product-name {
      font-weight: bold;
    }

    .product-details {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin-top: 5px;
      color: #888;
    }

    .remove-button {
      background-color: #ccc;
      color: #fff;
      padding: 5px 10px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }

    .checkout-button {
      margin-top: 20px;
      background-color: #4caf50;
      color: #fff;
      padding: 10px 20px;
      border: none;
      border-radius: 3px;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <div class="cart-header">
    <h2>Список покупок</h2>
    <a href="/actionClient">Вернуться в магазин</a>
  </div>
  <c:if test="${orderDto.basket.size() > 0}" var="result" scope="request">
  <c:forEach var="i" begin="0" end="${fn:length(orderDto.basket) - 1}">
  <ul>
    <li>
      <span class="product-name"><c:out value="${orderDto.products.get(i).nameProduct}"/></span>
      <div class="product-details">
        <span>Количество:<c:out value="${orderDto.basket.get(i).count}"/> </span>
        <span>Стоимость:<c:out value="${orderDto.basket.get(i).price}"/> BYN</span>
      </div>
      <form action="/basket" method="post">
        <input type="hidden" name="id_delete" value="${orderDto.basket.get(i).id}">
        <input type="submit" class="remove-button" value="Убрать">
      </form>
    </li>
  </ul>
</c:forEach>
</c:if>
  <div class="total-price">
  <c:set var="ternaryResult" value="${(requestScope.result !='false') ? '' : 'disable'}" />
      Общая стоимость: <c:out value="${orderDto.cost}"/> BYN
  </div>
  <form action="/basket" method="post">
    <input type="hidden" name="place_an_order" value="${ternaryResult}">
    <input type="hidden" name="id_order" value="${orderDto.id}">
    <input type="hidden" name="id_user" value="${orderDto.userId}">
    <input type="hidden" name="cost" value="${orderDto.cost}">
    <button class="checkout-button" type="submit" >Оформить заказ</button>
  </form>
</body>
</html>
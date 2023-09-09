package multi.basic.service;

import multi.api.contract.OrderApi;
import multi.api.dto.basket.BasketResponse;
import multi.api.dto.basket.OrderDto;
import multi.api.dto.order.OrderRequest;
import multi.api.dto.order.OrderResponse;
import multi.api.dto.product.ProductResponse;
import multi.api.exception.basket.BasketDbException;
import multi.basic.mapping.BasketMapper;
import multi.basic.mapping.OrderMapper;
import multi.basic.mapping.ProductMapper;
import multi.basic.repository.BasketDao;
import multi.basic.repository.OrderDao;
import multi.basic.repository.ProductDao;
import multi.domain.Basket;
import multi.domain.Order;
import multi.domain.Product;
import multi.domain.StatusOrder;

import java.util.List;
import java.util.Optional;

public class OrderService implements OrderApi {
    private OrderMapper orderMapper;
    private OrderDao repositoryOrder;
    private ProductDao repositoryProduct;
    private BasketDao repositoryBasket;
    private BasketMapper basketMapper;
    private ProductMapper productMapper;

    public OrderService(OrderMapper orderMapper, OrderDao repositoryOrder, ProductDao repositoryProduct, BasketDao repositoryBasket, BasketMapper basketMapper, ProductMapper productMapper) {
        this.orderMapper = orderMapper;
        this.repositoryOrder = repositoryOrder;
        this.repositoryProduct = repositoryProduct;
        this.repositoryBasket = repositoryBasket;
        this.basketMapper = basketMapper;
        this.productMapper = productMapper;
    }

    @Override
    public OrderResponse createOrder(long userId) {
        Order order;
        Optional<Order> cheakOrder = repositoryOrder.findOrderByUserId(userId).stream().filter(order1 -> order1.getStatus() == StatusOrder.NOT_FORMED).findFirst();
        if (cheakOrder.isEmpty()) {
            order = new Order(0, userId, 0, StatusOrder.NOT_FORMED);
            repositoryOrder.save(order);
        } else order = cheakOrder.get();
        return orderMapper.createResponse(order);
    }

    @Override
    public List<OrderResponse> getOrderByUser(Long userId) {
        return null;
    }

    @Override
    public OrderResponse changeStatus(OrderRequest orderRequest, StatusOrder statusOrder) {
        Order order = orderMapper.createOrder(orderRequest);
        order.setStatus(statusOrder);
        repositoryOrder.update(order);
        return orderMapper.createResponse(order);
    }

    @Override
    public OrderDto getOrderOrderId(long orderId) throws BasketDbException {
        Order order = repositoryOrder.findById(orderId);
        List<Basket> baskets = repositoryBasket.getBasketsByUser(orderId);
        List<Long> productIds = baskets.stream().map(Basket::getProductId).toList();
        List<Product> productList = repositoryProduct.findProductByIds(productIds);
        List<ProductResponse> productResponses = productList.stream().map(product -> productMapper.createResponse(product)).toList();
        List<BasketResponse> basketResponses = baskets.stream().map(basket -> basketMapper.createBasketResponse(basket)).toList();
        return orderMapper.createOrderDto(order, productResponses, basketResponses);
    }
}

package multi.basic.mapping;

import multi.api.dto.basket.BasketResponse;
import multi.api.dto.basket.OrderDto;
import multi.api.dto.order.OrderRequest;
import multi.api.dto.order.OrderResponse;
import multi.api.dto.product.ProductRequest;
import multi.api.dto.product.ProductResponse;
import multi.domain.Order;
import multi.domain.Product;
import multi.domain.StatusOrder;

import java.util.List;

public class OrderMapper {
    public Order createOrder(OrderRequest orderRequest) {
        return new Order(orderRequest.getSessionId(), orderRequest.getUserId(), 0, StatusOrder.NOT_FORMED);
    }

    public OrderResponse createResponse(Order order) {
        return new OrderResponse(order.getId(),order.getUserId(),order.getCost(),order.getStatus());
    }
    public OrderDto createOrderDto(Order order, List<ProductResponse> productResponses, List<BasketResponse> basketResponses) {
        return new OrderDto(order.getId(),basketResponses,productResponses,order.getUserId());
    }
}

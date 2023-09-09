package multi.api.contract;

import multi.api.dto.basket.OrderDto;
import multi.api.dto.order.OrderRequest;
import multi.api.dto.order.OrderResponse;
import multi.api.exception.basket.BasketDbException;
import multi.domain.StatusOrder;

import java.util.List;

public interface OrderApi {
    OrderResponse createOrder(long userId);

    List<OrderResponse> getOrderByUser(Long userId);

    OrderResponse changeStatus(OrderRequest orderRequest, StatusOrder statusOrder);

    OrderDto getOrderOrderId(long orderId);
}

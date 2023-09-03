package multi.api.contract;

import multi.api.dto.basket.BasketResponse;
import multi.domain.Basket;

import java.util.List;

public interface BasketApi {
    BasketResponse createBasket(long orderId, long productId, int count);
    void deleteBasket(long id);
    List<BasketResponse> getBasketByOrderId(long id);
}

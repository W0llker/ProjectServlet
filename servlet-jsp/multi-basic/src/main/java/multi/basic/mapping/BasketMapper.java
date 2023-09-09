package multi.basic.mapping;

import multi.api.dto.basket.BasketResponse;
import multi.domain.Basket;

public class BasketMapper {
    public BasketResponse createBasketResponse(Basket basket) {
        return new BasketResponse(basket.getId(), basket.getCount(), basket.getProductId());
    }
}

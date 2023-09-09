package multi.basic.repository;

import multi.domain.Basket;

import java.util.List;

public interface BasketDao {
    Basket save(Basket basket);

    void delete(long id);

    void deleteByOrderId(Long id);

    List<Basket> getBasketsByUser(long orderId);

    Basket findById(int sessionId);
}

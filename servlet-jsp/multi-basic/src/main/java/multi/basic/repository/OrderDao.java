package multi.basic.repository;

import multi.domain.Order;

import java.util.List;

public interface OrderDao {
    Order save(Order order);

    List<Order> findOrderByUserId(long userId);

    Order update(Order order);

    Order findById(long id);

    List<Order> getAllOrder();
}

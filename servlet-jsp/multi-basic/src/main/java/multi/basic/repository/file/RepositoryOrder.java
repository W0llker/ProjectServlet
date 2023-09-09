package multi.basic.repository.file;

import multi.basic.repository.OrderDao;
import multi.domain.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositoryOrder extends FileWork<Order> implements OrderDao {
    public RepositoryOrder() {
        path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-basic\\src\\main\\resources\\ListOrder";
        list = new ArrayList<>();
    }

    @Override
    public Order save(Order order) {
        list = deserialization();
        order.setId(deserialization().stream().max(Comparator.comparing(Order::getId)).get().getId() + 1);
        list.add(order);
        serialization(list);
        return order;
    }

    @Override
    public List<Order> findOrderByUserId(long userId) {
        return deserialization().stream().filter(order -> order.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Order update(Order order) {
        return order;
    }

    @Override
    public Order findById(long id) {
        return deserialization().stream().filter(order -> order.getId() == id).findFirst().get();
    }

    @Override
    public List<Order> getAllOrder() {
        return deserialization();
    }
}

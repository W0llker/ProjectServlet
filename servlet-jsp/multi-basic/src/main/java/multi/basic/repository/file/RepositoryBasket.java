package multi.basic.repository.file;

import multi.basic.repository.BasketDao;
import multi.domain.Basket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RepositoryBasket extends FileWork<Basket> implements BasketDao {
    public RepositoryBasket() {
        path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-basic\\src\\main\\resources\\ListBasket";
        list = new ArrayList<>();
    }

    @Override
    public Basket save(Basket basket) {
        list = deserialization();
        try {
            if (deserialization().size() > 0) {
                Optional<Basket> optional = deserialization().stream().max(Comparator.comparing(Basket::getId));
                basket.setId(optional.get().getId() + 1);
            } else {
                basket.setId(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        list.add(basket);
        serialization(list);
        return basket;
    }

    @Override
    public void delete(long id) {
        list = deserialization();
        list.removeIf(client -> client.getId() == id);
        serialization(list);
    }

    @Override
    public void deleteByOrderId(Long id) {

    }

    @Override
    public List<Basket> getBasketsByUser(long orderId) {
        list = deserialization();
        return list.stream().filter(basket -> basket.getOrderId() == orderId).collect(Collectors.toList());
    }

    @Override
    public Basket findById(int id) {
        list = deserialization();
        Optional<Basket> optional = list.stream().filter(basket -> basket.getId() == id).findAny();
        return optional.orElse(null);
    }
}

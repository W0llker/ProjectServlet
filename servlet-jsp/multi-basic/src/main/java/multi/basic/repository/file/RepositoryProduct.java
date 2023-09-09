package multi.basic.repository.file;


import multi.basic.repository.ProductDao;
import multi.domain.Product;

import java.util.*;
import java.util.stream.Collectors;

public class RepositoryProduct extends FileWork<Product> implements ProductDao {

    public RepositoryProduct() {
        path = "D:\\ProjectServletJsp\\servlet-jsp\\multi-basic\\src\\main\\resources\\ListProduct";
        list = new ArrayList<>();
    }

    public Product save(Product good) {
        list = deserialization();
        try {
            if (deserialization().size() > 0) {
                Optional<Product> optional = deserialization().stream().max(Comparator.comparing(Product::getId));
                good.setId(optional.get().getId() + 1);
            } else {
                good.setId(0);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        list.add(good);
        serialization(list);
        return good;
    }

    @Override
    public Product findProductByCode(Long code) {
        return null;
    }

    @Override
    public List<Product> findProductByIds(List<Long> longs) {
        return longs.stream().flatMap(x -> deserialization().stream().filter(product -> product.getId() == x)).collect(Collectors.toList());
    }

    public void delete(long id) {
        list = getAllProduct();
        list.removeIf(client -> client.getId() == id);
        serialization(list);
    }

    public Product find(long id) {
        list = getAllProduct();
        Optional<Product> optional = list.stream().filter(client -> client.getId() == id).findFirst();
        return optional.orElse(null);
    }
    public void update(Product product) {
        serialization(list);
    }

    public List<Product> getAllProduct() {
        return deserialization();
    }
}

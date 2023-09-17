package multi.basic.repository;

import multi.domain.Product;

import java.util.List;

public interface ProductDao {
    Product save(Product good);

    void delete(long id);

    Product find(long id);

    List<Product> findProductByIds(List<Long> longs);

    Product findProductByCode(Long code);

    void update(Product product);

    List<Product> getAllProduct();
}

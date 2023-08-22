package multi.api.contract;

import multi.api.dto.ProductRequest;
import multi.api.dto.ProductResponse;
import multi.domain.Product;

import java.util.List;

public interface ProductApi {
    void createProduct(ProductRequest productRequest);
    void updateProduct(int id, ProductRequest productRequest);
    void deleteProduct(int id);
    List<ProductResponse> showProduct();
}

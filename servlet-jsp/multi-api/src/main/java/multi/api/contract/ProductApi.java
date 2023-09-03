package multi.api.contract;

import multi.api.dto.product.ProductRequest;
import multi.api.dto.product.ProductResponse;

import java.util.List;

public interface ProductApi {
    void createProduct(ProductRequest productRequest);
    void updateProduct(long id, ProductRequest productRequest);
    void deleteProduct(int id);
    List<ProductResponse> showProduct();
}

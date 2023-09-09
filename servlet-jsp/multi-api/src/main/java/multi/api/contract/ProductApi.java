package multi.api.contract;

import multi.api.dto.product.ProductRequest;
import multi.api.dto.product.ProductResponse;
import multi.api.exception.product.ProductDbException;
import multi.api.exception.product.ProductValidatorException;

import java.util.List;

public interface ProductApi {
    void createProduct(ProductRequest productRequest) throws ProductValidatorException;

    void updateProduct(long id, ProductRequest productRequest) throws ProductValidatorException;

    void deleteProduct(long id);

    List<ProductResponse> showProduct();
}

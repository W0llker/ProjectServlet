package multi.api.mapping;

import multi.api.dto.ProductRequest;
import multi.api.dto.ProductResponse;
import multi.api.dto.UserRequest;
import multi.api.dto.UserResponse;
import multi.core.domain.Client;
import multi.core.domain.Product;

public class ProductMapping {
    public static Product createProduct(ProductRequest productRequest) {
        return new Product(productRequest.getTypeGood(), productRequest.getNameProduct(), productRequest.getCodeProduct(), productRequest.getPrice());
    }

    public static ProductResponse createResponse(Product product) {
        return new ProductResponse(product.getId(), product.getTypeGood(), product.getNameProduct(), product.getCodeProduct(), product.getPrice());
    }
}

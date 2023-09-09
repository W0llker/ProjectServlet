package multi.basic.mapping;


import multi.api.dto.product.ProductRequest;
import multi.api.dto.product.ProductResponse;
import multi.domain.Product;

public class ProductMapper {
    public Product createProduct(ProductRequest productRequest) {
        return new Product(productRequest.getTypeGood(), productRequest.getNameProduct(), productRequest.getCodeProduct(), productRequest.getPrice());
    }

    public ProductResponse createResponse(Product product) {
        return new ProductResponse(product.getId(), product.getTypeGood(), product.getNameProduct(), product.getCodeProduct(), product.getPrice());
    }
}

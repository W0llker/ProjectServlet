package multi.basic.service;

import multi.api.contract.ProductApi;
import multi.api.dto.product.ProductRequest;
import multi.api.dto.product.ProductResponse;
import multi.basic.mapping.ProductMapper;
import multi.basic.repository.ProductDao;
import multi.basic.repository.file.RepositoryProduct;
import multi.domain.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductService implements ProductApi {
    private ProductDao repositoryProduct;
    private ProductMapper productMapper;

    public ProductService(ProductDao repositoryProduct, ProductMapper productMapper) {
        this.repositoryProduct = repositoryProduct;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        repositoryProduct.save(productMapper.createProduct(productRequest));
    }

    @Override
    public void updateProduct(long id, ProductRequest productRequest) {
        Product product = productMapper.createProduct(productRequest);
        product.setId(id);
        repositoryProduct.update(product);
    }

    @Override
    public void deleteProduct(int id) {
        repositoryProduct.delete(id);
    }

    @Override
    public List<ProductResponse> showProduct() {
        List<Product> products = repositoryProduct.getAllProduct();
        List<ProductResponse> list = new ArrayList<>();
        for (Product product : products) {
            list.add(productMapper.createResponse(product));
        }
        return list;
    }
}

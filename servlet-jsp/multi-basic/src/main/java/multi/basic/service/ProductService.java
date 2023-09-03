package multi.basic.service;

import multi.api.contract.ProductApi;
import multi.api.dto.ProductRequest;
import multi.api.dto.ProductResponse;
import multi.basic.mapping.ProductMapper;
import multi.basic.repository.RepositoryProduct;
import multi.domain.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductService implements ProductApi {
    private RepositoryProduct repositoryProduct;
    private ProductMapper productMapper;

    public ProductService(RepositoryProduct repositoryProduct, ProductMapper productMapper) {
        this.repositoryProduct = repositoryProduct;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        repositoryProduct.save(productMapper.createProduct(productRequest));
    }

    @Override
    public void updateProduct(int id, ProductRequest productRequest) {
        Product product = repositoryProduct.find(id);
        product.setCodeProduct(productRequest.getCodeProduct());
        product.setNameProduct(productRequest.getNameProduct());
        product.setTypeGood(productRequest.getTypeGood());
        product.setPrice(productRequest.getPrice());
        repositoryProduct.update();
    }

    @Override
    public void deleteProduct(int id) {
        repositoryProduct.delete(id);
    }

    @Override
    public List<ProductResponse> showProduct() {
        List<ProductResponse> list = new ArrayList<>();
        for (Product product : repositoryProduct.getList()) {
            list.add(productMapper.createResponse(product));
        }
        return list;
    }
}

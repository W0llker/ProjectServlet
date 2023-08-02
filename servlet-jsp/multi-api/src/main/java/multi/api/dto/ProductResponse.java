package multi.api.dto;

import multi.core.domain.TypeProduct;

public class ProductResponse {
    private long id;
    private TypeProduct typeGood;
    private String nameProduct;
    private long codeProduct;
    private double price;

    public ProductResponse(long id, TypeProduct typeGood, String nameProduct, long codeProduct, double price) {
        this.id = id;
        this.typeGood = typeGood;
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.price = price;
    }
}

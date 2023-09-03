package multi.api.dto.product;


import multi.domain.TypeProduct;

public class ProductRequest {
    private TypeProduct typeGood;
    private String nameProduct;
    private long codeProduct;
    private double price;

    public ProductRequest(TypeProduct typeGood, String nameProduct, long codeProduct, double price) {
        this.typeGood = typeGood;
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.price = price;
    }

    public TypeProduct getTypeGood() {
        return typeGood;
    }

    public void setTypeGood(TypeProduct typeGood) {
        this.typeGood = typeGood;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(long codeProduct) {
        this.codeProduct = codeProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

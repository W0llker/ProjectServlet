package multi.domain;

import java.io.Serial;
import java.io.Serializable;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -5614387957786689456L;
    private long id;
    private TypeProduct typeGood;
    private String nameProduct;
    private long codeProduct;
    private double price;

    public Product() {
    }

    public Product(TypeProduct typeGood, String nameProduct, long codeProduct, double price) {
        this.typeGood = typeGood;
        this.nameProduct = nameProduct;
        this.codeProduct = codeProduct;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private void setId(String id) {
        this.id = Long.parseLong(id);
    }

    public TypeProduct getTypeGood() {
        return typeGood;
    }

    public void setTypeGood(TypeProduct typeGood) {
        this.typeGood = typeGood;
    }

    private void setTypeGood(String typeGood) {
        this.typeGood = TypeProduct.valueOf(typeGood);
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

    private void setCodeProduct(String codeProduct) {
        this.codeProduct = Long.parseLong(codeProduct);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", typeGood=" + typeGood +
                ", nameProduct='" + nameProduct + '\'' +
                ", codeProduct=" + codeProduct +
                ", price=" + price +
                "\n";
    }
}

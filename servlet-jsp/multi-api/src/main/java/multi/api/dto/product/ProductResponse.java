package multi.api.dto.product;


import multi.domain.TypeProduct;

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

    @Override
    public String toString() {
        return
                "id=" + id +
                " Тип товара=" + typeGood +
                " имя товара='" + nameProduct + '\'' +
                " код товара=" + codeProduct +
                " цена=" + price +
                "\n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

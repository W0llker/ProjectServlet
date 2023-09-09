package multi.api.dto.basket;

public class BasketResponse {
    private long id;
    private long productId;
    private int count;
    private double price;

    public BasketResponse(long id, int count, long productId) {
        this.id = id;
        this.productId = productId;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "BasketResponse{" +
                "id=" + id +
                ", productId=" + productId +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}

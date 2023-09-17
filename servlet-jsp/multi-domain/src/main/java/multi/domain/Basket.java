package multi.domain;

import java.io.Serial;
import java.io.Serializable;

public class Basket implements Serializable {
    @Serial
    private static final long serialVersionUID = -2827420252014478073L;
    private long id;
    private long orderId;
    private long productId;
    private int count;

    public Basket() {
    }

    public Basket(long orderId, long productId, int count) {
        this.orderId = orderId;
        this.productId = productId;
        this.count = count;
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

    private void setOrderId(String orderId) {
        this.orderId = Long.parseLong(orderId);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    private void setProductId(String productId) {
        this.productId = Long.parseLong(productId);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private void setCount(String count) {
        this.count = Integer.parseInt(count);
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", count=" + count +
                '}';
    }
}

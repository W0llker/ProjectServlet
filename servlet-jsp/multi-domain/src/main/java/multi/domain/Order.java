package multi.domain;

import java.io.Serial;
import java.io.Serializable;

public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 7711185135064143048L;
    private long id;
    private long userId;
    private double cost;
    private StatusOrder status;

    public Order() {
    }

    public Order(long id, long userId, double cost, StatusOrder status) {
        this.id = id;
        this.userId = userId;
        this.cost = cost;
        this.status = status;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private void setUserId(String userId) {
        this.userId = Long.parseLong(userId);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setCost(String cost) {
        this.cost = Double.parseDouble(cost);
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = StatusOrder.valueOf(status);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }
}

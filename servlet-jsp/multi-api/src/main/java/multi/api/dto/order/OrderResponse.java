package multi.api.dto.order;

import multi.api.dto.product.ProductResponse;
import multi.domain.Product;
import multi.domain.StatusOrder;

import java.util.List;

public class OrderResponse {
    private long id;
    private long userId;
    private double cost;
    private StatusOrder status;

    public OrderResponse(long id, long userId, double cost, StatusOrder status) {
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", userId=" + userId +
                ", cost=" + cost +
                ", status=" + status +
                '}';
    }
}

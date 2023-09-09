package multi.api.dto.order;

public class OrderRequest {
    private long sessionId;
    private long userId;
    private double cost;

    public OrderRequest(int sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
    }

    public OrderRequest(long sessionId, long userId, double cost) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.cost = cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

package multi.api.dto.order;

public class OrderRequest {
    private long sessionId;
    private long userId;

    public OrderRequest(int sessionId, int userId) {
        this.sessionId = sessionId;
        this.userId = userId;
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

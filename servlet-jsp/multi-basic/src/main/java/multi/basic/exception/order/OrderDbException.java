package multi.basic.exception.order;

public class OrderDbException extends RuntimeException {
    public OrderDbException(String message) {
        super(message);
    }
}

package multi.basic.exception.client;

public class ClientDbException extends RuntimeException {
    public ClientDbException(String message) {
        super(message);
    }
}

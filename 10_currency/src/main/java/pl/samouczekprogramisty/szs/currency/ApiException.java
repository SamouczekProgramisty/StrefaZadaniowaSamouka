package pl.samouczekprogramisty.szs.currency;

public class ApiException extends RuntimeException {
    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message) {
        super(message);
    }
}

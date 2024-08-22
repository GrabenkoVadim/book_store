package mate.academy.book_store.exception;

public class SpecificationException extends RuntimeException {
    public SpecificationException(String message) {
        super(message);
    }

    public SpecificationException(String message, Throwable cause) {
        super(message, cause);
    }
}

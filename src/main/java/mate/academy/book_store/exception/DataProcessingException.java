package mate.academy.book_store.exception;

public class DataProcessingException extends RuntimeException{
    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

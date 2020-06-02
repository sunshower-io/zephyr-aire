package io.zephyr.aire.api;

public class AireException extends RuntimeException {

    public AireException() {
    }

    public AireException(String message) {
        super(message);
    }

    public AireException(String message, Throwable cause) {
        super(message, cause);
    }

    public AireException(Throwable cause) {
        super(cause);
    }

    public AireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

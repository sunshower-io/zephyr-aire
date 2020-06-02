package io.zephyr.aire.api;

public class ViewDecorationFailureException extends AireException {
  public ViewDecorationFailureException() {}

  public ViewDecorationFailureException(String message) {
    super(message);
  }

  public ViewDecorationFailureException(String message, Throwable cause) {
    super(message, cause);
  }

  public ViewDecorationFailureException(Throwable cause) {
    super(cause);
  }

  public ViewDecorationFailureException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

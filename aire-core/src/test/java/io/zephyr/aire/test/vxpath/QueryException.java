package io.zephyr.aire.test.vxpath;

import java.io.IOException;

public class QueryException extends RuntimeException {
  public QueryException(IOException ex) {
    super(ex);
  }
}

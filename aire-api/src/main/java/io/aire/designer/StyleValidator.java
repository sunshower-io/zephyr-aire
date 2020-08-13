package io.aire.designer;

import java.io.Serializable;

public interface StyleValidator<T> {

  boolean validate(T value);
}

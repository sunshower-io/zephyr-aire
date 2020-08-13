package io.aire.designer;

public interface Alignment {

  enum Mode {
    Vertical,
    Horizontal
  }

  Mode getMode();

  String getValue();


}

final class DefaultAlignment implements Alignment {
  final Mode mode;
  final String value;

  DefaultAlignment(Mode mode, String value) {
    this.mode = mode;
    this.value = value;
  }

  @Override
  public Mode getMode() {
    return mode;
  }

  @Override
  public String getValue() {
    return value;
  }
}

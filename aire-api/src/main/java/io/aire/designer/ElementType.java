package io.aire.designer;

/**
 * a reference defines what a node refers to, which can be either another group of nodes
 * (Type.Reference) or a value
 */
public class ElementType {

  enum Mode {
    Reference,
    Value
  }

  final Mode mode;
  final Class<?> value;
  final String reference;

  public ElementType(Class<?> value) {
    this.mode = Mode.Value;
    this.value = value;
    this.reference = null;
  }

  public ElementType(String reference) {
    this.mode = Mode.Reference;
    this.reference = reference;
    this.value = null;
  }

  @Override
  public String toString() {
    return "ElementType{"
        + "mode="
        + mode
        + ", value="
        + value
        + ", reference='"
        + reference
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ElementType that = (ElementType) o;

    if (mode != that.mode) return false;
    if (value != null ? !value.equals(that.value) : that.value != null) return false;
    return reference != null ? reference.equals(that.reference) : that.reference == null;
  }

  @Override
  public int hashCode() {
    int result = mode != null ? mode.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = 31 * result + (reference != null ? reference.hashCode() : 0);
    return result;
  }
}

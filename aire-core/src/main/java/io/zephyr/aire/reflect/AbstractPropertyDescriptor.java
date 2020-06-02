package io.zephyr.aire.reflect;

abstract class AbstractPropertyDescriptor implements PropertyDescriptor {

  private final String name;
  private final String alias;
  private final Class<?> propertyType;

  protected AbstractPropertyDescriptor(String name, String alias, Class<?> propertyType) {
    this.name = name;
    this.alias = alias;
    this.propertyType = propertyType;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getAlias() {
    return alias;
  }

  public Class<?> getType() {
    return propertyType;
  }
}

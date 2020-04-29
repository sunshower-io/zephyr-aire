package io.zephyr.aire.reflect;

import java.util.HashMap;
import java.util.Map;

public class MutableAnnotatedPropertySet implements AnnotatedPropertySet {

  private final Map<String, PropertyDescriptor> descriptors;
  private final Map<String, PropertyDescriptor> aliasedDescriptors;

  public MutableAnnotatedPropertySet() {
    this.descriptors = new HashMap<>();
    this.aliasedDescriptors = new HashMap<>();
  }

  public void add(PropertyDescriptor descriptor) {
    descriptors.put(descriptor.getName(), descriptor);
    aliasedDescriptors.put(descriptor.getAlias(), descriptor);
  }

  @Override
  public int size() {
    return descriptors.size();
  }

  @Override
  public PropertyDescriptor get(String name) {
    return descriptors.get(name);
  }

  @Override
  public PropertyDescriptor getAliased(String alias) {
    return aliasedDescriptors.get(alias);
  }
}

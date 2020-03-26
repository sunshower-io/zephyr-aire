package io.zephyr.aire.extensions;

import io.sunshower.gyre.CompactTrieMap;
import io.sunshower.gyre.RegexStringAnalyzer;
import io.sunshower.gyre.TrieMap;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.val;

import java.util.*;

public class AireExtensionPointRegistry implements MutableExtensionPointRegistry {

  final TrieMap<String, ExtensionPointDefinition<?>> definitions;

  public AireExtensionPointRegistry() {
    definitions = new CompactTrieMap<>(new RegexStringAnalyzer(":"));
  }

  @Override
  public <T> void register(ExtensionPointDefinition<T> definition) {
    synchronized (definitions) {
      System.out.println("ADDING " + definition.getLocation());
      definitions.put(definition.getLocation(), definition);
    }
  }

  @Override
  public List<ExtensionPointDefinition<?>> getExtensionPoints() {
    return new ArrayList<>(definitions.values());
  }

  @Override
  public boolean containsDefinition(String beanName) {
    synchronized (definitions) {
      return definitions.containsKey(beanName);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> ExtensionPointDefinition<T> getDefinition(String name) {
    return (ExtensionPointDefinition<T>) definitions.get(name);
  }

  @Override
  public List<ExtensionPointDefinition<?>> getChildren(String s) {
    return definitions.level(s);
  }
}

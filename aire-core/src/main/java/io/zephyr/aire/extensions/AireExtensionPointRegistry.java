package io.zephyr.aire.extensions;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinService;
import io.aire.core.AireContainer;
import io.sunshower.gyre.Analyzer;
import io.sunshower.gyre.CompactTrieMap;
import io.sunshower.gyre.RegexStringAnalyzer;
import io.sunshower.gyre.TrieMap;
import io.zephyr.aire.annotation.ExtensionScanner;
import io.zephyr.aire.api.ExtensionDefinition;
import io.zephyr.aire.api.ExtensionPointDefinition;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import lombok.val;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import java.util.*;

public class AireExtensionPointRegistry implements MutableExtensionPointRegistry {

  static final Analyzer<String, String> analyzer = new RegexStringAnalyzer(":");

  private final ExtensionScanner scanner;
  private final ApplicationContext applicationContext;

  /** maps extensions to extension points */
  private final Map<String, Set<String>> extensionTargets;

  private final TrieMap<String, ExtensionPointDefinition<?>> definitions;
  private final Map<String, ExtensionDefinition<?>> extensionDefinitions;

  @Inject
  public AireExtensionPointRegistry(final ApplicationContext context) {
    this.scanner = new ExtensionScanner(this);
    this.applicationContext = context;
    this.extensionTargets = new HashMap<>();
    this.extensionDefinitions = new HashMap<>();
    this.definitions = new CompactTrieMap<>(analyzer);
  }

  @Override
  public <T> void register(ExtensionPointDefinition<T> definition) {
    synchronized (definitions) {
      definitions.put(definition.getLocation(), definition);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> void decorate(Class<T> type, T instance, ExtensionPointDefinition<T> definition) {

    for (ExtensionDefinition<?> extensionDef : extensionsFor(definition)) {
      val extType = extensionDef.getType();
      val extension = create(extType);

      if (ViewDecorator.class.isAssignableFrom(extType)) {
        ((ViewDecorator) extension).decorate((Component) instance);
      }

      if (AireContainer.class.isAssignableFrom(type) && extension instanceof Component) {
        ((AireContainer) instance).add((Component) extension);
      }
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

  @Override
  @SuppressWarnings("unchecked")
  public <T> T create(Class<T> type) {
    if (Component.class.isAssignableFrom(type)) {
      return (T)
          VaadinService.getCurrent().getInstantiator().createComponent((Class<Component>) type);
    }
    return (T)
        applicationContext
            .getAutowireCapableBeanFactory()
            .autowire(type, AutowireCapableBeanFactory.AUTOWIRE_NO, true);
  }

  @Override
  public <T> void register(Class<T> extension) {
    val result = scanner.scan(extension);
    if (result != null) {
      register(result);
    }
  }

  @Override
  public <T> void register(ExtensionDefinition<T> definition) {
    val id = definition.id();
    extensionDefinitions.put(id, definition);
    for (val target : definition.targets()) {
      extensionTargets.computeIfAbsent(target, t -> new HashSet<>()).add(id);
    }
  }

  @Override
  public boolean containsExtension(String id) {
    return extensionDefinitions.containsKey(id);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> ExtensionDefinition<T> getExtension(String id) {
    return (ExtensionDefinition<T>) extensionDefinitions.get(id);
  }

  @Override
  public <T> ExtensionDefinition<?>[] extensionsFor(ExtensionPointDefinition<T> definition) {
    val names = extensionTargets.get(definition.getLocation());
    if (names == null) {
      return new ExtensionDefinition[0];
    }
    val results = new ExtensionDefinition<?>[names.size()];
    int count = 0;
    for (val name : names) {
      results[count++] = getExtension(name);
    }
    return results;
  }
}

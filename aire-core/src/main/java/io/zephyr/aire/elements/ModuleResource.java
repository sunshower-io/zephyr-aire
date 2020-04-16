package io.zephyr.aire.elements;

import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import io.zephyr.kernel.Module;
import lombok.val;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.InputStream;
import java.lang.ref.WeakReference;

/** given a module, return a resource at [module-loader:/resource] */
public class ModuleResource extends StreamResource {

  public ModuleResource(String location, Module module) {
    super(location, new ModuleInputStreamFactory(location, module));
  }

  static class ModuleInputStreamFactory implements InputStreamFactory {
    final String location;
    final WeakReference<Module> moduleReference;

    static final InputStream EMPTY =
        new InputStream() {
          @Override
          public int read() {
            return -1;
          }
        };

    public ModuleInputStreamFactory(String location, Module module) {
      this.location = String.format("META-INF/public/%s", location);
      this.moduleReference = new WeakReference<>(module);
    }

    public boolean requiresLock() {
      return false;
    }

    @Override
    public InputStream createInputStream() {
      synchronized (moduleReference) {
        val module = moduleReference.get();

        if (module == null) {
          return EMPTY;
        }
        val result = module.getClassLoader().getResourceAsStream(location);
        if (result == null) {
          return EMPTY;
        }
        return result;
      }
    }
  }
}

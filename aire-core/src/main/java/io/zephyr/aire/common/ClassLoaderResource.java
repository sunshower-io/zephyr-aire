package io.zephyr.aire.common;

import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import lombok.val;

import java.io.InputStream;
import java.lang.ref.WeakReference;

public class ClassLoaderResource extends StreamResource {
  public ClassLoaderResource(String name, ClassLoader classLoader) {
    super(name, new ClassLoaderInputStreamFactory(name, classLoader));
  }

  static class ClassLoaderInputStreamFactory implements InputStreamFactory {

    private String location;
    private WeakReference<ClassLoader> reference;

    public ClassLoaderInputStreamFactory(String name, ClassLoader classLoader) {
      this.location = name;
      this.reference = new WeakReference<>(classLoader);
    }

    public boolean requiresLock() {
      return false;
    }

    @Override
    public InputStream createInputStream() {
      synchronized (reference) {
        val loader = reference.get();
        if (loader == null) {
          return ModuleResource.ModuleInputStreamFactory.EMPTY;
        }

        val result = loader.getResourceAsStream(location);
        if (result == null) {
          return ModuleResource.ModuleInputStreamFactory.EMPTY;
        }
        return result;
      }
    }
  }
}

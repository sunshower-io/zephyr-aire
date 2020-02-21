package io.zephyr.aire;

import io.sunshower.gyre.Scope;
import io.sunshower.yaml.state.YamlMemento;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.core.*;
import io.zephyr.kernel.dependencies.DependencyGraph;
import io.zephyr.kernel.launch.KernelOptions;
import io.zephyr.kernel.memento.Memento;
import io.zephyr.spring.embedded.EmbeddedModuleClasspath;
import io.zephyr.spring.embedded.EmbeddedModuleLoader;
import io.zephyr.spring.embedded.EmbeddedSpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.Collections;
import java.util.Optional;
import java.util.ServiceLoader;

@Configuration
@Import(EmbeddedSpringConfiguration.class)
public class AireConfiguration implements ApplicationListener<ContextRefreshedEvent> {

  //
  //    @Bean
  //    public FileSystem createFileSystem() {
  //
  //
  //    }

  @Bean
  public ClassLoader classLoader(ApplicationContext context) {
    return context.getClassLoader();
  }

  @Bean
  public Memento memento(ClassLoader classLoader) {
    return new YamlMemento();
    //    return ServiceLoader.load(MementoProvider.class,
    // classLoader).iterator().next().newMemento();
  }

  @Bean
  public File rootFile() {

    File file =
        new File(
            AireConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    KernelOptions opts = new KernelOptions();
    opts.setHomeDirectory(file);
    SunshowerKernel.setKernelOptions(opts);
    return file;
  }

  @Bean
  @DependsOn("rootFile")
  public FileSystem aireFileSystem() throws IOException {
    return FileSystems.newFileSystem(URI.create("droplet://aire"), Collections.emptyMap());
  }

  @Bean
  public ModuleClasspath moduleClasspath(ClassLoader classLoader) {
    return new EmbeddedModuleClasspath(new EmbeddedModuleLoader(classLoader));
  }

  @Bean
  public ModuleDescriptor moduleDescriptor(ClassLoader classLoader) {
    URL source = getClass().getProtectionDomain().getCodeSource().getLocation();
    File file = new File(source.getFile());
    for (ModuleScanner scanner : ServiceLoader.load(ModuleScanner.class, classLoader)) {

      final Optional<ModuleDescriptor> scan = scanner.scan(file, source);
      if (scan.isPresent()) {
        return scan.get();
      }
    }
    throw new IllegalStateException("No module descriptor somehow");
  }

  @Bean
  public ModuleClasspathManager moduleClasspathManager(
      DependencyGraph graph, ApplicationContext context, Kernel kernel) {
    ModuleClasspathManager mgr =
        Plugins.moduleClasspathManager(graph, context.getClassLoader(), kernel);
    ((SunshowerKernel) kernel).setModuleClasspathManager(mgr);
    return mgr;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ApplicationContext context = contextRefreshedEvent.getApplicationContext();
    Module module = context.getBean(Module.class);
    Kernel kernel = context.getBean(Kernel.class);
    DependencyGraph graph = kernel.getModuleManager().getDependencyGraph();
    graph.add(module);
    kernel.getModuleClasspathManager().install(module);
    module.getLifecycle().setState(Lifecycle.State.Installed);
  }
}

package io.zephyr.aire;

import com.vaadin.flow.di.Instantiator;
import com.vaadin.flow.server.VaadinContext;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletContext;
import com.vaadin.flow.spring.SpringVaadinServletService;
import io.sunshower.yaml.state.YamlMemento;
import io.zephyr.aire.annotation.ExtensionPointPostProcessor;
import io.zephyr.aire.annotation.ExtensionPointScanner;
import io.zephyr.aire.annotation.ExtensionScanner;
import io.zephyr.aire.api.ComponentRegistry;
import io.zephyr.aire.api.ExtensionPointRegistry;
import io.zephyr.aire.api.Session;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.decorators.DefaultMainViewDecorator;
import io.zephyr.aire.ext.MutableExtensionPointRegistry;
import io.zephyr.aire.extensions.AireExtensionPointRegistry;
import io.zephyr.api.ModuleActivator;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.concurrency.ModuleThread;
import io.zephyr.kernel.core.*;
import io.zephyr.kernel.dependencies.DependencyGraph;
import io.zephyr.kernel.launch.KernelLauncher;
import io.zephyr.kernel.launch.KernelOptions;
import io.zephyr.kernel.memento.Memento;
import io.zephyr.spring.embedded.EmbeddedModuleClasspath;
import io.zephyr.spring.embedded.EmbeddedModuleLoader;
import io.zephyr.spring.embedded.EmbeddedSpringConfiguration;
import lombok.val;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.servlet.ServletContext;
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

  @Bean
  public BeanPostProcessor extensionPointPostProcessor(MutableExtensionPointRegistry registry) {
    return new ExtensionPointPostProcessor(registry, new ExtensionPointScanner(registry));
  }

  @Bean
  public Session session() {
    return new Session() {};
  }

  @Bean
  public ClassLoader classLoader(ApplicationContext context) {
    return context.getClassLoader();
  }

  @Bean
  public Memento memento(ClassLoader classLoader) {
    return new YamlMemento();
  }

  @Bean
  public File rootFile() {

    File file =
        new File(
            AireConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    KernelOptions opts = new KernelOptions();
    opts.setHomeDirectory(file.getParentFile());
    SunshowerKernel.setKernelOptions(opts);
    return file;
  }

  @Bean
  public VaadinContext vaadinContext(ServletContext context) {
    return new VaadinServletContext(context);
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
  @Primary
  public ModuleClasspathManager moduleClasspathManager(
      DependencyGraph graph, ApplicationContext context, Kernel kernel) {
    ModuleClasspathManager mgr =
        Plugins.moduleClasspathManager(graph, context.getClassLoader(), kernel);
    ((SunshowerKernel) kernel).setModuleClasspathManager(mgr);
    return mgr;
  }

  @Bean
  public ModuleActivator moduleActivator() {
    return new AireModuleActivator();
  }

  @Bean
  public MutableExtensionPointRegistry extensionPointRegistry(ApplicationContext context) {
    val result = new AireExtensionPointRegistry(context);
    result.register(DefaultMainViewDecorator.class);
    return result;
  }


  @Bean
  public ViewManager viewManager(ExtensionPointRegistry registry) {
    return new VaadinViewManager((ComponentRegistry) registry, registry);
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    ApplicationContext context = contextRefreshedEvent.getApplicationContext();
    Module module = context.getBean(Module.class);
    Kernel kernel = context.getBean(Kernel.class);
    ModuleThread moduleThread = context.getBean(ModuleThread.class);
    ViewManager viewManager = context.getBean(ViewManager.class);
    kernel.start();
    DependencyGraph graph = kernel.getModuleManager().getDependencyGraph();
    graph.add(module);
    kernel.getModuleClasspathManager().install(module);
    module.getLifecycle().setState(Lifecycle.State.Installed);
    kernel
        .getScheduler()
        .getKernelExecutor()
        .submit(
            () -> {
              KernelLauncher.main(
                  new String[] {
                    "--scan", "--watch", "/home/josiah/sunshower/watch", "--install-on-start"
                  });
            });
    kernel.getVolatileStorage().set(ApplicationContext.class, context);
    kernel.createContext(module, moduleThread).register(ViewManager.class, viewManager);
  }
}

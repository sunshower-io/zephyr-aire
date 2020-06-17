package io.zephyr.aire;

import com.vaadin.flow.spring.annotation.EnableVaadin;
import io.sunshower.yaml.state.YamlMemento;
import io.zephyr.aire.api.*;
import io.zephyr.aire.core.deployments.DeploymentScanner;
import io.zephyr.aire.core.ui.ModuleViewRegistrationPostProcessor;
import io.zephyr.aire.core.ui.VaadinViewManager;
import io.zephyr.aire.servlet.ModuleResourceServlet;
import io.zephyr.aire.util.YamlPropertySourceFactory;
import io.zephyr.api.ModuleActivator;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.Module;
import io.zephyr.kernel.concurrency.ModuleThread;
import io.zephyr.kernel.core.*;
import io.zephyr.kernel.dependencies.DependencyGraph;
import io.zephyr.kernel.launch.KernelOptions;
import io.zephyr.kernel.memento.Memento;
import io.zephyr.spring.embedded.EmbeddedModuleClasspath;
import io.zephyr.spring.embedded.EmbeddedModuleLoader;
import io.zephyr.spring.embedded.EmbeddedSpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.util.*;

@Slf4j
@EnableVaadin
@Configuration
@Import(EmbeddedSpringConfiguration.class)
public class AireConfiguration implements ApplicationListener<ApplicationReadyEvent> {

  @Bean
  public DeploymentScanner deploymentScanner(
      Kernel kernel, @Value("${deployment.location}") String location) throws IOException {
    return new DeploymentScanner(kernel, Collections.singleton(location));
  }

  @Bean
  public ServletRegistrationBean<ModuleResourceServlet>
      moduleResourceServletServletRegistrationBean() {
    return new ServletRegistrationBean<>(new ModuleResourceServlet(), "/modules/*");
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

    //    File file =
    //        new File(
    //
    // AireConfiguration.class.getProtectionDomain().getCodeSource().getLocation().getFile());
    val file = new File(System.getProperty("user.dir"));
    KernelOptions opts = new KernelOptions();
    opts.setHomeDirectory(file);
    SunshowerKernel.setKernelOptions(opts);
    return file;
  }

  @Bean
  @DependsOn("rootFile")
  public FileSystem aireFileSystem(Kernel kernel) throws IOException {
    log.info("Attempting to resolve kernel filesystem");
    URI uri = URI.create("droplet://aire");
    FileSystem fs = null;
    try {
      log.info("Attempting to create filesystem...");
      fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
      log.info("Successfully created filesystem");
      ((SunshowerKernel) kernel).setFileSystem(fs);
      return fs;
    } catch (FileSystemAlreadyExistsException ex) {
      log.info("Filesystem already existed--returning it");
      fs = FileSystems.getFileSystem(uri);
    } catch (ProviderNotFoundException | ServiceConfigurationError ex) {
      log.info(
          "Couldn't create new filesystem or resolve existing filesystem...resolving from context");
      ServiceLoader<FileSystemProvider> sl =
          ServiceLoader.load(
              FileSystemProvider.class, Thread.currentThread().getContextClassLoader());
      for (FileSystemProvider provider : sl) {
        if (uri.getScheme().equalsIgnoreCase(provider.getScheme())) {
          try {
            fs = provider.newFileSystem(uri, Collections.emptyMap());
          } catch (FileSystemAlreadyExistsException e) {
            fs = provider.getFileSystem(uri);
          }
          break;
        }
      }
      if (fs == null) {
        throw new ProviderNotFoundException("Provider \"" + uri.getScheme() + "\" not found");
      }
    }
    ((SunshowerKernel) kernel).setFileSystem(fs);
    return fs;
  }

  @Bean
  public ModuleClasspath moduleClasspath(ClassLoader classLoader) {
    return new EmbeddedModuleClasspath(new EmbeddedModuleLoader(classLoader));
  }

  @Bean
  public ModuleDescriptor moduleDescriptor(ClassLoader classLoader) {

    val source = getClass().getProtectionDomain().getCodeSource().getLocation();
    File file = new File(source.getFile());
    for (ModuleScanner scanner : ServiceLoader.load(ModuleScanner.class, classLoader)) {

      final Optional<ModuleDescriptor> scan = scanner.scan(file, source);
      if (scan.isPresent()) {
        return scan.get();
      }
    }

    val coordinate = ModuleCoordinate.create("io.zephyr.aire", "zephyr-aire", "1.0.0");
    return new ModuleDescriptor(
        Module.Type.Plugin,
        source,
        1,
        new File(source.getFile()),
        coordinate,
        Collections.emptyList(),
        "Aire Plugin");
  }

  @Bean
  public BeanPostProcessor moduleViewRegistrationPostProcessor(ViewManager viewManager) {
    return new ModuleViewRegistrationPostProcessor(viewManager);
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
  public ViewManager viewManager() {
    return new VaadinViewManager();
    //    return new VaadinViewManager(context, (ComponentRegistry) registry, registry);
  }

  @EventListener(classes = ContextStoppedEvent.class)
  public void onContextStopped(ContextStoppedEvent event) {
    val thread = event.getApplicationContext().getBean(ModuleThread.class);
    thread.stop();
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent contextRefreshedEvent) {

    if (isFromThis(contextRefreshedEvent)) {
      ApplicationContext context = contextRefreshedEvent.getApplicationContext();
      Module module = context.getBean(Module.class);
      Kernel kernel = context.getBean(Kernel.class);
      log.info("Kernel File System: " + kernel.getFileSystem());

      DeploymentScanner scanner = context.getBean(DeploymentScanner.class);
      ModuleThread moduleThread = context.getBean(ModuleThread.class);
      ViewManager viewManager = context.getBean(ViewManager.class);
      kernel.start();
      moduleThread.start();

      register(context, module, kernel, scanner, moduleThread, viewManager);
    }
  }

  private void register(
      ApplicationContext context,
      Module module,
      Kernel kernel,
      DeploymentScanner scanner,
      ModuleThread moduleThread,
      ViewManager viewManager) {
    DependencyGraph graph = kernel.getModuleManager().getDependencyGraph();
    graph.add(module);
    kernel.getModuleClasspathManager().install(module);
    module.getLifecycle().setState(Lifecycle.State.Active);
    kernel.getVolatileStorage().set(ApplicationContext.class, context);
    kernel.createContext(module, moduleThread).register(ViewManager.class, viewManager);
    try {
      new Thread(
              () -> {
                try {
                  System.out.println("STARTING in 5");
                  Thread.sleep(5000);
                    System.out.println("STARTING NOW");
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                scanner.start();
              })
          .start();
    } catch (Exception ex) {
      log.info("Failed to start deployment scanner");
    }
  }

  private boolean isFromThis(ApplicationReadyEvent contextRefreshedEvent) {
    val ctx = contextRefreshedEvent.getApplicationContext();
    val classloader = ctx.getClassLoader();
    return classloader == AireConfiguration.class.getClassLoader();
  }
}

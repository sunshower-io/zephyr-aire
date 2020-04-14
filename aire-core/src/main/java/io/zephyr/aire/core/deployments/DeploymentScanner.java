package io.zephyr.aire.core.deployments;

import io.zephyr.api.Startable;
import io.zephyr.api.Stoppable;
import io.zephyr.cli.DefaultZephyr;
import io.zephyr.kernel.Coordinate;
import io.zephyr.kernel.Lifecycle;
import io.zephyr.kernel.core.Kernel;
import io.zephyr.scan.AbstractDeploymentScanner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class DeploymentScanner extends AbstractDeploymentScanner
    implements Runnable, Startable, Stoppable, ApplicationListener<ContextStoppedEvent> {

  //  @Value("#{'${deployment.locations}'.split('\\s*,\\s*)}}")
  //  private List<String> locations;

  public DeploymentScanner(Kernel kernel, Collection<String> paths) throws IOException {
    super(kernel, paths);
  }

  @Override
  public void run() {
    install(getPaths());
    super.run();
  }

  @SneakyThrows
  private void install(Set<String> paths) {

    Set<URL> urls = new HashSet<>();
    for (val path : paths) {
      doScan(path, urls);
    }
    val zephyr = new DefaultZephyr(getKernel());
    zephyr.install(urls);
    val kernel = getKernel();
    val toStart = new HashSet<String>();

    for (val url : urls) {
      for (val module : kernel.getModuleManager().getModules()) {
        val source = module.getSource();
        if (source != null && source.getLocation().equals(url.toURI())) {
          val coordinate = module.getCoordinate();
          if (!(coordinate == null || coordinate.getName().equals("zephyr-core")))
            toStart.add(module.getCoordinate().toCanonicalForm());
        }
      }
    }
    System.out.println("starting " + toStart);
    zephyr.start(toStart);

    //    val modules = kernel.getModuleManager().getModules(Lifecycle.State.Resolved);
    //    zephyr.start(
    //        modules.stream().map(t ->
    // t.getCoordinate().toCanonicalForm()).collect(Collectors.toSet()));
  }

  private void doScan(String path, Set<URL> urls) {
    val directory = resolve(path).toFile();
    val files = directory.listFiles();
    if (files == null) {
      return;
    }
    for (val file : files) {
      val descriptor = loadDescriptor(file);
      if (descriptor.isPresent()) {
        urls.add(urlFor(file));
      }
    }
  }

  @Override
  public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
    stop();
  }
}

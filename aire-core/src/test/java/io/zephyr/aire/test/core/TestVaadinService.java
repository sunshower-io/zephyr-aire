package io.zephyr.aire.test.core;

//import com.github.mvysny.kaributesting.v10.MockInstantiator;
import com.github.mvysny.kaributesting.v10.MockService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.di.Instantiator;
import com.vaadin.flow.function.DeploymentConfiguration;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.ServiceException;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.spring.SpringInstantiator;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;

public class TestVaadinService extends MockService {
  private final ApplicationContext context;

  public TestVaadinService(
      @NotNull VaadinServlet servlet,
      @NotNull DeploymentConfiguration deploymentConfiguration,
      ApplicationContext context) {
    super(servlet, deploymentConfiguration);
    this.context = context;
  }

  @Override
  protected Instantiator createInstantiator() throws ServiceException {
    return new SpringInstantiator(this, context);
  }
}

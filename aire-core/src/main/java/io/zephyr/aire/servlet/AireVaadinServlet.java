package io.zephyr.aire.servlet;

import com.vaadin.flow.server.*;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.SpringServlet;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.concurrent.atomic.AtomicReference;

public class AireVaadinServlet extends SpringServlet
    implements SessionInitListener, SessionDestroyListener {

  static final AtomicReference<VaadinServlet> instance;

  static {
    instance = new AtomicReference<>();
  }

  private Registration destroyRegistration;
  private Registration initializerRegistration;

  /**
   * Creates a new Vaadin servlet instance with the application {@code context} provided.
   *
   * @param context the Spring application context
   * @param forwardingEnforced
   */
  public AireVaadinServlet(ApplicationContext context, boolean forwardingEnforced) {
    super(context, forwardingEnforced);
  }

  public static VaadinServlet getInstance() {
    return instance.get();
  }

  public void destroy() {
    super.destroy();
    destroyRegistration.remove();
    initializerRegistration.remove();
  }

  @Override
  protected void servletInitialized() throws ServletException {
    super.servletInitialized();
    instance.set(this);
    destroyRegistration = getService().addSessionDestroyListener(this);
    initializerRegistration = getService().addSessionInitListener(this);
  }

  @Override
  public void sessionInit(SessionInitEvent event) throws ServiceException {}

  @Override
  public void sessionDestroy(SessionDestroyEvent event) {}
}

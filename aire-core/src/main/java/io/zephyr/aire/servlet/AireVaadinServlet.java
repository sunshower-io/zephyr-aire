package io.zephyr.aire.servlet;

import com.vaadin.flow.server.*;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.SpringServlet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AireVaadinServlet extends SpringServlet
    implements SessionInitListener, SessionDestroyListener {

  private static final Object lock;
  private static final AtomicReference<VaadinServlet> instance;

  private static volatile boolean initialized = false;

  static {
    lock = new Object();
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

  /**
   * blocks until setInstance is called
   *
   * @return the vaadin servlet instance
   */
  public static VaadinServlet getInstance() {
    synchronized (lock) {
      log.info("Waiting for VaadinServlet to initialize...");
      while (instance.get() == null) {
        try {
          lock.wait(500);
        } catch (InterruptedException e) {
          log.info("Interrupted--this should not happen but probably indicates a shutdown");
        }
      }
      if (!initialized) {
        initialized = true;
        log.info("VaadinServlet successfully initialized!");
      }
    }
    return instance.get();
  }

  public static void setInstance(VaadinServlet servlet) {
    synchronized (lock) {
      log.info("Initializing Vaadin Servlet");
      instance.set(servlet);
      lock.notifyAll();
    }
  }

  public static void clear() {
    instance.set(null);
    initialized = false;
  }

  public void destroy() {
    super.destroy();
    destroyRegistration.remove();
    initializerRegistration.remove();
    clear();
  }

  @Override
  protected void servletInitialized() throws ServletException {
    super.servletInitialized();
    setInstance(this);
    destroyRegistration = getService().addSessionDestroyListener(this);
    initializerRegistration = getService().addSessionInitListener(this);
  }

  @Override
  public void sessionInit(SessionInitEvent event) throws ServiceException {}

  @Override
  public void sessionDestroy(SessionDestroyEvent event) {}
}

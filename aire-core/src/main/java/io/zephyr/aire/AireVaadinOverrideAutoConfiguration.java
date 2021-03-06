package io.zephyr.aire;

import com.vaadin.flow.server.Constants;
import com.vaadin.flow.spring.*;
import io.zephyr.aire.servlet.AireVaadinServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@ConditionalOnClass(ServletContextInitializer.class)
@EnableConfigurationProperties(VaadinConfigurationProperties.class)
@Import({VaadinServletConfiguration.class})
public class AireVaadinOverrideAutoConfiguration {
  static final String VAADIN_SERVLET_MAPPING = "/vaadinServlet/*";

  @Autowired private WebApplicationContext context;

  @Autowired private VaadinConfigurationProperties configurationProperties;

  /**
   * Creates a {@link ServletContextInitializer} instance.
   *
   * @return a custom ServletContextInitializer instance
   */
  @Bean
  public ServletContextInitializer contextInitializer() {
    return new VaadinServletContextInitializer(context);
  }

  /**
   * Creates a {@link ServletRegistrationBean} instance with Spring aware Vaadin servlet.
   *
   * @return a custom ServletRegistrationBean instance
   */
  @Bean
  public ServletRegistrationBean<SpringServlet> servletRegistrationBean() {
    String mapping = configurationProperties.getUrlMapping();
    Map<String, String> initParameters = new HashMap<>();
    boolean rootMapping = RootMappedCondition.isRootMapping(mapping);
    if (rootMapping) {
      mapping = VAADIN_SERVLET_MAPPING;
      initParameters.put(
          Constants.SERVLET_PARAMETER_PUSH_URL, makeContextRelative(mapping.replace("*", "")));
    }
    ServletRegistrationBean<SpringServlet> registration =
        new ServletRegistrationBean<>(new AireVaadinServlet(context, rootMapping), mapping);
    registration.setInitParameters(initParameters);
    registration.setAsyncSupported(configurationProperties.isAsyncSupported());
    registration.setName(ClassUtils.getShortNameAsProperty(SpringServlet.class));
    return registration;
  }

  static String makeContextRelative(String url) {
    // / -> context://
    // foo -> context://foo
    // /foo -> context://foo
    if (url.startsWith("/")) {
      url = url.substring(1);
    }
    return "context://" + url;
  }

  /**
   * Deploys JSR-356 websocket endpoints when Atmosphere is available.
   *
   * @return the server endpoint exporter which does the actual work.
   */
  @Bean
  public ServerEndpointExporter websocketEndpointDeployer() {
    return new VaadinWebsocketEndpointExporter();
  }
}

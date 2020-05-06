package io.zephyr.aire.annotation;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import io.zephyr.aire.AireConfiguration;
import io.zephyr.aire.test.AireTest;
import io.zephyr.aire.MainView;
import io.zephyr.aire.test.ScanRoutes;
import io.zephyr.aire.api.LocationManager;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.elements.BreadCrumb;
import io.zephyr.aire.layout.AireApplicationViewport;
import io.zephyr.aire.test.AireTestConfiguration;
import io.zephyr.aire.test.AireTestContext;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@AireTest
@ScanRoutes("io.zephyr.aire")
@ContextConfiguration(
    classes = {
      AireConfiguration.class,
      AireTestConfiguration.class,
      NavigationObservingInvocationHandlerTest.Cfg.class
    })
@Disabled
class NavigationObservingInvocationHandlerTest {

  @Inject private AireTestContext context;
  @Inject private ViewManager viewManager;
  @Inject private LocationManager locationManager;

  @AfterEach
  void tearDown() {
    Mockito.clearInvocations(locationManager);
  }

  @BeforeEach
  void setUp() {
    Mockito.clearInvocations(locationManager);
  }

  @Test
  void ensureLocationManagerHasNotBeenFiredInitially() {
    verify(locationManager, times(0)).fireLocationChanged(any());
  }

  @Test
  void ensureThatChangingLocationsWorks() {
    UI.getCurrent().navigate(MainView.class);
    verify(locationManager, times(1)).fireLocationChanged("");
  }

//  @Test
//  void ensureDynamicallyRegisteringRouteWorks() {
//    viewManager.registerRoute(TestView.class);
//    UI.getCurrent().navigate(TestView.class);
//    verify(locationManager, atLeastOnce()).fireLocationChanged("test");
//  }

  @Test
  void ensureBreadCrumbHasHomeIcon() {
    UI.getCurrent().navigate(MainView.class);
    val breadcrumb =
        context.resolveFirst(
            t ->
                t instanceof BreadCrumb
                    && ((BreadCrumb) t).getClassNames().contains("aire-breadcrumb"));

    assertNotNull(breadcrumb);


  }

  @Route(value = "test", layout = AireApplicationViewport.class)
  public static class TestView extends Div {}

  @Configuration
  static class Cfg {
    @Bean
    public LocationManager locationManager() {
      return mock(LocationManager.class);
    }
  }
}

package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationListener;
import io.zephyr.aire.api.LocationManager;
import lombok.val;

import javax.inject.Inject;

public class BreadCrumb extends Div {

  private final LocationManager locationManager;

  @Inject
  public BreadCrumb(LocationManager locationManager) {
    this.locationManager = locationManager;
    this.addClassName("aire-breadcrumb");
    UI.getCurrent().addAfterNavigationListener(new LocationListener());

    for (val location : locationManager.getHierarchy()) {
      val span = new Span();
      span.setText(location);
      add(span);
    }
  }

  class LocationListener implements AfterNavigationListener {

    @Override
    public void afterNavigation(AfterNavigationEvent e) {
      locationManager.fireLocationChanged(e.getLocation().getPath());
    }
  }
}

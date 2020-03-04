package io.zephyr.aire.layout;

import com.vaadin.flow.component.Component;
import io.zephyr.aire.api.Placement;

public abstract class Region implements Placement {


  public static final class Header extends Region {
    @Override
    public void update(Component self, Component target) {}
  }

  public static final class Content extends Region {
    @Override
    public void update(Component self, Component target) {
      ((AireApplicationViewport) target).setContent(self);
    }
  }

  public static final class Footer extends Region {
    @Override
    public void update(Component self, Component target) {
      ((AireApplicationViewport) target).setFooter(self);
    }
  }
}

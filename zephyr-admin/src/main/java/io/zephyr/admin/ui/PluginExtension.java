package io.zephyr.admin.ui;

import com.vaadin.flow.router.RouterLink;
import io.zephyr.aire.api.Extension;
import io.zephyr.aire.api.ViewDecorator;
import io.zephyr.aire.api.ViewManager;
import io.zephyr.aire.elements.FontAwesome;
import io.zephyr.aire.layout.AireApplicationViewport;
import lombok.val;

import javax.inject.Inject;

@Extension(":ui:main")
public class PluginExtension implements ViewDecorator{

  @Inject private ViewManager viewManager;

//  @Override
//  public void decorate(AireApplicationViewport component) {
//
//    val routerLink = new RouterLink();
//    routerLink.setRoute(PluginListPage.class);
//    routerLink.add(FontAwesome.Plug.icon());
//    routerLink.setHighlightCondition((t, u) -> t.getHref().contains("plugins"));
//    component.getPrimaryNavigation().add(routerLink);
//  }

    @Override
    public <T> T decorate(Class<T> type, T instance) {
        return null;
    }
}

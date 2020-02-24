package io.zephyr.aire;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import io.zephyr.aire.layout.AireViewport;

@Theme(Lumo.class)
@PWA(name = "Aire Core", shortName = "aire")
@CssImport("./styles/themes/aire/theme.css")
public class AppShell extends AireViewport implements AppShellConfigurator {
}

package io.zephyr.aire;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;

@PWA(name = "Aire Core", shortName = "aire")
@CssImport("./styles/aire/layout/aire-structure.css")
public class AppShell implements AppShellConfigurator {}

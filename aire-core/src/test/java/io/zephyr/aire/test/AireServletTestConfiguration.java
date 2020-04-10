package io.zephyr.aire.test;

import io.zephyr.aire.servlet.ModuleResourceServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ContextConfiguration
public class AireServletTestConfiguration {

    @Bean
    public ModuleResourceServlet moduleResourceServlet(HttpServletRequest request, HttpServletResponse )
}

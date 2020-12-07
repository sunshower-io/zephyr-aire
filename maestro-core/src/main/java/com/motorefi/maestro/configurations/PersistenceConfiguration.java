package com.motorefi.maestro.configurations;

import io.zephyr.aire.util.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.util.Set;

@ToString
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "persistence")
@PropertySource(
    value = "classpath:/config/application.yaml",
    factory = YamlPropertySourceFactory.class)
@Import(JpaConfiguration.class)
public class PersistenceConfiguration {

  @Getter @Setter private Set<ConnectionConfiguration> connections;
}

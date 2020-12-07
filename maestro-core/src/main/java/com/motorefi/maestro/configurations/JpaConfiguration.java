package com.motorefi.maestro.configurations;

import com.zaxxer.hikari.HikariConfig;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class JpaConfiguration {

  @Inject private PersistenceConfiguration configuration;

  @Bean
  public DataSource poolingDataSource() {
    val config = new HikariConfig();
    config.setAutoCommit(configuration.isAutoCommit());
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource) {
    val result = new LocalContainerEntityManagerFactoryBean();
  }
}

package com.motorefi.maestro.configurations;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ConnectionConfiguration {

  /** the database URL to use for this connection */
  @Getter @Setter private String url;

  /** the database driver to use for this connection */
  @Getter @Setter private String driver;

  /** the database username (may be omitted if the connection string contains this) */
  @Getter @Setter private String username;

  /** the database password (may be omitted if the connection string contains this) */
  @Getter @Setter private String password;

  /**
   * connection autocommit mode this should usually be set to false to permit more granular
   * transaction granularity at the transaction site
   */
  @Getter @Setter private boolean autocommit;

  /**
   * the connection mode to use. In a replica environment, connections with the READ_ONLY mode will
   * be routed to the appropriate replicas
   */
  @Getter @Setter private ConnectionMode mode;
}

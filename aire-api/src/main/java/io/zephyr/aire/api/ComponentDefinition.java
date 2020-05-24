package io.zephyr.aire.api;

import io.zephyr.api.security.AccessControlList;
import io.zephyr.api.security.Role;
import io.zephyr.api.security.Session;

import java.util.Set;

public interface ComponentDefinition<T> {

  Operation<T> getOperation();

  void apply(Object property, Session session, Instantiator instantiator);

  Set<String> extensionPointLocations();

  Set<Role> getRequiredRoles();

  Set<AccessControlList> getRequiredAccessControls();
}

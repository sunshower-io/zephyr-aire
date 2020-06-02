package io.zephyr.aire.api;

import io.zephyr.api.security.AccessControlList;
import io.zephyr.api.security.Role;
import io.zephyr.api.security.Session;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PropertyBasedComponentDefinition<T> implements ComponentDefinition<T> {

  private final Set<Role> roles;
  private final Operation<T> operation;
  private final Set<String> locations;
  private final Set<AccessControlList> acls;

  public PropertyBasedComponentDefinition(
      final Operation<T> operation,
      final Collection<? extends String> locations,
      final Collection<? extends Role> roles,
      final Collection<? extends AccessControlList> acls) {
    this.operation = operation;
    this.acls = Collections.unmodifiableSet(new HashSet<>(acls));
    this.roles = Collections.unmodifiableSet(new HashSet<>(roles));
    this.locations = Collections.unmodifiableSet(new HashSet<>(locations));
  }

  public PropertyBasedComponentDefinition(
      Operation<T> operation, Collection<? extends String> locations) {
    this(operation, locations, Collections.emptySet(), Collections.emptySet());
  }

  @Override
  public Operation<T> getOperation() {
    return operation;
  }

  @Override
  public void apply(Object property, Session session, Instantiator instantiator) {
    if (checkPermissions(session)) {
      operation.apply((T) property, instantiator);
    }
  }

  @Override
  public Set<String> extensionPointLocations() {
    return locations;
  }

  @Override
  public Set<Role> getRequiredRoles() {
    return roles;
  }

  @Override
  public Set<AccessControlList> getRequiredAccessControls() {
    return acls;
  }

  protected boolean checkPermissions(Session session) {
    return true;
  }
}

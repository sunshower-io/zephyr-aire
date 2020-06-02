package io.zephyr.aire.api;

import lombok.val;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Registrations {

  public static Registration aggregate(Registration... registrations) {
    return new AggregateRegistration(Set.of(registrations));
  }

  public static Registration aggregate(Collection<? extends Registration> registrations) {
    return new AggregateRegistration(new HashSet<>(registrations));
  }

  static class AggregateRegistration implements Registration {
    final Set<Registration> registrations;

    AggregateRegistration(Set<Registration> registrations) {
      this.registrations = registrations;
    }

    @Override
    public void close() {
      for (val registration : registrations) {
        registration.close();
      }
    }
  }
}

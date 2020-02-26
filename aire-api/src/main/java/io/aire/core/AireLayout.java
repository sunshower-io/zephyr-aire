package io.aire.core;

public interface AireLayout<E extends Enum<E>> extends AireComposite {


  boolean contains(E location);

  AireComponent get(E location);

  AireComponent remove(E location);

  void set(E location, AireComponent component);
}

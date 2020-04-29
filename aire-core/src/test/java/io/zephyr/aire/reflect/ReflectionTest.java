package io.zephyr.aire.reflect;

import io.zephyr.aire.api.Slot;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

  private AnnotatedPropertySet propertySet;

  @Test
  void ensureBooleanFieldToGetterNameWorks() {
    val getter = Reflection.toGetter("a", boolean.class);
    assertEquals(getter, "isA");
  }

  @Test
  void ensureFieldNameToGetterNameWorksComplex() {
    val getter = Reflection.toGetter("helloWorld", Reflection.class);
    assertEquals(getter, "getHelloWorld");
  }

  @Test
  void ensureMutatingBySetterRetrievingByFieldWorks() throws ReflectiveOperationException {

    class A {
      private String thing;

      @Slot("hello")
      public void setThing(String thing) {
        this.thing = thing;
      }
    }

    propertySet = scan(A.class, Slot.class);

    val a1 = new A();
    propertySet.get("thing").set(a1, "sup");
    assertEquals(propertySet.getAliased("hello").get(a1), "sup");
  }

  @Test
  void ensureMutatorOnlyPropertyWorks() throws ReflectiveOperationException {
    class A {
      private String nopeNotHere;

      @Slot("hello")
      public String getThing() {
        return nopeNotHere;
      }

      public void setThing(String thing) {
        nopeNotHere = thing;
      }
    }

    propertySet = scan(A.class, Slot.class);

    val a1 = new A();
    propertySet.get("thing").set(a1, "sup");
    assertEquals(propertySet.getAliased("hello").get(a1), "sup");
  }

  @Test
  void ensureFieldOnlyPropertyWorks() throws ReflectiveOperationException {
    class B {
      @Slot("frapper")
      private String dapperWapper;
    }

    propertySet = scan(B.class, Slot.class);
    assertEquals(propertySet.size(), 1);

    val b1 = new B();
    propertySet.get("dapperWapper").set(b1, "coolio");
    assertEquals(b1.dapperWapper, "coolio");

    val b2 = new B();
    propertySet.getAliased("frapper").set(b2, "beansio");
    assertEquals(propertySet.getAliased("frapper").get(b2), "beansio");
  }

  @Test
  void ensureFieldWithGetterWorks() throws ReflectiveOperationException {

    class B {
      @Slot("frapper")
      private String helloWorld;

      public String getHelloWorld() {
        return helloWorld;
      }
    }

    propertySet = scan(B.class, Slot.class);

    val b1 = new B();
    propertySet.getAliased("frapper").set(b1, "schnorp");
    assertEquals(propertySet.get("helloWorld").get(b1), "schnorp");
  }

  @Test
  void ensureCompletePropertyWorks() throws ReflectiveOperationException {

    class A {
      @Slot("hello")
      private String a;

      public String getA() {
        return a;
      }

      public void setA(String a) {
        this.a = a;
      }
    }
    propertySet = scan(A.class, Slot.class);

    assertEquals(propertySet.size(), 1, "must have only one property");

    val i1 = new A();
    propertySet.get("a").set(i1, "frapper");
    assertEquals(i1.getA(), "frapper");

    val i2 = new A();
    propertySet.getAliased("hello").set(i2, "dapper");
    assertEquals(i2.getA(), "dapper");
  }

  static AnnotatedPropertySet scan(Class<?> type, Class<Slot> annotation) {
    return Reflection.getAnnotatedProperties(type, annotation, (a) -> ((Slot) a).value());
  }
}

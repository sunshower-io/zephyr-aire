package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.val;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AireBeanForm<T> extends VerticalLayout {

  private T instance;
  private final Class<T> type;
  private final boolean root;


  public interface FieldType {



  }

  public static final class Text implements FieldType {

      public static final class Email implements FieldType {

      }
  }

  public static final class Defaults implements FieldType {

  }

  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface FormField {
      Class<? extends FieldType> options() default Defaults.class;
      FieldValidation[] validation() default {};
  }

  public enum Validations {
      REGEXP,
      NONE
  }

  @Retention(RetentionPolicy.RUNTIME)
  public @interface FieldValidation {
      Validations type() default Validations.NONE;

      String value() default "_NONE_";

  }

  public AireBeanForm(Class<T> type) {
      this(type, null, true);
  }

  public AireBeanForm(Class<T> type, T instance) {
    this.type = type;
    doLayout();
  }

  private AireBeanForm(Class<T> type, T instance, boolean root) {
    this.type = type;
    this.root = root;
    this.instance = instance;
  }

  protected void doLayout() {

    for (val field : type.getDeclaredFields()) {
      val fieldType = field.getType();
      if (String.class.isAssignableFrom(fieldType)) {
        val textField = new TextField();
        textField.setLabel(field.getName());
        add(textField);
      }
      if (Boolean.class.isAssignableFrom(fieldType) || boolean.class.isAssignableFrom(fieldType)) {
        val checkbox = new Checkbox();
        checkbox.setLabel(field.getName());
        add(checkbox);
      }
      if(root) {}// add save/cancel buttons
    }
  }
}

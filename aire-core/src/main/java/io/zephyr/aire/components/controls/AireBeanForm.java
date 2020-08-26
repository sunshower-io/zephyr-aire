package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.shared.Registration;
import io.zephyr.aire.reflect.Reflection;
import lombok.Getter;
import lombok.val;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AireBeanForm<T> extends VerticalLayout {

  private T instance;
  private final Class<T> type;
  private boolean root = true;

  public interface FieldType {}

  public static final class Text implements FieldType {

    public static final class Email implements FieldType {}
  }

  public static final class Defaults implements FieldType {}

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

  public <T, U> Registration addOnDirtyListener(
      ComponentEventListener<ComponentEvent<AireBeanForm<T>>> listener) {
    //    return addListener(OnDirtyEvent.class,  listener);
    return null;
  }

  public static class OnDirtyEvent<T, U> extends ComponentEvent<AireBeanForm<T>> {

    @Getter private final U nextValue;
    @Getter private final U previousValue;
    @Getter private final String fieldName;

    public OnDirtyEvent(AireBeanForm<T> source, String fieldName, U previousValue, U nextValue) {
      super(source, false);
      this.fieldName = fieldName;
      this.nextValue = nextValue;
      this.previousValue = previousValue;
    }
  }

  class InternalTextFieldBlurListener
      implements ComponentEventListener<BlurNotifier.BlurEvent<TextField>> {

    final String fieldName;

    public InternalTextFieldBlurListener(String name) {
      this.fieldName = name;
    }

    @Override
    public void onComponentEvent(BlurNotifier.BlurEvent<TextField> textFieldBlurEvent) {
      dispatch(
          new OnDirtyEvent<>(
              AireBeanForm.this,
              fieldName,
              getValue(fieldName),
              textFieldBlurEvent.getSource().getValue()));
    }

    private <T, U> void dispatch(OnDirtyEvent<T, U> event) {
      AireBeanForm.this.fireEvent(event);
    }
  }

  private <T> T getValue(String fieldName) {
    return Reflection.fieldValue(fieldName, type, instance);
  }

  protected void doLayout() {

    for (val field : type.getDeclaredFields()) {
      val fieldType = field.getType();
      if (String.class.isAssignableFrom(fieldType)) {
        val textField = new TextField();
        textField.setLabel(field.getName());
        textField.addBlurListener(new InternalTextFieldBlurListener(field.getName()));
        add(textField);
      }
      if (Boolean.class.isAssignableFrom(fieldType) || boolean.class.isAssignableFrom(fieldType)) {
        val checkbox = new Checkbox();
        checkbox.setLabel(field.getName());
        add(checkbox);
      }
      if (root) {} // add save/cancel buttons
    }
  }
}

package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.BlurNotifier;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.shared.Registration;
import io.zephyr.aire.reflect.Reflection;
import lombok.Getter;
import lombok.val;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.Date;

public class AireBeanForm<T> extends VerticalLayout {

  private T instance;
  private final Class<T> type;
  private boolean root = true;

  public interface FieldType {}

  public static final class Text implements FieldType {

    public static final class Email implements FieldType {}
    public static final class TextArea implements  FieldType {}
    public static final class Password implements FieldType {}

  }

  public static final class Options implements FieldType {
    public static final class RadioButtons implements FieldType {}
    public static final class Checkboxes implements FieldType {}
    public static final class Select implements FieldType {}
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
    this(type, instance, true);
  }

  private AireBeanForm(Class<T> type, T instance, boolean root) {
    this.type = type;
    this.root = root;
    this.instance = instance;
    doLayout();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void doLayout() {

    for (val field : type.getDeclaredFields()) {
      try {
        val fieldType = field.getType();
        val annotation = field.getAnnotation(AireBeanForm.FormField.class);
        val option = annotation.options();

        //TODO validations

        if (String.class.isAssignableFrom(fieldType)) {
          if (option.isAssignableFrom(AireBeanForm.Text.Email.class)) {
            addField(field, EmailField.class, fieldType);
          } else if (option.isAssignableFrom(AireBeanForm.Text.TextArea.class)) {
            addField(field, TextArea.class, fieldType);
          } else if (option.isAssignableFrom(AireBeanForm.Text.Password.class)) {
            addField(field, PasswordField.class, fieldType);
          } else {
            addField(field, TextField.class, fieldType);
          }
        } else if (Boolean.class.isAssignableFrom(fieldType) || boolean.class.isAssignableFrom(fieldType)) {
          addField(field, Checkbox.class, fieldType);
        } else if (Number.class.isAssignableFrom(fieldType) || int.class.isAssignableFrom(fieldType)) {
          addField(field, NumberField.class, fieldType);
        } else if (Date.class.isAssignableFrom(fieldType)) {
          addField(field, DatePicker.class, fieldType);
        } else if (Enum.class.isAssignableFrom(fieldType)) {
          val values = fieldType.getEnumConstants();
          if (option.isAssignableFrom(AireBeanForm.Options.Select.class)) {
              val select = new Select<>();
              select.setLabel(getFieldName(field));
              select.setItemLabelGenerator(Object::toString);
              select.setItems(values);
              if (instance != null) {
                select.setValue(getFieldValue(field).toString());
              }
              add(select);
          } else {
              val group = new RadioButtonGroup<>();
              group.setLabel(getFieldName(field));
              group.setItems(values);
              group.setRenderer(new TextRenderer<>(Object::toString));
            if (instance != null) {
              group.setValue(getFieldValue(field).toString());
            }
              add(group);
          }
        } else {
          if (instance != null) {
            add(new AireBeanForm(field.getType(), getFieldValue(field), false));
          } else {
            add(new AireBeanForm<>(field.getClass(), null, false));
          }
        }

      } catch (NullPointerException nex) {
        //not on that field
      }

    }
    if (root) {
      val save = new Button("Save " + type.getName());
      add(save);
      val cancel = new Button("Cancel");
      add(cancel);
    }
  }

  protected Object getFieldValue(Field field) {
    try {
      return field.get(instance);
    } catch (IllegalAccessException e) {
      return null;
    }
  }

  protected String getFieldName(Field field) {
    return field.getName();
  }

  protected <U extends Component, V> void addField(Field field, Class<U> fieldType, Class<V> valueType) {
    try {
    val formField = Reflection.instantiate(fieldType);

    val setLabel = fieldType.getDeclaredMethod("setLabel", String.class);
    setLabel.invoke(formField, getFieldName(field));

    val addBlurListener = fieldType.getDeclaredMethod("addBlurListener", ComponentEventListener.class);
    addBlurListener.invoke(formField, new InternalTextFieldBlurListener(getFieldName(field)));

    if (instance != null) {
      val setValue = fieldType.getDeclaredMethod("setValue", valueType);
      setValue.invoke(formField, getFieldValue(field));
    }

    add(formField);
    } catch(Exception ex) {
      //TODO handle
    }
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
              getFieldValue(fieldName),
              textFieldBlurEvent.getSource().getValue()));
    }

    private <T, U> void dispatch(OnDirtyEvent<T, U> event) {
      AireBeanForm.this.fireEvent(event);
    }
  }

  private <T> T getFieldValue(String fieldName) {
    return Reflection.fieldValue(fieldName, type, instance);
  }


}

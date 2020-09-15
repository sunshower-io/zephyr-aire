package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
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

@CssImport("./styles/aire/components/aire-bean-form.css")
public class AireBeanForm<T> extends VerticalLayout {

  private T instance;
  private final Class<T> type;
  private int level = 0;

  private static final String CLASS_NAME = "aire-bean-form";

  public interface FieldType {}

  public static final class Text implements FieldType {

    public static final class Email implements FieldType {}
    public static final class TextArea implements  FieldType {}
    public static final class Password implements FieldType {}

  }

  public static final class Options implements FieldType {
    public static final class RadioButtons implements FieldType {}
    public static final class Select implements FieldType {}
  }

  public static final class Defaults implements FieldType {}

  @Target(ElementType.FIELD)
  @Retention(RetentionPolicy.RUNTIME)
  public @interface FormField {
    Class<? extends FieldType> options() default Defaults.class;

    FieldValidation[] validation() default {};

    FieldName name() default @FieldName;

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

  public enum NamePolicies {
    TRANSLATION,
    STRING
  }

  public @interface FieldName {
    NamePolicies type() default NamePolicies.STRING;

    String value() default "";
  }

  public AireBeanForm(Class<T> type) {
    this(type, null, 0);
  }

  public AireBeanForm(Class<T> type, T instance) {
    this(type, instance, 0);
  }

  private AireBeanForm(Class<T> type, T instance, int level) {
    this.type = type;
    this.level = level;
    this.instance = instance;
    this.setPadding(this.level == 0);
    this.setSpacing(false);
    this.addClassName(CLASS_NAME);
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
          val numField = new NumberField();
          numField.setLabel(getFieldName(field));
          add(numField);

          //TODO fix
//          addField(field, NumberField.class, fieldType);
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
          HtmlContainer header;
          if (level == 0) {
            header = new H1();
          } else if (level == 1) {
            header = new H2();
          } else if (level == 2) {
            header = new H3();
          } else if (level == 3) {
            header = new H4();
          } else if (level == 4) {
            header = new H5();
          } else {
            header = new H6();
          }

          header.setText(getFieldName(field));
          add(header);

          if (instance != null) {
            add(new AireBeanForm(field.getType(), getFieldValue(field), level + 1));
          } else {
            add(new AireBeanForm<>(field.getType(), null, level + 1));
          }
        }

      } catch (NullPointerException nex) {
        //not on that field
      }

    }
    if (level == 0) {
      val buttonGroup = new Div();
      buttonGroup.addClassName("aire-button-group");
      val save = new Button("Save");
      val cancel = new Button("Cancel");
      buttonGroup.add(save);
      buttonGroup.add(cancel);
      add(buttonGroup);
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
    val annotation = field.getAnnotation(AireBeanForm.FormField.class);
    val name = annotation.name();

    if (name.value().equals("")) {
      return field.getName();
    } else {
      if (name.type().equals(NamePolicies.STRING)) {
        return name.value();
      } else {
        return getTranslation(name.value());
      }
    }
  }

  protected <U extends Component, V> void addField(Field field, Class<U> fieldType, Class<V> valueType) {
    try {
      val formField = Reflection.instantiate(fieldType);

      val setLabel = fieldType.getDeclaredMethod("setLabel", String.class);
      setLabel.invoke(formField, getFieldName(field));

//      val addBlurListener = fieldType.getDeclaredMethod("addBlurListener", ComponentEventListener.class);
//      addBlurListener.invoke(formField, new InternalTextFieldBlurListener(getFieldName(field)));

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

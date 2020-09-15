package io.zephyr.aire.components.controls;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import io.zephyr.aire.MainView;
import io.zephyr.aire.api.ComponentDefinition;
import io.zephyr.aire.components.AireSize;
import io.zephyr.aire.test.*;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static io.zephyr.aire.api.Views.appendWith;
import static org.junit.jupiter.api.Assertions.*;

@AireTest
class AireBeanFormTest {

    ComponentDefinition<HasComponents> basicSetup() {
        return appendWith(() -> new AireBeanForm(TestFormClass.class)).to(":main:header");
    }
    
    @ViewTest(MainView.class)
    @EditView(withMethods = "basicSetup")
    void ensureFormGetsCorrectClass(@Element("//*[@class='aire-bean-form']") AireBeanForm form) {
        assertTrue(form.hasClassName("aire-bean-form"));
    }

    //doesn't work because content is in slots
//    @ViewTest(MainView.class)
//    @EditView(withMethods = "basicSetup")
//    void ensureFormCreatesFieldForFormFieldProperties(@Elements("//label") List<Label> labels) {
//        assertTrue(labels.size() > 0);
//        val label = labels.stream().filter(t -> t.getText().contains("displayMe")).findAny();
//        assertTrue(label.isPresent());
//    }

    //doesn't work because content is in slots
//    @ViewTest(MainView.class)
//    @EditView(withMethods = "basicSetup")
//    void ensureFormDoesNotCreateFieldForNonFormFieldProperties(@Elements("//label") List<Label> labels) {
//        val label = labels.stream().filter(t -> t.getText().contains("dontDisplayMe")).findAny();
//        assertFalse(label.isPresent());
//    }

    @Test
    void ensureFormCreatesFieldForFormFieldProperties() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val displayable = form.getChildren().filter(field -> field instanceof TextField).map(field -> (TextField) field).filter(field -> field.getLabel().equals("displayMe"));
        assertEquals(1, displayable.count());
    }

    @Test
    void ensureFormDoesNotCreateFieldForNonFormFieldProperties() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val displayable = form.getChildren().filter(field -> field instanceof TextField).map(field -> (TextField) field).filter(field -> field.getLabel().equals("dontDisplayMe"));
        assertEquals(0, displayable.count());
    }

    @ViewTest(MainView.class)
    @EditView(withMethods = "basicSetup")
    void ensureSubClassesGetRendered(@Elements("//*[@class='aire-bean-form']") List<AireBeanForm> forms) {
        assertEquals(2, forms.size());
    }

    @Test
    void ensureOuterFormGetsButtons(@Element("/*[@class='aire-bean-form']/div[@class='aire-button-group']") Div div) {
        val buttons = div.getChildren().filter(button -> button instanceof Button).map(button -> ((Button) button).getText()).collect(Collectors.toList());
        assertEquals(1, buttons.stream().filter(text -> text.equals("Save")).count());
        assertEquals(1, buttons.stream().filter(text -> text.equals("Cancel")).count());
    }

    //TODO Josiah "Expected EOL, didn't get it"
//    @Test
//    void ensureSubFormDoesNotGetButtons(@Element("/*[@class='aire-bean-form']/*[@class='aire-bean-form']") AireBeanForm form) {
//        assertNotNull(form);
//        val classes = form.getChildren().filter(div -> div instanceof Div).map(div -> ((Div) div).getClassName()).filter(className -> className.equals("aire-button-group")).count();
//        assertEquals(0, classes);
//    }


    //TODO Josiah "Expected EOL, didn't get it"
//    @ViewTest(MainView.class)
//    @EditView(withMethods = "basicSetup")
//    void ensureSubClassFieldsGetRendered(@Element("/*[@class='aire-bean-form']/*[@class='aire-bean-form']") AireBeanForm form) {
//        val displayable = form.getChildren().filter(field -> field instanceof TextField).map(field -> (TextField) field);
//        assertEquals(1, displayable.count());
//    }

    @ViewTest(MainView.class)
    @EditView(withMethods = "basicSetup")
    void ensureSubClassesGetAnAppropriateHeader(@Element("//h1") H1 header) {
        assertEquals("SubTest", header.getText());
    }

    @Test
    void ensureFieldsCanHaveALabelOtherThanTheirFieldName() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val labels = form.getChildren().filter(field -> field instanceof TextField).map(field -> ((TextField) field).getLabel()).collect(Collectors.toList());
        assertFalse(labels.stream().anyMatch(label -> label.equals("renameMe")));
        assertTrue(labels.stream().anyMatch(label -> label.equals("Renamed Field")));
    }

    @Test
    void ensureEmailFieldCanBeAdded() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof EmailField);
        assertEquals(1, fields.count());
    }

    @Test
    void ensureTextAreaCanBeAdded() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof TextArea);
        assertEquals(1, fields.count());
    }

    @Test
    void ensurePasswordFieldCanBeAdded() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof PasswordField);
        assertEquals(1, fields.count());
    }

    @Test
    void ensureCheckboxCanBeAdded() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof Checkbox);
        assertEquals(1, fields.count());
    }

    //TODO test numbers

    @Test
    void ensureDateCanBeAdded() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof DatePicker);
        assertEquals(1, fields.count());
    }

    @Test
    void ensureEnumCanBeAddedAsSelect() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof Select).map(field -> (Select) field);
        assertEquals(1, fields.count());
    }

    @Test
    void ensureEnumCanBeAddedAsRadioButtons() {
        val form = new AireBeanForm<>(TestFormClass.class);
        val fields = form.getChildren().filter(field -> field instanceof RadioButtonGroup);
        assertEquals(1, fields.count());
    }




    static class TestFormClass {
        @AireBeanForm.FormField(
                options = AireBeanForm.Text.Email.class,
                validation = @AireBeanForm.FieldValidation(type = AireBeanForm.Validations.REGEXP, value = "*"))
        private String whatever;

        @AireBeanForm.FormField(options = AireBeanForm.Text.TextArea.class)
        private String textArea;

        @AireBeanForm.FormField(options = AireBeanForm.Text.Password.class)
        private String thisFeelsInsecure;

        @AireBeanForm.FormField
        private Date anniversary;

        @AireBeanForm.FormField
        private boolean coolbean;

        @AireBeanForm.FormField(name=@AireBeanForm.FieldName(value="Renamed Field"))
        private String renameMe;

        @AireBeanForm.FormField(name=@AireBeanForm.FieldName(value="SubTest"))
        private SubTest sub;

        private String dontDisplayMe;

        @AireBeanForm.FormField
        private String displayMe;

        @AireBeanForm.FormField
        private AireSize size;

        @AireBeanForm.FormField(options = AireBeanForm.Options.Select.class)
        private AireSize otherSize;

        static class SubTest {

            @AireBeanForm.FormField
            private int myNumber;

            @AireBeanForm.FormField
            private String subField;

        }

    }
}
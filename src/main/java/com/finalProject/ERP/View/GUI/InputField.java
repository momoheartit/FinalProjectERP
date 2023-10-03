package com.finalProject.ERP.View.GUI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TextField;

public class InputField {

    private Label label;
    protected Control field;

    public InputField(String text) {
        label = new Label(text);
        field = createField();
        
        String labelStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';";
        label.setStyle(labelStyle);
    }

    public Label getLabel() {
        return label;
    }

    public void setField(Control field) {
        this.field = field;
    }

    public Control getField() {
        return field;
    }

    public String getValue() {
        if (field instanceof TextInputControl) {
            return ((TextInputControl) field).getText();
        } else if (field instanceof DatePicker) {
            LocalDate date = ((DatePicker) field).getValue();
            System.out.println("GetValues életbe lép");
            return (date != null) ? date.toString() : null;
        } else if (field instanceof CheckBox) {
            return String.valueOf(((CheckBox) field).isSelected());
        } else if (field instanceof ComboBox<?>) {
            Object selectedItem = ((ComboBox<?>) field).getSelectionModel().getSelectedItem();
            return (selectedItem != null) ? selectedItem.toString() : null;
        }

        return null;
    }

    public void setValue(String value) {
        if (field instanceof TextInputControl) {
            TextInputControl textInputControl = (TextInputControl) field;
            textInputControl.setText(value);
        } else if (field instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) field;
            System.out.println("Set Values életbe lép");
            // Az érték beállítása csak akkor, ha a value nem null
            if (value != null) {
                datePicker.setValue(LocalDate.parse(value));
            }
        } else if (field instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) field;
            checkBox.setSelected(Boolean.parseBoolean(value));

        } else {
            // Egyéb esetek kezelése
            // ...
        }
    }

    public void setPlaceholder(String placeholder) {
        TextInputControl control = (TextInputControl) field;
        control.setPromptText(placeholder);
    }

    protected Control createField() {
        //return new TextField();
        TextField textField = new TextField();
        String textFieldStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';" +
                               "-fx-background-color: #b8c4cf; -fx-cursor: hand;";
        textField.setStyle(textFieldStyle);
        
        return textField;
    }
}

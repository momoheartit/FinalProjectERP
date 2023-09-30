package com.finalProject.ERP.View.GUI;

import java.time.LocalDate;
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
    }

    public Label getLabel() {
        return label;
    }

    public Control getField() {
        return field;
    }

    public String getValue() {
        if (field instanceof TextInputControl) {
            return ((TextInputControl) field).getText();
        } else if (field instanceof DatePicker) {
            LocalDate date = ((DatePicker) field).getValue();
            return (date != null) ? date.toString() : null;
        } else if (field instanceof CheckBox) {
            return String.valueOf(((CheckBox) field).isSelected());
        } else if (field instanceof ComboBox<?>) {
            Object selectedItem = ((ComboBox<?>) field).getSelectionModel().getSelectedItem();
            return (selectedItem != null) ? selectedItem.toString() : null;
        }

        // Egyéb esetek kezelése
        // ...

        return null;
    }

    public void setValue(String value) {
        TextInputControl control = (TextInputControl) field;
        control.setText(value);
    }

    public void setPlaceholder(String placeholder) {
        TextInputControl control = (TextInputControl) field;
        control.setPromptText(placeholder);
    }

    protected Control createField() {
        return new TextField();
    }
}
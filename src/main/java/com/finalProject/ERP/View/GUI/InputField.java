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

        String labelStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';";
        label.setStyle(labelStyle);
    }
    
    public InputField(String text, FieldType fieldType) {
        label = new Label(text);

        switch (fieldType) {
            case NUMBER:
                field = createNumberField();
                break;
            case LETTER:
                field = createLetterField();
                break;
            default:
                field = createField(); // Alapértelmezett eset
        }

        String labelStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';";
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
            if (value != null && !value.isEmpty()) {
                datePicker.setValue(LocalDate.parse(value));
            } else {
                datePicker.setValue(null);
            }
        } else if (field instanceof CheckBox) {
            CheckBox checkBox = (CheckBox) field;
            checkBox.setSelected(Boolean.parseBoolean(value));

        } else {
            //ezt meghagyom tarcsiba
        }
    }

    public void setPlaceholder(String placeholder) {
        TextInputControl control = (TextInputControl) field;
        control.setPromptText(placeholder);
    }

    protected Control createField() {
        TextField textField = new TextField();
        String textFieldStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';"
                + "-fx-background-color: #b8c4cf; -fx-cursor: hand; -fx-border-color: #0d1321;";
        textField.setStyle(textFieldStyle);

        return textField;
    }

    protected Control createLetterField() {
        TextField textField = new TextField();
        String textFieldStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';"
                + "-fx-background-color: #b8c4cf; -fx-cursor: hand; -fx-border-color: #0d1321;";
        textField.setStyle(textFieldStyle);

        // Csak betűk engedélyezése
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("^[a-zA-Z.@]*$")) {
                textField.setText(oldValue);
            }
        });

        return textField;
    }

    protected Control createNumberField() {
        TextField textField = new TextField();
        String textFieldStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';"
                + "-fx-background-color: #b8c4cf; -fx-cursor: hand; -fx-border-color: #0d1321;";
        textField.setStyle(textFieldStyle);

        // Csak számok engedélyezése
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        return textField;
    }

    public enum FieldType {
        NUMBER,
        LETTER
    }

}

package com.finalProject.ERP.View.GUI;

import java.util.HashMap;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class InputForm extends GridPane {

    protected HashMap<String, InputField> fields;

    public InputForm(Pane parent) {
        fields = new HashMap<>();

        parent.getChildren().add(this);
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(20, 20, 20, 20));
    }

    public HashMap<String, String> getFilledValues() {
        HashMap<String, String> filledValues = new HashMap<>();

        fields.forEach((key, field) -> {
            String value = field.getValue();
            filledValues.put(key, value);
        });

        return filledValues;
    }

    public void setComboBoxOptions(String key, String[] options) {
        InputComboBox comboBox = (InputComboBox) fields.get(key);
        if (comboBox != null) {
            comboBox.setOptions(options);
        }
    }

    public void add(String key, InputField field, int row, int col) {
        fields.put(key, field);


        if (field instanceof InputComboBox) {
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row);
        } else if (field instanceof InputDatePicker) {
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row);
        } else if (field instanceof InputCheckBox) {
            add(field.getLabel(), col, row, 1, 1);
            add(field.getField(), col + 1, row);
        } else {
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row, 1, 1);
        }
    }

    public void button(String buttonText, Consumer<InputForm> onClick, int row, int col) {
        Button button = new Button(buttonText);

        String buttonFontStyle
                = "-fx-background-color: #3e5c76; -fx-text-fill: #0d1321; -fx-border-width: 2; "
                + "-fx-font-family: 'Britannic Bold'; -fx-font-size: 18; -fx-cursor: hand;";
        button.setStyle(buttonFontStyle);

        add(button, col, row, 2, 1);

        button.setOnAction(evt -> {
            onClick.accept(this);
        });
    }

    public String getValue(String key) {
        if (fields.containsKey(key)) {
            return fields.get(key).getValue();
        }
        return null;
    }

    public void setValue(String key, String value) {
        if (fields.containsKey(key)) {
            fields.get(key).setValue(value);
        }
    }

    public void clear() {
        fields.forEach((key, field) -> {
            field.setValue("");
        });
    }
}

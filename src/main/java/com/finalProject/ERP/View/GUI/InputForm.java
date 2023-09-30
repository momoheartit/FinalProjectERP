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
        setPadding(new Insets(10, 10, 10, 10));
    }
    
        public HashMap<String, String> getFilledValues() {
        HashMap<String, String> filledValues = new HashMap<>();

        fields.forEach((key, field) -> {
            String value = field.getValue();
            filledValues.put(key, value);
        });

        return filledValues;
    }

    public void add(String key, InputField field, int row, int col) {
        fields.put(key, field);

        // Az InputField típusának ellenőrzése
        if (field instanceof InputComboBox) {
            // Ha ComboBox, akkor hozzáadás két oszlopban
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row);
        } else if (field instanceof InputDatePicker) {
            // Ha DatePicker, akkor hozzáadás két oszlopban
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row);
        } else if (field instanceof InputCheckBox) {
            // Ha CheckBox, akkor hozzáadás egy oszlopban, a Label középen
            add(field.getLabel(), col, row, 1, 1);
            add(field.getField(), col + 1, row);
        } else {
            // Alapértelmezett eset: Label és mező egy oszlopban
            add(field.getLabel(), col, row);
            add(field.getField(), col + 1, row, 1, 1);
        }
    }

    public void button(String buttonText, Consumer<InputForm> onClick, int row, int col) {
        Button button = new Button(buttonText);
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

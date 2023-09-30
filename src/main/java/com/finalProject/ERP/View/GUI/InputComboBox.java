package com.finalProject.ERP.View.GUI;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;

public class InputComboBox extends InputField {

    public InputComboBox(String text, String[] options) {
        super(text);
        setOptions(options);
    }

    public void setOptions(String[] options) {
        ComboBox<String> box = (ComboBox<String>) field;
        var items = box.getItems();

        for (String option : options) {
            items.add(option);
        }
        setValue("0");
    }

    @Override
    public void setValue(String value) {
        ComboBox<String> box = (ComboBox<String>) field;
        int index = value.equals("") ? 0 : Integer.parseInt(value);
        box.getSelectionModel().select(index);
    }

    @Override
    public String getValue() {
        ComboBox<String> box = (ComboBox<String>) field;
        int index = box.getSelectionModel().getSelectedIndex();
        return Integer.toString(index);
    }

    @Override
    protected Control createField() {
        return new ComboBox<>();
    }
}
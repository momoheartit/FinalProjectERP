package com.finalProject.ERP.View.GUI;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;

public class InputComboBox extends InputField {

    public InputComboBox(String text, String[] options) {
        super(text);
        setOptions(options);
    }

    public InputComboBox(String text) {
        super(text);
        setOptions(new String[]{"=", "!=", ">", "<"}); // Alapértelmezett értékek
    }

    public void setOptions(String[] options) {
        ComboBox<String> box = (ComboBox<String>) field;
        var items = box.getItems();

        items.clear();

        for (String option : options) {
            items.add(option);
        }

        // Ha van legalább egy opció, akkor állítsd be az alapértelmezett értéket
        if (!items.isEmpty()) {
            setValue(items.get(0));
        }
    }

    @Override
    public void setValue(String value) {
        ComboBox box = (ComboBox) field;
        box.getSelectionModel().select(value);
    }

    @Override
    public String getValue() {
        ComboBox box = (ComboBox) field;
        return (String) box.getSelectionModel().getSelectedItem();
    }

    @Override
    protected Control createField() {
       // return new ComboBox();
        ComboBox<String> comboBox = new ComboBox<>();

        // ComboBox háttérszíne
        String comboBoxStyle = "-fx-background-color: #b8c4cf; -fx-cursor: hand; -fx-font-size: 16px; -fx-font-family: 'Britannic Bold';";
        comboBox.setStyle(comboBoxStyle);

//        // ComboBox betűtípusa és mérete
//        String comboBoxFontStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';";
//        comboBox.getEditor().setStyle(comboBoxFontStyle);

        return comboBox;
    }
    
}

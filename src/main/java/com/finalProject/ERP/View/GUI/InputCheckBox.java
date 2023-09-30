package com.finalProject.ERP.View.GUI;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;

public class InputCheckBox extends InputField {

    public InputCheckBox(String text) {
        super(text);
    }

    public boolean isChecked() {
        CheckBox checkBox = (CheckBox) getField();
        return checkBox.isSelected();
    }

    public void setChecked(boolean checked) {
        CheckBox checkBox = (CheckBox) getField();
        checkBox.setSelected(checked);
    }

    @Override
    protected Control createField() {
        return new CheckBox();
    }
}

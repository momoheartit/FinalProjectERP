
package com.finalProject.ERP.View.GUI;
import java.time.LocalDate;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;

public class InputDatePicker extends InputField {
    
    public InputDatePicker(String text) {
        super(text);
    }
    public void setValue(LocalDate value) { //(LocalDateTime value) {
        DatePicker datePicker = (DatePicker) getField();
        datePicker.setValue(value);//.toLocalDate()); - még a value zárójelében!
    }

    
    //@Override
    public LocalDate getPickedValue() {//Ez LocalDateTime volt!!!!
        DatePicker datePicker = (DatePicker) getField();
        LocalDate localDate = datePicker.getValue();
        if (localDate == null) {
            return null;
        }
        return localDate;
    }

    @Override
    protected Control createField() {
                DatePicker datePicker = new DatePicker();

        String datePickerStyle = "-fx-font-size: 16px; -fx-font-family: 'Britannic Bold';" +
                               "-fx-background-color: #b8c4cf; -fx-cursor: hand; -fx-border-color: #0d1321;";
        datePicker.getEditor().setStyle(datePickerStyle);

        datePicker.getEditor().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                datePicker.getEditor().getStyleClass().add("date-picker-inner-focused");
            } else {
                datePicker.getEditor().getStyleClass().remove("date-picker-inner-focused");
            }
        });
        datePicker.setEditable(false);


        return datePicker;
    }
    
}

package com.finalProject.ERP.View.GUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;

public class InputDatePicker extends InputField {
    
    public InputDatePicker(String text) {
        super(text);
    }
    
//    //@Override
//    public void setPickedValue(LocalDateTime value) {
//        DatePicker datePicker = (DatePicker) getField();
//        datePicker.setValue(value.toLocalDate());
//    }
    
        //@Override
    public void setValue(LocalDateTime value) {
        DatePicker datePicker = (DatePicker) getField();
        datePicker.setValue(value.toLocalDate());
    }
    
    //@Override
    public LocalDateTime getPickedValue() {
        DatePicker datePicker = (DatePicker) getField();
        LocalDate localDate = datePicker.getValue();
        if (localDate == null) {
            return null;
        }
        return localDate.atStartOfDay();
    }

    @Override
    protected Control createField() {
        return new DatePicker();
    }
    
}
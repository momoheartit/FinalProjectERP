
package com.finalProject.ERP.View.GUI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;

public class InputDatePicker extends InputField {
    
    public InputDatePicker(String text) {
        super(text);
    }
    //ez kb biztos, hogy nem kell
//    //@Override
//    public void setPickedValue(LocalDateTime value) {
//        DatePicker datePicker = (DatePicker) getField();
//        datePicker.setValue(value.toLocalDate());
//    }
    
    
    //ez az eredeti, de próbáljuk meg lekezelni a nem hibás exceptiont.
        //@Override
    public void setValue(LocalDateTime value) {
        DatePicker datePicker = (DatePicker) getField();
        datePicker.setValue(value.toLocalDate());
    }


//    
//      //  @Override
//    public void getValue(LocalDateTime value) {
//        DatePicker datePicker = (DatePicker) field;
//        LocalDate date = datePicker.getValue();
//        //return (date != null) ? date.atStartOfDay() : null;  // LocalDateTime-ba konvertálva
//    }
    
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
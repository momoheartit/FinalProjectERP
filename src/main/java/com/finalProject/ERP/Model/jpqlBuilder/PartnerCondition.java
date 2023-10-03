package com.finalProject.ERP.Model.jpqlBuilder;

import com.finalProject.ERP.View.GUI.InputForm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PartnerCondition {
    private String name;
    private String comboBoxValue;
    private String value;
    private boolean isDate;

    public PartnerCondition(String name, String comboBoxValue, String value, boolean isDate) {
        this.name = name;
        this.comboBoxValue = comboBoxValue;
        this.value = value;
        this.isDate = isDate;

        if (isDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(value, formatter);
        }

    }

    public String getName() {
        return name;
    }

    public String getComboBoxValue() {
        return comboBoxValue;
    }

    public String getValue() {
        return value;
    }

    public boolean isDate() {
        return isDate;
    }

    public boolean isValueEmpty() {
        return value == null || value.trim().isEmpty() || value.trim().equalsIgnoreCase("null");
    }

    public static List<PartnerCondition> createConditionsList(InputForm form) {
        List<PartnerCondition> conditionsList = new ArrayList<>();

        // ID
        conditionsList.add(new PartnerCondition("id", form.getValue("idComboBox"), form.getValue("id"), false));

        // Name
        conditionsList.add(new PartnerCondition("name", form.getValue("nameComboBox"), form.getValue("name"), false));

        // Partner
        conditionsList.add(new PartnerCondition("contact", form.getValue("contactComboBox"), form.getValue("contact"), false));


        List<PartnerCondition> filteredList = new ArrayList<>();

        for (PartnerCondition condition : conditionsList) {
            if (!condition.isValueEmpty()) {
                filteredList.add(condition);
            }
        }
        
        return filteredList;
    }
}

package com.finalProject.ERP.Model.jpqlBuilder;

import com.finalProject.ERP.View.GUI.InputForm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IncomeCondition {

    private String name;
    private String comboBoxValue;
    private String value;
    private boolean isDate;

    public IncomeCondition(String name, String comboBoxValue, String value, boolean isDate) {
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

    public static List<IncomeCondition> createConditionsList(InputForm form) {
        List<IncomeCondition> conditionsList = new ArrayList<>();

        // ID
        conditionsList.add(new IncomeCondition("id", form.getValue("idComboBox"), form.getValue("id"), false));

        // Partner
        conditionsList.add(new IncomeCondition("partner.id", form.getValue("partnerComboBox"), form.getValue("partner"), false));

        // Amount
        conditionsList.add(new IncomeCondition("amount", form.getValue("amountComboBox"), form.getValue("amount"), false));

        // Project
        conditionsList.add(new IncomeCondition("project", form.getValue("projectComboBox"), form.getValue("project"), false));

        // Created
        String createdValue = form.getValue("created");
        if (createdValue != null) {
            conditionsList.add(new IncomeCondition("created", form.getValue("createdComboBox"), createdValue, true));
        }

        // Approved
        String approvedValue = form.getValue("approved");
        if (approvedValue != null) {
            conditionsList.add(new IncomeCondition("approved", form.getValue("approvedComboBox"), approvedValue, true));
        }
        // Filtered list létrehozása
        List<IncomeCondition> filteredList = new ArrayList<>();

        // Végigmegyünk az összes feltételes objektumon és hozzáadjuk a filteredList-hez, kivéve, ha a harmadik érték üres vagy null
        for (IncomeCondition condition : conditionsList) {
            if (!condition.isValueEmpty()) {
                filteredList.add(condition);
            }
        }
        
        System.out.println("filteredList tartalma: " + filteredList);
        return filteredList;
        
    }
}

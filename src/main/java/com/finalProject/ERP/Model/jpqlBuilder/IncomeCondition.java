package com.finalProject.ERP.Model.jpqlBuilder;

import com.finalProject.ERP.View.GUI.InputForm;
import java.util.ArrayList;
import java.util.List;

public class IncomeCondition {

    private String name;
    private String comboBoxValue;
    private String value;

    public IncomeCondition(String name, String comboBoxValue, String value) {
        this.name = name;
        this.comboBoxValue = comboBoxValue;
        this.value = value;// != null && !value.trim().isEmpty() ? value : "üres";
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

    public boolean isValueEmpty() {
        return value == null || value.trim().isEmpty() || value.trim().equalsIgnoreCase("null");
    }

    public static List<IncomeCondition> createConditionsList(InputForm form) {
        List<IncomeCondition> conditionsList = new ArrayList<>();

        // ID
        conditionsList.add(new IncomeCondition("id", form.getValue("idComboBox"), form.getValue("id")));

        // Partner
        conditionsList.add(new IncomeCondition("partner.id", form.getValue("partnerComboBox"), form.getValue("partner")));

        // Amount
        conditionsList.add(new IncomeCondition("amount", form.getValue("amountComboBox"), form.getValue("amount")));
        
        // Amount
        conditionsList.add(new IncomeCondition("project", form.getValue("projectComboBox"), form.getValue("project")));

        // Created
        conditionsList.add(new IncomeCondition("created", form.getValue("createdComboBox"), form.getValue("created")));

        // Approved
        conditionsList.add(new IncomeCondition("approved", form.getValue("approvedComboBox"), form.getValue("approved")));

        // Filtered list létrehozása
        List<IncomeCondition> filteredList = new ArrayList<>();

        // Végigmegyünk az összes feltételes objektumon és hozzáadjuk a filteredList-hez, kivéve, ha a harmadik érték üres vagy null
        for (IncomeCondition condition : conditionsList) {
            if (!condition.isValueEmpty()) {
                filteredList.add(condition);
            }
        }

        return filteredList;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.finalProject.ERP.Model.jpqlBuilder;

import com.finalProject.ERP.View.GUI.InputForm;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author forAndroid
 */
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


        // Filtered list létrehozása
        List<PartnerCondition> filteredList = new ArrayList<>();

        // Végigmegyünk az összes feltételes objektumon és hozzáadjuk a filteredList-hez, kivéve, ha a harmadik érték üres vagy null
        for (PartnerCondition condition : conditionsList) {
            if (!condition.isValueEmpty()) {
                filteredList.add(condition);
            }
        }
        
        System.out.println("filteredList tartalma: " + filteredList);
        return filteredList;
        
    }
}

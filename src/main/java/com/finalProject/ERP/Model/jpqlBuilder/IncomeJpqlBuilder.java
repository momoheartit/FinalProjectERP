package com.finalProject.ERP.Model.jpqlBuilder;

import java.util.List;

public class IncomeJpqlBuilder {

    public String buildQuery(List<IncomeCondition> conditions) {
        StringBuilder jpqlQuery = new StringBuilder("SELECT i FROM IncomeEntity i");

        if (conditions != null && !conditions.isEmpty()) {
            jpqlQuery.append(" WHERE");
            for (int i = 0; i < conditions.size(); i++) {
                IncomeCondition condition = conditions.get(i);

                if (i > 0) {
                    jpqlQuery.append(" AND");
                }

                jpqlQuery.append(" i.").append(condition.getName());

                if (condition.getName().equals("project")) {
                    if ("=".equals(condition.getComboBoxValue())) {
                        jpqlQuery.append(" LIKE CONCAT('%', :").append(condition.getName()).append(",'%')");
                    } else if ("≠".equals(condition.getComboBoxValue())) {
                        jpqlQuery.append(" NOT LIKE CONCAT('%', :").append(condition.getName()).append(",'%')");
                    } else {
                        throw new IllegalArgumentException("Invalid operator: " + condition.getComboBoxValue());
                    }
                } else {
                    switch (condition.getComboBoxValue()) {
                        case "=":
                            jpqlQuery.append(" =");
                            break;
                        case "≠":
                            jpqlQuery.append(" <>");
                            break;
                        case "<":
                            jpqlQuery.append(" <");
                            break;
                        case ">":
                            jpqlQuery.append(" >");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + condition.getComboBoxValue());
                    }
                        jpqlQuery.append(" :").append(condition.getName());
                    
                }
            }
        }
        jpqlQuery.append(" ORDER BY i.created DESC");

        return jpqlQuery.toString();
    }
}

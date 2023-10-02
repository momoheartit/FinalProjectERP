package com.finalProject.ERP.Model.jpqlBuilder;

import java.time.LocalDateTime;
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

                // A project típusú érték esetén mindig LIKE típusú legyen a feltétel
                if (condition.getName().equals("project")) {
                    jpqlQuery.append(" LIKE '%").append(condition.getValue()).append("%'");
                } else {
                    switch (condition.getComboBoxValue()) {
                        case "=":
                            jpqlQuery.append(" =");
                            break;
                        case "!=":
                            jpqlQuery.append(" <>");
                            break;
                        case "<":
                            jpqlQuery.append(" >");
                            break;
                        case ">":
                            jpqlQuery.append(" <");
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid operator: " + condition.getComboBoxValue());
                    }

                    // Ha a feltétel dátum típusú, akkor idézőjelek között kell lennie
                    if (condition.isDate()) {
                        // Ha a feltétel dátum típusú, akkor helyettesítsd a helyét egy paraméterrel
                        jpqlQuery.append(" :").append(condition.getName());
                    } else {
                        jpqlQuery.append(" :").append(condition.getName());
                    }
                }
            }
        }

        return jpqlQuery.toString();
    }
}

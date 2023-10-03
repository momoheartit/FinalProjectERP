package com.finalProject.ERP.Model.jpqlBuilder;

import java.util.List;

public class PartnerJpqlBuilder {
       public String buildQuery(List<PartnerCondition> conditions) {
        StringBuilder jpqlQuery = new StringBuilder("SELECT i FROM PartnerEntity i");

        if (conditions != null && !conditions.isEmpty()) {
            jpqlQuery.append(" WHERE");
            for (int i = 0; i < conditions.size(); i++) {
                PartnerCondition condition = conditions.get(i);

                if (i > 0) {
                    jpqlQuery.append(" AND");
                }

                jpqlQuery.append(" i.").append(condition.getName());

                if (condition.getName().equals("name") || condition.getName().equals("contact")) {
                    jpqlQuery.append(" LIKE '%").append(condition.getValue()).append("%'");
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

                    if (condition.isDate()) {
                        jpqlQuery.append(" :").append(condition.getName());
                    } else {
                        jpqlQuery.append(" :").append(condition.getName());
                    }
                }
            }
        }
        jpqlQuery.append(" ORDER BY i.id ASC");

        return jpqlQuery.toString();
    }
}

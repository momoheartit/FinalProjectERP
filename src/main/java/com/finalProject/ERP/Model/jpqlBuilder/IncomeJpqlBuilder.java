package com.finalProject.ERP.Model.jpqlBuilder;

import java.time.LocalDateTime;
import java.util.List;
import static org.springframework.jdbc.core.JdbcOperationsExtensionsKt.query;

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
                    jpqlQuery.append(" LIKE CONCAT('%', :").append(condition.getName()).append(",'%')");
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
        jpqlQuery.append(" ORDER BY i.created DESC");

        return jpqlQuery.toString();
    }
}

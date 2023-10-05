package com.finalProject.ERP;

import com.finalProject.ERP.Model.jpqlBuilder.IncomeCondition;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeJpqlBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ErpApplicationTests {

    @Test
    public void testBuildQuery() {
        // Arrange
        IncomeJpqlBuilder jpqlBuilder = new IncomeJpqlBuilder();

        IncomeCondition condition1 = new IncomeCondition("id", "=", "123", false);
        IncomeCondition condition2 = new IncomeCondition("partnerId", ">", "456", false);
        IncomeCondition condition3 = new IncomeCondition("amount", "<", "1000", false);
        IncomeCondition condition4 = new IncomeCondition("project", "=", "ProjectA", false);
        IncomeCondition condition5 = new IncomeCondition("created", ">", "2023-01-01", true);
        IncomeCondition condition6 = new IncomeCondition("approved", "=", "2023-02-01", true);

        List<IncomeCondition> conditions = Arrays.asList(condition1, condition2, condition3, condition4, condition5, condition6);

        // Act
        String jpqlQuery = jpqlBuilder.buildQuery(conditions);

        // Assert
        String expectedJpqlQuery = "SELECT i FROM IncomeEntity i WHERE i.id = :id AND i.partnerId > "
                + ":partnerId AND i.amount < :amount AND i.project LIKE CONCAT('%', :project,'%') "
                + "AND i.created > :created AND i.approved = :approved ORDER BY i.id DESC";

        assertEquals(expectedJpqlQuery, jpqlQuery);
    }

    @Test
    public void testBuildQuery_IdEquals() {
        // Arrange
        IncomeJpqlBuilder jpqlBuilder = new IncomeJpqlBuilder();
        IncomeCondition condition = new IncomeCondition("id", "=", "123", false);

        // Act
        String jpqlQuery = jpqlBuilder.buildQuery(Collections.singletonList(condition));

        // Assert
        String expectedJpqlQuery = "SELECT i FROM IncomeEntity i WHERE i.id = :id ORDER BY i.id DESC";
        assertEquals(expectedJpqlQuery, jpqlQuery);
    }

    @Test
    public void testBuildQuery_IdNotEquals() {
        // Arrange
        IncomeJpqlBuilder jpqlBuilder = new IncomeJpqlBuilder();
        IncomeCondition condition = new IncomeCondition("id", "≠", "456", false);

        // Act
        String jpqlQuery = jpqlBuilder.buildQuery(Collections.singletonList(condition));

        // Assert
        String expectedJpqlQuery = "SELECT i FROM IncomeEntity i WHERE i.id <> :id ORDER BY i.id DESC";
        assertEquals(expectedJpqlQuery, jpqlQuery);
    }

    @Test
    public void testBuildQuery_CreatedAfter() {
        // Arrange
        IncomeJpqlBuilder jpqlBuilder = new IncomeJpqlBuilder();
        IncomeCondition condition = new IncomeCondition("created", ">", "2023-01-01", true);

        // Act
        String jpqlQuery = jpqlBuilder.buildQuery(Collections.singletonList(condition));

        // Assert
        String expectedJpqlQuery = "SELECT i FROM IncomeEntity i WHERE i.created > :created ORDER BY i.id DESC";
        assertEquals(expectedJpqlQuery, jpqlQuery);
    }

    @Test
    public void testBuildQuery_ApprovedBefore() {
        // Arrange
        IncomeJpqlBuilder jpqlBuilder = new IncomeJpqlBuilder();
        IncomeCondition condition = new IncomeCondition("approved", "<", "2023-02-01", true);

        // Act
        String jpqlQuery = jpqlBuilder.buildQuery(Collections.singletonList(condition));

        // Assert
        String expectedJpqlQuery = "SELECT i FROM IncomeEntity i WHERE i.approved < :approved ORDER BY i.id DESC";
        assertEquals(expectedJpqlQuery, jpqlQuery);
    }

}

package com.finalProject.ERP.View;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class IncomeFilter extends AnchorPane {
    
    Button searchButton;

    public IncomeFilter() {
        initializeUI();
    }

    private void initializeUI() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // 1. sor
        gridPane.add(new Label("ID"), 0, 0);
        gridPane.add(createNumberTextField(), 1, 0);
        ComboBox<String> idComboBox = createComboBox();
        gridPane.add(idComboBox, 2, 0);

        // 2. sor
        gridPane.add(new Label("Partner"), 0, 1);
        gridPane.add(createNumberTextField(), 1, 1);
        ComboBox<String> partnerComboBox = createComboBox();
        gridPane.add(partnerComboBox, 2, 1);

        // 3. sor
        gridPane.add(new Label("Amount"), 0, 2);
        gridPane.add(createNumberTextField(), 1, 2);
        gridPane.add(createNumberTextField(), 2, 2);
        ComboBox<String> amountComboBox = createComboBox();
        gridPane.add(amountComboBox, 3, 2);

        // 4. sor
        gridPane.add(new Label("Created"), 0, 3);
        gridPane.add(new DatePicker(), 1, 3);
        gridPane.add(new DatePicker(), 2, 3);
        ComboBox<String> createdComboBox = createComboBox();
        gridPane.add(createdComboBox, 3, 3);

        // 5. sor
        gridPane.add(new Label("Approved"), 0, 4);
        CheckBox approvedCheckBox = new CheckBox();
        gridPane.add(approvedCheckBox, 1, 4);

        DatePicker approvedDatePicker1 = new DatePicker();
        DatePicker approvedDatePicker2 = new DatePicker();
        CheckBox approvedCheckBox2 = new CheckBox();

        approvedDatePicker1.setVisible(false);
        approvedDatePicker2.setVisible(false);
        approvedCheckBox2.setVisible(false);

        gridPane.add(approvedDatePicker1, 2, 4);
        gridPane.add(approvedDatePicker2, 3, 4);
        gridPane.add(approvedCheckBox2, 4, 4);

        approvedCheckBox.setOnAction(event -> {
            boolean isSelected = approvedCheckBox.isSelected();
            approvedDatePicker1.setVisible(isSelected);
            approvedDatePicker2.setVisible(isSelected);
            approvedCheckBox2.setVisible(isSelected);
        });
        
        // 6. sor
        searchButton = new Button("Search");
        gridPane.add(searchButton, 1, 5);

        this.getChildren().add(gridPane);
    }

    private TextField createNumberTextField() {
        TextField textField = new TextField();
        return textField;
    }

    private ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("=", "≠", "<", ">");
        comboBox.setValue("=");
        return comboBox;
    }
    
    public Button getSearchButton() {
        return searchButton;
    }
}

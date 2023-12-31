package com.finalProject.ERP.View;

import com.finalProject.ERP.Controller.IncomeController;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.Model.jpqlBuilder.IncomeJpqlBuilder;
import com.finalProject.ERP.View.GUI.InputComboBox;
import com.finalProject.ERP.View.GUI.InputDatePicker;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import javafx.scene.layout.Pane;


public class IncomeFilter extends InputForm {

    private IncomeController controller;

    private IncomeJpqlBuilder incomeJpqlBuilder;

    public IncomeFilter(Pane parent, IncomeController controller, Model model) {
        super(parent);
        this.controller = controller;
        
        add("idComboBox", new InputComboBox("ID:", new String[]{"=", "≠", ">", "<"}), 0, 0);
        add("id", new InputField(null, InputField.FieldType.NUMBER), 0, 1);
        
        // 2. sor
        add("partnerComboBox", new InputComboBox("Partner's ID:", new String[]{"=", "≠", ">", "<"}), 1, 0);
        add("partner", new InputField(null, InputField.FieldType.NUMBER), 1, 1);
        
        // 3. sor
        add("amountComboBox", new InputComboBox("Amount:", new String[]{"=", "≠", ">", "<"}), 2, 0);
        add("amount", new InputField(null, InputField.FieldType.NUMBER), 2, 1);
              
        // 4.sor
        add("projectComboBox", new InputComboBox("Project name:", new String[]{"=", "≠"}), 3, 0);
        add("project", new InputField(null, InputField.FieldType.LETTER), 3, 1);
        
        // 4. sor
        add("createdComboBox", new InputComboBox("Created:", new String[]{"=", "≠", ">", "<"}), 4, 0);
        add("created", new InputDatePicker(null), 4, 1);
        
        // 5. sor
        add("approvedComboBox", new InputComboBox("Approved:", new String[]{"=", "≠", ">", "<"}), 5, 0);
        add("approved", new InputDatePicker(null), 5, 1);

        button("Search", event -> searchButton(), 6, 0);

    }

    public void searchButton() {
        controller.searchButtonClick(this);
    }
}

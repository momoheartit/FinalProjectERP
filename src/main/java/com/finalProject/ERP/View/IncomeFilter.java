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

        add("id", new InputField("ID"), 0, 0);
        add("idComboBox", new InputComboBox(null), 0, 1);

        // 2. sor
        add("partner", new InputField("Partner"), 1, 0);
        add("partnerComboBox", new InputComboBox(null), 1, 1);

        // 3. sor
        add("amount", new InputField("Amount"), 2, 0);
        add("amountComboBox", new InputComboBox(null), 2, 1);
        
        // 4.sor
        add("project", new InputField("Project"), 3, 0);
        add("projectComboBox", new InputComboBox(null), 3, 1);
        
        // 4. sor
        add("created", new InputDatePicker("Created"), 4, 0);
        add("createdComboBox", new InputComboBox(null), 4, 1);

        // 5. sor
        add("approved", new InputDatePicker("Approved"), 5, 0);
        add("approvedComboBox", new InputComboBox(null), 5, 1);

        button("Search", event -> handleSearchButtonClick(), 6, 0);

    }

    public void handleSearchButtonClick() {
        controller.handleSearchButtonClick(this);
    }
}

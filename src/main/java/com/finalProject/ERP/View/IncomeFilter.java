package com.finalProject.ERP.View;

import com.finalProject.ERP.Model.IncomeEntity;
import com.finalProject.ERP.View.GUI.InputCheckBox;
import com.finalProject.ERP.View.GUI.InputComboBox;
import com.finalProject.ERP.View.GUI.InputDatePicker;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class IncomeFilter extends InputForm {
    
    private IncomeEntity instance;

    public IncomeFilter(Pane parent) {
        super(parent);
        initializeUI();
    }

    private void initializeUI() {
        // 1. sor
        add("id", new InputField("ID"), 0, 0);
        add("idComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 0, 1);

        // 2. sor
        add("partner", new InputField("Partner"), 1, 0);
        add("partnerComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 1, 1);

        // 3. sor
        add("amount1", new InputField("Amount"), 2, 0);
        add("amount2", new InputField(null), 2, 1);
        add("amountComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 2, 2);

        // 4. sor
        add("created1", new InputDatePicker("Created"), 3, 0);
        add("created2", new InputDatePicker(null), 3, 1);
        add("createdComboBox", new InputComboBox(null, new String[]{"=", "≠", "<", ">"}), 3, 2);

        // 5. sor
        add("approvedCheckBox", new InputCheckBox("Approved"), 4, 0);
        add("approvedDatePicker1", new InputDatePicker(null), 4, 1);
        add("approvedDatePicker2", new InputDatePicker(null), 4, 2);
        add("approvedCheckBox2", new InputCheckBox(null), 4, 3);

        // 6. sor
        button("Search", form -> {
            System.out.println("múködik");
            /*String id = form.getValue("id");
            String idComparator = form.getValue("idComboBox");

            String partner = form.getValue("partner");
            String partnerComparator = form.getValue("partnerComboBox");

            String amount1 = form.getValue("amount1");
            String amount2 = form.getValue("amount2");
            String amountComparator = form.getValue("amountComboBox");

            String created1 = form.getValue("created1");
            String created2 = form.getValue("created2");
            String createdComparator = form.getValue("createdComboBox");

            boolean isApproved = form.getValue("approvedCheckBox").equals("true");
            String approved1 = form.getValue("approvedDatePicker1");
            String approved2 = form.getValue("approvedDatePicker2");
            boolean isApproved2 = form.getValue("approvedCheckBox2").equals("true");*/
        }, 5, 0);
    }
    
       
    
}

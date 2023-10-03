package com.finalProject.ERP.View;

import com.finalProject.ERP.Controller.PartnerController;
import com.finalProject.ERP.Model.Model;
import com.finalProject.ERP.Model.jpqlBuilder.PartnerJpqlBuilder;
import com.finalProject.ERP.View.GUI.InputComboBox;
import com.finalProject.ERP.View.GUI.InputField;
import com.finalProject.ERP.View.GUI.InputForm;
import javafx.scene.layout.Pane;

public class PartnerFilter extends InputForm {

    private PartnerController controller;

    private PartnerJpqlBuilder partnerJpqlBuilder;

    public PartnerFilter(Pane parent, PartnerController controller, Model model) {
        super(parent);
        this.controller = controller;

        add("id", new InputField("ID"), 0, 0);
        add("idComboBox", new InputComboBox(null), 0, 1);

        add("name", new InputField("Name"), 1, 0);
        add("nameComboBox", new InputComboBox(null), 1, 1);

        add("contact", new InputField("Contact"), 2, 0);
        add("contactComboBox", new InputComboBox(null), 2, 1);

        button("Search", event -> searchButton(), 3, 0);

    }

    public void searchButton() {
        controller.searchButtonClick(this);
    }
}

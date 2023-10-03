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
        add("idComboBox", new InputComboBox("ID:", new String[]{"=", "≠", ">", "<"}), 0, 0);
        add("id", new InputField(null), 0, 1);

        add("nameComboBox", new InputComboBox("Name contains:", new String[]{"=", "≠"}), 1, 0);
        add("name", new InputField(null), 1, 1);

        add("contactComboBox", new InputComboBox("Contact contains:", new String[]{"=", "≠"}), 2, 0);
        add("contact", new InputField(null), 2, 1);

        button("Search", event -> searchButton(), 3, 0);

    }

    public void searchButton() {
        controller.searchButtonClick(this);
    }
}
